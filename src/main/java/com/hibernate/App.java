package com.hibernate;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Box;

public class App {

	private JFrame frmGimnasio;
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
	private JComboBox comboBoxDif;
	JTable tableClienteEjercicio;
	JTable tableEntrenador;
	JTable tableCliente;
	JTable tableEjercicio;

	// Aplicacion

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmGimnasio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

//Funciones
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
		txtNumClientes.setText("");

	}

	public void limpiarEjercicio() {
		txtidEjercicio.setText("");
		txtNombreEjercicio.setText("");
		txtPesoEjercicio.setText("");
		txtSeriesEjercicio.setText("");
		txtRepsEjercicio.setText("");
		txtDescansoEjercicio.setText("");
		txtFoto.setText("");
	}

	public boolean comprobarExpReg(String nombre, String er) {
		Pattern pat = Pattern.compile(er);
		Matcher mat = pat.matcher(nombre);
		if (mat.matches()) {
			return true;
		} else {
			return false;
		}
	}

	private void centrartabla(JTable table) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < table.getColumnCount(); i++) {

			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

		}
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
		centrartabla(tableClienteEjercicio);
		centrartabla(tableCliente);
		centrartabla(tableEjercicio);
		centrartabla(tableEntrenador);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	// variables
	String nomEntrenador = "";
	int idEntrenador;
	int idEjercicio;
	int idCliente;
	String nomEjercicio = "";
	double pesoEjercicio = 0;
	int seriesEjercicio = 0;
	int repsEjercicio = 0;
	String descansoEjercicio = "";
	String dificultadEjercicio = "";
	String nomCliente = "";
	String apsCliente = "";
	String fecaNacCliente = "";
	String lesionesCliente = "";
	String objCliente = "";
	private JTable table;
	private JTextField txtNumClientes;
	int numClientes;
	int comprobacion = 0;
	String idEjLista;
	int idEjListaInt;
	private JTextField txtFoto;

	private void initialize() {

		frmGimnasio = new JFrame();
		frmGimnasio.setTitle("Gimnasio");
		frmGimnasio.getContentPane().setEnabled(false);
		frmGimnasio.setBounds(100, 100, 1270, 870);
		frmGimnasio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		entrenadorDAO = new EntrenadorDAO();
		entrenadores = entrenadorDAO.selectAllEntrenador();

		clienteDAO = new ClienteDAO();
		clientes = clienteDAO.selectAllCliente();

		ejercicioDAO = new EjercicioDAO();
		ejercicios = ejercicioDAO.selectAllEjercicio();

		lblFotoEj = new JLabel("");
		lblFotoEj.setBounds(510, 366, 200, 190);
		frmGimnasio.getContentPane().add(lblFotoEj);

		JLabel lblClEj = new JLabel("");
		lblClEj.setFont(new Font("Liberation Mono", Font.BOLD, 20));
		lblClEj.setForeground(Color.DARK_GRAY);
		lblClEj.setBounds(1010, 481, 238, 38);
		frmGimnasio.getContentPane().add(lblClEj);

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
		tableClienteEjercicio = new JTable(modelClienteEjercicio);
		frmGimnasio.getContentPane().setLayout(null);
		tableClienteEjercicio.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane3 = new JScrollPane(tableClienteEjercicio);
		scrollPane3.setBounds(780, 521, 440, 196);
		frmGimnasio.getContentPane().add(scrollPane3);

		// Tabla entrenador
		tableEntrenador = new JTable(modelEntrenador);
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
		frmGimnasio.getContentPane().setLayout(null);
		tableEntrenador.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(tableEntrenador);
		scrollPane.setBounds(43, 47, 455, 190);
		frmGimnasio.getContentPane().add(scrollPane);

		// Tabla cliente
		tableCliente = new JTable(modelCliente);
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
				lblClEj.setText(cliente.getNomCliente());

			}
		});
		frmGimnasio.getContentPane().setLayout(null);
		tableCliente.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane_1 = new JScrollPane(tableCliente);
		scrollPane_1.setBounds(788, 47, 455, 190);
		frmGimnasio.getContentPane().add(scrollPane_1);

		// Tabla ejercicio
		tableEjercicio = new JTable(modelEjercicio);
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
				comboBoxDif.setSelectedItem(modelEjercicio.getValueAt(index, 6).toString());
				int idEjercicio = (int) modelEjercicio.getValueAt(index, 0);
				Ejercicio ejercicio = ejercicioDAO.selectEjercicioById(idEjercicio);
// se selecciona la imagen y la muestra por pantalla
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
		frmGimnasio.getContentPane().setLayout(null);
		JScrollPane scrollPane_2 = new JScrollPane(tableEjercicio);
		scrollPane_2.setBounds(43, 438, 463, 190);
		frmGimnasio.getContentPane().add(scrollPane_2);

		// labels y txt

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(159, 349, 70, 15);
		frmGimnasio.getContentPane().add(lblNombre);

		txtNombreEntrenador = new JTextField();
		txtNombreEntrenador.setBounds(234, 347, 171, 19);
		frmGimnasio.getContentPane().add(txtNombreEntrenador);
		txtNombreEntrenador.setColumns(10);

		JLabel lblNombre_1 = new JLabel("Nombre:");
		lblNombre_1.setBounds(12, 735, 70, 15);
		frmGimnasio.getContentPane().add(lblNombre_1);

		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setBounds(22, 762, 70, 15);
		frmGimnasio.getContentPane().add(lblPeso);

		JLabel lblSeries = new JLabel("Series:");
		lblSeries.setBounds(12, 789, 70, 15);
		frmGimnasio.getContentPane().add(lblSeries);

		JLabel lblNewLabel_2 = new JLabel("Entrenadores");
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setFont(new Font("Liberation Mono", Font.BOLD, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(43, 12, 481, 31);
		frmGimnasio.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Clientes");
		lblNewLabel_2_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_2_1.setFont(new Font("Liberation Mono", Font.BOLD, 20));
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setBounds(778, 12, 465, 31);
		frmGimnasio.getContentPane().add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Ejercicios");
		lblNewLabel_2_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2_2.setFont(new Font("Liberation Mono", Font.BOLD, 20));
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setBounds(43, 407, 463, 31);
		frmGimnasio.getContentPane().add(lblNewLabel_2_2);

		JLabel lblRutinaDelCliente = new JLabel("Rutina del Cliente:");
		lblRutinaDelCliente.setForeground(Color.DARK_GRAY);
		lblRutinaDelCliente.setFont(new Font("Liberation Mono", Font.BOLD, 20));
		lblRutinaDelCliente.setBounds(781, 481, 238, 38);
		frmGimnasio.getContentPane().add(lblRutinaDelCliente);

		JLabel lblRepeticiones = new JLabel("Repeticiones:");
		lblRepeticiones.setBounds(232, 735, 109, 15);
		frmGimnasio.getContentPane().add(lblRepeticiones);

		JLabel lblDescanso = new JLabel("Descanso:");
		lblDescanso.setBounds(258, 762, 83, 15);
		frmGimnasio.getContentPane().add(lblDescanso);

		JLabel lblDificultad = new JLabel("Dificultad:");
		lblDificultad.setBounds(258, 789, 83, 15);
		frmGimnasio.getContentPane().add(lblDificultad);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(780, 354, 70, 15);
		frmGimnasio.getContentPane().add(lblNewLabel);

		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(780, 381, 70, 15);
		frmGimnasio.getContentPane().add(lblApellidos);

		JLabel lblSegundos = new JLabel("segundos");
		lblSegundos.setBounds(465, 762, 70, 15);
		frmGimnasio.getContentPane().add(lblSegundos);

		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
		lblFechaNacimiento.setBounds(994, 325, 130, 15);
		frmGimnasio.getContentPane().add(lblFechaNacimiento);

		comboBoxDif = new JComboBox();
		comboBoxDif.setModel(new DefaultComboBoxModel(new String[] { "Facil", "Intermedia", "Dificil" }));
		comboBoxDif.setBounds(346, 784, 107, 24);
		frmGimnasio.getContentPane().add(comboBoxDif);

		lblLesiones = new JLabel("Lesiones:");
		lblLesiones.setBounds(1054, 352, 70, 15);
		frmGimnasio.getContentPane().add(lblLesiones);

		JLabel lblId = new JLabel("id:");
		lblId.setBounds(159, 320, 70, 15);
		frmGimnasio.getContentPane().add(lblId);

		JLabel lblId_1 = new JLabel("id:");
		lblId_1.setBounds(22, 708, 70, 15);
		frmGimnasio.getContentPane().add(lblId_1);

		JLabel lblId_2 = new JLabel("id:");
		lblId_2.setBounds(780, 327, 70, 15);
		frmGimnasio.getContentPane().add(lblId_2);

		JLabel lblObjetivo = new JLabel("Objetivo:");
		lblObjetivo.setBounds(1054, 381, 70, 15);
		frmGimnasio.getContentPane().add(lblObjetivo);

		txtNombreEjercicio = new JTextField();
		txtNombreEjercicio.setBounds(100, 733, 114, 19);
		frmGimnasio.getContentPane().add(txtNombreEjercicio);
		txtNombreEjercicio.setColumns(10);

		txtPesoEjercicio = new JTextField();
		txtPesoEjercicio.setToolTipText("0.0-100.0");
		txtPesoEjercicio.setBounds(100, 760, 114, 19);
		frmGimnasio.getContentPane().add(txtPesoEjercicio);
		txtPesoEjercicio.setColumns(10);

		txtSeriesEjercicio = new JTextField();
		txtSeriesEjercicio.setToolTipText("0-50");
		txtSeriesEjercicio.setBounds(100, 787, 114, 19);
		frmGimnasio.getContentPane().add(txtSeriesEjercicio);
		txtSeriesEjercicio.setColumns(10);

		txtRepsEjercicio = new JTextField();
		txtRepsEjercicio.setToolTipText("0-50");
		txtRepsEjercicio.setBounds(346, 733, 114, 19);
		frmGimnasio.getContentPane().add(txtRepsEjercicio);
		txtRepsEjercicio.setColumns(10);

		txtDescansoEjercicio = new JTextField();
		txtDescansoEjercicio.setToolTipText("0-999");
		txtDescansoEjercicio.setBounds(346, 762, 114, 19);
		frmGimnasio.getContentPane().add(txtDescansoEjercicio);
		txtDescansoEjercicio.setColumns(10);

		txtFoto = new JTextField();
		txtFoto.setEditable(false);
		txtFoto.setBounds(663, 787, 75, 19);
		frmGimnasio.getContentPane().add(txtFoto);
		txtFoto.setColumns(10);

		txtNumClientes = new JTextField();
		txtNumClientes.setEditable(false);
		txtNumClientes.setBounds(234, 376, 171, 19);
		frmGimnasio.getContentPane().add(txtNumClientes);
		txtNumClientes.setColumns(10);

		txtNombreCliente = new JTextField();
		txtNombreCliente.setBounds(868, 350, 114, 19);
		frmGimnasio.getContentPane().add(txtNombreCliente);
		txtNombreCliente.setColumns(10);

		txtApellidosCliente = new JTextField();
		txtApellidosCliente.setBounds(868, 379, 114, 19);
		frmGimnasio.getContentPane().add(txtApellidosCliente);
		txtApellidosCliente.setColumns(10);

		txtFechaNacCliente = new JTextField();
		txtFechaNacCliente.setToolTipText("DD/MM/AAAA");
		txtFechaNacCliente.setBounds(1137, 323, 114, 19);
		frmGimnasio.getContentPane().add(txtFechaNacCliente);
		txtFechaNacCliente.setColumns(10);

		txtLesionesCliente = new JTextField();
	
		txtLesionesCliente.setBounds(1137, 350, 114, 19);
		frmGimnasio.getContentPane().add(txtLesionesCliente);
		txtLesionesCliente.setColumns(10);

		txtObjCliente = new JTextField();
		txtObjCliente.setBounds(1137, 377, 114, 19);
		frmGimnasio.getContentPane().add(txtObjCliente);
		txtObjCliente.setColumns(10);

		txtIdEntrenador = new JTextField();
		txtIdEntrenador.setEditable(false);
		txtIdEntrenador.setBounds(234, 316, 171, 19);
		frmGimnasio.getContentPane().add(txtIdEntrenador);
		txtIdEntrenador.setColumns(10);

		txtidEjercicio = new JTextField();
		txtidEjercicio.setEditable(false);
		txtidEjercicio.setBounds(100, 706, 114, 19);
		frmGimnasio.getContentPane().add(txtidEjercicio);
		txtidEjercicio.setColumns(10);

		txtIdCliente = new JTextField();
		txtIdCliente.setEditable(false);
		txtIdCliente.setBounds(868, 323, 114, 19);
		frmGimnasio.getContentPane().add(txtIdCliente);
		txtIdCliente.setColumns(10);

		// botones
		// entrenador
		JButton btnCrearEntrenador = new JButton("Crear Entrenador");
		btnCrearEntrenador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (txtNombreEntrenador.getText() == null || txtNombreEntrenador.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Falta el nombre", "ERROR", JOptionPane.ERROR_MESSAGE);

				} else {
					nomEntrenador = txtNombreEntrenador.getText();
					Entrenador entrenador = new Entrenador(nomEntrenador, 0);
					entrenadorDAO.insertEntrenador(entrenador);
					refrescarEntrenador();
					limpiarEntrenador();
					JOptionPane.showMessageDialog(frmGimnasio, "Entrenador creado");
				}
			}
		});
		btnCrearEntrenador.setBounds(12, 273, 157, 25);
		frmGimnasio.getContentPane().add(btnCrearEntrenador);

		JButton btnBorrarEntrenador = new JButton("Borrar Entrenador");
		btnBorrarEntrenador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// control de errores

				if (txtIdEntrenador.getText() == null || txtIdEntrenador.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No has seleccionado ningun entrenador", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (Integer.parseInt(txtIdEntrenador.getText()) == 100000) { // si el id del entrenador es este
																					// significa que es la clave
																					// que he utilizado para decir que
																					// un cliente no tiene entrenador
																					// no se puede borrar ni actualizar

					JOptionPane.showMessageDialog(null, "Este entrenador no es un entrenador", "No se puede borrar",
							JOptionPane.ERROR_MESSAGE);

				} else if (Integer.parseInt(txtNumClientes.getText()) > 0) {
					numClientes = Integer.parseInt(txtNumClientes.getText());
					JOptionPane.showMessageDialog(null, "Este entrenador tiene asigandos clientes",
							"No se puede borrar", JOptionPane.ERROR_MESSAGE);
				} else {
					idEntrenador = Integer.parseInt(txtIdEntrenador.getText());
					entrenadorDAO.deleteEntrenador(idEntrenador);
					refrescarEntrenador();
					limpiarEntrenador();
					JOptionPane.showMessageDialog(frmGimnasio, "Entrenador borrado");
				}
			}
		});
		btnBorrarEntrenador.setBounds(397, 273, 171, 25);
		frmGimnasio.getContentPane().add(btnBorrarEntrenador);

		JButton btnActualizarEntrenador = new JButton("Actualizar Entrenador");
		btnActualizarEntrenador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// control de errores

				if (txtIdEntrenador.getText() == null || txtIdEntrenador.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No has seleccionado ningun entrenador", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (txtNombreEntrenador.getText() == null || txtNombreEntrenador.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debes poner el nombre del entrenador", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (Integer.parseInt(txtIdEntrenador.getText()) == 100000) {

					JOptionPane.showMessageDialog(null, "Este entrenador no es un entrenador", "No se puede editar",
							JOptionPane.ERROR_MESSAGE);

				} else {

					idEntrenador = Integer.parseInt(txtIdEntrenador.getText());
					nomEntrenador = txtNombreEntrenador.getText();

					Entrenador entrenador2 = entrenadorDAO.selectEntrenadorById(idEntrenador);
					entrenador2.setNomEntrenador(nomEntrenador);

					entrenadorDAO.updateEntrenador(entrenador2);

					refrescarEntrenador();
					limpiarEntrenador();
					JOptionPane.showMessageDialog(frmGimnasio, "Entrenador actualizado");

				}
			}
		});
		btnActualizarEntrenador.setBounds(184, 273, 200, 25);
		frmGimnasio.getContentPane().add(btnActualizarEntrenador);

		// ejercicio

		JButton btnCrearEjercicio = new JButton("Crear Ejercicio");
		btnCrearEjercicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// control de errores

				if (txtNombreEjercicio.getText() == null || txtNombreEjercicio.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Falta el nombre del ejercicio", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (txtPesoEjercicio.getText() == null || txtPesoEjercicio.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Falta el peso del ejercicio", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (!comprobarExpReg(txtPesoEjercicio.getText(), "^(?:100(?:\\.0)?|\\d{1,2}(?:\\.\\d)?)$")) {
					JOptionPane.showMessageDialog(null, "El peso debe ser un numero del 0.0 al 100.0", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (txtSeriesEjercicio.getText() == null || txtSeriesEjercicio.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Faltan las series del ejercicio", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (!comprobarExpReg(txtSeriesEjercicio.getText(), "^(?:50|[0-4]?\\d)$")) {
					JOptionPane.showMessageDialog(null, "Las series deben ser un numero del 0 al 50", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (txtRepsEjercicio.getText() == null || txtRepsEjercicio.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, " Faltan las repeticiones del ejercicio", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (!comprobarExpReg(txtRepsEjercicio.getText(), "^(?:50|[0-4]?\\d)$")) {
					JOptionPane.showMessageDialog(null, "Las repeticiones deben ser un numero del 0 al 50", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (txtDescansoEjercicio.getText() == null || txtDescansoEjercicio.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Falta el descanso del ejercicio", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (!comprobarExpReg(txtDescansoEjercicio.getText(), "^[0-9]{1,3}$")) {
					JOptionPane.showMessageDialog(null, "En el descanso debes poner minimo 1 cifra y maximo 3 cifras",
							"ERROR", JOptionPane.ERROR_MESSAGE);

				} else if (txtFoto.getText() == null || txtFoto.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Falta la foto del ejercicio", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else {

					nomEjercicio = txtNombreEjercicio.getText();
					pesoEjercicio = Double.parseDouble(txtPesoEjercicio.getText());
					seriesEjercicio = Integer.parseInt(txtSeriesEjercicio.getText());
					repsEjercicio = Integer.parseInt(txtRepsEjercicio.getText());
					descansoEjercicio = txtDescansoEjercicio.getText();
					dificultadEjercicio = comboBoxDif.getSelectedItem().toString();
					String textFoto = txtFoto.getText();
					byte[] fotoB;
// escoger la foto
					try {

						fotoB = Files.readAllBytes(Paths.get(textFoto));

						img = new com.mysql.cj.jdbc.Blob(fotoB, null);

					} catch (IOException e3) {

					}

					Ejercicio ejercicio = new Ejercicio(nomEjercicio, pesoEjercicio, seriesEjercicio, repsEjercicio,
							descansoEjercicio, dificultadEjercicio, img);
					ejercicioDAO.insertEjercicio(ejercicio);
					refrescarEjercicio();
					limpiarEjercicio();
					JOptionPane.showMessageDialog(frmGimnasio, "Ejercicio creado");

				}
			}
		});
		btnCrearEjercicio.setBounds(12, 666, 157, 25);
		frmGimnasio.getContentPane().add(btnCrearEjercicio);

		JButton btnBorrarEjercicio = new JButton("Borrar Ejercicio");
		btnBorrarEjercicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (txtidEjercicio.getText() == null || txtidEjercicio.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecciona el ejercicio", "ERROR", JOptionPane.ERROR_MESSAGE);

				} else {
					idEjercicio = Integer.parseInt(txtidEjercicio.getText());
					ejercicioDAO.deleteEjercicio(idEjercicio);
					refrescarEjercicio();
					limpiarEjercicio();

					if (txtIdCliente.getText() == null || txtIdCliente.getText().isEmpty()) {

					} else {
						idCliente = Integer.parseInt(txtIdCliente.getText());
						Cliente cliente = clienteDAO.selectClienteById(idCliente);
						clienteDAO.updateCliente(cliente);
						refrescarClienteEjercicio(cliente);
						JOptionPane.showMessageDialog(frmGimnasio, "Ejercicio Borrado");
					}

				}

			}
		});
		btnBorrarEjercicio.setBounds(397, 666, 171, 25);
		frmGimnasio.getContentPane().add(btnBorrarEjercicio);
// Actualiza ejercicio
		JButton btnActualizarEjercicio = new JButton("Actualizar Ejercicio");
		btnActualizarEjercicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtNombreEjercicio.getText() == null || txtNombreEjercicio.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Falta el nombre del ejercicio", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (txtPesoEjercicio.getText() == null || txtPesoEjercicio.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Falta el peso del ejercicio", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (!comprobarExpReg(txtPesoEjercicio.getText(), "^(?:100(?:\\.0)?|\\d{1,2}(?:\\.\\d)?)$")) {
					JOptionPane.showMessageDialog(null, "El peso debe ser un numero del 0.0 al 100.0", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (txtSeriesEjercicio.getText() == null || txtSeriesEjercicio.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Faltan las series del ejercicio", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (!comprobarExpReg(txtSeriesEjercicio.getText(), "^(?:50|[0-4]?\\d)$")) {
					JOptionPane.showMessageDialog(null, "Las series deben ser un numero del 0 al 50", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (txtRepsEjercicio.getText() == null || txtRepsEjercicio.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, " Faltan las repeticiones del ejercicio", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (!comprobarExpReg(txtRepsEjercicio.getText(), "^(?:50|[0-4]?\\d)$")) {
					JOptionPane.showMessageDialog(null, "Las repeticiones deben ser un numero del 0 al 50", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (txtDescansoEjercicio.getText() == null || txtDescansoEjercicio.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Falta el descanso del ejercicio", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (!comprobarExpReg(txtDescansoEjercicio.getText(), "^[0-9]{1,3}$")) {
					JOptionPane.showMessageDialog(null, "En el descanso debes poner minimo 1 cifra y masximo 3 cifras",
							"ERROR", JOptionPane.ERROR_MESSAGE);

				} else {

					idEjercicio = Integer.parseInt(txtidEjercicio.getText());
					nomEjercicio = txtNombreEjercicio.getText();
					pesoEjercicio = Double.parseDouble(txtPesoEjercicio.getText());
					seriesEjercicio = Integer.parseInt(txtSeriesEjercicio.getText());
					repsEjercicio = Integer.parseInt(txtRepsEjercicio.getText());
					descansoEjercicio = txtDescansoEjercicio.getText();
					dificultadEjercicio = comboBoxDif.getSelectedItem().toString();
					String textFoto = txtFoto.getText();
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
					if (txtIdCliente.getText() == null || txtIdCliente.getText().isEmpty()) {

					} else {
						idCliente = Integer.parseInt(txtIdCliente.getText());
						Cliente cliente = clienteDAO.selectClienteById(idCliente);
						refrescarClienteEjercicio(cliente);
						JOptionPane.showMessageDialog(frmGimnasio, "Ejercicio Actualizado");
					}
				}
			}
		});

		btnActualizarEjercicio.setBounds(184, 664, 200, 25);
		frmGimnasio.getContentPane().add(btnActualizarEjercicio);

		// cliente

		JButton btnCrearCliente = new JButton("Crear Cliente");
		btnCrearCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int edadCliente = 0;
				// control de errores
				try {
		            LocalDate hoy = LocalDate.now();
		            LocalDate dobDate = LocalDate.parse(txtFechaNacCliente.getText(), java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		            edadCliente = Period.between(dobDate, hoy).getYears();
		      
		        
				
				if (txtNombreCliente.getText() == null || txtNombreCliente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Falta el nombre del cliente", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (txtApellidosCliente.getText() == null || txtApellidosCliente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Falta los apellidos del cliente", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (txtFechaNacCliente.getText() == null || txtFechaNacCliente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Falta la fecha de nacimiento del cliente", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (!comprobarExpReg(txtFechaNacCliente.getText(), "^\\d{2}/\\d{2}/\\d{4}$")) {
					JOptionPane.showMessageDialog(null, "Fecha de nacimiento erronea.\nSu formato es: DD/MM/AAAA",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					
				} else if (edadCliente<0){
		            JOptionPane.showMessageDialog(null, "El perfil que estas creando es menor de la edad minima perimitida", "No podemos crear un perfil de un menor de 10 años", 
		            		JOptionPane.WARNING_MESSAGE);
		       
		           
				} else if (txtLesionesCliente.getText() == null || txtLesionesCliente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, " Faltan las lesiones del cliente", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				

				} else if (txtObjCliente.getText() == null || txtObjCliente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Falta el objetivo del cliente", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				
				} else {
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
					JOptionPane.showMessageDialog(frmGimnasio, "Cliente Creado");
				}
				  } catch (DateTimeParseException ex) {
					  JOptionPane.showMessageDialog(null, "Has introducido la fecha de nacimeinto mal\n sigue el siguiente formato DD/MM/AAAA ", "Error en la fecha de nacimiento", 
			            		JOptionPane.WARNING_MESSAGE);
		        }
			}
		});
		btnCrearCliente.setBounds(770, 273, 142, 25);
		frmGimnasio.getContentPane().add(btnCrearCliente);
// borrado de cliente
		JButton btnBorrarCliente = new JButton("Borrar Cliente");
		btnBorrarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (txtIdCliente.getText() == null || txtIdCliente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecciona un cliente", "ERROR", JOptionPane.ERROR_MESSAGE);

				} else {
					idCliente = Integer.parseInt(txtIdCliente.getText());
					clienteDAO.deleteCliente(idCliente);
					refrescarCliente();
					limpiarCliente();
					JOptionPane.showMessageDialog(frmGimnasio, "Cliente Borrado");
				}
			}
		});
		btnBorrarCliente.setBounds(1107, 273, 136, 25);
		frmGimnasio.getContentPane().add(btnBorrarCliente);

		JButton btnActualizarCliente = new JButton("Actualizar Cliente");
		btnActualizarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// control de errores

				int edadCliente = 0;
				// control de errores
				try {
		            LocalDate hoy = LocalDate.now();
		            LocalDate dobDate = LocalDate.parse(txtFechaNacCliente.getText(), java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		            edadCliente = Period.between(dobDate, hoy).getYears();
		      
		        
				
				if (txtNombreCliente.getText() == null || txtNombreCliente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Falta el nombre del cliente", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (txtApellidosCliente.getText() == null || txtApellidosCliente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Falta los apellidos del cliente", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (txtFechaNacCliente.getText() == null || txtFechaNacCliente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Falta la fecha de nacimiento del cliente", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (!comprobarExpReg(txtFechaNacCliente.getText(), "^\\d{2}/\\d{2}/\\d{4}$")) {
					JOptionPane.showMessageDialog(null, "Fecha de nacimiento erronea.\nSu formato es: DD/MM/AAAA",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					
				} else if (edadCliente<10){
		            JOptionPane.showMessageDialog(null, "El perfil que estas creando es menor de la edad minima perimitida", "No podemos crear un perfil de un menor de 10 años", 
		            		JOptionPane.WARNING_MESSAGE);
		       
		           
				} else if (txtLesionesCliente.getText() == null || txtLesionesCliente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, " Faltan las lesiones del cliente", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					

				} else if (txtObjCliente.getText() == null || txtObjCliente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Falta el objetivo del cliente", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				
				} else {
					idCliente = Integer.parseInt(txtIdCliente.getText());
					nomCliente = txtNombreCliente.getText();
					apsCliente = txtApellidosCliente.getText();
					fecaNacCliente = txtFechaNacCliente.getText();
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
					JOptionPane.showMessageDialog(frmGimnasio, "Cliente Actualizado");
				}
				 } catch (DateTimeParseException ex) {
					  JOptionPane.showMessageDialog(null, "Has introducido la fecha de nacimeinto mal\n sigue el siguiente formato DD/MM/AAAA ", "Error en la fecha de nacimiento", 
			            		JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnActualizarCliente.setBounds(924, 273, 171, 25);
		frmGimnasio.getContentPane().add(btnActualizarCliente);

		JButton btnAsignarRutina = new JButton("Añadir Ejercicio a la Rutina");
		btnAsignarRutina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e2) {

				if (txtIdCliente.getText() == null || txtIdCliente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecciona un cliente", "ERROR", JOptionPane.ERROR_MESSAGE);

				} else if (txtidEjercicio.getText() == null || txtidEjercicio.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecciona un ejercicio", "ERROR", JOptionPane.ERROR_MESSAGE);

				} else {

					comprobacion = 0;
					idCliente = Integer.parseInt(txtIdCliente.getText());
					idEjercicio = Integer.parseInt(txtidEjercicio.getText());

					Cliente cliente = clienteDAO.selectClienteById(idCliente);
					Ejercicio ejercicio = ejercicioDAO.selectEjercicioById(idEjercicio);

					cliente.getEjercicios();

					List<Ejercicio> ejercicios = cliente.getEjercicios();

					for (Ejercicio ejercicioLista : ejercicios) {

						if (String.valueOf(ejercicioLista.getIdEjercicio())
								.equals(String.valueOf(ejercicio.getIdEjercicio()))) {
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
			}
		});
		btnAsignarRutina.setBounds(767, 752, 227, 25);
		frmGimnasio.getContentPane().add(btnAsignarRutina);

		JButton btnEliminarRutina = new JButton("Eliminar Ejercicio de la Rutina");
		btnEliminarRutina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableClienteEjercicio.getSelectedRow();
				if (index == -1) {// comprobar si ha selelcionado un ejercicio de la tabla
					JOptionPane.showMessageDialog(null, "No has seleccionado ningun ejercicio de la rutina", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int idEjercicioE = (int) (modelClienteEjercicio.getValueAt(index, 0));
					TableModel modelClienteEjercicio = tableClienteEjercicio.getModel();

					Cliente cliente = clienteDAO.selectClienteById(Integer.parseInt(txtIdCliente.getText()));
					Ejercicio ejercicio = ejercicioDAO.selectEjercicioById(idEjercicioE);

					cliente.quitarEjercicio(ejercicio);

					clienteDAO.updateCliente(cliente);
					refrescarClienteEjercicio(cliente);
				}
			}
		});
		btnEliminarRutina.setBounds(1002, 752, 246, 25);
		frmGimnasio.getContentPane().add(btnEliminarRutina);

		JButton btnAsignarEntrenador = new JButton("Asignar Cliente");
		btnAsignarEntrenador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtIdCliente.getText() == null || txtIdCliente.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Selecciona un cliente de la tabla clientes", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (txtIdEntrenador.getText() == null || txtIdEntrenador.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Selecciona un entrenador de la tabla entrenadores", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}else if(Integer.parseInt(txtIdEntrenador.getText())==100000) {
					JOptionPane.showMessageDialog(null, "Este no es un entrenador", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else {

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
						JOptionPane.showMessageDialog(frmGimnasio, "Cliente asigando al entrenador");
					}
				}
			}
		});
		btnAsignarEntrenador.setBounds(551, 86, 175, 25);
		frmGimnasio.getContentPane().add(btnAsignarEntrenador);

		JButton btnDesasignarCliente = new JButton("Desasignar Cliente");
		btnDesasignarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (txtIdCliente.getText() == null || txtIdCliente.getText().isEmpty()) {//compreba si el txt del id del cliente esta nulo

					JOptionPane.showMessageDialog(null, "Primero selecciona un cliente de la tabla clientes", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else {

					idCliente = Integer.parseInt(txtIdCliente.getText());
					Cliente cliente = clienteDAO.selectClienteById(idCliente);

					if (cliente.getIdEntrenador() == 100000) {// si en el idEntrenador del cliente apareze 100000 significa que el cliente no tiene entrenador

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

						JOptionPane.showMessageDialog(frmGimnasio, "Cliente desasignado del entrenador");
					}
				}
			}
		});
		btnDesasignarCliente.setBounds(551, 123, 175, 25);
		frmGimnasio.getContentPane().add(btnDesasignarCliente);

		JLabel lblNewLabel_1 = new JLabel("Num clientes:");
		lblNewLabel_1.setBounds(129, 376, 96, 15);
		frmGimnasio.getContentPane().add(lblNewLabel_1);

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

		btnFoto.setBounds(465, 784, 175, 25);
		frmGimnasio.getContentPane().add(btnFoto);

		// Botones info

		JButton btnInfoasignCl = new JButton("i");
		btnInfoasignCl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(frmGimnasio,
						"Para Asignar un Cliente a un entrenador\nSelecciona el entrenador de la tabla\nSelecciona el cliente de la tabla\nDale al boton Asignar Cliente");

			}
		});
		btnInfoasignCl.setBounds(501, 86, 38, 25);
		frmGimnasio.getContentPane().add(btnInfoasignCl);

		JButton btnInfoDesCl = new JButton("i");
		btnInfoDesCl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JOptionPane.showMessageDialog(frmGimnasio,
						"Para Desasignar un Cliente de un Entrenador\nSelecciona el cliente de la tabla\nDale al boton Desasignar Cliente");

			}
		});
		btnInfoDesCl.setBounds(738, 123, 38, 25);
		frmGimnasio.getContentPane().add(btnInfoDesCl);

		JButton btnInfoCreaEn = new JButton("i");
		btnInfoCreaEn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JOptionPane.showMessageDialog(frmGimnasio,
						"Para Crear un Entrenador\nIntroduce el nombre que desees en la casilla del nombre\nDale al boton Crear Entrenador");
			}
		});
		btnInfoCreaEn.setBounds(76, 236, 38, 25);
		frmGimnasio.getContentPane().add(btnInfoCreaEn);

		JButton btnInfoActEn = new JButton("i");
		btnInfoActEn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JOptionPane.showMessageDialog(frmGimnasio,
						"Para Actualizar un Entrenador\nIntroduce el nuevo nombre que desees en la casilla del nombre\nDale al boton Actualizar Entrenador");
			}
		});
		btnInfoActEn.setBounds(258, 236, 38, 25);
		frmGimnasio.getContentPane().add(btnInfoActEn);

		JButton btnInfoBorEN = new JButton("i");
		btnInfoBorEN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JOptionPane.showMessageDialog(frmGimnasio,
						"Para Borrar un Entrenador\nSelecciona el entrenador de la tabla Entrenador\nDale al boton Borrar Entrenador");

			}
		});
		btnInfoBorEN.setBounds(460, 236, 38, 25);
		frmGimnasio.getContentPane().add(btnInfoBorEN);

		JButton btnInfoCrCl = new JButton("i");
		btnInfoCrCl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JOptionPane.showMessageDialog(frmGimnasio,
						"Para Crear un Cliente\nIntroduce los datos del cliente en las casillas\nDale al boton Crear Cliente");

			}
		});
		btnInfoCrCl.setBounds(820, 236, 38, 25);
		frmGimnasio.getContentPane().add(btnInfoCrCl);

		JButton btnInfoActCl = new JButton("i");
		btnInfoActCl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(frmGimnasio,
						"Para Actualizar un Cliente\nIntroduce los nuevos datos del cliente en las casillas\nDale al boton Actualizar Cliente");

			}
		});
		btnInfoActCl.setBounds(994, 236, 38, 25);
		frmGimnasio.getContentPane().add(btnInfoActCl);

		JButton btnInfoBrCl = new JButton("i");
		btnInfoBrCl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JOptionPane.showMessageDialog(frmGimnasio,
						"Para Borrar un Cliente\nSelecciona el cliente de la tabla CLiente\nDale al boton Borrar Cliente");

			}
		});
		btnInfoBrCl.setBounds(1156, 236, 38, 25);
		frmGimnasio.getContentPane().add(btnInfoBrCl);

		JButton btnInfoAñEjRut = new JButton("i");
		btnInfoAñEjRut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JOptionPane.showMessageDialog(frmGimnasio,
						"Para añadir un ejercicio a la rutina de un cliente\nSelecciona el cliente de la tabla Cliente\nSelecciona el ejercicio de la tabla Ejercicio\nDale al boton Añadir Ejercicio A La Rutina");

			}
		});
		btnInfoAñEjRut.setBounds(850, 715, 38, 25);
		frmGimnasio.getContentPane().add(btnInfoAñEjRut);

		JButton btnInfoElEjRut = new JButton("i");
		btnInfoElEjRut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JOptionPane.showMessageDialog(frmGimnasio,
						"Para eliminar un ejercicio de la rutina de un cliente\nSelecciona el ejercicio de la tabla Rutina\nDale al boton Eliminar Ejercicio De La Rutina");

			}
		});
		btnInfoElEjRut.setBounds(1086, 715, 38, 25);
		frmGimnasio.getContentPane().add(btnInfoElEjRut);

		JButton btnInfoCreaEj = new JButton("i");
		btnInfoCreaEj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JOptionPane.showMessageDialog(frmGimnasio,
						"Para Crear un Ejercicio\nIntroduce los datos del ejercicio en las casillas\nDale al boton Crear Ejercicio");

			}
		});
		btnInfoCreaEj.setBounds(64, 627, 38, 25);
		frmGimnasio.getContentPane().add(btnInfoCreaEj);

		JButton btnInfoActEj = new JButton("i");
		btnInfoActEj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(frmGimnasio,
						"Para Actualizar un Ejercicio\nIntroduce los nuevos datos del ejercicio en las casillas\nDale al boton Actualizar Ejercicio");

			}
		});
		btnInfoActEj.setBounds(258, 627, 38, 25);
		frmGimnasio.getContentPane().add(btnInfoActEj);

		JButton btnInfoBorrEj = new JButton("i");
		btnInfoBorrEj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JOptionPane.showMessageDialog(frmGimnasio,
						"Para Borrar un Ejercicio\nSelecciona el ejercicio de la tabla Ejercicio\nDale al boton Borrar Ejercicio");

			}
		});
		btnInfoBorrEj.setBounds(446, 627, 38, 25);
		frmGimnasio.getContentPane().add(btnInfoBorrEj);

		// botones limpiar

		JButton btnLimpiarEntr = new JButton("Limpiar");
		btnLimpiarEntr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtIdEntrenador.setText("");
				txtNombreEntrenador.setText("");
				txtNumClientes.setText("");
			}
		});
		btnLimpiarEntr.setBounds(12, 344, 117, 25);
		frmGimnasio.getContentPane().add(btnLimpiarEntr);

		JButton btnLimpiarEj = new JButton("Limpiar");
		btnLimpiarEj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				txtidEjercicio.setText("");
				txtNombreEjercicio.setText("");
				txtPesoEjercicio.setText("");
				txtSeriesEjercicio.setText("");
				txtRepsEjercicio.setText("");
				txtDescansoEjercicio.setText("");
				txtFoto.setText("");

			}
		});
		btnLimpiarEj.setBounds(501, 725, 117, 25);
		frmGimnasio.getContentPane().add(btnLimpiarEj);

		JButton btnLimpiarCl = new JButton("Limpiar ");
		btnLimpiarCl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				txtIdCliente.setText("");
				txtNombreCliente.setText("");
				txtApellidosCliente.setText("");
				txtFechaNacCliente.setText("");
				txtLesionesCliente.setText("");
				txtObjCliente.setText("");
			}
		});
		btnLimpiarCl.setBounds(980, 411, 117, 25);
		frmGimnasio.getContentPane().add(btnLimpiarCl);

		// Mostrar las tres tablas

		refrescarCliente();
		refrescarEjercicio();
		refrescarEntrenador();

	}
}