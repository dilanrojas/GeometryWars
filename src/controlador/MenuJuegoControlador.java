package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CRUD.controlador.grafico.ConfiguracionesControlador;
import CRUD.modelo.Usuario;
import CRUD.modelo.dao.IUsuarioDAO;
import CRUD.vista.grafico.ConfiguracionesVista;
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
	
	private ConfiguracionesVista configuracionesVista;
	private ConfiguracionesControlador configuracionesControlador;
	
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
			jugar();
		} else if (source == vista.getBtnEstadisticas()) {
			estadisticas();
		} else if (source == vista.getBtnConfiguraciones()) {
			configuraciones();
		} else if (source == vista.getBtnSalir()) {
			vista.cerrar();
		}
	}
	
	public void jugar() {
		nivelesVista = new NivelesVista();
		nivelesControlador = new NivelesControlador(modelo, nivelesVista, usuarioIngresado);
		vista.cerrar();
	}
	
	public void estadisticas() {
		vista.mostrarMsj(usuarioIngresado.estadisticas());
	}
	
	public void configuraciones() {
		configuracionesVista = new ConfiguracionesVista();
		configuracionesControlador = new ConfiguracionesControlador(modelo, configuracionesVista);
		configuracionesControlador.mostrarDatos(usuarioIngresado);
	}
}
