package CRUD.modelo;

/**
 * @author Dilan Rojas
 * @date Nov 18, 2025
 * @version 1.0
 * @description description
 */

public class Configuraciones {
	// Atributos
	private int velocidad;
	private int arma;
	private int dificultad;
	private int vidas;
	
	// Constructores
	public Configuraciones() {
		this(1, 1, 1, 3);
	}

	public Configuraciones(int velocidad, int arma, int dificultad, int vidas) {
		this.velocidad = velocidad;
		this.arma = arma;
		this.dificultad = dificultad;
		this.vidas = vidas;
	}

	// Getters & Setters
	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getArma() {
		return arma;
	}

	public void setArma(int arma) {
		this.arma = arma;
	}

	public int getDificultad() {
		return dificultad;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	// toString
	@Override
	public String toString() {
		return "Configuraciones [velocidad=" + velocidad + ", arma=" + arma + ", dificultad=" + dificultad + "]";
	}
}
