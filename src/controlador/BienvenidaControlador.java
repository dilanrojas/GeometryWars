package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CRUD.controlador.grafico.InsertarControlador;
import CRUD.controlador.grafico.MenuControlador;
import CRUD.modelo.dao.IUsuarioDAO;
import CRUD.vista.grafico.InsertarVista;
import CRUD.vista.grafico.MenuVista;
import vista.BienvenidaVista;
import vista.IngresarJuegoVista;

/**
 * @author AnaGonzalezC5F593
 * @date 19 nov 2025
 * @version 1.0
 * @description TODO
 */

public class BienvenidaControlador implements ActionListener{

    private BienvenidaVista vista;
    private IUsuarioDAO modelo;

    public BienvenidaControlador(IUsuarioDAO modelo, BienvenidaVista vista) {
        this.vista = vista;
        this.modelo = modelo;

        vista.setEscuchadores(this);

        cargarRecords();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source == vista.getBtnInscribir()) {
            abrirInscripcion();
        } else if (source == vista.getBtnJugar()) {
            ingresarJuego();
        } else if (source == vista.getBtnCrud()) {
        	crud();
        } else if (source == vista.getBtnSalir()) {
            vista.cerrar();
        }
    }

    private void abrirInscripcion() {
        InsertarVista insertar = new InsertarVista();
        InsertarControlador controlador = new InsertarControlador(modelo, insertar);
    }

    private void ingresarJuego() {
        vista.cerrar();
        IngresarJuegoVista ingresarJuegoVista = new IngresarJuegoVista();
        IngresarJuegoControlador ingresarJuegoControlador = new IngresarJuegoControlador(modelo, ingresarJuegoVista);
    }

    private void cargarRecords() {
        vista.setListaRecords(modelo.getRecords(5));
    }
    
    private void crud() {
    	MenuVista menuVista = new MenuVista();
    	MenuControlador menuControlador = new MenuControlador(modelo, menuVista);
    	vista.cerrar();
    }
}
