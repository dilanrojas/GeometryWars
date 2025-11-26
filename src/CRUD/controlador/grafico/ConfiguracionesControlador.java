package CRUD.controlador.grafico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import CRUD.modelo.Configuraciones;
import CRUD.modelo.Usuario;
import CRUD.modelo.dao.IUsuarioDAO;
import CRUD.vista.grafico.ConfiguracionesVista;
import juego.Config;

/**
 * @author Dilan Rojas
 * @date Nov 5, 2025
 * @version 1.0
 * @description description
 */

public class ConfiguracionesControlador implements ActionListener, IMostrarDatos {
	// Componentes
	private ConfiguracionesVista vista;
	private IUsuarioDAO modelo;

	// Constructor
	public ConfiguracionesControlador(
			IUsuarioDAO modelo ,
			ConfiguracionesVista vista
			) {
		this.vista = vista;
		this.modelo = modelo;

		vista.setEscuchadores(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == vista.getBtnGuardar()) {
			int usuarioID = vista.getID() - 1;
			Usuario usuario = modelo.getElemento(usuarioID);

			Configuraciones configsNuevas = new Configuraciones(
					vista.getVelocidad(),
					vista.getArma(),
					vista.getDificultad(),
					vista.getVidas()
					);

			usuario.setConfiguraciones(configsNuevas);
			modelo.guardarDataset();
			vista.mostrarMsj("Configuraciones guardadas");
			aplicarConfiguraciones(usuario);
			vista.cerrar();

		} else if (source == vista.getBtnRestablecer()) {
			vista.restablecer();
			vista.mostrarMsj("Configuraciones reestablecidas");

		} else if (source == vista.getBtnCancelar()) {
			int usuarioID = vista.getID() - 1;
			Usuario usuario = modelo.getElemento(usuarioID);

			Configuraciones configsGuardadas = usuario.getConfiguraciones();

			if (!vista.equals(configsGuardadas)) {
				int eleccion = vista.mostrarConfirmacion();

				if (eleccion == JOptionPane.YES_OPTION) {
					vista.cerrar();
				} else {
					return;
				}
			}

			vista.cerrar();
		}
	}

	@Override
	public void mostrarDatos(Usuario usuario) {
		Configuraciones configuraciones = usuario.getConfiguraciones();

		vista.setArma(configuraciones.getArma());
		vista.setVelocidad(configuraciones.getVelocidad());
		vista.setVidas(configuraciones.getVidas());
		vista.setDificultad(configuraciones.getDificultad());
		vista.setID(usuario.getID());
		vista.setVisible(true);
	}
	
	public void aplicarConfiguraciones(Usuario usuario) {
		switch (usuario.getConfiguraciones().getVelocidad()) {
		case 1:
			Config.VELOCIDAD = 1;
			break;
		case 2:
			Config.VELOCIDAD = 1.4;
			break;
		case 3:
			Config.VELOCIDAD = 1.7;
			break;
		default:
			Config.VELOCIDAD = 1;
			break;
		}

		switch(usuario.getConfiguraciones().getDificultad()) {
		case 1:
			Config.DIFICULTAD = 1;
			break;
		case 2:
			Config.DIFICULTAD = 1.4;
			break;
		case 3:
			Config.DIFICULTAD = 1.7;
			break;
		default:
		}

		Config.ARMA = usuario.getConfiguraciones().getArma();
		Config.VIDAS = usuario.getConfiguraciones().getVidas();
	}
}
