package com.hibernate;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;
import com.hibernate.dao.ClienteDAO;
import com.hibernate.model.Ejercicio;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Image;

public class App {

	private JFrame frame;
	private JTextField txtId;
	private JTextField txtNom;
	private JTextField txtTemp;
	private JTextField txtCap;
	private DefaultTableModel modelEntrenador;
	private DefaultTableModel modelCliente;
	private DefaultTableModel modelEjercicio;
	private DefaultTableModel modelClienteEjercicio;
	private EntrenadorDAO entrenadorDAO;
	private ClienteDAO clienteDAO;
	private EjercicioDAO ejercicioDAO;
	private Cliente cliente;
	private List<Entrenador> entrenadores;
	private List<Cliente> clientes;
	private List<Ejercicio> ejercicios;
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
	private JTextField txtIdEntrenador;
	private JTextField txtidEjercicio;
	private JTextField txtIdCliente;
	private Blob img;
	private JLabel lblFotoEj;

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
		List<Entrenador> entrenadores = null;
		modelEntrenador.setRowCount(0);
		entrenadores = entrenadorDAO.selectAllEntrenador();
		entrenadores.forEach(s -> {
			Object[] row = new Object[3];
			row[0] = s.getIdEntrenador();
			row[1] = s.getNomEntrenador();
			row[2] = s.getNumClientes();
			modelEntrenador.addRow(row);
		});

	}

	public void refrescarClienteEjercicio(Cliente cliente) {

		List<Ejercicio> clientesEjer = null;
		modelClienteEjercicio.setRowCount(0);
		clientesEjer = cliente.getEjercicios();
		for (Ejercicio e : clientesEjer) {
			Object[] row = new Object[6];
			row[0] = e.getIdEjercicio();
			row[1] = e.getNomEjercicio();
			row[2] = e.getPeso();
			row[3] = e.getSeries();
			row[4] = e.getReps();
			row[5] = e.getDescanso();
			modelClienteEjercicio.addRow(row);
		}

	}
	
	public void refrescarCliente() {
		List<Cliente> clientes = null;
		modelCliente.setRowCount(0);
		clientes = clienteDAO.selectAllCliente();
		clientes.forEach(s -> {
			Object[] row = new Object[7];
			row[0] = s.getIdCliente();
			row[1] = s.getNomCliente();
			row[2] = s.getApeCliente();
			row[3] = s.getFechaNac();
			row[4] = s.getLesiones();
			row[5] = s.getObjetivo();
			row[6] = s.getIdEntrenador();

			modelCliente.addRow(row);
		});

	}

	public void refrescarEjercicio() {
		List<Ejercicio> ejercicios = null;
		modelEjercicio.setRowCount(0);
		ejercicios = ejercicioDAO.selectAllEjercicio();
		ejercicios.forEach(s -> {
			Object[] row = new Object[7];
			row[0] = s.getIdEjercicio();
			row[1] = s.getNomEjercicio();
			row[2] = s.getPeso();
			row[3] = s.getSeries();
			row[4] = s.getReps();
			row[5] = s.getDescanso();
			row[6] = s.getDificultad();
			modelEjercicio.addRow(row);
		});
	}

	public void limpiarCliente() {
		txtIdCliente.setText("");
		txtNombreCliente.setText("");
		txtApellidosCliente.setText("");
		txtFechaNacCliente.setText("");
		txtLesionesCliente.setText("");
		txtObjCliente.setText("");
	}

	public void limpiarEntrenador() {
		txtIdEntrenador.setText("");
		txtNombreEntrenador.setText("");

	}

	public void limpiarEjercicio() {
		txtidEjercicio.setText("");
		txtNombreEjercicio.setText("");
		txtPesoEjercicio.setText("");
		txtSeriesEjercicio.setText("");
		txtRepsEjercicio.setText("");
		txtDescansoEjercicio.setText("");
		txtdificultadEjercicio.setText("");
		txtFoto.setText("");
	}
	
	 boolean comprobarExpReg(String nombre, String er) {
			Pattern pat = Pattern.compile(er);
			Matcher mat = pat.matcher(nombre);
			if (mat.matches()) {
				return true;
			} else {
				return false;
			}
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
	// variables
	String nomEntrenador="";
	int idEntrenador;
	int idEjercicio;
	int idCliente;
	String nomEjercicio="";
	double pesoEjercicio=0;
	int seriesEjercicio=0;
	int repsEjercicio=0;
	String descansoEjercicio="";
	String dificultadEjercicio="";
	String nomCliente="";
	String apsCliente="";
	String fecaNacCliente="";
	String lesionesCliente="";
	String objCliente="";
	private JTable table;
	private JTextField txtNumClientes;
	int numClientes;
	int comprobacion = 0;
	String idEjLista;
	int idEjListaInt;
	private JTextField txtFoto;

	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 1250, 830);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		entrenadorDAO = new EntrenadorDAO();
		entrenadores = entrenadorDAO.selectAllEntrenador();

		clienteDAO = new ClienteDAO();
		clientes = clienteDAO.selectAllCliente();

		ejercicioDAO = new EjercicioDAO();
		ejercicios = ejercicioDAO.selectAllEjercicio();
		
		 lblFotoEj = new JLabel("");
		lblFotoEj.setBounds(510, 366, 200, 190);
		frame.getContentPane().add(lblFotoEj);
		
		JLabel lblClEj = new JLabel("");
		lblClEj.setFont(new Font("Dialog", Font.BOLD, 17));
		lblClEj.setForeground(Color.RED);
		lblClEj.setBounds(772, 394, 410, 38);
		frame.getContentPane().add(lblClEj);

		// Columnas de las 3 tablas entrenador, cliente, ejercicios

		// Tabla ejercicioCliente
		modelClienteEjercicio = new DefaultTableModel() {
			public boolean isCellEditable(int row, int colum) {
				return false;
			}
		};
		modelClienteEjercicio.addColumn("ID");
		modelClienteEjercicio.addColumn("Ejercicios");
		modelClienteEjercicio.addColumn("Peso");
		modelClienteEjercicio.addColumn("Series");
		modelClienteEjercicio.addColumn("Repeticiones");
		modelClienteEjercicio.addColumn("Descanso");
		;
		// Entrenador

		modelEntrenador = new DefaultTableModel() {
			public boolean isCellEditable(int row, int colum) {
				return false;
		
				
			}
		};
		modelEntrenador.addColumn("ID");
		modelEntrenador.addColumn("Nombre");
		modelEntrenador.addColumn("Clientes");

		// Cliente
		modelCliente = new DefaultTableModel() {
			public boolean isCellEditable(int row, int colum) {
				return false;
			}
		};
		modelCliente.addColumn("ID");
		modelCliente.addColumn("Nombre");
		modelCliente.addColumn("Apellidos");
		modelCliente.addColumn("Fecha Nacimiento");
		modelCliente.addColumn("Lesiones");
		modelCliente.addColumn("Objetivo");
		modelCliente.addColumn("idEntrenador");
		
		// Ejercicio
		modelEjercicio = new DefaultTableModel() {
			public boolean isCellEditable(int row, int colum) {
				return false;
			}
		};
		modelEjercicio.addColumn("ID");
		modelEjercicio.addColumn("Nombre");
		modelEjercicio.addColumn("Peso");
		modelEjercicio.addColumn("Series");
		modelEjercicio.addColumn("Repeticiones");
		modelEjercicio.addColumn("Descanso");
		modelEjercicio.addColumn("Dificultad");

		// Las tres tablas con los scrollpane

		// ClienteEjercicio
		JTable tableClienteEjercicio = new JTable(modelClienteEjercicio);
		frame.getContentPane().setLayout(null);
		tableClienteEjercicio.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane3 = new JScrollPane(tableClienteEjercicio);
		scrollPane3.setBounds(772, 440, 440, 196);
		frame.getContentPane().add(scrollPane3);


		// Tabla entrenador
		JTable tableEntrenador = new JTable(modelEntrenador);
		tableEntrenador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableEntrenador.getSelectedRow();
				TableModel modelEntrenador = tableEntrenador.getModel();
				txtIdEntrenador.setText(modelEntrenador.getValueAt(index, 0).toString());
				txtNombreEntrenador.setText(modelEntrenador.getValueAt(index, 1).toString());
				txtNumClientes.setText(modelEntrenador.getValueAt(index, 2).toString());
			}
		});
		frame.getContentPane().setLayout(null);
		tableEntrenador.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(tableEntrenador);
		scrollPane.setBounds(12, 12, 481, 190);
		frame.getContentPane().add(scrollPane);

		// Tabla cliente
		JTable tableCliente = new JTable(modelCliente);
		tableCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int index = tableCliente.getSelectedRow();
				TableModel modelCliente = tableCliente.getModel();
				txtIdCliente.setText(modelCliente.getValueAt(index, 0).toString());
				txtNombreCliente.setText(modelCliente.getValueAt(index, 1).toString());
				txtApellidosCliente.setText(modelCliente.getValueAt(index, 2).toString());
				txtFechaNacCliente.setText(modelCliente.getValueAt(index, 3).toString());
				txtLesionesCliente.setText(modelCliente.getValueAt(index, 4).toString());
				txtObjCliente.setText(modelCliente.getValueAt(index, 5).toString());

				idCliente = Integer.parseInt(txtIdCliente.getText());
				Cliente cliente = clienteDAO.selectClienteById(idCliente);
				refrescarClienteEjercicio(cliente);
				lblClEj.setText("Rutina del Cliente: " + cliente.getNomCliente());
				
				
			}
		});
		frame.getContentPane().setLayout(null);
		tableCliente.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane_1 = new JScrollPane(tableCliente);
		scrollPane_1.setBounds(749, 12, 463, 190);
		frame.getContentPane().add(scrollPane_1);

		// Tabla ejercicio
		JTable tableEjercicio = new JTable(modelEjercicio);
		tableEjercicio.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableEjercicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableEjercicio.getSelectedRow();
				TableModel modelEjercicio = tableEjercicio.getModel();
				txtidEjercicio.setText(modelEjercicio.getValueAt(index, 0).toString());
				txtNombreEjercicio.setText(modelEjercicio.getValueAt(index, 1).toString());
				txtPesoEjercicio.setText(modelEjercicio.getValueAt(index, 2).toString());
				txtSeriesEjercicio.setText(modelEjercicio.getValueAt(index, 3).toString());
				txtRepsEjercicio.setText(modelEjercicio.getValueAt(index, 4).toString());
				txtDescansoEjercicio.setText(modelEjercicio.getValueAt(index, 5).toString());
				txtdificultadEjercicio.setText(modelEjercicio.getValueAt(index, 6).toString());
				int idEjercicio=(int)modelEjercicio.getValueAt(index, 0);
				Ejercicio ejercicio=ejercicioDAO.selectEjercicioById(idEjercicio);
				
				try {

					img = ejercicio.getFotoEjercicio();

					byte[] fotoByte = img.getBytes(1, (int) img.length());

					ImageIcon imageIcon = new ImageIcon(fotoByte);

					Image image = imageIcon.getImage();

					image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);

					ImageIcon resizedImage = new ImageIcon(image);

					lblFotoEj.setIcon(resizedImage);

					lblFotoEj.revalidate();

				} catch (Exception imgException) {



				}
			}
		});
		frame.getContentPane().setLayout(null);
		JScrollPane scrollPane_2 = new JScrollPane(tableEjercicio);
		scrollPane_2.setBounds(12, 366, 463, 190);
		frame.getContentPane().add(scrollPane_2);

		// labels y txt

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(84, 317, 70, 15);
		frame.getContentPane().add(lblNombre);

		txtNombreEntrenador = new JTextField();
		txtNombreEntrenador.setBounds(159, 315, 171, 19);
		frame.getContentPane().add(txtNombreEntrenador);
		txtNombreEntrenador.setColumns(10);

		JLabel lblNombre_1 = new JLabel("Nombre:");
		lblNombre_1.setBounds(27, 668, 70, 15);
		frame.getContentPane().add(lblNombre_1);

		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setBounds(37, 695, 70, 15);
		frame.getContentPane().add(lblPeso);

		JLabel lblSeries = new JLabel("Series:");
		lblSeries.setBounds(27, 722, 70, 15);
		frame.getContentPane().add(lblSeries);

		JLabel lblRepeticiones = new JLabel("Repeticiones:");
		lblRepeticiones.setBounds(247, 668, 109, 15);
		frame.getContentPane().add(lblRepeticiones);

		JLabel lblDescanso = new JLabel("Descanso:");
		lblDescanso.setBounds(273, 695, 83, 15);
		frame.getContentPane().add(lblDescanso);

		JLabel lblDificultad = new JLabel("Dificultad:");
		lblDificultad.setBounds(273, 722, 83, 15);
		frame.getContentPane().add(lblDificultad);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(749, 319, 70, 15);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(749, 346, 70, 15);
		frame.getContentPane().add(lblApellidos);

		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
		lblFechaNacimiento.setBounds(963, 290, 130, 15);
		frame.getContentPane().add(lblFechaNacimiento);

		lblLesiones = new JLabel("Lesiones:");
		lblLesiones.setBounds(1023, 317, 70, 15);
		frame.getContentPane().add(lblLesiones);

		JLabel lblId = new JLabel("id:");
		lblId.setBounds(84, 288, 70, 15);
		frame.getContentPane().add(lblId);

		JLabel lblId_1 = new JLabel("id:");
		lblId_1.setBounds(159, 639, 70, 15);
		frame.getContentPane().add(lblId_1);

		JLabel lblId_2 = new JLabel("id:");
		lblId_2.setBounds(749, 292, 70, 15);
		frame.getContentPane().add(lblId_2);

		JLabel lblObjetivo = new JLabel("Objetivo:");
		lblObjetivo.setBounds(1023, 346, 70, 15);
		frame.getContentPane().add(lblObjetivo);

		txtNombreEjercicio = new JTextField();
		txtNombreEjercicio.setBounds(115, 666, 114, 19);
		frame.getContentPane().add(txtNombreEjercicio);
		txtNombreEjercicio.setColumns(10);

		txtPesoEjercicio = new JTextField();
		txtPesoEjercicio.setBounds(115, 693, 114, 19);
		frame.getContentPane().add(txtPesoEjercicio);
		txtPesoEjercicio.setColumns(10);

		txtSeriesEjercicio = new JTextField();
		txtSeriesEjercicio.setBounds(115, 720, 114, 19);
		frame.getContentPane().add(txtSeriesEjercicio);
		txtSeriesEjercicio.setColumns(10);

		txtRepsEjercicio = new JTextField();
		txtRepsEjercicio.setBounds(361, 666, 114, 19);
		frame.getContentPane().add(txtRepsEjercicio);
		txtRepsEjercicio.setColumns(10);

		txtDescansoEjercicio = new JTextField();
		txtDescansoEjercicio.setBounds(361, 695, 114, 19);
		frame.getContentPane().add(txtDescansoEjercicio);
		txtDescansoEjercicio.setColumns(10);

		txtFoto = new JTextField();
		txtFoto.setEditable(false);
		txtFoto.setBounds(247, 766, 114, 19);
		frame.getContentPane().add(txtFoto);
		txtFoto.setColumns(10);
		
		txtNumClientes = new JTextField();
		txtNumClientes.setEditable(false);
		txtNumClientes.setBounds(159, 344, 171, 19);
		frame.getContentPane().add(txtNumClientes);
		txtNumClientes.setColumns(10);

		txtdificultadEjercicio = new JTextField();
		txtdificultadEjercicio.setBounds(361, 720, 114, 19);
		frame.getContentPane().add(txtdificultadEjercicio);
		txtdificultadEjercicio.setColumns(10);

		txtNombreCliente = new JTextField();
		txtNombreCliente.setBounds(837, 315, 114, 19);
		frame.getContentPane().add(txtNombreCliente);
		txtNombreCliente.setColumns(10);

		txtApellidosCliente = new JTextField();
		txtApellidosCliente.setBounds(837, 344, 114, 19);
		frame.getContentPane().add(txtApellidosCliente);
		txtApellidosCliente.setColumns(10);

		txtFechaNacCliente = new JTextField();
		txtFechaNacCliente.setBounds(1106, 288, 114, 19);
		frame.getContentPane().add(txtFechaNacCliente);
		txtFechaNacCliente.setColumns(10);

		txtLesionesCliente = new JTextField();
		txtLesionesCliente.setBounds(1106, 315, 114, 19);
		frame.getContentPane().add(txtLesionesCliente);
		txtLesionesCliente.setColumns(10);

		txtObjCliente = new JTextField();
		txtObjCliente.setBounds(1106, 342, 114, 19);
		frame.getContentPane().add(txtObjCliente);
		txtObjCliente.setColumns(10);

		txtIdEntrenador = new JTextField();
		txtIdEntrenador.setEditable(false);
		txtIdEntrenador.setBounds(159, 284, 171, 19);
		frame.getContentPane().add(txtIdEntrenador);
		txtIdEntrenador.setColumns(10);

		txtidEjercicio = new JTextField();
		txtidEjercicio.setEditable(false);
		txtidEjercicio.setBounds(221, 637, 114, 19);
		frame.getContentPane().add(txtidEjercicio);
		txtidEjercicio.setColumns(10);

		txtIdCliente = new JTextField();
		txtIdCliente.setEditable(false);
		txtIdCliente.setBounds(837, 288, 114, 19);
		frame.getContentPane().add(txtIdCliente);
		txtIdCliente.setColumns(10);

		// botones
		// entrenador
		JButton btnCrearEntrenador = new JButton("Crear Entrenador");
		btnCrearEntrenador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				nomEntrenador = txtNombreEntrenador.getText();
				
				if (!comprobarExpReg(nomEntrenador, ".*[a-zA-Z].*")) {
		            JOptionPane.showMessageDialog(null, "Has introducido mal el nombre, intentalo de nuevo", "Nombre mal introducido", JOptionPane.WARNING_MESSAGE);

		 }else {
				
				Entrenador entrenador = new Entrenador(nomEntrenador, 0);
				entrenadorDAO.insertEntrenador(entrenador);
				refrescarEntrenador();
				limpiarEntrenador();
				JOptionPane.showMessageDialog(frame,"Entrenador creado");
			}
			}
		});
		btnCrearEntrenador.setBounds(12, 214, 157, 25);
		frame.getContentPane().add(btnCrearEntrenador);

		JButton btnBorrarEntrenador = new JButton("Borrar Entrenador");
		btnBorrarEntrenador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				idEntrenador = Integer.parseInt(txtIdEntrenador.getText());
				entrenadorDAO.deleteEntrenador(idEntrenador);
				refrescarEntrenador();
				limpiarEntrenador();
				JOptionPane.showMessageDialog(frame,"Entrenador borrado");
			}
		});
		btnBorrarEntrenador.setBounds(322, 214, 171, 25);
		frame.getContentPane().add(btnBorrarEntrenador);

		JButton btnActualizarEntrenador = new JButton("Actualizar Entrenador");
		btnActualizarEntrenador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idEntrenador = Integer.parseInt(txtIdEntrenador.getText());
				nomEntrenador = txtNombreEntrenador.getText();
				if (!comprobarExpReg(nomEntrenador, ".*[a-zA-Z].*")) {
		            JOptionPane.showMessageDialog(null, "Has introducido mal el nombre, minimo debe tener una letra", "Nombre mal introducido", JOptionPane.WARNING_MESSAGE);

		 }else {
				Entrenador entrenador2 = entrenadorDAO.selectEntrenadorById(idEntrenador);
				entrenador2.setNomEntrenador(nomEntrenador);

				entrenadorDAO.updateEntrenador(entrenador2);

				refrescarEntrenador();
				limpiarEntrenador();
				JOptionPane.showMessageDialog(frame,"Entrenador actualizado");
		 }
			}
		});
		btnActualizarEntrenador.setBounds(146, 251, 200, 25);
		frame.getContentPane().add(btnActualizarEntrenador);

		// ejercicio

		JButton btnCrearEjercicio = new JButton("Crear Ejercicio");
		btnCrearEjercicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
nomEjercicio="";
				nomEjercicio = txtNombreEjercicio.getText();
				pesoEjercicio = Double.parseDouble(txtPesoEjercicio.getText());
				seriesEjercicio = Integer.parseInt(txtSeriesEjercicio.getText());
				repsEjercicio = Integer.parseInt(txtRepsEjercicio.getText());
				descansoEjercicio = txtDescansoEjercicio.getText();
				dificultadEjercicio = txtdificultadEjercicio.getText();
				String textFoto= txtFoto.getText();
				byte[] fotoB; 
		
				try {

					fotoB = Files.readAllBytes(Paths.get(textFoto));

					img = new com.mysql.cj.jdbc.Blob(fotoB, null);

				} catch (IOException e3) {

				}
				
				Ejercicio ejercicio = new Ejercicio(nomEjercicio, pesoEjercicio, seriesEjercicio, repsEjercicio,
				descansoEjercicio, dificultadEjercicio,img);
				ejercicioDAO.insertEjercicio(ejercicio);
				refrescarEjercicio();
				limpiarEjercicio();
				JOptionPane.showMessageDialog(frame,"Ejercicio creado");
				
			}
		});
		btnCrearEjercicio.setBounds(12, 568, 157, 25);
		frame.getContentPane().add(btnCrearEjercicio);

		JButton btnBorrarEjercicio = new JButton("Borrar Ejercicio");
		btnBorrarEjercicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				idEjercicio = Integer.parseInt(txtidEjercicio.getText());
				ejercicioDAO.deleteEjercicio(idEjercicio);
				refrescarEjercicio();
				limpiarEjercicio();
				refrescarClienteEjercicio(cliente);
			}
		});
		btnBorrarEjercicio.setBounds(304, 568, 171, 25);
		frame.getContentPane().add(btnBorrarEjercicio);

		JButton btnActualizarEjercicio = new JButton("Actualizar Ejercicio");
		btnActualizarEjercicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				idEjercicio = Integer.parseInt(txtidEjercicio.getText());
				nomEjercicio = txtNombreEjercicio.getText();
				pesoEjercicio = Double.parseDouble(txtPesoEjercicio.getText());
				seriesEjercicio = Integer.parseInt(txtSeriesEjercicio.getText());
				repsEjercicio = Integer.parseInt(txtRepsEjercicio.getText());
				descansoEjercicio = txtDescansoEjercicio.getText();
				dificultadEjercicio = txtdificultadEjercicio.getText();
				String textFoto= txtFoto.getText();
				byte[] fotoB; 
				try {

					fotoB = Files.readAllBytes(Paths.get(textFoto));

					img = new com.mysql.cj.jdbc.Blob(fotoB, null);

				} catch (IOException e3) {

				}
				Ejercicio ejercicio2 = ejercicioDAO.selectEjercicioById(idEjercicio);
				ejercicio2.setNomEjercicio(nomEjercicio);
				ejercicio2.setPeso(pesoEjercicio);
				ejercicio2.setSeries(seriesEjercicio);
				ejercicio2.setReps(repsEjercicio);
				ejercicio2.setDescanso(descansoEjercicio);
				ejercicio2.setDificultad(dificultadEjercicio);
				ejercicio2.setFotoEjercicio(img);
				ejercicioDAO.updateEjercicio(ejercicio2);

				refrescarEjercicio();
				limpiarEjercicio();
				refrescarClienteEjercicio(cliente);
			}
		});
		btnActualizarEjercicio.setBounds(146, 605, 200, 25);
		frame.getContentPane().add(btnActualizarEjercicio);

		// cliente

		JButton btnCrearCliente = new JButton("Crear Cliente");
		btnCrearCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				nomCliente = txtNombreCliente.getText();
				apsCliente = txtApellidosCliente.getText();
				fecaNacCliente = txtFechaNacCliente.getText();
				lesionesCliente = txtLesionesCliente.getText();
				objCliente = txtObjCliente.getText();

				Cliente cliente = new Cliente(nomCliente, apsCliente, fecaNacCliente, lesionesCliente, objCliente,
						100000);
				clienteDAO.insertCliente(cliente);
				refrescarCliente();
				limpiarCliente();

			}
		});
		btnCrearCliente.setBounds(749, 214, 171, 25);
		frame.getContentPane().add(btnCrearCliente);

		JButton btnBorrarCliente = new JButton("Borrar Cliente");
		btnBorrarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				idCliente = Integer.parseInt(txtIdCliente.getText());
				clienteDAO.deleteCliente(idCliente);
				refrescarCliente();
				limpiarCliente();

			}
		});
		btnBorrarCliente.setBounds(1060, 214, 157, 25);
		frame.getContentPane().add(btnBorrarCliente);

		JButton btnActualizarCliente = new JButton("Actualizar Cliente");
		btnActualizarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				idCliente = Integer.parseInt(txtIdCliente.getText());
				nomCliente = txtNombreCliente.getText();
				apsCliente = txtApellidosCliente.getText();
				fecaNacCliente = txtApellidosCliente.getText();
				lesionesCliente = txtLesionesCliente.getText();
				objCliente = txtObjCliente.getText();

				Cliente cliente2 = clienteDAO.selectClienteById(idCliente);
				cliente2.setNomCliente(nomCliente);
				cliente2.setApeCliente(apsCliente);
				cliente2.setFechaNac(fecaNacCliente);
				cliente2.setLesiones(lesionesCliente);
				cliente2.setObjetivo(objCliente);

				clienteDAO.updateCliente(cliente2);

				refrescarCliente();
				limpiarCliente();
			}
		});
		btnActualizarCliente.setBounds(903, 251, 184, 25);
		frame.getContentPane().add(btnActualizarCliente);

		JButton btnAsignarRutina = new JButton("Añadir Ejercicio");
		btnAsignarRutina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e2) {
				comprobacion = 0;
				idCliente = Integer.parseInt(txtIdCliente.getText());
				idEjercicio = Integer.parseInt(txtidEjercicio.getText());

				Cliente cliente = clienteDAO.selectClienteById(idCliente);
				Ejercicio ejercicio = ejercicioDAO.selectEjercicioById(idEjercicio);

				cliente.getEjercicios();
			
				List<Ejercicio> ejercicios = cliente.getEjercicios();

				for (Ejercicio ejercicioLista : ejercicios) {

					if (String.valueOf(ejercicioLista.getIdEjercicio()).equals(String.valueOf(ejercicio.getIdEjercicio()))) {
						comprobacion++;
					}
				}
				if (comprobacion == 0) {
					cliente.anyadirEjercicio(ejercicio);

					clienteDAO.updateCliente(cliente);

					refrescarClienteEjercicio(cliente);
				} else {
					JOptionPane.showMessageDialog(null, "Este ejercicio ya esta en la rutina del usuario", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnAsignarRutina.setBounds(772, 644, 157, 25);
		frame.getContentPane().add(btnAsignarRutina);
		// ME quedo por aqui falta eliminar el ejercicio de la lista
		JButton btnEliminarRutina = new JButton("Eliminar Ejercicio");
		btnEliminarRutina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index= tableClienteEjercicio.getSelectedRow();
				int idEjercicioE=(int)(modelClienteEjercicio.getValueAt(index, 0));
				TableModel modelClienteEjercicio = tableClienteEjercicio.getModel();
				
				Cliente cliente = clienteDAO.selectClienteById(Integer.parseInt(txtIdCliente.getText()));
				Ejercicio ejercicio = ejercicioDAO.selectEjercicioById(idEjercicioE);

				cliente.getEjercicios().removeIf(c -> String.valueOf(c.getIdEjercicio()).equals(String.valueOf(idEjercicioE)) );
				List<Ejercicio> clientesEjer = null;
				
				clienteDAO.updateCliente(cliente);
				refrescarClienteEjercicio(cliente);
			}
		});
		btnEliminarRutina.setBounds(1055, 648, 157, 25);
		frame.getContentPane().add(btnEliminarRutina);

		JButton btnAsignarEntrenador = new JButton("Asignar Cliente");
		btnAsignarEntrenador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				idCliente = Integer.parseInt(txtIdCliente.getText());
				idEntrenador = Integer.parseInt(txtIdEntrenador.getText());
				Cliente cl = clienteDAO.selectClienteById(idCliente);

				if (cl.getIdEntrenador() != 100000) {

					JOptionPane.showMessageDialog(null, "Este cliente ya tiene asigando un entrenador", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else {
					Cliente cliente = clienteDAO.selectClienteById(idCliente);
					cliente.setIdEntrenador(idEntrenador);
					clienteDAO.updateCliente(cliente);

					Entrenador entrenador = entrenadorDAO.selectEntrenadorById(idEntrenador);

					numClientes = entrenador.getNumClientes();
					numClientes++;
					

					entrenador.setNumClientes(numClientes);
					entrenadorDAO.updateEntrenador(entrenador);

					refrescarCliente();
					refrescarEntrenador();
					limpiarCliente();
					limpiarEntrenador();
				}
			}
		});
		btnAsignarEntrenador.setBounds(531, 50, 179, 25);
		frame.getContentPane().add(btnAsignarEntrenador);

		JButton btnDesasignarCliente = new JButton("Desasignar Cliente");
		btnDesasignarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idCliente = Integer.parseInt(txtIdCliente.getText());
				Cliente cliente = clienteDAO.selectClienteById(idCliente);

				if (cliente.getIdEntrenador() == 100000) {

					JOptionPane.showMessageDialog(null, "Este cliente aun no tiene entrenador", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else {
					idEntrenador = cliente.getIdEntrenador();
					Entrenador entrenador = entrenadorDAO.selectEntrenadorById(idEntrenador);
					entrenador.setNumClientes(entrenador.getNumClientes() - 1);
					cliente.setIdEntrenador(100000);
					clienteDAO.updateCliente(cliente);
					entrenadorDAO.updateEntrenador(entrenador);
					refrescarEntrenador();
					refrescarCliente();
					limpiarCliente();
					limpiarEntrenador();
				}
			}
		});
		btnDesasignarCliente.setBounds(531, 87, 179, 25);
		frame.getContentPane().add(btnDesasignarCliente);

		JLabel lblNewLabel_1 = new JLabel("Num clientes:");
		lblNewLabel_1.setBounds(54, 344, 96, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnFoto = new JButton("Seleccionar Foto");
		btnFoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JFileChooser chooser = new JFileChooser();

				chooser.showOpenDialog(null);

				if (chooser.getSelectedFile() != null) {

					java.io.File f = chooser.getSelectedFile();

					String fileName = f.getAbsolutePath();

					txtFoto.setText(fileName);

				}
			}
		});
		
		
		
		
		btnFoto.setBounds(54, 763, 175, 25);
		frame.getContentPane().add(btnFoto);
		
		
		
		

		/*
		 * //Decoradores JSeparator separator = new JSeparator();
		 * separator.setBackground(new Color(0, 0, 0)); separator.setBounds(0, 332, 556,
		 * 2); frame.getContentPane().add(separator);
		 * 
		 * 
		 * JSeparator separator_1 = new JSeparator(); separator_1.setBackground(new
		 * Color(0, 0, 0)); separator_1.setOrientation(SwingConstants.VERTICAL);
		 * separator_1.setBounds(555, 0, 2, 820);
		 * frame.getContentPane().add(separator_1);
		 * 
		 * 
		 * JPanel panel = new JPanel();
		 * panel.setBackground(UIManager.getColor("Button.darkShadow"));
		 * panel.setBounds(0, 0, 556, 334); frame.getContentPane().add(panel);
		 * 
		 * 
		 * JPanel panel_1 = new JPanel();
		 * panel_1.setBackground(UIManager.getColor("Button.focus"));
		 * panel_1.setBounds(0, 332, 556, 488); frame.getContentPane().add(panel_1);
		 * 
		 * 
		 * JSeparator separator_2 = new JSeparator(); separator_2.setBackground(new
		 * Color(0, 0, 0)); separator_2.setBounds(567, 474, 683, 2);
		 * frame.getContentPane().add(separator_2);
		 * 
		 * 
		 * JPanel panel_2 = new JPanel();
		 * panel_2.setBackground(UIManager.getColor("Button.shadow"));
		 * panel_2.setBounds(555, 0, 685, 476); frame.getContentPane().add(panel_2);
		 */

		// Mostrar las tres tablas

		refrescarCliente();
		refrescarEjercicio();
		refrescarEntrenador();

	}
}
