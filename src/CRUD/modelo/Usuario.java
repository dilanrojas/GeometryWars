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

	// Constructor
	public Usuario(
		String nombre,
		String nickname,
		String contrasena,
		int nivel,
		int puntaje,
		int id,
		int tiempoJugado, 
		Configuraciones configuraciones
	) {
		this.nombre = nombre;
		this.nickname = nickname;
		this.contrasena = contrasena;
		this.nivel = nivel;
		this.puntaje = puntaje;
		this.ID = id;
		this.configuraciones = configuraciones;
		this.tiempoJugado = tiempoJugado;
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
	
	public int getTiempoJugado() {
		return tiempoJugado;
	}

	public void setTiempoJugado(int tiempoJugado) {
		this.tiempoJugado = tiempoJugado;
	}

	public String estadisticas() {
		String estadisticaTiempo = "";
		int tiempoEnMinutos = (int) Math.round(tiempoJugado / 60);
		int tiempoEnHoras = (int) Math.round(tiempoJugado / 3600);
		int tiempoEnDias = (int) Math.round(tiempoJugado / 86400);
		
		if (tiempoEnMinutos < 1) {
			estadisticaTiempo = tiempoJugado + " segundos";
			
		} else if (tiempoEnMinutos >= 1 && tiempoEnHoras < 1) {
			estadisticaTiempo = tiempoEnMinutos + " minuto(s)";
			
		} else if (tiempoEnHoras >= 1 && tiempoEnDias < 1) {
			estadisticaTiempo = tiempoEnHoras + " hora(s)";
			
		} else if (tiempoEnDias >= 1) {
			estadisticaTiempo = tiempoEnDias + " día(s)";
		}
		
		return "Nombre: " + nombre +
				"\nNickname: " + nickname +
				"\nPuntaje máximo: " + puntaje + 
				"\nNivel actual: "+ nivel + 
				"\nTiempo jugado: " + estadisticaTiempo;
	}

	@Override
	public String toString() {
		return nickname;
	}
}
