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

import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import datatypes.DTActividad;
import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.salidaNoExisteException;
import excepciones.salidaYaExisteException;
import excepciones.usuarioNoExisteException;

import java.awt.Button;
import javax.swing.JSpinner;

public class AltaSalida extends JInternalFrame{
	
	private IControladorDepartamento icd;
	
	private JLabel lblDep;
	private JComboBox<String> listaDep;
	private JLabel lblAct;
	private JComboBox<String> listaAct;
	private JLabel lblNombre;
	private JTextField textNombre;
	private JLabel lblFecha;
	private JLabel lblCant;
	private JSpinner spinnerCant;
	private JLabel lblLugar;
	private JTextField textLugar;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	
	
	public AltaSalida(IControladorDepartamento picd) {
		
		icd = picd;
		
		setTitle("Alta de Salida Turistica");
		//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		
		
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
		
		//Evento en listaDptos que carga lista de actividades
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
		
		
		lblFecha = new JLabel("Fecha de Salida:");
		
		lblLugar = new JLabel("Lugar de Salida:");
		textLugar = new JTextField();
		textLugar.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		
		btnAceptar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (checkCampos()) {
					try {
						
						String nombreSalida = textNombre.getText();
						int cantTuristas = (int) spinnerCant.getValue();
						String lugarSalida = textLugar.getText();
						
						String datosDep = (String) listaDep.getSelectedItem();
						//Separacion del nombre del departamento del resto de datos
						int pos = datosDep.indexOf("(");
						String nombreDep = datosDep.substring(0,pos-1);
						
						String datosAct = (String) listaAct.getSelectedItem();
						//Separacion del nombre de la actividad del resto de datos
						int pos2 = datosAct.indexOf("(");
						String nombreAct = datosAct.substring(0,pos2-1);
							
						boolean existeSalida = icd.ingresarDatosSalida(nombreSalida, cantTuristas, new GregorianCalendar(), lugarSalida, nombreDep, nombreAct);
					
						if (existeSalida) {
							JOptionPane.showMessageDialog(null, "Ya existe una salida con el mismo nombre reingrese los datos.", "Ya existe", JOptionPane.ERROR_MESSAGE);
						}
						else {
								JOptionPane.showMessageDialog(null, "Salida ingresada con éxito!", "Salida ingresada", JOptionPane.OK_OPTION);
								limpiarCampos();
								cargarDptos();
						}
						
					}
					catch(actividadNoExisteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "La actividad seleccionada no está registrada en el sistema", JOptionPane.ERROR_MESSAGE);
					}
					catch(salidaYaExisteException e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage(), "La salida ya existe", JOptionPane.ERROR_MESSAGE);
					}	
				}
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		
		btnCancelar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				limpiarCampos();
				setVisible(false);
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDep)
							.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
							.addComponent(listaDep, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombre)
								.addComponent(lblAct)
								.addComponent(lblCant))
							.addPreferredGap(ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(spinnerCant)
								.addComponent(textNombre)
								.addComponent(listaAct, 0, 208, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblLugar, Alignment.LEADING)
										.addComponent(btnAceptar, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
									.addGap(18))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblFecha)
									.addGap(125)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textLugar, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
								.addComponent(btnCancelar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDep)
						.addComponent(listaDep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(listaAct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAct))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCant)
						.addComponent(spinnerCant, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblFecha)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLugar)
						.addComponent(textLugar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAceptar)
						.addComponent(btnCancelar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
		
		if (nombreSalida.isEmpty())
			JOptionPane.showMessageDialog(null, "Ingrese el nombre de la salida.", "Salida no ingresada", JOptionPane.ERROR_MESSAGE);
		else
			if (listaAct.getSelectedItem() == null)
				JOptionPane.showMessageDialog(null, "Seleccione una actividad.", "Actividad no seleccionada", JOptionPane.ERROR_MESSAGE);
			else
				if ((int)spinnerCant.getValue() < 1)
					JOptionPane.showMessageDialog(null, "Ingrese una cantidad de personas a registrar mayor o igual a 1.", "Cantidad inválida", JOptionPane.ERROR_MESSAGE);
		return (!nombreSalida.isEmpty() && listaAct.getSelectedItem() != null && (int)spinnerCant.getValue() >= 1);
	}
	
	public void cargarDptos() {
		Set<String> dptos = icd.obtenerDepartamentos();
		for (String s : dptos)
			listaDep.addItem(s);
		listaDep.setSelectedItem(null);
		listaAct.setSelectedItem(null);
	}	
}


