package vista;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

/**
 * @author Dilan Rojas
 * @date 21 nov 2025
 * @version 1.0
 * @description TODO
 */

public class IngresarJuegoVista extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField tfNickname;
	private JTextField tfContrasena;
	private JLabel lblNickname;
	private JLabel lblContrasena;
	private JButton btnIngresar;
	private JButton btnCancelar;

	public static void main(String[] args) {
		IngresarJuegoVista vista = new IngresarJuegoVista();
	}

	public IngresarJuegoVista() {
		initComponents();
		setTitle("Ingresar | GeometryWars");
		setSize(400, 400);
		setResizable(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void initComponents() {
		getContentPane().setLayout(null);

		lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(110, 76, 110, 20);
		lblNickname.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblNickname);

		tfNickname = new JTextField();
		tfNickname.setBackground(new Color(255, 255, 255));
		tfNickname.setBounds(110, 106, 180, 30);
		tfNickname.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(tfNickname);

		lblContrasena = new JLabel("Contraseña");
		lblContrasena.setBounds(110, 146, 110, 20);
		lblContrasena.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblContrasena);

		tfContrasena = new JTextField();
		tfContrasena.setBackground(new Color(255, 255, 255));
		tfContrasena.setBounds(110, 176, 180, 30);
		tfContrasena.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(tfContrasena);
		tfContrasena.setColumns(10);

		btnIngresar = new JButton("Ingresar");
		btnIngresar.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnIngresar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnIngresar.setBackground(Color.LIGHT_GRAY);
		btnIngresar.setBounds(110, 226, 180, 30);
		getContentPane().add(btnIngresar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.LIGHT_GRAY);
		btnCancelar.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnCancelar.setBounds(110, 268, 180, 30);
		getContentPane().add(btnCancelar);
	}

	public String getNickname() {
		return tfNickname.getText();
	}

	public String getContrasena() {
		return tfContrasena.getText();
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnIngresar() {
		return btnIngresar;
	}

	public void cerrar() {
		dispose();
	}

	public void mostrarMsj(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	public void setEscuchadores(ActionListener escuchador) {
		btnIngresar.addActionListener(escuchador);
		btnCancelar.addActionListener(escuchador);
	}
}


