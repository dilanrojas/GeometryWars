package CRUD.vista.grafico;

/**
 * @author Ana
 * @date Nov 5, 2025
 * @version 1.0
 * @description description
 */

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

public class MenuVista extends JFrame {
	// Componentes gráficos
	private static final long serialVersionUID = 1L;
	private JButton btnInsertar;
	private JButton btnMostrar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JButton btnConfiguraciones;
	private JButton btnVolver;

	// Main (Pruebas)
	public static void main(String[] args) {
		MenuVista menu = new MenuVista();
	}
	
	// Constructor
	public MenuVista() {
		initComponents();
		setTitle("Menú | CRUD");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// Inicializar componentes
	private void initComponents() {
		getContentPane().setLayout(null);
		
		btnInsertar = new JButton("Insertar");
		btnInsertar.setBounds(77, 54, 322, 27);
		getContentPane().add(btnInsertar);
		
		btnMostrar = new JButton("Mostrar");
		btnMostrar.setBounds(77, 92, 322, 27);
		getContentPane().add(btnMostrar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(77, 131, 322, 27);
		getContentPane().add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(77, 170, 322, 27);
		getContentPane().add(btnEliminar);
		
		btnConfiguraciones = new JButton("Configuraciones del juego");
		btnConfiguraciones.setBounds(77, 209, 322, 27);
		getContentPane().add(btnConfiguraciones);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(77, 248, 322, 27);
		getContentPane().add(btnVolver);
	}

	// Setters & Getters
	public JButton getBtnInsertar() { return btnInsertar; }
	public JButton getBtnMostrar() { return btnMostrar; }
	public JButton getBtnActualizar() { return btnActualizar; }
	public JButton getBtnEliminar() { return btnEliminar; }
	public JButton getBtnConfiguraciones() { return btnConfiguraciones; }
	public JButton getBtnVolver() { return btnVolver; }

	// Configurar escuchadores
	public void setEscuchadores(ActionListener escuchador) {
		btnInsertar.addActionListener(escuchador);
		btnMostrar.addActionListener(escuchador);
		btnActualizar.addActionListener(escuchador);
		btnEliminar.addActionListener(escuchador);
		btnConfiguraciones.addActionListener(escuchador);
		btnVolver.addActionListener(escuchador);
	}

	// Método mostrar mensaje
	public void mostrarMsj(String msj) {
		JOptionPane.showMessageDialog(this, msj);
	}
	
	// Método cerrar
	public void cerrar() {
		dispose();
	}
}
