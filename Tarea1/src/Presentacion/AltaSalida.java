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
import java.time.ZonedDateTime;
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
import excepciones.proveedorNoExisteException;
import excepciones.salidaNoExisteException;
import excepciones.salidaYaExisteException;
import excepciones.usuarioNoExisteException;

import java.awt.Button;
import javax.swing.JSpinner;

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
	private JLabel lblCant;
	private JSpinner spinnerCant;
	private JLabel lblLugar;
	private JTextField textLugar;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	
	
	public AltaSalida(IControladorDepartamento picd) {
		
		
		icd = picd;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Salida Turistica");
        setBounds(30, 30, 397, 310);
		
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
						String nombreDep = (String) listaDep.getSelectedItem();
						String nombreAct = (String) listaAct.getSelectedItem();
						GregorianCalendar fechaActual = GregorianCalendar.from(ZonedDateTime.now());
						int horaSalida = 1;// lo ingresa el usuario junto con fecha tambien DE 0 A 23 hs
						boolean existeSalida = icd.ingresarDatosSalida(nombreSalida, cantTuristas, fechaActual, new GregorianCalendar(2023, 4, 28), horaSalida, lugarSalida, nombreDep, nombreAct);
					
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
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
        		JOptionPane.showMessageDialog(null, "Alta cancelada!", "Alta de salida", JOptionPane.INFORMATION_MESSAGE);
				limpiarCampos();
				setVisible(false);
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblDep)
							.addPreferredGap(ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
							.addComponent(listaDep, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombre)
								.addComponent(lblAct)
								.addComponent(lblCant))
							.addPreferredGap(ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(spinnerCant, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textNombre)
								.addComponent(listaAct, 0, 208, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblLugar)
							.addPreferredGap(ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
							.addComponent(textLugar, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAceptar, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
						.addComponent(lblFecha))
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
					.addContainerGap(13, Short.MAX_VALUE))
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


