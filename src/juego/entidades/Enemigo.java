package juego.entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import motor.entidades.SpriteMovible;
import motor.entidades.interfaces.IColisionable;
import motor.util.Vector2D;

/**
 * @author AnaGonzalezC5F593
 * @date 21 nov 2025
 * @version 1.0
 * @description Plantilla para enemigos
 */

public abstract class Enemigo extends SpriteMovible {

	// Attributes
	private Vector2D direccionActual;
	private Nave target;

	private double velocidad;
	private double aceleracion;
	private double desviacion;

	public Enemigo(
		BufferedImage textura,
		Vector2D posicion,
		Nave target,
		double velocidad,
		double aceleracion,
		double desviacion
	) {
		super(textura, posicion);

		this.target = target;
		this.velocidad = velocidad;
		this.aceleracion = aceleracion;
		this.desviacion = desviacion;

		direccionActual = Vector2D.ZERO;
	}

	@Override
	public void actualizar() {
    perseguir(target.getPosicion());
		super.actualizar();
	}

	@Override
	public void dibujar(Graphics g) {
		super.dibujar(g);
	}

  public void perseguir(Vector2D posicionJugador) {
		Vector2D objetivo = posicionJugador.subtract(transform.getPosicion()).normalize();

		// Configuraciones de movimiento
		getPhysics().setAceleracion(this.aceleracion);
		getMovement().setVelocidad(this.velocidad);
		double maxDesviacion = Math.toRadians(this.desviacion);

		// Suavizado
		double factorSuavizado = 0.15; // default: 0.15
		direccionActual = direccionActual.lerp(objetivo, factorSuavizado).normalize();

		// Pequeño ruido angular
		double ruido = (Math.random() * 2 - 1) * maxDesviacion;
		double cos = Math.cos(ruido);
		double sin = Math.sin(ruido);
		double x = direccionActual.getX() * cos - direccionActual.getY() * sin;
		double y = direccionActual.getX() * sin + direccionActual.getY() * cos;

		direccionActual = new Vector2D(x, y).normalize();

		movement.setDireccion(direccionActual);
		physics.acelerar(movement);
		movement.mover(transform);
  }
	@Override
	public void alColisionarCon(IColisionable otro) {
		super.destruir();
	}

  // Getters & Setters
	public double getVelocidad() {
		return velocidad;
	}

  public void setVelocidad(double velocidad) {
    this.velocidad = velocidad;
  }

	public double getAceleracion() {
		return aceleracion;
	}

  public void setAceleracion(double aceleracion) {
    this.aceleracion = aceleracion;
  }

	public double getDesviacion() {
		return desviacion;
	}

  public void setDesviacion(double desviacion) {
    this.desviacion = desviacion;
  }

	public Nave getTarget() {
		return target;
	}
}

