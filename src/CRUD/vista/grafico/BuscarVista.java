package CRUD.vista.grafico;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import CRUD.modelo.Usuario;
import javax.swing.border.LineBorder;
import java.awt.Color;

/**
 * @author Dilan Rojas
 * @date Nov 5, 2025
 * @version 1.0
 * @description description
 */

public class BuscarVista extends JFrame {
	// Componentes gráficos
	private static final long serialVersionUID = 1L;
	private JTextField tfBuscarInput;
	private JButton btnBuscar;
	private JList<Usuario> listaUsuarios;
	private DefaultListModel<Usuario> modeloUsuarios;
	private JButton btnVolver;

	// Main (Pruebas)
	public static void main(String[] args) {
		MostrarVista vista = new MostrarVista();
	}

	// Constructor
	public BuscarVista() {
		initComponents();
        setTitle("Buscar | CRUD");
        setSize(750, 470);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
	}

	// Inicializar componentes
	public void initComponents() {
		getContentPane().setLayout(null);
		
        tfBuscarInput = new JTextField();
        tfBuscarInput.setToolTipText("");
        tfBuscarInput.setBounds(111, 52, 409, 38);
        getContentPane().add(tfBuscarInput);
        tfBuscarInput.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Nombre | Nickname | Puntaje | Nivel");
        lblNewLabel.setBounds(266, 23, 254, 17);
        getContentPane().add(lblNewLabel);
        
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(532, 52, 85, 37);
        getContentPane().add(btnBuscar);
        
        modeloUsuarios = new DefaultListModel<Usuario>();
        listaUsuarios = new JList<Usuario>(modeloUsuarios);
        listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaUsuarios.setBounds(111, 125, 506, 206);
        getContentPane().add(listaUsuarios);
        
        btnVolver = new JButton("Volver");
        btnVolver.setBorder(new LineBorder(new Color(153, 204, 255), 2, true));
        btnVolver.setBounds(111, 343, 119, 27);
        getContentPane().add(btnVolver);
	}
	
	// Setters & Getters
	public String getTfBuscarInput() {
		return tfBuscarInput.getText();
	}
	
	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setListaUsuarios(Usuario[] lista) {
		modeloUsuarios.clear();
		for (Usuario usuario : lista) {
			modeloUsuarios.addElement(usuario);
		}
	}
	
	// Configurar escuchadores
	public void setEscuchadores(ActionListener escuchador) {
		btnBuscar.addActionListener(escuchador);
		btnVolver.addActionListener(escuchador);
		
	}
	
	public void setEscuchadorLista(MouseListener escuchador) {
		listaUsuarios.addMouseListener(escuchador);
	}
	
	// Obtener la lista de usuarios de la búsqueda
	public JList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	
	public JButton getBtnVolver() {
		return btnVolver;
	}
	// Método mostrar mensaje
	public void mostrarMsj(String msg) {
	    JOptionPane.showMessageDialog(this, msg, "CRUD", JOptionPane.INFORMATION_MESSAGE);
	}
	
	// Método cerrar
	public void cerrar() {
		dispose();
	}
}
