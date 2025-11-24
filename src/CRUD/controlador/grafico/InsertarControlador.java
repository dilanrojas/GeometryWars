package CRUD.controlador.grafico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CRUD.modelo.Configuraciones;
import CRUD.modelo.Usuario;
import CRUD.modelo.dao.IUsuarioDAO;
import CRUD.vista.grafico.InsertarVista;

/**
 * @author Sebastian Castro Ulate
 * @date Nov 7, 2025
 * @version 1.0
 * @description description
 */

public class InsertarControlador implements ActionListener {
	private IUsuarioDAO modelo;
	private InsertarVista vista;

	public InsertarControlador(
		IUsuarioDAO modelo,
		InsertarVista vista
	) {
		this.modelo = modelo;
		this.vista = vista;

		vista.setEscuchadores(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == vista.getBtnCancelar()) vista.cerrar();

		if (source == vista.getBtnCrear()) {
			// Comprobar datos
			if (!comprobarDatos()) return;

			// Verificar que nivel sea un número
			int nivel = comprobarNivel();
			if (nivel == -1) return;

			Usuario usuario = null;
			try {
				usuario = new Usuario(vista.getNombre(), vista.getNickname(), vista.getContrasena(), nivel, 0, 0, 0, new Configuraciones());
				modelo.agregar(usuario);
				vista.mostrarMsj("Usuario agregado corractamente");
				vista.cerrar();
				return;
			} catch (Exception ex) {
				vista.mostrarMsj("No es posible agregar el usuario");
				vista.cerrar();
				return;
			}
		}
	}

	private boolean comprobarDatos() {
		// Comprobar nombre
		if (vista.getNombre().equals("") || vista.getNombre().isBlank()) {
			vista.mostrarMsj("Ingrese un nombre válido");
			return false;
		}

		// Comprobar Nickname
		if (vista.getNickname().equals("") || vista.getNickname().isBlank()) {
			vista.mostrarMsj("Ingrese un nickname válido");
			return false;
		} else if (vista.getNickname().contains(" ")) {
			vista.mostrarMsj("El nickname no debe contener espacios");
			return false;
		} else if (modelo.buscar(vista.getNickname()) != null) {
			Usuario coincidenciaNickname = null;
			
			try {
				coincidenciaNickname = modelo.buscar(vista.getNickname())[0];
				
				if (coincidenciaNickname.getNickname().equals(vista.getNickname())) {
					vista.mostrarMsj("El nickname ya se encuentra registrado");
					return false;
				}
			} catch (Exception ex) {
				
			}
			
		}

		// Comprobar contraseña
		String contrasena = vista.getContrasena();
		String confirmarContrasena = vista.getConfirmarContrasena();

		// Comprobar contraseña válida
		if (!contrasena.equals(confirmarContrasena)) {
			vista.mostrarMsj("Las contraseñas no coinciden");
			return false;
			
		} else if (contrasena == "" || contrasena.isBlank()) {
			vista.mostrarMsj("Ingrese una contraseña válida");
			return false;
			
		}else if (contrasena.length() < 4) {
			vista.mostrarMsj("La contraseña debe tener un mínimo de 4 caractéres");
			return false;
			
		} else if (contrasena.contains(" ")) {
			vista.mostrarMsj("La contraseña no debe tener espacios");
			return false;
		}
		return true;
	}

	private int comprobarNivel() {
		int numero = 0;
		try {
			numero = vista.getNivel();
			if (numero < 0 || numero > 10) {
				vista.mostrarMsj("Ingrese un nivel válido (entre 0 y 10)");
				return -1;
			}
		} catch (NumberFormatException e1) {
			vista.mostrarMsj("El nivel debe ser un numero");
			return -1;
		}

		return numero;
	}
}
