package Presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import javax.swing.SwingConstants;

import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTActividad;

import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.proveedorNoExisteException;
import excepciones.salidaNoExisteException;
import excepciones.salidaYaExisteException;
import excepciones.usuarioNoExisteException;

import java.awt.Button;
import javax.swing.JSpinner;
import javax.swing.SpringLayout;

public class AltaSalida extends JInternalFrame{
	

	private static final long serialVersionUID = 1L;
	private IControladorDepartamento icd;
	
	private JLabel lblDep;
	private JComboBox<String> listaDep;
	private JLabel lblAct;
	private JComboBox<String> listaAct;
	private JLabel lblNombre;
	private JTextField textNombre;
	private JLabel lblFecha;
	private JDatePickerImpl datePicker;
	private JLabel lblHora;
	private JSpinner spinnerHora;
	private JLabel lblCant;
	private JSpinner spinnerCant;
	private JLabel lblLugar;
	private JTextField textLugar;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	private SpringLayout springLayout;
	
	
	
	public AltaSalida(IControladorDepartamento picd) {
		
		
		icd = picd;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Salida Turistica");
        setBounds(30, 30, 430, 325);
		
	    addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
            	limpiarCampos();
				setVisible(false);
            }
        });
		
		lblDep = new JLabel("Seleccione un departamento:");		
		listaDep = new JComboBox<String>();
		
		lblAct = new JLabel("Selecccione una actividad:");	
		listaAct = new JComboBox<String>();
		
		listaDep.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					if (listaDep.getSelectedItem() != null) {
						listaAct.removeAllItems();
						HashSet<DTActividad> acts = icd.obtenerDatosActividadesAsociadas((String) listaDep.getSelectedItem());
						for (DTActividad a : acts)
							listaAct.addItem(a.getNombre());
					}
				}
				catch(departamentoNoExisteException e1){
					JOptionPane.showMessageDialog(null, e1.getMessage(), "El departamento seleccionado no está registrado en el sistema", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		lblNombre = new JLabel("Nombre de la nueva Salida:");	
		textNombre = new JTextField();
		textNombre.setColumns(10);
		
		lblCant = new JLabel("Cantidad de Turistas:");
		spinnerCant = new JSpinner();
		spinnerCant.setToolTipText("Ingrese un número mayor a 0.");
		SpinnerNumberModel snm = new SpinnerNumberModel(1,1,10000,1);
		spinnerCant.setModel(snm);
		JFormattedTextField txtSpinner=((JSpinner.DefaultEditor)spinnerCant.getEditor()).getTextField(); 
		txtSpinner.setEditable(false);
		
		
		lblHora = new JLabel("Hora de Salida:");
		lblHora.setHorizontalAlignment(SwingConstants.LEFT);
		
		spinnerHora = new JSpinner();
		spinnerHora.setToolTipText("Ingrese un número entre 0 y 23");
		spinnerHora.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		JFormattedTextField txtSpinner1=((JSpinner.DefaultEditor)spinnerHora.getEditor()).getTextField(); 
		txtSpinner1.setEditable(false);
		
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		
		lblFecha = new JLabel("Fecha de Salida:");
		
		lblLugar = new JLabel("Lugar de Salida:");
		lblLugar.setHorizontalAlignment(SwingConstants.LEFT);
		lblLugar.setVerticalAlignment(SwingConstants.TOP);
		textLugar = new JTextField();
		textLugar.setColumns(10);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.getJFormattedTextField().setHorizontalAlignment(SwingConstants.LEFT);
		springLayout = (SpringLayout) datePicker.getLayout();
		datePicker.setShowYearButtons(true);
		datePicker.setVisible(true);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
        		JOptionPane.showMessageDialog(null, "Alta cancelada!", "Alta de salida", JOptionPane.INFORMATION_MESSAGE);
				limpiarCampos();
				setVisible(false);
			}
		});
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (checkCampos()) {
					try {
						
						String nombreSalida = textNombre.getText();
						int cantTuristas = (int) spinnerCant.getValue();
						int horaSalida = (int) spinnerHora.getValue();
						String lugarSalida = textLugar.getText();
						String nombreDep = (String) listaDep.getSelectedItem();
						String nombreAct = (String) listaAct.getSelectedItem();
						GregorianCalendar fechaSalida = new GregorianCalendar(datePicker.getModel().getYear(), datePicker.getModel().getMonth(), datePicker.getModel().getDay());						
						GregorianCalendar fechaActual = GregorianCalendar.from(ZonedDateTime.now());
						
						boolean existeSalida = icd.ingresarDatosSalida(nombreSalida, cantTuristas, fechaActual, fechaSalida, horaSalida, lugarSalida, nombreDep, nombreAct);
					
						if (existeSalida) {
							JOptionPane.showMessageDialog(null, "Ya existe una salida con el mismo nombre reingrese los datos.", "Ya existe", JOptionPane.ERROR_MESSAGE);
						}
						else {
								JOptionPane.showMessageDialog(null, "Salida ingresada con éxito!", "Salida ingresada", JOptionPane.INFORMATION_MESSAGE);
								limpiarCampos();
								cargarDptos();
						}
						
					}
					catch(actividadNoExisteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "La actividad seleccionada no está registrada en el sistema", JOptionPane.ERROR_MESSAGE);
					}
					catch(proveedorNoExisteException e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage(), "El proveedor seleccionado no está registrado en el sistema", JOptionPane.ERROR_MESSAGE);
					}	
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDep)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(listaDep, 0, 254, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFecha)
							.addGap(67)
							.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombre)
								.addComponent(lblCant)
								.addComponent(lblAct))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(listaAct, 0, 255, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(spinnerCant, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(textNombre, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblLugar)
								.addGap(65)
								.addComponent(textLugar))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblHora)
								.addGap(72)
								.addComponent(spinnerHora, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDep)
						.addComponent(listaDep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAct)
						.addComponent(listaAct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCant)
						.addComponent(spinnerCant, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblFecha))
						.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(7)
							.addComponent(lblHora))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(spinnerHora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textLugar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLugar))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAceptar)
						.addComponent(btnCancelar)))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	private void limpiarCampos() {
		listaDep.removeAllItems();
		listaAct.removeAllItems();
		listaDep.setSelectedItem(null);
		listaAct.setSelectedItem(null);
		textNombre.setText("");
		spinnerCant.setValue(1);
		textLugar.setText("");
	}
	
	private boolean checkCampos() {
		String nombreSalida = textNombre.getText();
		String lugarSalida = textLugar.getText();
		
		if (nombreSalida.isEmpty())
			JOptionPane.showMessageDialog(null, "Ingrese el nombre de la salida.", "Salida no ingresada", JOptionPane.ERROR_MESSAGE);
		if (listaAct.getSelectedItem() == null)
			JOptionPane.showMessageDialog(null, "Seleccione una actividad.", "Actividad no seleccionada", JOptionPane.ERROR_MESSAGE);
		if (lugarSalida.isEmpty())
			JOptionPane.showMessageDialog(null, "Ingrese un lugar.", "Lugar no ingresado", JOptionPane.ERROR_MESSAGE);
		if ((int)spinnerCant.getValue() < 1)
			JOptionPane.showMessageDialog(null, "Ingrese una cantidad de personas a registrar mayor o igual a 1.", "Cantidad inválida", JOptionPane.ERROR_MESSAGE);
		return (!nombreSalida.isEmpty() && listaAct.getSelectedItem() != null && (int)spinnerCant.getValue() >= 1 && !lugarSalida.isEmpty());
	}
	
	public void cargarDptos() {
		Set<String> dptos = icd.obtenerDepartamentos();
		for (String s : dptos)
			listaDep.addItem(s);
		listaDep.setSelectedItem(null);
		listaAct.setSelectedItem(null);
	}	
}


