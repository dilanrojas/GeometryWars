package vista;

import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 * @author AnaGonzalezC5F593
 * @date 19 nov 2025
 * @version 1.0
 * @description TODO
 */

public class BienvenidaVista extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lblNombreJuego, lblAutores, lblNombres, lblRecords;
	private JButton btnInscribir;
	private JButton btnJugar;
	private JButton btnSalir;
	private JTextArea jtaRecords;
	private JButton btnCrud;

	public BienvenidaVista() {
	        initComponents();
	        setTitle("Bienvenida | Geometry Wars");
	        setSize(500, 600);
	        setResizable(false);
	        setLocationRelativeTo(null);
	        getContentPane().setLayout(null);

	        setVisible(true);
	    }

	private void initComponents() {
		lblNombreJuego = new JLabel("¡Bienvenido a Geometry Wars! ");
		lblNombreJuego.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreJuego.setBounds(118, 30, 241, 45);
		getContentPane().add(lblNombreJuego);

		lblAutores = new JLabel("Autores:");
		lblAutores.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAutores.setBounds(27, 68, 76, 22);
		getContentPane().add(lblAutores);

		lblNombres = new JLabel("Dilan Rojas - Ana González - Sebastián Castro");
		lblNombres.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombres.setBounds(96, 65, 293, 29);
		getContentPane().add(lblNombres);

		btnInscribir = new JButton("Inscribirse");
		btnInscribir.setBounds(157, 106, 177, 30);
		getContentPane().add(btnInscribir);

		btnJugar = new JButton("Ingresar al juego");
		btnJugar.setBounds(157, 151, 177, 30);
		getContentPane().add(btnJugar);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(157, 237, 177, 30);
		getContentPane().add(btnSalir);

		lblRecords = new JLabel("Los mejores récords");
		lblRecords.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRecords.setBounds(172, 292, 150, 31);
		getContentPane().add(lblRecords);
		
        jtaRecords = new JTextArea();
        jtaRecords.setEditable(false);
        jtaRecords.setBounds(85, 335, 320, 166);
        getContentPane().add(jtaRecords);
        
        btnCrud = new JButton("CRUD");
        btnCrud.setBounds(157, 195, 177, 30);
        getContentPane().add(btnCrud);
	}

	public void setListaRecords(String lista) {
		jtaRecords.setText(lista);
	}

	public JButton getBtnInscribir() {
		return btnInscribir;
	}

	public JButton getBtnJugar() {
		return btnJugar;
	}
	
	public JButton getBtnCrud() {
		return btnCrud;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setEscuchadores(ActionListener escuchador) {
		btnInscribir.addActionListener(escuchador);
		btnJugar.addActionListener(escuchador);
		btnSalir.addActionListener(escuchador);
		btnCrud.addActionListener(escuchador);
	}

	public void cerrar() {
		dispose();
	}

	public static void main(String[] args) {
		BienvenidaVista vista = new BienvenidaVista();
	}
}
