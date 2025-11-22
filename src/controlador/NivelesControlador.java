package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		reproducirMusica();
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
		
		for (int i = 0; i < nivelActual; i++) {
			vista.getListaNiveles()[i].setEnabled(true);
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
    
    public void reproducirMusica() {
    	Assets.reproducirMusicaFondo();
    }
    
    public void comenzarNivel(Scene nivel) {
        Game game = new Game(nivel);
        GameLoop gameLoop = new GameLoop(game);
    }
    
    public Nivel[] listaNiveles() {
    	Nivel[] lista = {
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
