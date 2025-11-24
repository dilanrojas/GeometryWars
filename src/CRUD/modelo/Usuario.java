package CRUD.modelo;

/**
 * @author Dilan Rojas
 * @date Nov 5, 2025
 * @version 1.0
 * @description description
 */


// TODO --> Agregar atributo nickname y actualizar las clases
// que lo requieren. Además, agregar valicaciones (No espacios, ñ, tíldes ni símbolos)

public class Usuario {
	// Atributos
	private String nombre;
	private String nickname;
	private String contrasena;
	private int nivel;
	private int puntaje;
	private int ID;
	private Configuraciones configuraciones;
	private int tiempoJugado;

	private int[] nivelesJugados = new int[10];
	private int contadorNiveles = 0;

	// Constructor
	public Usuario(String nombre, String nickname, String contrasena, int nivel, int puntaje, int id,
			Configuraciones configuraciones) {
		this.nombre = nombre;
		this.nickname = nickname;
		this.contrasena = contrasena;
		this.nivel = nivel;
		this.puntaje = puntaje;
		this.ID = id;
		this.configuraciones = configuraciones;
	}

	// Getters & Setters
	public String getNombre() {
		return nombre;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public Configuraciones getConfiguraciones() {
		return configuraciones;
	}

	public void setConfiguraciones(Configuraciones configuraciones) {
		this.configuraciones = configuraciones;
	}
	public void agregarTiempoJugado(int segundos) {
		tiempoJugado += segundos;
	}

	public int getTiempoJugado() {
		return tiempoJugado;
	}

	public void setTiempoJugado(int tiempoJugado) {
		this.tiempoJugado = tiempoJugado;
	}

	// historial de niveles
	public void agregarNivelJugado(int nivel) {
		if (contadorNiveles < nivelesJugados.length) {
			nivelesJugados[contadorNiveles++] = nivel;
		}
	}

	public String getNivelesJugados() {
		if (contadorNiveles == 0)
			return "Ninguno";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < contadorNiveles; i++) {
			sb.append(nivelesJugados[i]);
			if (i < contadorNiveles - 1)
				sb.append(", ");
		}
		return sb.toString();
	}

	public String estadisticas() {
		return "Nombre: " + nombre +
				"\nNickname: " + nickname +
				"\nPuntaje máximo: " + puntaje + 
				"\nNivel actual: "+ nivel + 
				"\nNiveles jugados: " + getNivelesJugados() + 
				"\nTiempo jugado: " + tiempoJugado + " segundos";
	}

	@Override
	public String toString() {
		return nickname;
	}
}
