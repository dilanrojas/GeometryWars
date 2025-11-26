package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CRUD.controlador.grafico.ConfiguracionesControlador;
import CRUD.modelo.Usuario;
import CRUD.modelo.dao.IUsuarioDAO;
import juego.Config;
import vista.BienvenidaVista;
import vista.IngresarJuegoVista;
import vista.MenuJuegoVista;

/**
 * @author AnaGonzalezC5F593
 * @date 19 nov 2025
 * @version 1.0
 * @description TODO
 */

public class IngresarJuegoControlador implements ActionListener {
	private IngresarJuegoVista vista;
	private IUsuarioDAO modelo;
	
	private MenuJuegoVista menuJuegoVista;
	private MenuJuegoControlador menuJuegoControlador;
	
	public IngresarJuegoControlador(IUsuarioDAO modelo, IngresarJuegoVista vista) {
		this.vista = vista;
		this.modelo = modelo;
		
		vista.setEscuchadores(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		Usuario usuarioIngresado = null;
		
		if (source == vista.getBtnIngresar()) {
			if (vista.getNickname().isBlank() && vista.getContrasena().isBlank()) {
				vista.mostrarMsj("Ingrese un usuario válido para continuar");
				return;
			}
			
			try {
				usuarioIngresado = modelo.buscar(vista.getNickname())[0];
				if (!usuarioIngresado.getNickname().equals(vista.getNickname())) {
					vista.mostrarMsj("El usuario ingresado es válido");
					return;
				}
			} catch (Exception ex) {
				vista.mostrarMsj("El usuario ingresado no existe");
				return;
			}
			
			if (!usuarioIngresado.getContrasena().equals(vista.getContrasena())) {
				vista.mostrarMsj("La contraseña ingresada no es correcta");
				return;
			}
			
			menuJuegoVista = new MenuJuegoVista();
			menuJuegoControlador = new MenuJuegoControlador(modelo, menuJuegoVista, usuarioIngresado);
			
			// Implementar configuaciones del usuario
			switch (usuarioIngresado.getConfiguraciones().getVelocidad()) {
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

			switch(usuarioIngresado.getConfiguraciones().getDificultad()) {
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

			Config.VIDAS = usuarioIngresado.getConfiguraciones().getVidas();
			
			vista.cerrar();
			
		} else if (source == vista.getBtnCancelar()) {
			volver();
		}
		
	}
	
	public void volver() {
		vista.cerrar();
		BienvenidaVista bienvenidaVista = new BienvenidaVista();
		BienvenidaControlador bienvenidaControlador = new BienvenidaControlador(modelo, bienvenidaVista);
	}
}

