package Presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import logica.controladores.IControladorDepartamento;
import logica.datatypes.DTActividad;
import logica.datatypes.DTSalida;

public class ConsultaDeActividad extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ConsultaDeSalida consultaDeSalida;
	
	private IControladorDepartamento cDpto;
	private JTextField textFieldNombre;
	private JTextArea textAreaDescripcion;
	private JTextField textFieldDuracion;
	private JTextField textFieldCosto;
	private JTextField textFieldCiudad;
	private JTextField textFieldFechaAlta;
	private JButton buttonInfo;
	
	private JComboBox<String> comboBoxDepartamento;
	private JComboBox<String> comboBoxActividad;
	private JComboBox<String> comboBoxSalida;
	
	private JLabel tagSalidas;
	private JLabel tagDepartamento;
	private JLabel tagActividad;
	private String actividadSeleccionada;
	private String departamentoSeleccionado;
	
	/**
	 * Create the frame.
	 */
	public ConsultaDeActividad(IControladorDepartamento icd) {
		
		consultaDeSalida = new ConsultaDeSalida(icd);
		this.getContentPane().add(consultaDeSalida);
		
		setMaximizable(true);
		setClosable(true);
		setResizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		cDpto = icd;
		setTitle("Consulta de Actividad");
		setBounds(100, 100, 499, 511);
		
		addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
				setVisible(false);
				limpiarFormulario();
				comboBoxDepartamento.removeAllItems();
				comboBoxActividad.removeAllItems();
				comboBoxSalida.removeAllItems();
				
				comboBoxDepartamento.setSelectedItem(null);
				comboBoxActividad.setSelectedItem(null);
				comboBoxSalida.setSelectedItem(null);
				
				comboBoxActividad.setEnabled(false);
				comboBoxSalida.setEnabled(false);
            }
        });
		
		tagDepartamento = new JLabel("Departamento");
		tagDepartamento.setHorizontalAlignment(SwingConstants.RIGHT);
		
		comboBoxDepartamento = new JComboBox<String>();
		
		comboBoxDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxActividad.setEnabled(false);				
				comboBoxActividad.removeAllItems();				
				//comboBoxActividad.setSelectedItem(null);
				
				comboBoxSalida.setEnabled(false);				
				comboBoxSalida.removeAllItems();				
				//comboBoxSalida.setSelectedItem(null);
				
				departamentoSeleccionado = (String) comboBoxDepartamento.getSelectedItem();
				if(comboBoxDepartamento.getSelectedItem() != null) {
					cargarActividades();
					comboBoxActividad.setEnabled(true);
				}
				limpiarFormulario();
				mostrarActividad();
				
				
			}
		});
		
		tagActividad = new JLabel("Actividad");
		tagActividad.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		comboBoxActividad = new JComboBox<String>();
		comboBoxActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBoxSalida.setEnabled(false);
				comboBoxSalida.setSelectedItem(null);
				comboBoxSalida.removeAllItems();
				actividadSeleccionada = (String) comboBoxActividad.getSelectedItem();
				if(actividadSeleccionada != null) {
					mostrarActividad();
					cargarSalidas();
					comboBoxSalida.setEnabled(true);
				}else {
					limpiarFormulario();
				}
			
			}});
		
		comboBoxActividad.setEnabled(false);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		
		JLabel lblNewLabel_2 = new JLabel("Descripci\u00F3n");
		
		JLabel lblNewLabel_3 = new JLabel("Duraci\u00F3n");
		
		JLabel lblNewLabel_4 = new JLabel("Costo");
		
		JLabel lblNewLabel_5 = new JLabel("Ciudad");
		
		JLabel lblNewLabel_6 = new JLabel("Fecha de alta");
		
		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setColumns(10);
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setWrapStyleWord(true);
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setEditable(false);
		textAreaDescripcion.setColumns(10);
		
		textFieldDuracion = new JTextField();
		textFieldDuracion.setEditable(false);
		textFieldDuracion.setColumns(10);
		
		textFieldCosto = new JTextField();
		textFieldCosto.setEditable(false);
		textFieldCosto.setColumns(10);
		
		textFieldCiudad = new JTextField();
		textFieldCiudad.setEditable(false);
		textFieldCiudad.setColumns(10);
		
		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setEditable(false);
		textFieldFechaAlta.setColumns(10);
		
		tagSalidas = new JLabel("Salidas");
		
		comboBoxSalida = new JComboBox<String>();
		comboBoxSalida.setEnabled(false);
		
		buttonInfo = new JButton("Ver");
		buttonInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	if(comboBoxSalida.getSelectedItem() != null)
            		mostrarInfo();
            }
        });
		
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tagSalidas, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
										.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
										.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
										.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(tagActividad, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(tagDepartamento, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textFieldFechaAlta)
										.addComponent(textFieldCosto)
										.addComponent(textFieldDuracion)
										.addComponent(comboBoxSalida, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(buttonInfo, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(textAreaDescripcion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
										.addComponent(textFieldNombre, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(comboBoxActividad, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(comboBoxDepartamento, 0, 129, Short.MAX_VALUE))))
									.addGap(40))
								.addComponent(textFieldCiudad))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tagDepartamento)
						.addComponent(comboBoxDepartamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tagActividad)
						.addComponent(comboBoxActividad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textAreaDescripcion, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textFieldDuracion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(textFieldCosto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(textFieldCiudad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldFechaAlta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tagSalidas)
						.addComponent(comboBoxSalida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonInfo))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
	
	//Funciones
	
	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textAreaDescripcion.setText("");
		textFieldDuracion.setText("");
		textFieldCosto.setText("");
		textFieldCiudad.setText("");
		textFieldFechaAlta.setText("");
	}
	
	public void cargarDepartamentos() {
		Set<String> dptos = cDpto.obtenerDepartamentos();
		for (String s : dptos)
			comboBoxDepartamento.addItem(s);
		comboBoxDepartamento.setSelectedItem(null);
		comboBoxActividad.setSelectedItem(null);
		
		comboBoxActividad.setEnabled(false);
		limpiarFormulario();
	}
	
	private void cargarActividades() {
		//try
		if (comboBoxDepartamento.getSelectedItem() != null) {
			try {
				HashSet<DTActividad> acts = cDpto.obtenerDatosActividadesAsociadas((String) comboBoxDepartamento.getSelectedItem());
				for (DTActividad a : acts)
					comboBoxActividad.addItem(a.getNombre());
			}catch(departamentoNoExisteException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Departamento invalido", JOptionPane.ERROR_MESSAGE);
			}
									
		}	
		
	}
	
	private void mostrarActividad() {
		if (comboBoxDepartamento.getSelectedItem() != null && actividadSeleccionada != null) {
			try {
				HashSet<DTActividad> acts = cDpto.obtenerDatosActividadesAsociadas((String) comboBoxDepartamento.getSelectedItem());
				DTActividad actividad = null;
				
				for(DTActividad it : acts) {
					if(it.getNombre() == actividadSeleccionada)
						actividad = it;
				}
				
				textFieldNombre.setText(actividad.getNombre());
				textAreaDescripcion.setText(actividad.getDescripcion());
				Integer duracion = actividad.getDuracion();
				textFieldDuracion.setText(duracion.toString());
				textFieldCosto.setText(Float.toString(actividad.getCosto()));
				textFieldCiudad.setText(actividad.getCiudad());
				cargarSalidas();
				comboBoxSalida.setVisible(true);
				GregorianCalendar fechaAlta = actividad.getAlta();
		        
		       
		        
		        Integer diaA = fechaAlta.get(fechaAlta.DAY_OF_MONTH);
		        Integer mesA = fechaAlta.get(fechaAlta.MONTH) + 1;
		        Integer anioA = fechaAlta.get(fechaAlta.YEAR);
		        String fechaAltaString = diaA.toString()+"/"+mesA.toString()+"/"+anioA.toString();
				
				textFieldFechaAlta.setText(fechaAltaString);
				
				
				
				
			}catch(departamentoNoExisteException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Departamento invalido", JOptionPane.ERROR_MESSAGE);
			}
									
		}
	}
	
	
	private void cargarSalidas() {
		comboBoxSalida.removeAllItems();
		DefaultComboBoxModel<String> model;
		if (actividadSeleccionada != null) {
			try {
				if (actividadSeleccionada!=null) {
					HashSet<DTSalida> salidas = cDpto.obtenerDatosSalidasParaActividad(actividadSeleccionada);
					model = new DefaultComboBoxModel<String>(obtenerNombreSalidas(salidas));
					comboBoxSalida.setModel(model);
					
				}
			}catch(actividadNoExisteException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Actividad inválida", JOptionPane.ERROR_MESSAGE);
			}
									
		}
				
	}
	
	private String[] obtenerNombreSalidas(HashSet<DTSalida> salidas) {
		HashSet<String> resu = new HashSet<String>();
		for (DTSalida salida: salidas) {
			resu.add(salida.getNombre());
		}
		return resu.toArray(String[]::new);
	
	}

	private void mostrarInfo() {
		consultaDeSalida.setVisible(true);
		String departamento = departamentoSeleccionado;
		String actividad = actividadSeleccionada;
		String salida = (String) comboBoxSalida.getSelectedItem();
		if (departamento!=null && actividad!=null && salida!=null) {
			consultaDeSalida.mostrar(departamento, actividad, salida);
		}
			
	}
	
	public void mostrarDT(DTActividad actividad) {
		textFieldNombre.setText(actividad.getNombre());
		textAreaDescripcion.setText(actividad.getDescripcion());
		
		Integer duracion = actividad.getDuracion();
		textFieldDuracion.setText(duracion.toString());
		
		String costo = String.valueOf(actividad.getCosto());
		textFieldCosto.setText(costo);
		textFieldCiudad.setText(actividad.getCiudad());
		
		GregorianCalendar fechaAlta = actividad.getAlta();
		

		actividadSeleccionada = actividad.getNombre();
		departamentoSeleccionado = cDpto.obtenerDeptoActividad(actividadSeleccionada);
		comboBoxSalida.setEnabled(true);
		comboBoxSalida.setVisible(true);
		cargarSalidas();
        
        Integer diaA = fechaAlta.get(fechaAlta.DAY_OF_MONTH);
        Integer mesA = fechaAlta.get(fechaAlta.MONTH) + 1;
        Integer anioA = fechaAlta.get(fechaAlta.YEAR);
        String fechaAltaString = diaA.toString()+"/"+mesA.toString()+"/"+anioA.toString();
		
		textFieldFechaAlta.setText(fechaAltaString);
		
		comboBoxDepartamento.setVisible(false);
		comboBoxActividad.setVisible(false);
		
		tagDepartamento.setVisible(false);
		tagActividad.setVisible(false);

		
		
	}
	
	
	
}
