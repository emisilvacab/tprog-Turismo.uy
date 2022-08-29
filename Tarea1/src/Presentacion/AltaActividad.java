package Presentacion;

import javax.swing.JInternalFrame;

import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;

import java.awt.Component;
import java.awt.event.ActionEvent;
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

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import datatypes.DTActividad;
import datatypes.DTProveedor;
import datatypes.DTTurista;
import datatypes.DTUsuario;
import excepciones.UsuarioRepetidoException;
import excepciones.departamentoNoExisteException;
import excepciones.proveedorNoExisteException;
import excepciones.salidaNoExisteException;
import excepciones.usuarioNoExisteException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JComboBox;

//@SuppressWarnings("serial")
public class AltaActividad extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private IControladorUsuario iCtrlUsuario;
	private IControladorDepartamento iCtrlDepartamento;
	
	private JTextField txfNombre;
	private JTextField txfDescripcion;
	private JFormattedTextField txfDuracion;
	private JFormattedTextField txfCosto;
	private JTextField txfCiudad;
	private JLabel labelDepartamento;
	private JComboBox<String> comboDepartamento;
	private JLabel labelProveedor;
	private JLabel lblNewLabel_1_1;
	private JComboBox<String> comboProveedor;
	private JLabel lblNewLabel_1;
	private JLabel labelNombre;
	private JLabel lblNewLabel_1_3;
	private JLabel labelDescripcion;
	private JLabel lblNewLabel_1_3_1;
	private JLabel labelDuracion;
	private JLabel lblNewLabel_1_3_2;
	private JLabel labelCosto;
	private JLabel lblNewLabel_1_3_3;
	private JLabel labelCiudad;
	private JLabel lblNewLabel_1_3_4;
	private JLabel lblNewLabel_3;
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	public AltaActividad(IControladorDepartamento IConD, IControladorUsuario IConU) {
		
		iCtrlUsuario = IConU;
		iCtrlDepartamento = IConD;
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Alta de Actividad Turistica");
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(0,0,500,350);
		getContentPane().setLayout(new GridLayout(8, 3, 3, 15));
		
		labelDepartamento = new JLabel("Departamento:");
		labelDepartamento.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(labelDepartamento);
		
		comboDepartamento = new JComboBox<String>();
		getContentPane().add(comboDepartamento);
		
		lblNewLabel_1_1 = new JLabel("");
		getContentPane().add(lblNewLabel_1_1);
		
		labelProveedor = new JLabel("Proveedor:");
		labelProveedor.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(labelProveedor);
		
		comboProveedor = new JComboBox<String>();
		getContentPane().add(comboProveedor);
		
		lblNewLabel_1 = new JLabel("");
		getContentPane().add(lblNewLabel_1);
		
		labelNombre = new JLabel("Nombre de la actividad:");
		labelNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(labelNombre);
		
		txfNombre = new JTextField();
		getContentPane().add(txfNombre);
		txfNombre.setColumns(10);
		
		lblNewLabel_1_3 = new JLabel("");
		getContentPane().add(lblNewLabel_1_3);
		
		labelDescripcion = new JLabel("Descripcion:");
		labelDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(labelDescripcion);
		
		txfDescripcion = new JTextField();
		txfDescripcion.setColumns(10);
		getContentPane().add(txfDescripcion);
		
		lblNewLabel_1_3_1 = new JLabel("");
		getContentPane().add(lblNewLabel_1_3_1);
		
		labelDuracion = new JLabel("Duracion de la actividad:");
		labelDuracion.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(labelDuracion);
		
		txfDuracion = new JFormattedTextField();
		txfDuracion.setColumns(10);
		getContentPane().add(txfDuracion);
		
		lblNewLabel_1_3_2 = new JLabel("dias");
		lblNewLabel_1_3_2.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblNewLabel_1_3_2);
		
		labelCosto = new JLabel("Costo:");
		labelCosto.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(labelCosto);
		
		txfCosto = new JFormattedTextField();
		txfCosto.setColumns(10);
		getContentPane().add(txfCosto);
		
		lblNewLabel_1_3_3 = new JLabel("");
		lblNewLabel_1_3_3.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblNewLabel_1_3_3);
		
		labelCiudad = new JLabel("Ciudad:");
		labelCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(labelCiudad);
		
		txfCiudad = new JTextField();
		txfCiudad.setColumns(10);
		getContentPane().add(txfCiudad);
		
		lblNewLabel_1_3_4 = new JLabel("");
		getContentPane().add(lblNewLabel_1_3_4);
		
		lblNewLabel_3 = new JLabel("");
		getContentPane().add(lblNewLabel_3);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkFormulario()) {
					try {
						String nombre = txfNombre.getText();
						String descripcion = txfDescripcion.getText();
						int duracion = (int) txfDuracion.getValue();
						float costo = (float) txfCosto.getValue();
						String ciudad = txfCiudad.getText();
						String departamento = (String) comboDepartamento.getSelectedItem();
						String proveedor = (String) comboProveedor.getSelectedItem();
						GregorianCalendar fecha = GregorianCalendar.from(ZonedDateTime.now());
						
						boolean existeAct = iCtrlDepartamento.ingresarDatosActividad(nombre,  descripcion, duracion, costo, ciudad, fecha, proveedor, departamento);
					
						if (existeAct) {
							JOptionPane.showMessageDialog(null, "Ya hay una actividad con el nombre "+ nombre + ". Cambie los datos para ingresar una nueva actividad.", "Actividad " + nombre + " ya existente", JOptionPane.ERROR_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Actividad dada de alta con exito!", "Nueva activiad ingresada", JOptionPane.OK_OPTION);
							limpiarFormulario();
						}
					}
					catch(departamentoNoExisteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "El departamento seleccionado no está registrado en el sistema", JOptionPane.ERROR_MESSAGE);
					}
					catch(proveedorNoExisteException e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage(), "El proveedor ingresado no está registrado en el sistema", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		getContentPane().add(btnCancelar);
		
	}
	
	private boolean checkFormulario() {
		return true;
	}
	
	private void limpiarFormulario() {
		txfNombre.setText("");
		txfDescripcion.setText("");
		txfDuracion.setText("");
		txfCosto.setText("");
		txfCiudad.setText("");
		comboDepartamento.setSelectedItem(null);
		comboProveedor.setSelectedItem(null);
    }
	
	public void cargarDepartamentos() {
		Set<String> departamentos = iCtrlDepartamento.obtenerDepartamentos();
		for (String s : departamentos)
			comboDepartamento.addItem(s);
		comboDepartamento.setSelectedItem(null);
	}
	
	public void cargarProveedores() {
		String[] proveedores = iCtrlUsuario.obtenerProveedores();
		for (String s : proveedores)
			comboProveedor.addItem(s);
		comboProveedor.setSelectedItem(null);
    }
	
}