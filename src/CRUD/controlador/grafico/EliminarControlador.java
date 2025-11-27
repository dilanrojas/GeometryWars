package CRUD.controlador.grafico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CRUD.modelo.Usuario;
import CRUD.modelo.dao.IUsuarioDAO;
import CRUD.vista.grafico.EliminarVista;

/**
 * @author Sebastian Castro Ulate
 * @date Nov 7, 2025
 * @version 1.0
 * @description description
 */

public class EliminarControlador implements ActionListener, IMostrarDatos {
	// Componentes
	private IUsuarioDAO modelo;
	private EliminarVista vista;
	
	// Constructor
	public EliminarControlador(
		IUsuarioDAO modelo,
		EliminarVista vista
	) {
		this.modelo = modelo;
		this.vista = vista;
		
        // Registrar escuchadores
        vista.setEscuchadores(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    Object source = e.getSource();

	    if (source == vista.getBtnEliminar()) {
        	int usuarioID = vista.getID() - 1;
            Usuario usuario = modelo.getElemento(0);
            
            if (usuario != null) {
                modelo.eliminar(usuarioID);
                vista.mostrarMsj("Usuario eliminado correctamente");
                vista.cerrar();
                vista.cerrar();
            } else {
                vista.mostrarMsj("No se encontró el usuario.");
            }
	    }
	    
	    if (source == vista.getBtnCancelar()) vista.cerrar();
	}

    // Metodo para mostrar los datos
    public void mostrarDatos(Usuario usuario) {
        vista.setTfNombre(usuario.getNombre());
        vista.setTfNivel(usuario.getNivel());
        vista.setTfPuntaje(usuario.getPuntaje());
        vista.setTfID(usuario.getID());
        vista.setVisible(true);
    }
}
