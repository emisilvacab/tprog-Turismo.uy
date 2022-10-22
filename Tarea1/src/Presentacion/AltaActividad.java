package Presentacion;

import javax.swing.JInternalFrame;

import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import excepciones.departamentoNoExisteException;
import excepciones.proveedorNoExisteException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import javax.swing.JSplitPane;
import java.awt.Dimension;

public class AltaActividad extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private IControladorUsuario iCtrlUsuario;
	private IControladorDepartamento iCtrlDepartamento;
	private Image imagenAct = null;
	
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
	private JLabel labelImagen;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_2;
	private JSplitPane splitPane;
	private JButton btnAbrir;
	private JButton btnBorrar;
	private JLabel txfImagen;
	
	public AltaActividad(IControladorDepartamento IConD, IControladorUsuario IConU) {
		
		iCtrlUsuario = IConU;
		iCtrlDepartamento = IConD;
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Alta de Actividad Turistica");
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(0,0,499,421);
		getContentPane().setLayout(new GridLayout(10, 3, 3, 15));
		
	    addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
            	limpiarFormulario();
				setVisible(false);
            }
        });
		
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
		
		labelImagen = new JLabel("Imagen:");
		labelImagen.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(labelImagen);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (checkFormulario()) {
					try {
						String nombre = txfNombre.getText();
						String descripcion = txfDescripcion.getText();				
						String dur = (String) txfDuracion.getText();
						int duracion = Integer.parseInt(dur);
						String cos = (String) txfCosto.getText();
						float costo = Float.parseFloat(cos);
						String ciudad = txfCiudad.getText();
						String departamento = (String) comboDepartamento.getSelectedItem();
						String proveedor = (String) comboProveedor.getSelectedItem();
						GregorianCalendar fecha = GregorianCalendar.from(ZonedDateTime.now());
						
						//Agregar Set<String> con nombres de categorias que van a esa actividad
						
						boolean existeAct = iCtrlDepartamento.ingresarDatosActividad(nombre,  descripcion, duracion, costo, ciudad, fecha, proveedor, departamento, null, null);
					
						if (existeAct) {
							JOptionPane.showMessageDialog(null, "Ya hay una actividad con el nombre "+ nombre + ". Cambie los datos para ingresar una nueva actividad.", "Actividad " + nombre + " ya existente", JOptionPane.ERROR_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Actividad dada de alta con exito!", "Nueva actividad ingresada", JOptionPane.INFORMATION_MESSAGE);
							limpiarFormulario();
							setVisible(false);
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
		
		txfImagen = new JLabel("Sin imagen");
		txfImagen.setIcon(null);
		getContentPane().add(txfImagen);
		
		splitPane = new JSplitPane();
		getContentPane().add(splitPane);
		
		btnAbrir = new JButton("Abrir");
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
							imagenAct = ImageIO.read(archivo); // seteo el valor de imagenAct (de clase Image) leyendo el archivo. imagenAct la voy a usar despues para crear la actividad
							txfImagen.setText(nombreArchivo);
							txfImagen.setIcon(new ImageIcon(imagenAct.getScaledInstance(txfImagen.getHeight(), -1, 1))); // esto se puede hacer porque es un JLabel, por el setIcon
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
		
		btnAbrir.setMinimumSize(new Dimension(70, 23));
		splitPane.setLeftComponent(btnAbrir);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				imagenAct = null; // si no hay imagen le seteamos null al valor
				txfImagen.setText("Sin imagen");
				txfImagen.setIcon(null); // si no hay imagen le seteamos null al valor
			}
		});
		btnBorrar.setMinimumSize(new Dimension(61, 23));
		splitPane.setRightComponent(btnBorrar);
		
		lblNewLabel_2 = new JLabel("");
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_5 = new JLabel("");
		getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("");
		getContentPane().add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("");
		getContentPane().add(lblNewLabel_7);
		getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
        		JOptionPane.showMessageDialog(null, "Alta cancelada!", "Alta de actividad", JOptionPane.INFORMATION_MESSAGE);
        		limpiarFormulario();
				setVisible(false);
			}
		});
		getContentPane().add(btnCancelar);
		
	}
	
	private boolean checkFormulario() {
		String nombreAct = txfNombre.getText();
		String ciudadAct = txfCiudad.getText();
		String descripcionAct = txfDescripcion.getText();
		String duracionAct = txfDuracion.getText();
		String costoAct = txfCosto.getText();
		
		boolean ans = true;
		//fijarse que duración sea int y sea mayor que 0 y luego que costo sea un numero tambien
		
		if (comboDepartamento.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Seleccione un departamento.", "Departamento no seleccionado", JOptionPane.ERROR_MESSAGE);
			ans = false;
		} else
			if (comboProveedor.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Seleccione un proveedor.", "Proveedor no seleccionado", JOptionPane.ERROR_MESSAGE);
				ans = false;
			} else
				if (nombreAct.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ingrese el nombre de la actividad.", "Nombre no ingresado", JOptionPane.ERROR_MESSAGE);
					ans = false;
				} else
					if (duracionAct.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Ingrese la duracion de la actividad.", "Duracion no ingresada", JOptionPane.ERROR_MESSAGE);
						ans = false;
					} else
						if (costoAct.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Ingrese el costo de la actividad.", "Costo no ingresado", JOptionPane.ERROR_MESSAGE);
							ans = false;
						} else
							if (descripcionAct.isEmpty()) {
								JOptionPane.showMessageDialog(null, "Ingrese el descripción de la actividad.", "Descripción no ingresado", JOptionPane.ERROR_MESSAGE);
								ans = false;
							} else
								if (ciudadAct.isEmpty()) {
									JOptionPane.showMessageDialog(null, "Ingrese una ciudad.", "Ciudad no ingresada", JOptionPane.ERROR_MESSAGE);
									ans = false;
								} else {
									try {
								            int dur = Integer.parseInt(duracionAct);
								            if (dur <= 0) {
								            	JOptionPane.showMessageDialog(this, "La duración de la actividad debe ser un numero entero mayor que 0",  "Duracion no ingresada",
									                    JOptionPane.ERROR_MESSAGE);
								            	ans = false;
								            }
								        } catch (NumberFormatException e) {
								            JOptionPane.showMessageDialog(this, "La duración de la actividad debe ser un numero entero mayor que 0", "Duracion no ingresada",
								                    JOptionPane.ERROR_MESSAGE);
								            ans = false;
								        }
									try {
							            float cos = Float.parseFloat(costoAct);
							            if (cos < 0.0) {
							            	JOptionPane.showMessageDialog(this, "La duración de la actividad debe ser un numero mayor o igual que 0.0", "Costo no ingresado",
								                    JOptionPane.ERROR_MESSAGE);
							            	ans = false;
							            }
							        } catch (NumberFormatException e) {
							            JOptionPane.showMessageDialog(this, "El costo de la actividad debe ser un numero mayor o igual a 0.0", "Costo no ingresado",
							                    JOptionPane.ERROR_MESSAGE);
							            ans = false;
							        }
								}
		return (ans);
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
        DefaultComboBoxModel<String> model;
        model = new DefaultComboBoxModel<String>(iCtrlUsuario.obtenerProveedores());
        comboProveedor.setModel(model);
    }
	
	
}