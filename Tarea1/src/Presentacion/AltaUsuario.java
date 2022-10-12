package Presentacion;

import javax.swing.JInternalFrame;

import logica.controladores.IControladorUsuario;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import excepciones.UsuarioRepetidoException;

import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;

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
	private String contrasena;
	private GregorianCalendar fecha;
	private String nacionalidad;
	private String link;
	private String descripcion;
	private JPasswordField contrasenaField;
	private JButton btnAbrir;
	private JButton btnBorrar;
	private Image imagenUsr = null;
	private JLabel txfImagen;


	
	public AltaUsuario(IControladorUsuario icu) {
		contrUsers = icu;
		setResizable(true);
        setMaximizable(true);
        setClosable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Agregar un usuario");
        setBounds(30, 30, 530, 330);
        
	    addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
            	limpiarFormulario();
				setVisible(false);
            }
        });
        
        JLabel tipoUserLabel = new JLabel("Indique el tipo de usuario");
        tipoUserLabel.setBounds(135, 12, 159, 14);
        
        turistaButton = new JRadioButton("Turista");
        turistaButton.setBounds(6, 33, 110, 23);
        
        proveedorButton = new JRadioButton("Proveedor");
        proveedorButton.setBounds(298, 33, 110, 23);
        
        JLabel nombreLabel = new JLabel("Nombre: ");
        nombreLabel.setBounds(11, 65, 88, 14);
        
        nombreField = new JTextField();
        nombreField.setBounds(99, 59, 132, 20);
        nombreField.setColumns(10);
        
        JLabel apellidoLabel = new JLabel("Apellido: ");
        apellidoLabel.setBounds(280, 65, 97, 14);
        
        apellidoField = new JTextField();
        apellidoField.setBounds(387, 59, 120, 20);
        apellidoField.setColumns(10);
        
        JLabel nicknameLabel = new JLabel("Nickname: ");
        nicknameLabel.setBounds(11, 95, 88, 14);
        
        nicknameField = new JTextField();
        nicknameField.setBounds(99, 89, 132, 20);
        nicknameField.setColumns(10);
        
        JLabel correoLabel = new JLabel("Correo: ");
        correoLabel.setBounds(11, 125, 88, 14);
        
        correoField = new JTextField();
        correoField.setBounds(99, 119, 132, 20);
        correoField.setColumns(10);
        
        JLabel nacionalidadLabel = new JLabel("Nacionalidad: ");
        nacionalidadLabel.setBounds(11, 155, 105, 14);
        nacionalidadLabel.setVisible(false);
        
        nacionalidadField = new JTextField();
        nacionalidadField.setBounds(99, 152, 132, 20);
        nacionalidadField.setVisible(false);
        nacionalidadField.setColumns(10);
        
        JLabel descripcionLabel = new JLabel("Descripción: ");
        descripcionLabel.setBounds(22, 194, 94, 14);
        descripcionLabel.setVisible(false);
        
        descripcionField = new JTextField();
        descripcionField.setBounds(119, 189, 343, 24);
        descripcionField.setColumns(10);
        descripcionField.setVisible(false);
        
        JLabel linkLabel = new JLabel("Link (opcional): ");
        linkLabel.setBounds(19, 220, 97, 14);
        linkLabel.setVisible(false);
        
        linkField = new JTextField();
        linkField.setBounds(119, 220, 334, 23);
        linkField.setColumns(10);
        linkField.setVisible(false);
        
        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setBounds(298, 255, 185, 23);
        
        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.setBounds(6, 255, 184, 23);
        
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
        nacimientoLabel.setBounds(280, 92, 97, 23);
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
        
        btnBorrar = new JButton("Borrar");
        btnBorrar.setBounds(341, 149, 75, 29);
        getContentPane().add(btnBorrar);
        
        btnAbrir = new JButton("Abrir");
        btnAbrir.setBounds(425, 149, 75, 29);
        getContentPane().add(btnAbrir);
		btnAbrir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser selector = new JFileChooser(); // esta clase se importo
				int respuesta = selector.showOpenDialog(null); // selecciona archivo a abrir
				if (respuesta == JFileChooser.APPROVE_OPTION) {
					File archivo = new File(selector.getSelectedFile().getAbsolutePath()); // creo el archivo a partir de la ruta
					String nombreArchivo = archivo.getName();
					String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1); // obtengo la extension del archivo y me fijo que sea una imagen
					if (extension.equals("jpeg") || extension.equals("jpg") || extension.equals("png")) {
						try {
							imagenUsr = ImageIO.read(archivo); // seteo el valor de imagenAct (de clase Image) leyendo el archivo. imagenAct la voy a usar despues para crear la actividad
							txfImagen.setText(nombreArchivo);
							txfImagen.setIcon(new ImageIcon(imagenUsr.getScaledInstance(txfImagen.getHeight(), -1, 1))); // esto se puede hacer porque es un JLabel, por el setIcon
							// Le paso al atributo del icono una instancia de ImageIcon que es el imagenAct pero con otro tamaño para no modificarlo
							// Le seteo la altura del JLabel para que quede del tamaño del campo, -1 para que la altura mantenga relacion de aspecto y el ultimo campo es para flags
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Error al leer imagen", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Debe ingresar una imagen (formato JPG, JPEG o PNG)", "No se ingresó archivo imagen", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
				
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				imagenUsr = null; // si no hay imagen le seteamos null al valor
				txfImagen.setText("Sin imagen");
				txfImagen.setIcon(null); // si no hay imagen le seteamos null al valor
			}
		});
        
        JLabel contrasenaLabel = new JLabel("Contraseña: ");
        contrasenaLabel.setBounds(280, 127, 88, 16);
        getContentPane().add(contrasenaLabel);
        
        contrasenaField = new JPasswordField();
        contrasenaField.setBounds(370, 119, 130, 26);
        getContentPane().add(contrasenaField);
        contrasenaField.setColumns(10);
        
        JLabel imagenLabel = new JLabel("Imagen: ");
        imagenLabel.setBounds(280, 154, 61, 16);
        getContentPane().add(imagenLabel);
        
        txfImagen = new JLabel("Sin imagen");
        txfImagen.setBounds(280, 173, 120, 16);
        getContentPane().add(txfImagen);
       
        
        confirmarButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		getDatosFromUser();
        		if (chequearDatos()) {
        			DTUsuario user;
        			if (esTurista) {
        				user = new DTTurista(nickname, nombre, apellido, correo, fecha, contrasena, nacionalidad);
        			}else {
        				user = new DTProveedor(nickname, nombre, apellido, correo, fecha, contrasena, descripcion, link);
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
		if (nombre.length()==0 || apellido.length()==0|| nickname.length()==0 || correo.length()==0 || contrasena.length()==0) {
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
		contrasena = contrasenaField.getText();
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
		contrasenaField.setText("");
		LocalDate date = LocalDate.now();
		datePicker.getModel().setDate(date.getYear(), date.getMonthValue() -1, date.getDayOfMonth());
		datePicker.getModel().setSelected(true);
		proveedorButton.setSelected(false);
		turistaButton.setSelected(false);
		nacionalidadField.setText("");
		linkField.setText("");
		descripcionField.setText("");
	}
}
