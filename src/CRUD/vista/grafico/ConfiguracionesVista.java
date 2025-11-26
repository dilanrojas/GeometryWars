package CRUD.vista.grafico;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JSlider;

import CRUD.modelo.Configuraciones;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;

/**
 * @author Dilan Rojas
 * @date Nov 5, 2025
 * @version 1.0
 * @description description
 */

public class ConfiguracionesVista extends JFrame {
	// Componentes gráficos
	private static final long serialVersionUID = 1L;
	private int ID;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JRadioButton rdBtnSubfusil;
	private JRadioButton rdBtnEscopeta;
	private JRadioButton rdBtnManual;
	private JRadioButton rdBtnVidas3;
	private JRadioButton rdBtnVidas5;
	private JRadioButton rdBtnVidas7;
	private JRadioButton rdBtnVidas10;
	private JRadioButton rdBtnFacil;
	private JRadioButton rdBtnMedio;
	private JRadioButton rdBtnDificil;
	private JSlider sVelocidad;
	private JButton btnRestablecer;

	// Main (Pruebas)
	public static void main(String[] args) {
		ConfiguracionesVista vista = new ConfiguracionesVista();
	}

	// Constructor
	public ConfiguracionesVista() {
		initComponents();
		setTitle("Configuraciones | CRUD");
		setSize(510, 480);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(false);
	}

	// Inicializar componentes
	public void initComponents() {
		getContentPane().setLayout(null);
		sVelocidad = new JSlider();
		sVelocidad.setValue(1);
		sVelocidad.setMinimum(1);
		sVelocidad.setMaximum(3);
		sVelocidad.setBounds(211, 117, 215, 16);
		getContentPane().add(sVelocidad);

		JLabel lblNewLabel = new JLabel("Dificultad");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(50, 49, 105, 17);
		getContentPane().add(lblNewLabel);

		ButtonGroup grupoDificultad = new ButtonGroup();

		rdBtnFacil = new JRadioButton("Fácil");
		rdBtnFacil.setBounds(199, 45, 60, 25);
		getContentPane().add(rdBtnFacil);

		rdBtnMedio = new JRadioButton("Medio");
		rdBtnMedio.setBounds(293, 45, 63, 25);
		getContentPane().add(rdBtnMedio);

		rdBtnDificil = new JRadioButton("Difícil");
		rdBtnDificil.setBounds(391, 45, 132, 25);
		getContentPane().add(rdBtnDificil);

		grupoDificultad.add(rdBtnDificil);
		grupoDificultad.add(rdBtnMedio);
		grupoDificultad.add(rdBtnFacil);

		JLabel lblNewLabel_1 = new JLabel("Velocidad");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1.setBounds(50, 117, 105, 17);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Normal");
		lblNewLabel_2.setBounds(199, 145, 60, 17);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Rápido");
		lblNewLabel_2_1.setBounds(395, 145, 60, 17);
		getContentPane().add(lblNewLabel_2_1);

		JLabel lblNewLabel_1_1 = new JLabel("Tipo de arma");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(50, 226, 105, 17);
		getContentPane().add(lblNewLabel_1_1);

		ButtonGroup grupoArmas = new ButtonGroup();

		rdBtnSubfusil = new JRadioButton("Subfusil");
		rdBtnSubfusil.setBounds(199, 222, 81, 25);
		getContentPane().add(rdBtnSubfusil);

		rdBtnEscopeta = new JRadioButton("Escopeta");
		rdBtnEscopeta.setBounds(293, 222, 87, 25);
		getContentPane().add(rdBtnEscopeta);
		
		rdBtnManual = new JRadioButton("Manual");
		rdBtnManual.setBounds(390, 222, 81, 25);
		getContentPane().add(rdBtnManual);

		grupoArmas.add(rdBtnSubfusil);
		grupoArmas.add(rdBtnEscopeta);
		grupoArmas.add(rdBtnManual);

		JLabel lblNewLabel_1_1_1 = new JLabel("Vidas extra");
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(50, 301, 105, 17);
		getContentPane().add(lblNewLabel_1_1_1);

		ButtonGroup grupoVidas = new ButtonGroup();

		rdBtnVidas3 = new JRadioButton("3");
		rdBtnVidas3.setBounds(199, 297, 47, 25);
		getContentPane().add(rdBtnVidas3);

		rdBtnVidas5 = new JRadioButton("5");
		rdBtnVidas5.setBounds(256, 297, 47, 25);
		getContentPane().add(rdBtnVidas5);

		rdBtnVidas10 = new JRadioButton("10");
		rdBtnVidas10.setBounds(374, 297, 52, 25);
		getContentPane().add(rdBtnVidas10);

		rdBtnVidas7 = new JRadioButton("7");
		rdBtnVidas7.setBounds(318, 297, 52, 25);
		getContentPane().add(rdBtnVidas7);

		grupoVidas.add(rdBtnVidas3);
		grupoVidas.add(rdBtnVidas5);
		grupoVidas.add(rdBtnVidas7);
		grupoVidas.add(rdBtnVidas10);

		JLabel lblNewLabel_3 = new JLabel("Acelerado");
		lblNewLabel_3.setBounds(293, 145, 60, 17);
		getContentPane().add(lblNewLabel_3);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(50, 370, 123, 27);
		getContentPane().add(btnCancelar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(318, 370, 123, 27);
		getContentPane().add(btnGuardar);

		btnRestablecer = new JButton("Restablecer");
		btnRestablecer.setBounds(185, 370, 123, 27);
		getContentPane().add(btnRestablecer);
	}

	// Setters & Getters
	public void setVelocidad(int valor) {
		sVelocidad.setValue(valor);
	}

	public void setArma(int valor) {
		if (valor == 1) {
			rdBtnSubfusil.setSelected(true);
		} else if (valor == 2) {
			rdBtnEscopeta.setSelected(true);
		} else if (valor == 3) {
			rdBtnManual.setSelected(true);
		}
	}

	public void setVidas(int valor) {
		if (valor == 3) {
			rdBtnVidas3.setSelected(true);
		} else if (valor == 5) {
			rdBtnVidas5.setSelected(true);
		} else if (valor == 7) {
			rdBtnVidas7.setSelected(true);
		} else if (valor == 10) {
			rdBtnVidas10.setSelected(true);
		}
	}

	public void setDificultad(int valor) {
		if (valor == 1) {
			rdBtnFacil.setSelected(true);
		} else if (valor == 2) {
			rdBtnMedio.setSelected(true);
		} else if (valor == 3) {
			rdBtnDificil.setSelected(true);
		}
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public int getID() {
		return ID;
	}

	public JButton getBtnRestablecer() {
		return btnRestablecer;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public int getArma() {
		if (rdBtnSubfusil.isSelected()) {
			return 1;
		} else if (rdBtnEscopeta.isSelected()) {
			return 2;
		} else if (rdBtnManual.isSelected()) {
			return 3;
		}

		// Por defecto (Subfusil)
		return 1;
	}

	public int getVelocidad() {
		return sVelocidad.getValue();
	}

	public int getVidas() {
		if (rdBtnVidas3.isSelected()) {
			return 3;
		} else if (rdBtnVidas5.isSelected()) {
			return 5;
		} else if (rdBtnVidas7.isSelected()) {
			return 7;
		} else if (rdBtnVidas10.isSelected()) {
			return 10;
		}

		// Por defecto (3 vidas)
		return 3;
	}

	public int getDificultad() {
		if (rdBtnFacil.isSelected()) {
			return 1;
		} else if (rdBtnMedio.isSelected()) {
			return 2;
		} else if (rdBtnDificil.isSelected()) {
			return 3;
		}

		// Por defecto (Fácil)
		return 1;
	}

	public void restablecer() {
		setArma(1);
		setVelocidad(1);
		setDificultad(1);
		setVidas(3);
	}

	// Configurar escuchadores
	public void setEscuchadores(ActionListener listener) {
		btnGuardar.addActionListener(listener);
		btnCancelar.addActionListener(listener);
		btnRestablecer.addActionListener(listener);
	}

	// Método mostrar mensaje
	public void mostrarMsj(String msg) {
		JOptionPane.showMessageDialog(this, msg, "CRUD", JOptionPane.INFORMATION_MESSAGE);
	}

	public int mostrarConfirmacion() {
		return JOptionPane.showConfirmDialog(this, "¿Desea salir sin guardar los cambios?", "CRUD", JOptionPane.YES_NO_OPTION);
	}

	public boolean equals(Configuraciones configs) {
		return getVelocidad() == configs.getVelocidad() &&
				getArma() == configs.getArma() &&
				getDificultad() == configs.getDificultad() &&
				getVidas() == configs.getVidas();
	}

	// Método cerrar
	public void cerrar() {
		dispose();
	}
}
