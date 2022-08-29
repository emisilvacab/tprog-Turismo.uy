package Presentacion;

import javax.swing.JInternalFrame;

import excepciones.actividadNoExisteException;

import logica.controladores.IControladorDepartamento;
import logica.datatypes.DTActividad;
import logica.datatypes.DTSalida;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ConsultaDeActividad extends JInternalFrame {
	private ConsultaDeSalida consultaDeSalida;
	
	private IControladorDepartamento cDpto;
	private JTextField textFieldNombre;
	private JTextArea textAreaDescripcion;
	private JTextField textFieldDuracion;
	private JTextField textFieldCosto;
	private JTextField textFieldCiudad;
	private JTextField textFieldFechaAlta;
	
	private JComboBox<String> comboBoxDepartamento;
	private JComboBox<String> comboBoxActividad;
	private JComboBox<String> comboBoxSalida;
	
	/**
	 * Create the frame.
	 */
	public ConsultaDeActividad(IControladorDepartamento icd) {
		
		consultaDeSalida = new ConsultaDeSalida(icd);
		this.getContentPane().add(consultaDeSalida);
		
		setMaximizable(true);
		setClosable(true);
		cDpto = icd;
		setTitle("Consulta de Actividad");
		setBounds(100, 100, 499, 453);
		
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
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setHorizontalAlignment(SwingConstants.RIGHT);
		
		comboBoxDepartamento = new JComboBox<String>();
		
		comboBoxDepartamento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxActividad.setEnabled(false);				
				comboBoxActividad.removeAllItems();				
				//comboBoxActividad.setSelectedItem(null);
				
				comboBoxSalida.setEnabled(false);				
				comboBoxSalida.removeAllItems();				
				//comboBoxSalida.setSelectedItem(null);
				
				
				if(comboBoxDepartamento.getSelectedItem() != null) {
					cargarActividades();
					comboBoxActividad.setEnabled(true);
				}
				limpiarFormulario();
				mostrarActividad();
				
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("Actividad");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		comboBoxActividad = new JComboBox<String>();
		comboBoxActividad.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				comboBoxSalida.setEnabled(false);
				comboBoxSalida.setSelectedItem(null);
				comboBoxSalida.removeAllItems();
				
				if(comboBoxActividad.getSelectedItem() != null) {
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
		
		JLabel lblNewLabel_7 = new JLabel("Salidas");
		
		comboBoxSalida = new JComboBox<String>();
		comboBoxSalida.setEnabled(false);
		
		JButton buttonInfo = new JButton("Ver");
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
						.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
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
									.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblDepartamento, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)))
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
						.addComponent(lblDepartamento)
						.addComponent(comboBoxDepartamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
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
						.addComponent(lblNewLabel_7)
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
		if (comboBoxDepartamento.getSelectedItem() != null && comboBoxActividad.getSelectedItem() != null) {
			try {
				HashSet<DTActividad> acts = cDpto.obtenerDatosActividadesAsociadas((String) comboBoxDepartamento.getSelectedItem());
				DTActividad actividad = null;
				
				for(DTActividad it : acts) {
					if(it.getNombre() == comboBoxActividad.getSelectedItem())
						actividad = it;
				}
				
				textFieldNombre.setText(actividad.getNombre());
				textAreaDescripcion.setText(actividad.getDescripcion());
				Integer duracion = actividad.getDuracion();
				textFieldDuracion.setText(duracion.toString());
				textFieldCosto.setText(Float.toString(actividad.getCosto()));
				textFieldCiudad.setText(actividad.getCiudad());
				
				GregorianCalendar fechaAlta = actividad.getAlta();
		        
		       
		        
		        Integer diaA = fechaAlta.get(fechaAlta.DAY_OF_MONTH);
		        Integer mesA = fechaAlta.get(fechaAlta.MONTH);
		        Integer anioA = fechaAlta.get(fechaAlta.YEAR);
		        String fechaAltaString = diaA.toString()+"/"+mesA.toString()+"/"+anioA.toString();
				
				textFieldFechaAlta.setText(fechaAltaString);
				
				
				
				
			}catch(departamentoNoExisteException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Departamento invalido", JOptionPane.ERROR_MESSAGE);
			}
									
		}
	}
	
	private void cargarSalidas() {
		//try
		if (comboBoxActividad.getSelectedItem() != null) {
			try {
				HashSet<DTSalida> sals = cDpto.obtenerDatosSalidasVigentes((String) comboBoxActividad.getSelectedItem(),(String) comboBoxDepartamento.getSelectedItem());
				for (DTSalida s : sals)
					comboBoxSalida.addItem(s.getNombre());
			}catch(actividadNoExisteException | departamentoNoExisteException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Actividad o departamento invalido", JOptionPane.ERROR_MESSAGE);
			}
									
		}
				
	}
	
	private void mostrarInfo() {
		consultaDeSalida.setVisible(true);
		consultaDeSalida.mostrar((String) comboBoxDepartamento.getSelectedItem(), (String) comboBoxActividad.getSelectedItem(),(String) comboBoxSalida.getSelectedItem());
			
	}
	
	
	
}
