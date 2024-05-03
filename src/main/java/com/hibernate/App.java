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
	private DefaultTableModel modelClienteEjercicio;
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
	private JTextField txtIdEntrenador;
	private JTextField txtidEjercicio;
	private JTextField txtIdCliente;
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
			int idEntrenador;
			int idEjercicio;
			int idCliente;
			String nomEjercicio;
			double pesoEjercicio;
			int seriesEjercicio;
			int repsEjercicio;
			String descansoEjercicio;
			String dificultadEjercicio;
			String nomCliente;
			String apsCliente;
			String fecaNacCliente;
			String lesionesCliente;
			String objCliente;
			private JTable table;
			
			
	
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
		
		//Tabla ejercicioCliente
		modelClienteEjercicio = new DefaultTableModel() {
			public boolean isCellEditable(int row, int colum) {
				return false;
				}
		};
		modelClienteEjercicio.addColumn("Ejercicios");
		modelClienteEjercicio.addColumn("Algo");
		//Entrenador
		
		modelEntrenador = new DefaultTableModel() {
				public boolean isCellEditable(int row, int colum) {
				return false;
				}
		};
		modelEntrenador.addColumn("ID");
		modelEntrenador.addColumn("Nombre");
		
		//Cliente
		modelCliente = new DefaultTableModel(){
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
		//Ejercicio
		modelEjercicio = new DefaultTableModel(){
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
     	
     	//Las tres tablas con los scrollpane
     	
     	JTable tableClienteEjercicio = new JTable(modelClienteEjercicio);
     	frame.getContentPane().setLayout(null);
     	tableClienteEjercicio.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane3 = new JScrollPane(tableClienteEjercicio);
		scrollPane3.setBounds(593, 498, 550, 196);
		frame.getContentPane().add(scrollPane3);
     	//Tabla entrenador
		JTable tableEntrenador = new JTable(modelEntrenador);
		tableEntrenador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableEntrenador.getSelectedRow();
				TableModel modelEntrenador= tableEntrenador.getModel();
				txtIdEntrenador.setText(modelEntrenador.getValueAt(index, 0).toString());
				txtNombreEntrenador.setText(modelEntrenador.getValueAt(index, 1).toString());
			}
		});
		frame.getContentPane().setLayout(null);
		tableEntrenador.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(tableEntrenador);
		scrollPane.setBounds(12, 12, 481, 190);
		frame.getContentPane().add(scrollPane);
		 
		//Tabla cliente
		JTable tableCliente = new JTable(modelCliente); 
		tableCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = tableCliente.getSelectedRow();
				TableModel modelCliente= tableCliente.getModel();
				txtIdCliente.setText(modelCliente.getValueAt(index, 0).toString());
				txtNombreCliente.setText(modelCliente.getValueAt(index, 1).toString());
				txtApellidosCliente.setText(modelCliente.getValueAt(index, 2).toString());
				txtFechaNacCliente.setText(modelCliente.getValueAt(index, 3).toString());
				txtLesionesCliente.setText(modelCliente.getValueAt(index, 4).toString());
				txtObjCliente.setText(modelCliente.getValueAt(index, 5).toString());
				
			}
		});
		frame.getContentPane().setLayout(null); 
		tableCliente.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
		JScrollPane scrollPane_1 = new JScrollPane(tableCliente); 
		scrollPane_1.setBounds(749, 12, 463, 190); 
		frame.getContentPane().add(scrollPane_1); 
		
		//Tabla ejercicio
		JTable tableEjercicio= new JTable(modelEjercicio); 
		tableEjercicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableEjercicio.getSelectedRow();
				TableModel modelEjercicio= tableEjercicio.getModel();
				txtidEjercicio.setText(modelEjercicio.getValueAt(index, 0).toString());
				txtNombreEjercicio.setText(modelEjercicio.getValueAt(index, 1).toString());
				txtPesoEjercicio.setText(modelEjercicio.getValueAt(index, 2).toString());
				txtSeriesEjercicio.setText(modelEjercicio.getValueAt(index, 3).toString());
				txtRepsEjercicio.setText(modelEjercicio.getValueAt(index, 4).toString());
				txtDescansoEjercicio.setText(modelEjercicio.getValueAt(index, 5).toString());
				txtdificultadEjercicio.setText(modelEjercicio.getValueAt(index, 6).toString());
				
			}
		});
		frame.getContentPane().setLayout(null); 
		tableEjercicio.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
		JScrollPane scrollPane_2 = new JScrollPane(tableEjercicio); 
		scrollPane_2.setBounds(12, 346, 463, 190); 
		frame.getContentPane().add(scrollPane_2); 
		
		//labels y txt 
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(84, 317, 70, 15);
		frame.getContentPane().add(lblNombre);
		
		txtNombreEntrenador = new JTextField();
		txtNombreEntrenador.setBounds(159, 315, 171, 19);
		frame.getContentPane().add(txtNombreEntrenador);
		txtNombreEntrenador.setColumns(10);
		
		JLabel lblNombre_1 = new JLabel("Nombre:");
		lblNombre_1.setBounds(126, 652, 70, 15);
		frame.getContentPane().add(lblNombre_1);
		
		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setBounds(126, 679, 70, 15);
		frame.getContentPane().add(lblPeso);
		
		JLabel lblSeries = new JLabel("Series:");
		lblSeries.setBounds(126, 708, 70, 15);
		frame.getContentPane().add(lblSeries);
		
		JLabel lblRepeticiones = new JLabel("Repeticiones:");
		lblRepeticiones.setBounds(126, 735, 109, 15);
		frame.getContentPane().add(lblRepeticiones);
		
		JLabel lblDescanso = new JLabel("Descanso:");
		lblDescanso.setBounds(126, 762, 83, 15);
		frame.getContentPane().add(lblDescanso);
		
		JLabel lblDificultad = new JLabel("Dificultad:");
		lblDificultad.setBounds(126, 791, 83, 15);
		frame.getContentPane().add(lblDificultad);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(873, 313, 70, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(873, 340, 70, 15);
		frame.getContentPane().add(lblApellidos);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
		lblFechaNacimiento.setBounds(873, 372, 130, 15);
		frame.getContentPane().add(lblFechaNacimiento);
		
		lblLesiones = new JLabel("Lesiones:");
		lblLesiones.setBounds(873, 399, 70, 15);
		frame.getContentPane().add(lblLesiones);
		
		JLabel lblId = new JLabel("id:");
		lblId.setBounds(84, 288, 70, 15);
		frame.getContentPane().add(lblId);
		
		JLabel lblId_1 = new JLabel("id:");
		lblId_1.setBounds(126, 625, 70, 15);
		frame.getContentPane().add(lblId_1);

		JLabel lblId_2 = new JLabel("id:");
		lblId_2.setBounds(873, 286, 70, 15);
		frame.getContentPane().add(lblId_2);
		
		JLabel lblObjetivo = new JLabel("Objetivo:");
		lblObjetivo.setBounds(873, 426, 70, 15);
		frame.getContentPane().add(lblObjetivo);
		
		txtNombreEjercicio = new JTextField();
		txtNombreEjercicio.setBounds(232, 650, 114, 19);
		frame.getContentPane().add(txtNombreEjercicio);
		txtNombreEjercicio.setColumns(10);
		
		txtPesoEjercicio = new JTextField();
		txtPesoEjercicio.setBounds(232, 677, 114, 19);
		frame.getContentPane().add(txtPesoEjercicio);
		txtPesoEjercicio.setColumns(10);
		
		txtSeriesEjercicio = new JTextField();
		txtSeriesEjercicio.setBounds(232, 706, 114, 19);
		frame.getContentPane().add(txtSeriesEjercicio);
		txtSeriesEjercicio.setColumns(10);
		
		txtRepsEjercicio = new JTextField();
		txtRepsEjercicio.setBounds(232, 733, 114, 19);
		frame.getContentPane().add(txtRepsEjercicio);
		txtRepsEjercicio.setColumns(10);
		
		txtDescansoEjercicio = new JTextField();
		txtDescansoEjercicio.setBounds(232, 760, 114, 19);
		frame.getContentPane().add(txtDescansoEjercicio);
		txtDescansoEjercicio.setColumns(10);
		
		txtdificultadEjercicio = new JTextField();
		txtdificultadEjercicio.setBounds(232, 789, 114, 19);
		frame.getContentPane().add(txtdificultadEjercicio);
		txtdificultadEjercicio.setColumns(10);
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setBounds(1016, 313, 114, 19);
		frame.getContentPane().add(txtNombreCliente);
		txtNombreCliente.setColumns(10);
		
		txtApellidosCliente = new JTextField();
		txtApellidosCliente.setBounds(1016, 338, 114, 19);
		frame.getContentPane().add(txtApellidosCliente);
		txtApellidosCliente.setColumns(10);
		
		txtFechaNacCliente = new JTextField();
		txtFechaNacCliente.setBounds(1016, 370, 114, 19);
		frame.getContentPane().add(txtFechaNacCliente);
		txtFechaNacCliente.setColumns(10);
		
		txtLesionesCliente = new JTextField();
		txtLesionesCliente.setBounds(1016, 397, 114, 19);
		frame.getContentPane().add(txtLesionesCliente);
		txtLesionesCliente.setColumns(10);
		
		txtObjCliente = new JTextField();
		txtObjCliente.setBounds(1016, 424, 114, 19);
		frame.getContentPane().add(txtObjCliente);
		txtObjCliente.setColumns(10);
		
		txtIdEntrenador = new JTextField();
		txtIdEntrenador.setEditable(false);
		txtIdEntrenador.setBounds(159, 284, 171, 19);
		frame.getContentPane().add(txtIdEntrenador);
		txtIdEntrenador.setColumns(10);
		
		txtidEjercicio = new JTextField();
		txtidEjercicio.setEditable(false);
		txtidEjercicio.setBounds(232, 619, 114, 19);
		frame.getContentPane().add(txtidEjercicio);
		txtidEjercicio.setColumns(10);
		
		txtIdCliente = new JTextField();
		txtIdCliente.setEditable(false);
		txtIdCliente.setBounds(1016, 282, 114, 19);
		frame.getContentPane().add(txtIdCliente);
		txtIdCliente.setColumns(10);
		
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
				limpiarEntrenador();
			}
		});
		btnCrearEntrenador.setBounds(12, 214, 157, 25);
		frame.getContentPane().add(btnCrearEntrenador);
		
		JButton btnBorrarEntrenador = new JButton("Borrar Entrenador");
		btnBorrarEntrenador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				idEntrenador=Integer.parseInt(txtIdEntrenador.getText());
				entrenadorDAO.deleteEntrenador(idEntrenador);
				refrescarEntrenador();
				limpiarEntrenador();
			}
		});
		btnBorrarEntrenador.setBounds(322, 214, 171, 25);
		frame.getContentPane().add(btnBorrarEntrenador);
		
		JButton btnActualizarEntrenador = new JButton("Actualizar Entrenador");
		btnActualizarEntrenador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idEntrenador=Integer.parseInt(txtIdEntrenador.getText());
				nomEntrenador=txtNombreEntrenador.getText();
				
				Entrenador entrenador2=entrenadorDAO.selectEntrenadorById(idEntrenador);				
				entrenador2.setNomEntrenador(nomEntrenador);

				entrenadorDAO.updateEntrenador(entrenador2);
				
				refrescarEntrenador();
				limpiarEntrenador();
			}
		});
		btnActualizarEntrenador.setBounds(146, 251, 200, 25);
		frame.getContentPane().add(btnActualizarEntrenador);
		
		//ejercicio
		
		JButton btnCrearEjercicio = new JButton("Crear Ejercicio");
		btnCrearEjercicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				nomEjercicio=txtNombreEjercicio.getText();
				pesoEjercicio=Double.parseDouble(txtPesoEjercicio.getText());
				seriesEjercicio=Integer.parseInt(txtSeriesEjercicio.getText());
				repsEjercicio=Integer.parseInt(txtRepsEjercicio.getText());
				descansoEjercicio=txtDescansoEjercicio.getText();
				dificultadEjercicio=txtdificultadEjercicio.getText();
				
				Ejercicio ejercicio=new Ejercicio(nomEjercicio,pesoEjercicio,seriesEjercicio,repsEjercicio,descansoEjercicio,dificultadEjercicio);				
				ejercicioDAO.insertEjercicio(ejercicio);
				refrescarEjercicio();
				limpiarEjercicio();
				
			}
		});
		btnCrearEjercicio.setBounds(22, 548, 157, 25);
		frame.getContentPane().add(btnCrearEjercicio);
		
		JButton btnBorrarEjercicio = new JButton("Borrar Ejercicio");
		btnBorrarEjercicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				idEjercicio=Integer.parseInt(txtidEjercicio.getText());
				ejercicioDAO.deleteEjercicio(idEjercicio);
				refrescarEjercicio();
				limpiarEjercicio();
			}
		});
		btnBorrarEjercicio.setBounds(304, 548, 171, 25);
		frame.getContentPane().add(btnBorrarEjercicio);
		
		JButton btnActualizarEjercicio = new JButton("Actualizar Ejercicio");
		btnActualizarEjercicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				idEjercicio=Integer.parseInt(txtidEjercicio.getText());
				nomEjercicio=txtNombreEjercicio.getText();
				pesoEjercicio=Double.parseDouble(txtPesoEjercicio.getText());
				seriesEjercicio=Integer.parseInt(txtSeriesEjercicio.getText());
				repsEjercicio=Integer.parseInt(txtRepsEjercicio.getText());
				descansoEjercicio=txtDescansoEjercicio.getText();
				dificultadEjercicio=txtdificultadEjercicio.getText();
				
				Ejercicio ejercicio2=ejercicioDAO.selectEjercicioById(idEjercicio);				
				ejercicio2.setNomEjercicio(nomEjercicio);
				ejercicio2.setPeso(pesoEjercicio);
				ejercicio2.setSeries(seriesEjercicio);
				ejercicio2.setReps(repsEjercicio);
				ejercicio2.setDescanso(descansoEjercicio);
				ejercicio2.setDificultad(dificultadEjercicio);

				ejercicioDAO.updateEjercicio(ejercicio2);
				
				refrescarEjercicio();
				limpiarEjercicio();
			}
		});
		btnActualizarEjercicio.setBounds(146, 585, 200, 25);
		frame.getContentPane().add(btnActualizarEjercicio);
		
		//cliente
		
		JButton btnCrearCliente = new JButton("Crear Cliente");
		btnCrearCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				

				nomCliente=txtNombreCliente.getText();
				apsCliente=txtApellidosCliente.getText();
				fecaNacCliente=txtFechaNacCliente.getText();
				lesionesCliente=txtLesionesCliente.getText();
				objCliente=txtObjCliente.getText();
				
				Cliente cliente=new Cliente(nomCliente,apsCliente,fecaNacCliente,lesionesCliente,objCliente,100000);				
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
				
				idCliente=Integer.parseInt(txtIdCliente.getText());
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
				
				idCliente=Integer.parseInt(txtIdCliente.getText());
				nomCliente=txtNombreCliente.getText();
				apsCliente=txtApellidosCliente.getText();
				fecaNacCliente=txtApellidosCliente.getText();
				lesionesCliente=txtLesionesCliente.getText();
				objCliente=txtObjCliente.getText();
				
				
				Cliente cliente2=clienteDAO.selectClienteById(idCliente);				
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
		btnAsignarRutina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAsignarRutina.setBounds(650, 706, 157, 25);
		frame.getContentPane().add(btnAsignarRutina);
		
		JButton btnEliminarRutina = new JButton("Eliminar Ejercicio");
		btnEliminarRutina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEliminarRutina.setBounds(962, 706, 157, 25);
		frame.getContentPane().add(btnEliminarRutina);
		
		//ACABAR
		
		
		JButton btnAsignarEntrenador = new JButton("Asignar Cliente");
		btnAsignarEntrenador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				idCliente=Integer.parseInt(txtIdCliente.getText());
				idEntrenador=Integer.parseInt(txtIdEntrenador.getText());
				
				Cliente cliente2=clienteDAO.selectClienteById(idCliente);				
				cliente2.setIdEntrenador(idEntrenador);
				refrescarCliente();
				
			}
		});
		btnAsignarEntrenador.setBounds(531, 50, 179, 25);
		frame.getContentPane().add(btnAsignarEntrenador);
		
		
		
		JButton btnDesasignarCliente = new JButton("Desasignar Cliente");
		btnDesasignarCliente.setBounds(531, 87, 179, 25);
		frame.getContentPane().add(btnDesasignarCliente);
		
	
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
		separator_2.setBounds(567, 474, 683, 2);
		frame.getContentPane().add(separator_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(UIManager.getColor("Button.shadow"));
		panel_2.setBounds(555, 0, 685, 476);
		frame.getContentPane().add(panel_2);
		
		*/
		
		//Mostrar las tres tablas
				refrescarEntrenador();
				refrescarCliente();
				refrescarEjercicio();
		


 
	}
}
