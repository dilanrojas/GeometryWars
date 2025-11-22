package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CRUD.modelo.Usuario;
import CRUD.modelo.dao.IUsuarioDAO;
import vista.MenuJuegoVista;
import vista.NivelesVista;

/**
 * @author AnaGonzalezC5F593
 * @date 19 nov 2025
 * @version 1.0
 * @description TODO
 */

public class MenuJuegoControlador implements ActionListener {
	// Componentes
	private IUsuarioDAO modelo;
	private MenuJuegoVista vista;
	private NivelesVista nivelesVista;
	private NivelesControlador nivelesControlador;
	private Usuario usuarioIngresado;
	
	// Constructor
	public MenuJuegoControlador(IUsuarioDAO modelo, MenuJuegoVista vista, Usuario usuarioIngresado) {
		this.modelo = modelo;
		this.vista = vista;
		this.usuarioIngresado = usuarioIngresado;
		
		vista.setEscuchadores(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == vista.getBtnJugar()) {
			nivelesVista = new NivelesVista();
			nivelesControlador = new NivelesControlador(modelo, nivelesVista, usuarioIngresado);
			vista.cerrar();
		} else if (source == vista.getBtnEstadisticas()) {
			vista.mostrarMsj(usuarioIngresado.estadisticas());
		} else if (source == vista.getBtnSalir()) {
			vista.cerrar();
		}
		
	}
}
