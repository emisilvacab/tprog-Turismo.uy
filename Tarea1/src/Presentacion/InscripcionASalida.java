package Presentacion;

import javax.swing.JInternalFrame;

import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import datatypes.DTActividad;
import datatypes.DTSalida;
import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.salidaNoExisteException;
import excepciones.usuarioNoExisteException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.ZonedDateTime;

import javax.swing.JSpinner;
import java.awt.event.ItemListener;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;

public class InscripcionASalida extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	private IControladorUsuario icu;
	private IControladorDepartamento icd;
	
	private JComboBox<String> listaDptos;
	private JLabel lblSeleccionarDpto;
	private JButton botonContinuar;
	private JButton botonCancelar;
	private JComboBox<String> listaActs;
	private JLabel lblSeleccionarAct;
	private JLabel lblSeleccionarSals;
	private JComboBox<String> listaSals;
	private JLabel lblIngresarUser;
	private JTextField textFieldUser;
	private JLabel lblCantUsers;
	private JSpinner spinnerCantUsers;
	
	

	public InscripcionASalida(IControladorUsuario picu, IControladorDepartamento picd) {
		
		icu = picu;
		icd = picd;
		
		Dimension dmsInternal = new Dimension(500,500);
		setMaximumSize(dmsInternal);
		setMaximizable(false);
		setResizable(true);
	    setClosable(true);
	    setTitle("Inscripción a salida");
	    setBounds(0, 0, 410, 350);
	    
	    addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
            	limpiarCampos();
				setVisible(false);
            }
        });
	    
		//Cargar lista de departamentos
		lblSeleccionarDpto = new JLabel("Seleccione un departamento:");
		listaDptos = new JComboBox<String>();
		
		//Definicion de la lista de actividades
		lblSeleccionarAct = new JLabel("Seleccione una actividad:");
		listaActs = new JComboBox<String>();
		
		//Definicion de la lista de salidas
		lblSeleccionarSals = new JLabel("Seleccione una salida:");
		listaSals = new JComboBox<String>();
		
		//Evento en listaDptos que carga lista de actividades
		listaDptos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					if (listaDptos.getSelectedItem() != null) {
						listaActs.removeAllItems();
						HashSet<DTActividad> acts = icd.obtenerDatosActividadesAsociadas((String) listaDptos.getSelectedItem());
						for (DTActividad a : acts)
							listaActs.addItem(a.getNombre());
					}
				}
				catch(departamentoNoExisteException e1){
					JOptionPane.showMessageDialog(null, e1.getMessage(), "El departamento seleccionado no está registrado en el sistema", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		//Evento en listaActs que carga lista de salidas
		listaActs.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					if(listaActs.getSelectedItem() != null) {
						listaSals.removeAllItems();
						HashSet<DTSalida> sals = icd.obtenerDatosSalidasVigentes((String) listaActs.getSelectedItem(),(String) listaDptos.getSelectedItem());
						for (DTSalida s : sals)
							listaSals.addItem(s.getNombre() + " (desde: " + s.getLugarDTSalida() + ") " + s.getFechaDTSalida().get(Calendar.DAY_OF_MONTH) + "/" + s.getFechaDTSalida().get(Calendar.MONTH) + "/" + s.getFechaDTSalida().get(Calendar.YEAR));
					}
				}
				catch (actividadNoExisteException e2) {	
					JOptionPane.showMessageDialog(null, e2.getMessage(), "La actividad seleccionada no está registrada en el sistema", JOptionPane.ERROR_MESSAGE);
				}
				catch (departamentoNoExisteException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "El departamento seleccionado no está registrado en el sistema", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//Definicion de textField de usuario
		lblIngresarUser = new JLabel("Nickname del usuario:");
		textFieldUser = new JTextField();
		textFieldUser.setColumns(10);
		
		//Definicion de spinner de cantidad de personas
		lblCantUsers = new JLabel("Cantidad de personas a registrar:");
		spinnerCantUsers = new JSpinner();
		spinnerCantUsers.setToolTipText("Ingrese un número mayor a 1.");
		SpinnerNumberModel snm = new SpinnerNumberModel(1,1,10000,1);
		spinnerCantUsers.setModel(snm);
		JFormattedTextField txtSpinner=((JSpinner.DefaultEditor)spinnerCantUsers.getEditor()).getTextField(); 
		txtSpinner.setEditable(false);
		
		//Apretar botón continuar
		botonContinuar = new JButton("Continuar");
		botonContinuar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkCampos()) {
					try {
						
						String nombreUsuario = textFieldUser.getText();
						int cant = (int) spinnerCantUsers.getValue();
						String datosSalida = (String) listaSals.getSelectedItem();
					
						//Separación del nombre de la salida del resto de datos
						int pos = datosSalida.indexOf("(");
						String nombreSalida = datosSalida.substring(0,pos-1);
						GregorianCalendar fechaActual = GregorianCalendar.from(ZonedDateTime.now());
						String problema = icu.ingresarDatosInscripcion(nombreUsuario,nombreSalida,cant,fechaActual);
					
						if (problema == "lleno") {
							JOptionPane.showMessageDialog(null, "La salida no cuenta con la capacidad para la cantidad de personas registradas. Cambie la salida seleccionada o la cantidad de personas a registrar.", "Capacidad superada", JOptionPane.ERROR_MESSAGE);
						}
						else {
							if (problema == "existe") {
								JOptionPane.showMessageDialog(null, "El usuario ingresado ya está inscripto a la salida seleccionada. Cambie la salida seleccionada o ingrese otro usuario.", "Inscripción ya existente", JOptionPane.ERROR_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(null, "Usuario inscipto con éxito!", "Usuario inscripto", JOptionPane.INFORMATION_MESSAGE);
								limpiarCampos();
								cargarDptos();
							}
						}
					}
					catch(salidaNoExisteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "La salida seleccionada no está registrada en el sistema", JOptionPane.ERROR_MESSAGE);
					}
					catch(usuarioNoExisteException e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage(), "El usuario ingresado no está registrado en el sistema", JOptionPane.ERROR_MESSAGE);
					}	
				}
			}
		});
		
		//Apretar boton cancelar
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarCampos();
				setVisible(false);
			}
		});
		
		
		//Ingreso de todos los componentes en el groupLayout (realizado con el window design)
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(126)
							.addComponent(lblSeleccionarSals, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(124))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblSeleccionarDpto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblSeleccionarAct, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(listaActs, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(listaDptos, 0, 175, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblIngresarUser, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textFieldUser, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(25)
											.addComponent(botonCancelar, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
											.addGap(75))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblCantUsers, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGap(18)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(botonContinuar, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(spinnerCantUsers)
											.addGap(27)))
									.addGap(12)))
							.addGap(14))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(listaSals, 0, 382, Short.MAX_VALUE)))
					.addGap(5))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(listaDptos)
						.addComponent(lblSeleccionarDpto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(listaActs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSeleccionarAct, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(lblSeleccionarSals, GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(listaSals, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldUser)
						.addComponent(lblIngresarUser, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerCantUsers, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addComponent(lblCantUsers, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(botonCancelar, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(botonContinuar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(15))
		);
		getContentPane().setLayout(groupLayout);
		
		
	}


	private void limpiarCampos() {
		listaDptos.removeAllItems();
		listaActs.removeAllItems();
		listaSals.removeAllItems();;
		listaDptos.setSelectedItem(null);
		listaActs.setSelectedItem(null);
		listaSals.setSelectedItem(null);
		textFieldUser.setText("");
		spinnerCantUsers.setValue(1);
	}
	
	private boolean checkCampos() {
		String nombreUsuario = textFieldUser.getText();
		
		if (nombreUsuario.isEmpty())
			JOptionPane.showMessageDialog(null, "Ingrese el nickname de un usuario.", "Nickname no ingresado", JOptionPane.ERROR_MESSAGE);
		else
			if (listaSals.getSelectedItem() == null)
				JOptionPane.showMessageDialog(null, "Seleccione una salida.", "Salida no seleccionada", JOptionPane.ERROR_MESSAGE);
			else
				if ((int)spinnerCantUsers.getValue() < 1)
					JOptionPane.showMessageDialog(null, "Ingrese una cantidad de personas a registrar mayor o igual a 1.", "Cantidad inválida", JOptionPane.ERROR_MESSAGE);
		return (!nombreUsuario.isEmpty() && listaSals.getSelectedItem() != null && (int)spinnerCantUsers.getValue() >= 1);
	}


	public void cargarDptos() {
		Set<String> dptos = icd.obtenerDepartamentos();
		for (String s : dptos)
			listaDptos.addItem(s);
		listaDptos.setSelectedItem(null);
		listaActs.setSelectedItem(null);
		listaSals.setSelectedItem(null);
	}	
	
}





