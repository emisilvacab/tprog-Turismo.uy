package Presentacion;

import javax.swing.JInternalFrame;

import logica.controladores.IControladorUsuario;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
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

import datatypes.DTProveedor;
import datatypes.DTTurista;
import datatypes.DTUsuario;
import excepciones.UsuarioRepetidoException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import net.miginfocom.swing.MigLayout;

public class AltaUsuario extends JInternalFrame{
	/**
	 * 
	 */
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
        setIconifiable(true);
        setMaximizable(true);
        setClosable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Agregar un usuario");
        setBounds(30, 30, 628, 390);
        
        JLabel tipoUserLabel = new JLabel("Indique el tipo de usuario");
        
        turistaButton = new JRadioButton("Turista");
        
        proveedorButton = new JRadioButton("Proveedor");
        
        JLabel nombreLabel = new JLabel("Nombre: ");
        
        nombreField = new JTextField();
        nombreField.setColumns(10);
        
        JLabel apellidoLabel = new JLabel("Apellido: ");
        
        apellidoField = new JTextField();
        apellidoField.setColumns(10);
        
        JLabel nicknameLabel = new JLabel("Nickname: ");
        
        nicknameField = new JTextField();
        nicknameField.setColumns(10);
        
        JLabel correoLabel = new JLabel("Correo: ");
        
        correoField = new JTextField();
        correoField.setColumns(10);
        
        JLabel nacionalidadLabel = new JLabel("Nacionalidad: ");
        nacionalidadLabel.setVisible(false);
        
        nacionalidadField = new JTextField();
        nacionalidadField.setVisible(false);
        nacionalidadField.setColumns(10);
        
        JLabel descripcionLabel = new JLabel("Descripcion: ");
        descripcionLabel.setVisible(false);
        
        descripcionField = new JTextField();
        descripcionField.setColumns(10);
        descripcionField.setVisible(false);
        
        JLabel linkLabel = new JLabel("Link: ");
        linkLabel.setVisible(false);
        
        linkField = new JTextField();
        linkField.setColumns(10);
        linkField.setVisible(false);
        
        JButton cancelarButton = new JButton("Cancelar");
        
        JButton confirmarButton = new JButton("Confirmar");
        
        //los agrupo para que solo se seleccione uno
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
        
        turistaButton.addActionListener( new ActionListener() {
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
        getContentPane().setLayout(new MigLayout("", "[1px][90px][6px][8px][38px][168px][83px][18px][130px]", "[1px][16px][23px][26px][26px][26px][26px][29px]"));
        getContentPane().add(tipoUserLabel, "cell 5 1,growx,aligny top");
        getContentPane().add(turistaButton, "cell 1 2 3 1,alignx right,aligny top");
        getContentPane().add(nombreLabel, "cell 1 3,alignx left,aligny center");
        getContentPane().add(nombreField, "cell 3 3 3 1,alignx left,aligny top");
        
        JLabel nacimientoLabel = new JLabel("Nacimiento:");
        getContentPane().add(nacimientoLabel, "cell 6 4");
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setShowYearButtons(true);
        getContentPane().add(datePicker);
        getContentPane().add(datePicker, "cell 8 4,grow");
        datePicker.setVisible(true);
        getContentPane().add(correoLabel, "cell 1 5,alignx left,aligny center");
        getContentPane().add(correoField, "cell 3 5 3 1,alignx left,aligny top");
        getContentPane().add(nicknameLabel, "cell 1 4,alignx left,aligny center");
        getContentPane().add(nicknameField, "cell 3 4 3 1,alignx left,aligny top");
        getContentPane().add(nacionalidadLabel, "cell 1 6,alignx left,aligny center");
        getContentPane().add(cancelarButton, "cell 3 7 3 1,alignx left,aligny top");
        getContentPane().add(nacionalidadField, "cell 3 6 3 1,alignx left,aligny top");
        getContentPane().add(confirmarButton, "cell 6 7 3 1,alignx left,aligny top");
        getContentPane().add(descripcionLabel, "cell 6 5,alignx left,aligny center");
        getContentPane().add(apellidoLabel, "cell 6 3,alignx right,aligny center");
        getContentPane().add(linkLabel, "cell 6 6,alignx right,aligny center");
        getContentPane().add(linkField, "cell 8 6,alignx left,aligny top");
        getContentPane().add(descripcionField, "cell 8 5,alignx left,aligny top");
        getContentPane().add(proveedorButton, "cell 8 2,alignx left,aligny top");
        getContentPane().add(apellidoField, "cell 8 3,alignx left,aligny top");
        
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
        			limpiarFormulario();
        			try {
						contrUsers.altaUsuario(user);
					} catch (UsuarioRepetidoException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage(), "Alta Usuario", JOptionPane.ERROR_MESSAGE);
					}
        			JOptionPane.showMessageDialog(null, "Usuario cargado con éxito!", "Alta de usuario",
                            JOptionPane.INFORMATION_MESSAGE);
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
					return descripcion.length()!=0 && link.length()!=0;
				}else return false;
			}
		}
	}
	

	private void getDatosFromUser() {
		nombre = nombreField.getText();
		nickname = nicknameField.getText();
		apellido = apellidoField.getText();
		correo = correoField.getText();
		fecha =  new GregorianCalendar(datePicker.getModel().getYear(), datePicker.getModel().getMonth()-1, datePicker.getModel().getDay());
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
