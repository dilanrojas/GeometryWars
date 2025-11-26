package vista;

import javax.swing.JFrame;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @author AnaGonzalezC5F593
 * @date 19 nov 2025
 * @version 1.0
 * @description TODO
 */

public class MenuJuegoVista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblMenu;
	private JButton btnEstadisticas;
	private JButton btnSalir;
	private JButton btnJugar;
	
	// Main (Pruebas)
	public static void main(String[] args) {
		MenuJuegoVista vista = new MenuJuegoVista();
	}

	public MenuJuegoVista() {
		initComponents();
		setTitle("Menu | Geometry Wars");
		setSize(375, 340);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setVisible(true);
	}

	private void initComponents() {
		btnJugar = new JButton("Jugar");
		btnJugar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnJugar.setBounds(107, 88, 146, 37);
		getContentPane().add(btnJugar);

		btnEstadisticas = new JButton("Estadisticas");
		btnEstadisticas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEstadisticas.setBounds(107, 137, 146, 37);
		getContentPane().add(btnEstadisticas);

		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSalir.setBounds(107, 186, 146, 37);
		getContentPane().add(btnSalir);

		lblMenu = new JLabel("Menu del jugador");
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMenu.setBounds(124, 30, 146, 26);
		getContentPane().add(lblMenu);
	}

	//Getters
	public JButton getBtnEstadisticas() {
		return btnEstadisticas;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public JButton getBtnJugar() {
		return btnJugar;
	}

	// Configurar escuchadores
	public void setEscuchadores(ActionListener escuchador) {
		btnJugar.addActionListener(escuchador);
		btnEstadisticas.addActionListener(escuchador);
		btnSalir.addActionListener(escuchador);
	}

	// Método mostrar mensaje
	public void mostrarMsj(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Menu Juego", JOptionPane.INFORMATION_MESSAGE);
	}

	// Método cerrar
	public void cerrar() {
		dispose();
	}
}
