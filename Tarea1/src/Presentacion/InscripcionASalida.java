package Presentacion;

import javax.swing.JInternalFrame;

import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTActividad;
import logica.datatypes.DTSalida;

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
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.salidaNoExisteException;
import excepciones.usuarioNoExisteException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.ZonedDateTime;

import java.awt.event.ItemListener;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.Color;

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
	private JTextField textFieldCantUsers;
	private JTextField txfPersonas;
	
	

	public InscripcionASalida(IControladorUsuario picu, IControladorDepartamento picd) {
		
		icu = picu;
		icd = picd;
		
		Dimension dmsInternal = new Dimension(700, 417);
		setMaximumSize(dmsInternal);
		setMaximizable(true);
		setResizable(true);
	    setClosable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    setTitle("Inscripción a salida");
	    setBounds(0, 0, 410, 417);
	    
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
					if (listaActs.getSelectedItem() != null) {
						listaSals.removeAllItems();
						HashSet<DTSalida> sals = icd.obtenerDatosSalidasVigentes((String) listaActs.getSelectedItem());
						for (DTSalida s : sals)
							listaSals.addItem(s.getNombre() + " (desde: " + s.getLugarDTSalida() + ") " + s.getFechaDTSalida().get(Calendar.DAY_OF_MONTH) + "/" + (s.getFechaDTSalida().get(Calendar.MONTH)+1) + "/" + s.getFechaDTSalida().get(Calendar.YEAR));
					}
				}
				catch (actividadNoExisteException e2) {	
					JOptionPane.showMessageDialog(null, e2.getMessage(), "La actividad seleccionada no está registrada en el sistema", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//Evento en listaSals que carga cantidad de lugares disponibles en salida 
		listaSals.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (listaSals.getSelectedItem() != null) {
					try {
						
						String datosSalida = (String) listaSals.getSelectedItem();
						
						//Separación del nombre de la salida del resto de datos
						int pos = datosSalida.indexOf("(");
						String nombreSalida = datosSalida.substring(0, pos-1);
						
						int cant = icd.obtenerlugaresDisponibles(nombreSalida);
						
						if (cant == 0) {
							textFieldCantUsers.setBackground(new Color(240, 128, 128));
							textFieldCantUsers.setText("No quedan lugares disponibles para esta salida!");
						}
						if (cant > 0) {
							textFieldCantUsers.setBackground(new Color(152, 251, 152));
							textFieldCantUsers.setText("Aún quedan " + cant + " lugares disponibles en esta salida");
						}
					} catch (salidaNoExisteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "La salida seleccionada no está registrada en el sistema", JOptionPane.ERROR_MESSAGE);
					}
				}
					
			}
		});
		
		//Definicion de textFieldd de usuario
		lblIngresarUser = new JLabel("Nickname del usuario:");
		textFieldUser = new JTextField();
		textFieldUser.setColumns(10);
		
		lblCantUsers = new JLabel("Cantidad de personas a registrar:");
		
		//Apretar botón continuar
		botonContinuar = new JButton("Continuar");
		botonContinuar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkCampos()) {
					try {
						
						String nombreUsuario = textFieldUser.getText();
						int cant = Integer.parseInt(txfPersonas.getText());
						String datosSalida = (String) listaSals.getSelectedItem();
					
						//Separación del nombre de la salida del resto de datos
						int pos = datosSalida.indexOf("(");
						String nombreSalida = datosSalida.substring(0, pos-1);
						
						GregorianCalendar fechaActual = GregorianCalendar.from(ZonedDateTime.now());
						String problema = icu.ingresarDatosInscripcion(nombreUsuario, nombreSalida, cant, fechaActual);
					
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
			        			setVisible(false);
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
		
		//Definicion de textField que indica cantidad de usuarios restantes
		textFieldCantUsers = new JTextField();
		textFieldCantUsers.setBackground(Color.WHITE);
		textFieldCantUsers.setColumns(10);
		textFieldCantUsers.setEditable(false);
		
		txfPersonas = new JTextField();
		txfPersonas.setColumns(10);
		
		
		
		//Ingreso de todos los componentes en el groupLayout (realizado con el window design)
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblSeleccionarDpto, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
								.addComponent(lblSeleccionarAct, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(listaActs, 0, 167, Short.MAX_VALUE)
								.addComponent(listaDptos, 0, 167, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(25)
							.addComponent(botonCancelar, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
							.addGap(75)
							.addComponent(botonContinuar, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
							.addGap(12))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCantUsers, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txfPersonas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(43))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(textFieldCantUsers, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblIngresarUser, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textFieldUser, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)))
									.addGap(6)))))
					.addGap(19))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(122)
					.addComponent(lblSeleccionarSals, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
					.addGap(126))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(3)
					.addComponent(listaSals, 0, 381, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(listaDptos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSeleccionarDpto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(listaActs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSeleccionarAct, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSeleccionarSals, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(listaSals, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIngresarUser, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textFieldCantUsers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCantUsers, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(txfPersonas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
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
		textFieldCantUsers.setText("");
		textFieldCantUsers.setBackground(Color.WHITE);
		txfPersonas.setText("");
	}
	
	private boolean checkCampos() {
		String nombreUsuario = textFieldUser.getText();
		
		boolean ans = true;
		
		if (nombreUsuario.isEmpty())
			JOptionPane.showMessageDialog(null, "Ingrese el nickname de un usuario.", "Nickname no ingresado", JOptionPane.ERROR_MESSAGE);
		else
			if (listaSals.getSelectedItem() == null)
				JOptionPane.showMessageDialog(null, "Seleccione una salida.", "Salida no seleccionada", JOptionPane.ERROR_MESSAGE);
			else {
				String datosSalida = (String) listaSals.getSelectedItem();
				int pos = datosSalida.indexOf("(");
				String nombreSalida = datosSalida.substring(0, pos-1);
				try {
					int cant = icd.obtenerlugaresDisponibles(nombreSalida);
					try {
			            int pers = Integer.parseInt(txfPersonas.getText());
			            if (pers <= 0) {
			            	JOptionPane.showMessageDialog(null, "Ingrese una cantidad de personas a registrar mayor o igual a 1.", "Cantidad inválida", JOptionPane.ERROR_MESSAGE);
			            	ans = false;
			            } else
			            	if (pers > cant) {
			            		JOptionPane.showMessageDialog(null, "Ingrese una cantidad de personas a registrar menor o igual que la cantidad disponible.", "Cantidad inválida", JOptionPane.ERROR_MESSAGE);
				            	ans = false;
			            	}
			        } catch (NumberFormatException e) {
			        	JOptionPane.showMessageDialog(null, "Ingrese una cantidad de personas a registrar mayor o igual a 1.", "Cantidad inválida", JOptionPane.ERROR_MESSAGE);
			            ans = false;
			        }
				} catch (salidaNoExisteException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "La salida seleccionada no está registrada en el sistema", JOptionPane.ERROR_MESSAGE);
				}
			}
		return (!nombreUsuario.isEmpty() && listaSals.getSelectedItem() != null && ans);
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







