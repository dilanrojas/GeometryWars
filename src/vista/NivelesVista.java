package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;

/**
 * @author Dilan Rojas
 * @date Nov 22, 2025
 * @version 1.0
 * @description description
 */

public class NivelesVista extends JFrame {

	// Components
	private JLabel lblNewLabel;
	private JButton btnNivel0;
	private JButton btnNivel1;
	private JButton btnNivel2;
	private JButton btnNivel3;
	private JButton btnNivel4;
	private JButton btnNivel5;
	private JButton btnNivel6;
	private JButton btnNivel7;
	private JButton btnNivel8;
	private JButton btnNivel9;
	private JButton btnNivelExtra;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnVolver;

	public NivelesVista() {
		initComponents();
		setTitle("Niveles | Geometry Wars");
		setSize(400, 430);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		setVisible(true);
	}

	public void initComponents() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("Niveles");
		lblNewLabel.setBounds(56, 52, 60, 17);
		contentPane.add(lblNewLabel);

		btnNivel0 = new JButton("0");
		btnNivel0.setEnabled(false);
		btnNivel0.setBounds(56, 81, 47, 47);
		contentPane.add(btnNivel0);
		
		btnNivel1 = new JButton("1");
		btnNivel1.setEnabled(false);
		btnNivel1.setBounds(115, 81, 47, 47);
		contentPane.add(btnNivel1);

		btnNivel2 = new JButton("2");
		btnNivel2.setEnabled(false);
		btnNivel2.setBounds(174, 81, 47, 47);
		contentPane.add(btnNivel2);

		btnNivel3 = new JButton("3");
		btnNivel3.setEnabled(false);
		btnNivel3.setBounds(233, 81, 47, 47);
		contentPane.add(btnNivel3);

		btnNivel4 = new JButton("4");
		btnNivel4.setEnabled(false);
		btnNivel4.setBounds(292, 81, 47, 47);
		contentPane.add(btnNivel4);

		btnNivel5 = new JButton("5");
		btnNivel5.setEnabled(false);
		btnNivel5.setBounds(56, 140, 47, 47);
		contentPane.add(btnNivel5);

		btnNivel6 = new JButton("6");
		btnNivel6.setEnabled(false);
		btnNivel6.setBounds(115, 140, 47, 47);
		contentPane.add(btnNivel6);

		btnNivel7 = new JButton("7");
		btnNivel7.setEnabled(false);
		btnNivel7.setBounds(174, 140, 47, 47);
		contentPane.add(btnNivel7);

		btnNivel8 = new JButton("8");
		btnNivel8.setEnabled(false);
		btnNivel8.setBounds(233, 140, 47, 47);
		contentPane.add(btnNivel8);

		btnNivel9 = new JButton("9");
		btnNivel9.setEnabled(false);
		btnNivel9.setBounds(292, 140, 47, 47);
		contentPane.add(btnNivel9);

		btnNivelExtra = new JButton("?");
		btnNivelExtra.setBackground(new Color(233, 193, 193));
		btnNivelExtra.setEnabled(false);
		btnNivelExtra.setBounds(174, 208, 47, 47);
		contentPane.add(btnNivelExtra);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(56, 320, 106, 27);
		contentPane.add(btnVolver);
	}

	public JButton getBtnNivel1() {
		return btnNivel1;
	}

	public JButton getBtnNivel2() {
		return btnNivel2;
	}

	public JButton getBtnNivel3() {
		return btnNivel3;
	}

	public JButton getBtnNivel4() {
		return btnNivel4;
	}

	public JButton getBtnNivel5() {
		return btnNivel5;
	}

	public JButton getBtnNivel6() {
		return btnNivel6;
	}

	public JButton getBtnNivel7() {
		return btnNivel7;
	}

	public JButton getBtnNivel8() {
		return btnNivel8;
	}

	public JButton getBtnNivel9() {
		return btnNivel9;
	}

	public JButton getBtnNivelExtra() {
		return btnNivelExtra;
	}
	
	public JButton[] getListaNiveles() {
		JButton[] lista = {
				btnNivel0, btnNivel1, btnNivel2, btnNivel3, btnNivel4, btnNivel5,
				btnNivel6, btnNivel7, btnNivel8, btnNivel9, btnNivelExtra
		};
		
		return lista;
	}
	
	public JButton getBtnVolver() {
		return btnVolver;
	}
	
	public void cerrar() {
		dispose();
	}

	public void mostrarMsj(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public void setEscuchadores(ActionListener listener) {
		for (int i = 0; i < getListaNiveles().length; i++) {
			getListaNiveles()[i].addActionListener(listener);
		}
		
		btnVolver.addActionListener(listener);
	}
}
