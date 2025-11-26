package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import CRUD.modelo.Usuario;
import CRUD.modelo.dao.IUsuarioDAO;
import vista.MenuJuegoVista;
import vista.NivelesVista;
import juego.Assets;
import juego.Game;
import juego.escenas.Nivel;
import juego.escenas.niveles.*;
import motor.GameLoop;
import motor.Scene;

/**
 * @author Dilan Rojas
 * @date Nov 22, 2025
 * @version 1.0
 * @description description
 */

public class NivelesControlador implements ActionListener {
	// Componentes
	private IUsuarioDAO modelo;
	private NivelesVista vista;
	private Usuario usuarioIngresado;
	
	private MenuJuegoControlador menuJuegoControlador;
	private MenuJuegoVista menuJuegoVista;
	
	private GameLoop gameLoopActual;
	private Game gameActual;
	
	private String instrucciones =
			"Instrucciones y reglas:\n" +
			" Evada y elimine a los enemigos para ganar puntos\n\n" +
			"Controles: \n" +
			" Avanzar: W\n" +
			" Girar cámara: Puntero (Mouse)\n" +
			" Disparar: Click izquierdo o Tecla espaciadora";
	
	// Constructor
	public NivelesControlador(IUsuarioDAO modelo, NivelesVista vista, Usuario usuarioIngresado) {
		this.modelo = modelo;
		this.vista = vista;
		this.usuarioIngresado = usuarioIngresado;
		
		vista.setEscuchadores(this);
		cargarNiveles();
		
		if (!comprobarAssets()) {
			vista.mostrarMsj("Error al cargar los assets");
			return;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		for (int i = 0; i < vista.getListaNiveles().length; i++) {
			if (source == vista.getListaNiveles()[i]) {
				comenzarNivel(listaNiveles()[i]);
			}
		}
		
		if (source == vista.getBtnVolver()) {
			volver();
		}
	}
	
	public void cargarNiveles() {
		int nivelActual = usuarioIngresado.getNivel();
		JButton nivelPendiente = null;
		JButton nivelCompletado = null;
		
		// Si juega por primera vez, mostrar instrucciones
		if (usuarioIngresado.getNivel() == 0) {
			vista.mostrarMsj(instrucciones);
		}
		
		for (int i = 0; i <= nivelActual; i++) {
			// Comprobar niveles pendiente
			try {
				nivelPendiente = vista.getListaNiveles()[i];
				nivelPendiente.setEnabled(true);
			} catch (Exception e) {
				// Error al obtener el nivel pendiente
				vista.mostrarMsj("No se logró obtener el nivel pendiente");
			}
			
			// Cambiar el fondo a niveles completados
			try {
				nivelCompletado = vista.getListaNiveles()[i - 1];
				nivelCompletado.setBackground(new Color(193, 233, 193));
			} catch (Exception e2) {
				// No ha completado el primer nivel
			}
		}
	}
	
	public void volver() {
		menuJuegoVista = new MenuJuegoVista();
		menuJuegoControlador = new MenuJuegoControlador(modelo, menuJuegoVista, usuarioIngresado);
		vista.cerrar();
	}
	
    public boolean comprobarAssets() {
    	return Assets.cargados();
    }
    
    public void comenzarNivel(Scene nivel) {
        ((Nivel) nivel).setControlador(this);
        
        gameActual = new Game(nivel);
        gameLoopActual = new GameLoop(gameActual);
        Assets.reproducirMusicaFondo();
    }
    
    public void cerrarJuego(boolean gano, int puntajeObtenido, int nivelAlcanzado, int tiempoDelNivel) {
    	if (gano) {
    		usuarioIngresado.setPuntaje(usuarioIngresado.getPuntaje() + puntajeObtenido);
    		if (usuarioIngresado.getNivel() != listaNiveles().length - 1 &&
    			usuarioIngresado.getNivel() <= nivelAlcanzado) {
    			usuarioIngresado.setNivel(nivelAlcanzado + 1);
    		}
    		usuarioIngresado.setTiempoJugado(usuarioIngresado.getTiempoJugado() + tiempoDelNivel);

    		modelo.guardarDataset();
    		cargarNiveles();
    	}

    	Assets.detenerMusicaFondo();
    	if (gameLoopActual != null) gameLoopActual.detener();
    	if (gameActual != null) gameActual.destruir();
    }
	
    public Nivel[] listaNiveles() {
    	Nivel[] lista = {
    			new Nivel0(),
    			new Nivel1(),
    			new Nivel2(),
    			new Nivel3(),
    			new Nivel4(),
    			new Nivel5(),
    			new Nivel6(),
    			new Nivel7(),
    			new Nivel8(),
    			new Nivel9(),
    			new NivelExtra()
    	};
    	
    	return lista;
    }
}
