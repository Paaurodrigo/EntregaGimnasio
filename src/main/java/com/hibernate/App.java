package com.hibernate;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import com.hibernate.dao.EntrenadorDAO;
import com.hibernate.model.Entrenador;
import com.hibernate.dao.EjercicioDAO;
import com.hibernate.model.Cliente;
import com.hibernate.dao.ClienteDAO;
import com.hibernate.model.Ejercicio;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class App {

	private JFrame frame;
	private JTextField txtId;
	private JTextField txtNom;
	private JTextField txtTemp;
	private JTextField txtCap;
	private DefaultTableModel modelEntrenador; 
	private DefaultTableModel modelCliente; 
	private DefaultTableModel modelEjercicio; 
	private EntrenadorDAO entrenadorDAO;
	private ClienteDAO clienteDAO;
	private EjercicioDAO ejercicioDAO;
	private List <Entrenador> entrenadores;
	private List <Cliente> clientes;
	private List <Ejercicio> ejercicios;
	private JTextField txtNombreEntrenador;
	private JLabel lblLesiones;
	private JTextField txtNombreEjercicio;
	private JTextField txtPesoEjercicio;
	private JTextField txtSeriesEjercicio;
	private JTextField txtRepsEjercicio;
	private JTextField txtDescansoEjercicio;
	private JTextField txtdificultadEjercicio;
	private JTextField txtNombreCliente;
	private JTextField txtApellidosCliente;
	private JTextField txtFechaNacCliente;
	private JTextField txtLesionesCliente;
	private JTextField txtObjCliente;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void refrescarEntrenador() {
		List<Entrenador> entrenadores= null;
		modelEntrenador.setRowCount(0);
		entrenadores = entrenadorDAO.selectAllEntrenador();
		entrenadores.forEach(s->{
			Object [] row = new Object [2];
			row [0]=s.getIdEntrenador();
			row [1]=s.getNomEntrenador();
			modelEntrenador.addRow(row);
		});
		
		
	}
	public void refrescarCliente() {
		List<Cliente> clientes= null;
		modelCliente.setRowCount(0);
		clientes = clienteDAO.selectAllCliente();
		clientes.forEach(s->{
			Object [] row = new Object [7];
			row [0]=s.getIdCliente();
			row [1]=s.getNomCliente();
			row [2]=s.getApeCliente();
			row [3]=s.getFechaNac();
			row [4]=s.getLesiones();
			row [5]=s.getObjetivo();
			row [6]=s.getIdEntrenador();
	
			modelCliente.addRow(row);
		});
		
		
	}
	
	public void refrescarEjercicio() {
		List<Ejercicio> ejercicios= null;
		modelEjercicio.setRowCount(0);
		ejercicios = ejercicioDAO.selectAllEjercicio();
		ejercicios.forEach(s->{
			Object [] row = new Object [7];
			row [0]=s.getIdEjercicio();
			row [1]=s.getNomEjercicio();
			row [2]=s.getPeso();
			row [3]=s.getSeries();
			row [4]=s.getReps();
			row [5]=s.getDescanso();
			row [6]=s.getDificultad();
			modelEjercicio.addRow(row);
		});
	}
	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	//variables
			String nomEntrenador;
	
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1250, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		entrenadorDAO=new EntrenadorDAO();
		entrenadores=entrenadorDAO.selectAllEntrenador();
		
		clienteDAO=new ClienteDAO();
		clientes=clienteDAO.selectAllCliente();
		
		ejercicioDAO=new EjercicioDAO();
		ejercicios=ejercicioDAO.selectAllEjercicio();
		
		
		
		//Columnas de las 3 tablas entrenador, cliente, ejercicios
		//Entrenador
		modelEntrenador = new DefaultTableModel();
		modelEntrenador.addColumn("ID");
		modelEntrenador.addColumn("Nombre");
		//Cliente
		modelCliente = new DefaultTableModel(); 
		modelCliente.addColumn("ID"); 
		modelCliente.addColumn("Nombre"); 
		modelCliente.addColumn("Apellidos"); 
		modelCliente.addColumn("Fecha Nacimiento"); 
		modelCliente.addColumn("Lesiones"); 
		modelCliente.addColumn("Objetivo"); 
		modelCliente.addColumn("idEntrenador"); 
		//Ejercicio
		modelEjercicio = new DefaultTableModel(); 
		modelEjercicio.addColumn("ID"); 
		modelEjercicio.addColumn("Nombre"); 
		modelEjercicio.addColumn("Peso"); 
		modelEjercicio.addColumn("Series"); 
		modelEjercicio.addColumn("Repeticiones"); 
		modelEjercicio.addColumn("Descanso"); 
     	modelEjercicio.addColumn("Dificultad"); 
     	
     	//Las tres tablas con los scrollpane
     	//Tabla entrenador
		JTable tableEntrenador = new JTable(modelEntrenador);
		frame.getContentPane().setLayout(null);
		tableEntrenador.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(tableEntrenador);
		scrollPane.setBounds(12, 12, 481, 190);
		frame.getContentPane().add(scrollPane);
		 
		//Tabla cliente
		JTable tableCliente = new JTable(modelCliente); 
		frame.getContentPane().setLayout(null); 
		tableCliente.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
		JScrollPane scrollPane_1 = new JScrollPane(tableCliente); 
		scrollPane_1.setBounds(616, 12, 463, 190); 
		frame.getContentPane().add(scrollPane_1); 
		
		//Tabla ejercicio
		JTable tableEjercicio= new JTable(modelEjercicio); 
		frame.getContentPane().setLayout(null); 
		tableEjercicio.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
		JScrollPane scrollPane_2 = new JScrollPane(tableEjercicio); 
		scrollPane_2.setBounds(12, 346, 463, 190); 
		frame.getContentPane().add(scrollPane_2); 
		
		//labels y txt 
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(79, 296, 70, 15);
		frame.getContentPane().add(lblNombre);
		
		txtNombreEntrenador = new JTextField();
		txtNombreEntrenador.setBounds(156, 294, 171, 19);
		frame.getContentPane().add(txtNombreEntrenador);
		txtNombreEntrenador.setColumns(10);
		
		JLabel lblNombre_1 = new JLabel("Nombre:");
		lblNombre_1.setBounds(22, 634, 70, 15);
		frame.getContentPane().add(lblNombre_1);
		
		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setBounds(22, 661, 70, 15);
		frame.getContentPane().add(lblPeso);
		
		JLabel lblSeries = new JLabel("Series:");
		lblSeries.setBounds(22, 690, 70, 15);
		frame.getContentPane().add(lblSeries);
		
		JLabel lblRepeticiones = new JLabel("Repeticiones:");
		lblRepeticiones.setBounds(22, 717, 109, 15);
		frame.getContentPane().add(lblRepeticiones);
		
		JLabel lblDescanso = new JLabel("Descanso:");
		lblDescanso.setBounds(22, 744, 83, 15);
		frame.getContentPane().add(lblDescanso);
		
		JLabel lblDificultad = new JLabel("Dificultad:");
		lblDificultad.setBounds(22, 773, 83, 15);
		frame.getContentPane().add(lblDificultad);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(666, 288, 70, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(666, 315, 70, 15);
		frame.getContentPane().add(lblApellidos);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
		lblFechaNacimiento.setBounds(666, 347, 130, 15);
		frame.getContentPane().add(lblFechaNacimiento);
		
		lblLesiones = new JLabel("Lesiones:");
		lblLesiones.setBounds(666, 374, 70, 15);
		frame.getContentPane().add(lblLesiones);
		
		JLabel lblObjetivo = new JLabel("Objetivo:");
		lblObjetivo.setBounds(666, 401, 70, 15);
		frame.getContentPane().add(lblObjetivo);
		
		txtNombreEjercicio = new JTextField();
		txtNombreEjercicio.setBounds(128, 632, 114, 19);
		frame.getContentPane().add(txtNombreEjercicio);
		txtNombreEjercicio.setColumns(10);
		
		txtPesoEjercicio = new JTextField();
		txtPesoEjercicio.setBounds(128, 659, 114, 19);
		frame.getContentPane().add(txtPesoEjercicio);
		txtPesoEjercicio.setColumns(10);
		
		txtSeriesEjercicio = new JTextField();
		txtSeriesEjercicio.setBounds(128, 688, 114, 19);
		frame.getContentPane().add(txtSeriesEjercicio);
		txtSeriesEjercicio.setColumns(10);
		
		txtRepsEjercicio = new JTextField();
		txtRepsEjercicio.setBounds(128, 715, 114, 19);
		frame.getContentPane().add(txtRepsEjercicio);
		txtRepsEjercicio.setColumns(10);
		
		txtDescansoEjercicio = new JTextField();
		txtDescansoEjercicio.setBounds(128, 742, 114, 19);
		frame.getContentPane().add(txtDescansoEjercicio);
		txtDescansoEjercicio.setColumns(10);
		
		txtdificultadEjercicio = new JTextField();
		txtdificultadEjercicio.setBounds(128, 771, 114, 19);
		frame.getContentPane().add(txtdificultadEjercicio);
		txtdificultadEjercicio.setColumns(10);
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setBounds(809, 288, 114, 19);
		frame.getContentPane().add(txtNombreCliente);
		txtNombreCliente.setColumns(10);
		
		txtApellidosCliente = new JTextField();
		txtApellidosCliente.setBounds(809, 313, 114, 19);
		frame.getContentPane().add(txtApellidosCliente);
		txtApellidosCliente.setColumns(10);
		
		txtFechaNacCliente = new JTextField();
		txtFechaNacCliente.setBounds(809, 345, 114, 19);
		frame.getContentPane().add(txtFechaNacCliente);
		txtFechaNacCliente.setColumns(10);
		
		txtLesionesCliente = new JTextField();
		txtLesionesCliente.setBounds(809, 372, 114, 19);
		frame.getContentPane().add(txtLesionesCliente);
		txtLesionesCliente.setColumns(10);
		
		txtObjCliente = new JTextField();
		txtObjCliente.setBounds(809, 399, 114, 19);
		frame.getContentPane().add(txtObjCliente);
		txtObjCliente.setColumns(10);
		
		//botones
		//entrenador
		JButton btnCrearEntrenador = new JButton("Crear Entrenador");
		btnCrearEntrenador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nomEntrenador=txtNombreEntrenador.getText();
				Entrenador entrenador=new Entrenador(nomEntrenador);				
				entrenadorDAO.insertEntrenador(entrenador);
				refrescarEntrenador();
			}
		});
		btnCrearEntrenador.setBounds(12, 214, 157, 25);
		frame.getContentPane().add(btnCrearEntrenador);
		
		JButton btnBorrarEntrenador = new JButton("Borrar Entrenador");
		btnBorrarEntrenador.setBounds(322, 214, 171, 25);
		frame.getContentPane().add(btnBorrarEntrenador);
		
		JButton btnActualizarEntrenador = new JButton("Actualizar Entrenador");
		btnActualizarEntrenador.setBounds(146, 251, 200, 25);
		frame.getContentPane().add(btnActualizarEntrenador);
		
		//ejercicio
		
		JButton btnCrearEjercicio = new JButton("Crear Ejercicio");
		btnCrearEjercicio.setBounds(22, 548, 157, 25);
		frame.getContentPane().add(btnCrearEjercicio);
		
		JButton btnBorrarEjercicio = new JButton("Borrar Ejercicio");
		btnBorrarEjercicio.setBounds(304, 548, 171, 25);
		frame.getContentPane().add(btnBorrarEjercicio);
		
		JButton btnActualizarEjercicio = new JButton("Actualizar Ejercicio");
		btnActualizarEjercicio.setBounds(146, 585, 200, 25);
		frame.getContentPane().add(btnActualizarEjercicio);
		
		//cliente
		
		JButton btnCrearCliente = new JButton("Crear Cliente");
		btnCrearCliente.setBounds(616, 214, 171, 25);
		frame.getContentPane().add(btnCrearCliente);
		
		JButton btnBorrarCliente = new JButton("Borrar Cliente");
		btnBorrarCliente.setBounds(927, 214, 157, 25);
		frame.getContentPane().add(btnBorrarCliente);
		
		JButton btnActualizarProducto = new JButton("Actualizar Producto");
		btnActualizarProducto.setBounds(770, 251, 184, 25);
		frame.getContentPane().add(btnActualizarProducto);
	/*	
		//Decoradores
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(0, 332, 556, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 0, 0));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(555, 0, 2, 820);
		frame.getContentPane().add(separator_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.darkShadow"));
		panel.setBounds(0, 0, 556, 334);
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("Button.focus"));
		panel_1.setBounds(0, 332, 556, 488);
		frame.getContentPane().add(panel_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(new Color(0, 0, 0));
		separator_2.setBounds(557, 428, 683, 2);
		frame.getContentPane().add(separator_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(UIManager.getColor("Button.shadow"));
		panel_2.setBounds(555, 0, 685, 430);
		frame.getContentPane().add(panel_2);
		
		*/
		
		//Mostrar las tres tablas
				refrescarEntrenador();
				refrescarCliente();
				refrescarEjercicio();
		


 
	}
}
