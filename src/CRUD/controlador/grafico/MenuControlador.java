package CRUD.controlador.grafico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CRUD.modelo.dao.IUsuarioDAO;
import CRUD.vista.grafico.ActualizarVista;
import CRUD.vista.grafico.BuscarVista;
import CRUD.vista.grafico.ConfiguracionesVista;
import CRUD.vista.grafico.EliminarVista;
import CRUD.vista.grafico.InsertarVista;
import CRUD.vista.grafico.MenuVista;
import CRUD.vista.grafico.MostrarVista;
import controlador.BienvenidaControlador;
import vista.BienvenidaVista;
import CRUD.controlador.grafico.MostrarControlador;

/**
 * @author Ana Gonzalez
 * @date Nov 9, 2025
 * @version 1.0
 * @description description
 */

public class MenuControlador implements ActionListener {
	private IUsuarioDAO modelo;

	private BuscarVista buscarVista;
	private BuscarControlador buscarControlador;
	
	private MenuVista menuVista;
	
	private InsertarVista insertarVista;
	private InsertarControlador insertarControlador;
	
	private ActualizarVista actualizarVista;
	private ActualizarControlador actualizarControlador;
	
	private EliminarVista eliminarVista;
	private EliminarControlador eliminarControlador;
	
	private ConfiguracionesVista configuracionesVista;
	private ConfiguracionesControlador configuracionesControlador;
	
	private MostrarVista mostrarVista;
	private MostrarControlador mostrarControlador;
	
	private BienvenidaVista bienvenidaVista;
	private BienvenidaControlador bienvenidaControlador;

	public MenuControlador(
		IUsuarioDAO modelo,
		MenuVista menuVista
	) {
		this.modelo = modelo;
        this.menuVista = menuVista;

        // Escuchar los eventos del menú principal
        menuVista.setEscuchadores(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == menuVista.getBtnInsertar()) {
        	insertar();
        } else if (source == menuVista.getBtnMostrar()) {
        	mostrar();
        } else if (source == menuVista.getBtnEliminar()) {
        	eliminar();
        } else if (source == menuVista.getBtnActualizar()) {
        	actualizar();
        } else if (source == menuVista.getBtnConfiguraciones()) {
        	configuraciones();
        } else if (source == menuVista.getBtnVolver()) {
        	volver();
        }
    }
    
    public void buscar(IMostrarDatos controlador) {
    	buscarVista = new BuscarVista();
    	buscarControlador = new BuscarControlador(buscarVista, modelo, controlador);
    }
    
    public void insertar() {
    	insertarVista = new InsertarVista();
    	insertarControlador = new InsertarControlador(modelo, insertarVista);
    }
    
    public void mostrar() {
    	mostrarVista = new MostrarVista();
    	mostrarControlador = new MostrarControlador(mostrarVista);
    	buscar(mostrarControlador);
    }
    
    public void actualizar() {
    	actualizarVista = new ActualizarVista();
    	actualizarControlador = new ActualizarControlador(modelo, actualizarVista);
    	buscar(actualizarControlador);
    }

    public void eliminar() {
    	eliminarVista = new EliminarVista();
    	eliminarControlador = new EliminarControlador(modelo, eliminarVista);
    	buscar(eliminarControlador);
    }
    
    public void configuraciones() {
    	configuracionesVista = new ConfiguracionesVista();
    	configuracionesControlador = new ConfiguracionesControlador(modelo, configuracionesVista);
    	buscar(configuracionesControlador);
    }
    
    public void volver() {
    	bienvenidaVista = new BienvenidaVista();
    	bienvenidaControlador = new BienvenidaControlador(modelo, bienvenidaVista);
    	menuVista.cerrar();
    }
}