package Presentacion;

import javax.swing.JInternalFrame;

import logica.controladores.IControladorUsuario;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import excepciones.UsuarioRepetidoException;

import javax.swing.JTextField;
import javax.swing.JButton;

public class AltaUsuario extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private IControladorUsuario contrUsers;
	private Boolean esTurista;
	private JTextField nombreField;
	private JTextField apellidoField;
	private JTextField nicknameField;
	private JTextField correoField;
	private JTextField nacionalidadField;
	private JTextField descripcionField;
	private JTextField linkField;
	private JDatePickerImpl datePicker;
	private JRadioButton turistaButton;
    private JRadioButton proveedorButton;
    private String nombre;
	private String nickname;
	private String apellido;
	private String correo;
	private GregorianCalendar fecha;
	private String nacionalidad;
	private String link;
	private String descripcion;
	
	public AltaUsuario(IControladorUsuario icu) {
		contrUsers = icu;
		setResizable(true);
        setMaximizable(true);
        setClosable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Agregar un usuario");
        setBounds(30, 30, 530, 330);
        
        JLabel tipoUserLabel = new JLabel("Indique el tipo de usuario");
        tipoUserLabel.setBounds(135, 12, 159, 14);
        
        turistaButton = new JRadioButton("Turista");
        turistaButton.setBounds(6, 33, 110, 23);
        
        proveedorButton = new JRadioButton("Proveedor");
        proveedorButton.setBounds(298, 33, 110, 23);
        
        JLabel nombreLabel = new JLabel("Nombre: ");
        nombreLabel.setBounds(11, 65, 88, 14);
        
        nombreField = new JTextField();
        nombreField.setBounds(99, 59, 96, 20);
        nombreField.setColumns(10);
        
        JLabel apellidoLabel = new JLabel("Apellido: ");
        apellidoLabel.setBounds(298, 65, 79, 14);
        
        apellidoField = new JTextField();
        apellidoField.setBounds(387, 59, 96, 20);
        apellidoField.setColumns(10);
        
        JLabel nicknameLabel = new JLabel("Nickname: ");
        nicknameLabel.setBounds(11, 95, 88, 14);
        
        nicknameField = new JTextField();
        nicknameField.setBounds(99, 89, 96, 20);
        nicknameField.setColumns(10);
        
        JLabel correoLabel = new JLabel("Correo: ");
        correoLabel.setBounds(11, 125, 88, 14);
        
        correoField = new JTextField();
        correoField.setBounds(99, 119, 96, 20);
        correoField.setColumns(10);
        
        JLabel nacionalidadLabel = new JLabel("Nacionalidad: ");
        nacionalidadLabel.setBounds(11, 155, 88, 14);
        nacionalidadLabel.setVisible(false);
        
        nacionalidadField = new JTextField();
        nacionalidadField.setBounds(99, 149, 96, 20);
        nacionalidadField.setVisible(false);
        nacionalidadField.setColumns(10);
        
        JLabel descripcionLabel = new JLabel("Descripcion: ");
        descripcionLabel.setBounds(298, 125, 81, 14);
        descripcionLabel.setVisible(false);
        
        descripcionField = new JTextField();
        descripcionField.setBounds(387, 119, 96, 20);
        descripcionField.setColumns(10);
        descripcionField.setVisible(false);
        
        JLabel linkLabel = new JLabel("Link: ");
        linkLabel.setBounds(298, 155, 81, 14);
        linkLabel.setVisible(false);
        
        linkField = new JTextField();
        linkField.setBounds(387, 149, 96, 20);
        linkField.setColumns(10);
        linkField.setVisible(false);
        
        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setBounds(298, 181, 185, 23);
        
        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.setBounds(11, 181, 184, 23);
        
        ButtonGroup turOProv = new ButtonGroup();
        turOProv.add(proveedorButton);
        turOProv.add(turistaButton);
        
        proveedorButton.addActionListener( new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		esTurista = false;
        		nacionalidadLabel.setVisible(false);
        		nacionalidadField.setVisible(false);
        		linkLabel.setVisible(true);
        		linkField.setVisible(true);
        		descripcionLabel.setVisible(true);
        		descripcionField.setVisible(true);
        	}
        });
        
        turistaButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		esTurista = true;
        		nacionalidadLabel.setVisible(true);
        		nacionalidadField.setVisible(true);
        		linkLabel.setVisible(false);
        		linkField.setVisible(false);
        		descripcionLabel.setVisible(false);
        		descripcionField.setVisible(false);
        	}
        });
        cancelarButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null, "Alta cancelada!", "Alta de usuario",
                        JOptionPane.INFORMATION_MESSAGE);
        		setVisible(false);
        		limpiarFormulario();
        	}
        });
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        getContentPane().setLayout(null);
        getContentPane().add(tipoUserLabel);
        getContentPane().add(turistaButton);
        getContentPane().add(nombreLabel);
        getContentPane().add(nombreField);
        
        JLabel nacimientoLabel = new JLabel("Nacimiento:");
        nacimientoLabel.setBounds(298, 95, 79, 14);
        getContentPane().add(nacimientoLabel);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBounds(387, 89, 120, 26);
        datePicker.setShowYearButtons(true);
        getContentPane().add(datePicker);
        getContentPane().add(datePicker, "cell 8 4,grow");
        datePicker.setVisible(true);
        getContentPane().add(correoLabel);
        getContentPane().add(correoField);
        getContentPane().add(nicknameLabel);
        getContentPane().add(nicknameField);
        getContentPane().add(nacionalidadLabel);
        getContentPane().add(cancelarButton);
        getContentPane().add(nacionalidadField);
        getContentPane().add(confirmarButton);
        getContentPane().add(descripcionLabel);
        getContentPane().add(apellidoLabel);
        getContentPane().add(linkLabel);
        getContentPane().add(linkField);
        getContentPane().add(descripcionField);
        getContentPane().add(proveedorButton);
        getContentPane().add(apellidoField);
        
        confirmarButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		getDatosFromUser();
        		if (chequearDatos()) {
        			DTUsuario user;
        			if (esTurista) {
        				user = new DTTurista(nickname, nombre, apellido, correo, fecha, nacionalidad);
        			}else {
        				user = new DTProveedor(nickname, nombre, apellido, correo, fecha, descripcion, link);
        			}
        			try {
						contrUsers.altaUsuario(user);
					} catch (UsuarioRepetidoException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage(), "Alta Usuario", JOptionPane.ERROR_MESSAGE);
						return;
					}
        			JOptionPane.showMessageDialog(null, "Usuario cargado con éxito!", "Alta de usuario",
                            JOptionPane.INFORMATION_MESSAGE);
        			limpiarFormulario();
        			setVisible(false);
        			
        		}else {
        			JOptionPane.showMessageDialog(null, "Todos los datos son obligatorios!", "Alta de usuario",
                            JOptionPane.INFORMATION_MESSAGE);
        		}
        		
        	}
        });
        
        
        
        
        
        
        
	}
	
	private Boolean chequearDatos() {
		if (nombre.length()==0 || apellido.length()==0|| nickname.length()==0 || correo.length()==0) {
			return false;
		}else {
			if (turistaButton.isSelected()) {
				return nacionalidad.length()!=0;
			}else {
				if (proveedorButton.isSelected()) {
					return descripcion.length()!=0;
				}else return false;
			}
		}
	}
	

	private void getDatosFromUser() {
		nombre = nombreField.getText();
		nickname = nicknameField.getText();
		apellido = apellidoField.getText();
		correo = correoField.getText();
		fecha =  new GregorianCalendar(datePicker.getModel().getYear(), datePicker.getModel().getMonth(), datePicker.getModel().getDay());
		if (turistaButton.isSelected()) {
			nacionalidad = nacionalidadField.getText();
		}else {
			if (proveedorButton.isSelected()) {
				link = linkField.getText();
				descripcion = descripcionField.getText();
			}
		}
		

			
		}
	
	private void limpiarFormulario() {
		nombreField.setText("");
		nicknameField.setText("");
		apellidoField.setText("");
		correoField.setText("");
		datePicker.getModel().setDate(GregorianCalendar.DAY_OF_MONTH, GregorianCalendar.MONTH ,GregorianCalendar.YEAR);
		proveedorButton.setSelected(false);
		turistaButton.setSelected(false);
		nacionalidadField.setText("");
		linkField.setText("");
		descripcionField.setText("");
	}
		
	
}
