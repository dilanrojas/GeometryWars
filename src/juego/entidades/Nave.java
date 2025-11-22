package juego.entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import juego.Assets;
import motor.entidades.Sprite;
import motor.entidades.SpriteMovible;
import motor.entidades.interfaces.IColisionable;
import motor.input.InputKeyboard;
import motor.util.Vector2D;

/**
 * @author Dilan Rojas
 * @date Nov 18, 2025
 * @version 1.0
 * @description description
 */

public class Nave extends SpriteMovible {

	private static final int FACTOR_ROTACION = 5; // grados por frame
	private static final double VELOCIDAD_MAX = 300; // píxeles/segundo
	private static final double FACTOR_ACELERACION = 2; // multiplicador de empuje
	private static final double VELOCIDAD_BALA = 600; // píxeles/segundo
	private static final int OFFSET_ANGULAR = 90; // Ajuste de la imagen

	private int direccionActual;// 0° = derecha

	private Controles controles;

	private Sprite fireDerecho, fireIzquierdo;
	private boolean isAcelerando;

	public Nave(BufferedImage textura, Vector2D posicion, Controles controles) {
		super(textura, posicion);

		fireDerecho = new Sprite(Assets.textura_fire, posicion);
		fireIzquierdo = new Sprite(Assets.textura_fire, posicion);

		direccionActual = -OFFSET_ANGULAR;
		setOffset_angular(OFFSET_ANGULAR);

		// Configurar física y movimiento
		getPhysics().setAceleracion(300); // px/s^2
		getPhysics().setFriccion(0.98); // pierde 2% por frame

		escalarloA(0.7); // HACEMOS LA NAVE MAS PEQUENA

		this.controles = controles;

		fireDerecho = new Sprite(Assets.textura_fire,Vector2D.ZERO);
		fireDerecho.setOffset_angular(OFFSET_ANGULAR);
		
		fireIzquierdo = new Sprite(Assets.textura_fire,Vector2D.ZERO);
		fireIzquierdo.setOffset_angular(OFFSET_ANGULAR);
		
		isAcelerando = false;

	}

	public Sprite getFireDerecho() {
		return fireDerecho;
	}

	public Sprite getFireIzquierdo() {
		return fireIzquierdo;
	}

	public void setFireDerecho(Sprite fireDerecho) {
		this.fireDerecho = fireDerecho;
	}

	public void setFireIzquierdo(Sprite fireIzquierdo) {
		this.fireIzquierdo = fireIzquierdo;
	}

	/** Rota la nave hacia la derecha. */
	public void rotarDerecha() {
		rotarloA(direccionActual+= FACTOR_ROTACION);
	}

	/** Rota la nave hacia la izquierda. */
	public void rotarIzquierda() {
		rotarloA(direccionActual-= FACTOR_ROTACION);
	}

	public void acelerar() {
		getPhysics().acelerar(movement, direccionActual);
		isAcelerando = true;
	}

	public void frenar() {
		getPhysics().aplicarFriccion(getMovement());
		isAcelerando = false;
	}

	public void detener() {
		getMovement().detener();
	}

	public void actualizar() {
		// === CONTROL DE ROTACIÓN ===
		if (InputKeyboard.isDown(controles.giroDerecha)) {
			rotarDerecha();
		}

		if (InputKeyboard.isDown(controles.giroIzquierda)) {
			rotarIzquierda();
		}

		// === CONTROL DE ACELERACIÓN / FRICCIÓN ===
		if (InputKeyboard.isDown(controles.acelerar)) {
			acelerar();
		} else {
			frenar();
		}

		// === LIMITAR VELOCIDAD ===
		getMovement().limitarVelocidad(0, VELOCIDAD_MAX);

		// === MOVER ===
		getMovement().mover(transform, FACTOR_ACELERACION);
		// actualizarFire();
		super.actualizar(); // IMPORTANTE ACTUALIZAMOS TODOS LOS ATRIBUTOS DE LA SUPER CLASE

		encenderPropulsion();
	}

	@Override
	public void dibujar(Graphics g) {
		if (isAcelerando) {
			fireDerecho.dibujar(g);
			fireIzquierdo.dibujar(g);
		}
		
		super.dibujar(g);

	}

	public int getDireccionActual() {
		return direccionActual;
	}

	/** Cuando recibe un impacto */
	public void recibirImpacto() {
		System.out.println("La nave ha sido destruida!");
	}

	/**
	 * Dispara una bala desde la punta de la nave si la nave
	 */
	public Bala disparar() {
		// PENDIENTE CALCULAR LA DISTANCIA A LA PUNTA DE LA NAVE Y NO DESDE EL CENTRO

		// Posición de la bala desde el centro
		Vector2D posicionBala = getPosicion().add(getCentro());

		// Crear la bala
		return new Bala(posicionBala, VELOCIDAD_BALA, direccionActual);
	}

	public boolean quiereDisparar() {
		return InputKeyboard.isKeyPressed(controles.disparar);
	}

	@Override
	public void alColisionarCon(IColisionable otro) {
		super.destruir();
		// super.alColisionarCon(otro);
	}


	private void encenderPropulsion() {
	    Vector2D centroNave = getPosicion().add(getCentro());

	    Vector2D direccion = Vector2D.getVectorDelAngulo(direccionActual);  // hacia adelante
	    Vector2D haciaAtras = direccion.scale(-1);                          // hacia atrás
	    Vector2D lateral = new Vector2D(direccion.getY(), -direccion.getX()); // perpendicular (derecha)

	    double distanciaDetras = 30;     // distancia detrás de la nave

	    Vector2D offsetDerecha = haciaAtras.scale(distanciaDetras).add(lateral.scale(+18));
	    Vector2D offsetIzquierda = haciaAtras.scale(distanciaDetras).add(lateral.scale(-18));

	    Vector2D centroFireMundial = centroNave.add(offsetDerecha);
	    Vector2D centroFireMundial2 = centroNave.add(offsetIzquierda);

	    Vector2D topLeftFire = centroFireMundial.subtract(fireDerecho.getCentro());
	    Vector2D topRightFire = centroFireMundial2.subtract(fireIzquierdo.getCentro());

	    fireDerecho.posicionarloA(topLeftFire);
	    fireDerecho.rotarloA(direccionActual);

	    fireIzquierdo.posicionarloA(topRightFire);
	    fireIzquierdo.rotarloA(direccionActual);
	}
}
