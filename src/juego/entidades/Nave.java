package juego.entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import juego.Assets;
import juego.Config;
import motor.entidades.Sprite;
import motor.entidades.SpriteMovible;
import motor.entidades.interfaces.IColisionable;
import motor.input.InputKeyboard;
import motor.input.InputMouse;
import motor.util.Vector2D;

/**
 * @author Dilan Rojas
 * @date Nov 18, 2025
 * @version 1.0
 * @description description
 */

public class Nave extends SpriteMovible {
	// Configuraciones de la nave
	private static final double FACTOR_VELOCIDAD = 600 * Config.VELOCIDAD;
	private static final double VELOCIDAD_MAX = 300 * Config.VELOCIDAD; // píxeles/segundo
	private static final double FACTOR_ACELERACION = 1; // multiplicador de empuje
	private static final double VELOCIDAD_BALA = 600 * Config.VELOCIDAD; // píxeles/segundo
	private static final int OFFSET_ANGULAR = 90; // Ajuste de la imagen
	private Controles controles;
	private int direccionActual;
	private Sprite fireDerecho, fireIzquierdo;
	private boolean isAcelerando;	
	private int vidas;
	private Random random = new Random();
	
	// === PARPADEO DE REAPARICIÓN ===
	private boolean parpadeando = false;
	private double tiempoParpadeo = 0;
	private double tiempoTotalParpadeo = 0;
	private double intervaloParpadeo = 0.2;

	public Nave(BufferedImage textura, Vector2D posicion, Controles controles) {
		super(textura, posicion);
		
		this.vidas = Config.VIDAS;

		fireDerecho = new Sprite(Assets.textura_fire, posicion);
		fireIzquierdo = new Sprite(Assets.textura_fire, posicion);

		direccionActual = -OFFSET_ANGULAR;
		setOffset_angular(OFFSET_ANGULAR);

		// Configurar física y movimiento
		getPhysics().setAceleracion(FACTOR_VELOCIDAD);
		getPhysics().setFriccion(0.88);

		escalarloA(0.7); // HACEMOS LA NAVE MAS PEQUENA

		this.controles = controles;

		fireDerecho = new Sprite(Assets.textura_fire,Vector2D.ZERO);
		fireDerecho.setOffset_angular(OFFSET_ANGULAR);
		
		fireIzquierdo = new Sprite(Assets.textura_fire,Vector2D.ZERO);
		fireIzquierdo.setOffset_angular(OFFSET_ANGULAR);
		
		isAcelerando = false;
	}
	
	@Override
	public void actualizar() {
	    double dt = motor.GameLoop.deltaTimeSeconds;

	    // === PARPADEO DE REAPARICIÓN ===
	    if (parpadeando) {

	        tiempoParpadeo += dt;

	        // alternar visible/invisible cada intervalo
	        if ((int)(tiempoParpadeo / intervaloParpadeo) % 2 == 0) {
	            setVisible(false);
	        } else {
	            setVisible(true);
	        }

	        // Terminar parpadeo
	        if (tiempoParpadeo >= tiempoTotalParpadeo) {
	            parpadeando = false;
	            setVisible(true);
	        }

	        // No permitir movimientos mientras parpadea
	        detener();
	        isAcelerando = false;
	        super.actualizar();
	        return;
	    }

		// === CONTROL DE ACELERACIÓN / FRICCIÓN ===
		if (InputKeyboard.isDown(controles.acelerar)) {
			acelerar();
		} else {
			frenar();
		}
		
		Vector2D mousePos = InputMouse.getPosicion();
		Vector2D centroNave = getPosicion().add(getCentro());
		Vector2D direccionMouse = mousePos.subtract(centroNave);
		direccionActual = (int) Math.toDegrees(Math.atan2(direccionMouse.getY(), direccionMouse.getX()));
		rotarloA(direccionActual);
		
		// === LIMITAR VELOCIDAD ===
		getMovement().limitarVelocidad(0, VELOCIDAD_MAX);

		// === MOVER ===
		getMovement().mover(transform, FACTOR_ACELERACION);
		
	    // === EVITAR QUE LA NAVE SE SALGA DE LA PANTALLA ===
	    limites();
		
		super.actualizar();

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
	
	@Override
	public void destruir() {
		super.destruir();
	}
	
	@Override
	public void alColisionarCon(IColisionable otro) {
		if (vidas > 0) {
			return;
		} else {
			super.destruir();
		}
	}
	
	// Comenzar parpadeo
	public void iniciarParpadeo(double duracionSeg) {
	    parpadeando = true;
	    tiempoParpadeo = 0;
	    tiempoTotalParpadeo = duracionSeg;
	}
	
	public void limites() {

	    double x = getPosicion().getX();
	    double y = getPosicion().getY();

	    double ancho = getWidth();
	    double alto = getHeight();

	    Vector2D vel = getMovement().getVelocidadVectorial();

	    boolean tocandoBorde = false;

	    // Borde Izquierdo
	    if (x < 0) {
	        x = 0;
	        vel = new Vector2D(Math.max(0, vel.getX()), vel.getY());
	        tocandoBorde = true;
	    }
	    // Border derecho
	    else if (x + ancho > Config.WIDTH) {
	        x = Config.WIDTH - ancho;
	        vel = new Vector2D(Math.min(0, vel.getX()), vel.getY());
	        tocandoBorde = true;
	    }

	    // Border superior
	    if (y < 0) {
	        y = 0;
	        vel = new Vector2D(vel.getX(), Math.max(0, vel.getY()));
	        tocandoBorde = true;
	    }
	    
	    // Borde inferior
	    else if (y + alto > Config.HEIGHT) {
	        y = Config.HEIGHT - alto;
	        vel = new Vector2D(vel.getX(), Math.min(0, vel.getY()));
	        tocandoBorde = true;
	    }

	    if (tocandoBorde) {
	        getMovement().setVelocidadVectorial(vel);;
	    }

	    posicionarloA(new Vector2D(x, y));
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

	public int getDireccionActual() {
		return direccionActual;
	}
	
	public int getVidas() {
		return vidas;
	}
	
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	/** Cuando recibe un impacto */
	public void recibirImpacto() {
		System.out.println("La nave ha sido destruida!");
	}

	/**
	 * Dispara una bala desde la punta de la nave si la nave
	 */
	public Bala disparoAutomatico() {
		Vector2D posicionBala = dispararBala();
		
		return new Bala(dispararBala(), VELOCIDAD_BALA, direccionActual);
	}

	public boolean quiereDisparar() {
		return InputMouse.isPressed() || InputKeyboard.isDown(controles.disparar);
	}
	
	public boolean quiereDispararManual() {
		return InputMouse.isClicked() || InputKeyboard.isKeyPressed(controles.disparar);
	}
	
	public Bala[] disparoEscopeta(int direccionBase) {
	    int angulo1 = 3 + random.nextInt(7);
	    int angulo2 = 3 + random.nextInt(7);

	    return new Bala[] {
	        new Bala(dispararBala(), VELOCIDAD_BALA, direccionBase),
	        new Bala(dispararBala(), VELOCIDAD_BALA, direccionBase + angulo1),
	        new Bala(dispararBala(), VELOCIDAD_BALA, direccionBase - angulo2)
	    };
	}

	public Bala disparoManual() {
		// Posición de la bala desde el centro
		Vector2D posicionBala = dispararBala();

		// Crear la bala
		return new Bala(posicionBala, VELOCIDAD_BALA, direccionActual);
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

	private Vector2D dispararBala() {
		Vector2D centroNave = getPosicion().add(getCentro());
		
		Vector2D direccion = Vector2D.getVectorDelAngulo(direccionActual);  // hacia adelante
	    Vector2D haciaAtras = direccion.scale(-1);                          // hacia atrás
	    //Vector2D lateral = new Vector2D(direccion.getY(), -direccion.getX()); // perpendicular (derecha)
	    
	    double distanciaDetras = -22; 
	    
	    Vector2D offsetDerecha = haciaAtras.scale(distanciaDetras).add(new Vector2D(0,-13.5));//lateral.scale(+0));
	    
	    Vector2D centroFireMundial = centroNave.add(offsetDerecha);
	    
	    return centroFireMundial;
	}
}
