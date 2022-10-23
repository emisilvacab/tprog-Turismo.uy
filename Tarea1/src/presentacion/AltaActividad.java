package presentacion;

import javax.swing.JInternalFrame;

import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
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

import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import javax.swing.JSplitPane;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JList;

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
	private JLabel lblNewLabel11;
	private JComboBox<String> comboProveedor;
	private JLabel lblNewLabel1;
	private JLabel labelNombre;
	private JLabel lblNewLabel13;
	private JLabel labelDescripcion;
	private JLabel lblNewLabel131;
	private JLabel labelDuracion;
	private JLabel lblNewLabel132;
	private JLabel labelCosto;
	private JLabel lblNewLabel133;
	private JLabel labelCiudad;
	private JLabel lblNewLabel134;
	private JLabel labelImagen;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel lblNewLabel6;
	private JLabel lblNewLabel7;
	private JLabel labelCategorias;
	private JSplitPane splitPane;
	private JButton btnAbrir;
	private JButton btnBorrar;
	private JLabel txfImagen;
	private JScrollPane scrollPane;
	private JList<String> listCategorias;
	
	public AltaActividad(IControladorDepartamento IConD, IControladorUsuario IConU) {
		
		iCtrlUsuario = IConU;
		iCtrlDepartamento = IConD;
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Alta de Actividad Turistica");
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(0, 0, 499, 461);
		
	    addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent except) {
            	limpiarFormulario();
				setVisible(false);
            }
        });
		getContentPane().setLayout(null);
		
		labelDepartamento = new JLabel("Departamento:");
		labelDepartamento.setBounds(0, 3, 159, 25);
		labelDepartamento.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(labelDepartamento);
		
		comboDepartamento = new JComboBox<String>();
		comboDepartamento.setBounds(162, 3, 159, 25);
		getContentPane().add(comboDepartamento);
		
		lblNewLabel11 = new JLabel("");
		lblNewLabel11.setBounds(324, 3, 159, 25);
		getContentPane().add(lblNewLabel11);
		
		labelProveedor = new JLabel("Proveedor:");
		labelProveedor.setBounds(0, 43, 159, 25);
		labelProveedor.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(labelProveedor);
		
		comboProveedor = new JComboBox<String>();
		comboProveedor.setBounds(162, 43, 159, 25);
		getContentPane().add(comboProveedor);
		
		lblNewLabel1 = new JLabel("");
		lblNewLabel1.setBounds(324, 43, 159, 25);
		getContentPane().add(lblNewLabel1);
		
		labelNombre = new JLabel("Nombre de la actividad:");
		labelNombre.setBounds(0, 83, 159, 25);
		labelNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(labelNombre);
		
		txfNombre = new JTextField();
		txfNombre.setBounds(162, 83, 159, 25);
		getContentPane().add(txfNombre);
		txfNombre.setColumns(10);
		
		lblNewLabel13 = new JLabel("");
		lblNewLabel13.setBounds(324, 83, 159, 25);
		getContentPane().add(lblNewLabel13);
		
		labelDescripcion = new JLabel("Descripcion:");
		labelDescripcion.setBounds(0, 123, 159, 25);
		labelDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(labelDescripcion);
		
		txfDescripcion = new JTextField();
		txfDescripcion.setBounds(162, 123, 159, 25);
		txfDescripcion.setColumns(10);
		getContentPane().add(txfDescripcion);
		
		lblNewLabel131 = new JLabel("");
		lblNewLabel131.setBounds(324, 123, 159, 25);
		getContentPane().add(lblNewLabel131);
		
		labelDuracion = new JLabel("Duracion de la actividad:");
		labelDuracion.setBounds(0, 163, 159, 25);
		labelDuracion.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(labelDuracion);
		
		txfDuracion = new JFormattedTextField();
		txfDuracion.setBounds(162, 163, 159, 25);
		txfDuracion.setColumns(10);
		getContentPane().add(txfDuracion);
		
		lblNewLabel132 = new JLabel("dias");
		lblNewLabel132.setBounds(324, 163, 159, 25);
		lblNewLabel132.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblNewLabel132);
		
		labelCosto = new JLabel("Costo:");
		labelCosto.setBounds(0, 203, 159, 25);
		labelCosto.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(labelCosto);
		
		txfCosto = new JFormattedTextField();
		txfCosto.setBounds(162, 203, 159, 25);
		txfCosto.setColumns(10);
		getContentPane().add(txfCosto);
		
		lblNewLabel133 = new JLabel("");
		lblNewLabel133.setBounds(324, 203, 159, 25);
		lblNewLabel133.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblNewLabel133);
		
		labelCiudad = new JLabel("Ciudad:");
		labelCiudad.setBounds(0, 243, 159, 25);
		labelCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(labelCiudad);
		
		txfCiudad = new JTextField();
		txfCiudad.setBounds(162, 243, 159, 25);
		txfCiudad.setColumns(10);
		getContentPane().add(txfCiudad);
		
		lblNewLabel134 = new JLabel("");
		lblNewLabel134.setBounds(324, 243, 159, 25);
		getContentPane().add(lblNewLabel134);
		
		labelImagen = new JLabel("Imagen:");
		labelImagen.setVisible(false);
		labelImagen.setBounds(0, 363, 159, 25);
		labelImagen.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(labelImagen);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(162, 395, 159, 25);
		btnAceptar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent except) {
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
						
						List<String> categorias = listCategorias.getSelectedValuesList();						
						Set<String> setCategorias = new HashSet<String>(); // Se le envian los nombres al controlador
						for (String categoria: categorias) {
							setCategorias.add(categoria);
						}
						 

						boolean existeAct = iCtrlDepartamento.ingresarDatosActividad(nombre,  descripcion, duracion, costo, ciudad, fecha, proveedor, departamento, setCategorias, null);

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
		txfImagen.setVisible(false);
		txfImagen.setBounds(162, 363, 159, 25);
		txfImagen.setIcon(null);
		getContentPane().add(txfImagen);
		
		splitPane = new JSplitPane();
		splitPane.setVisible(false);
		splitPane.setBounds(324, 363, 159, 25);
		getContentPane().add(splitPane);
		
		btnAbrir = new JButton("Abrir");
		btnAbrir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent except) {
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
			public void mouseClicked(MouseEvent except) {
				imagenAct = null; // si no hay imagen le seteamos null al valor
				txfImagen.setText("Sin imagen");
				txfImagen.setIcon(null); // si no hay imagen le seteamos null al valor
			}
		});
		btnBorrar.setMinimumSize(new Dimension(61, 23));
		splitPane.setRightComponent(btnBorrar);
		
		labelCategorias = new JLabel("Categorías:");
		labelCategorias.setBounds(0, 287, 159, 25);
		labelCategorias.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(labelCategorias);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(162, 287, 159, 65);
		getContentPane().add(scrollPane);
		
		listCategorias = new JList<String>();
		listCategorias.setToolTipText("Agregue una o varias categorías");
		listCategorias.setVisibleRowCount(3);
		listCategorias.setSelectionModel(new DefaultListSelectionModel(){ // Para poder seleccionar mas de uno sin usar "ctrl"
		    @Override
		    public void setSelectionInterval(int start, int end) {
		        if (start != end) {
		            super.setSelectionInterval(start, end);
		        } else if (isSelectedIndex(start)) {
		            removeSelectionInterval(start, end);
		        } else {
		            addSelectionInterval(start, end);
		        }
		    }
		});
		scrollPane.setViewportView(listCategorias);
		
		lblNewLabel6 = new JLabel("");
		lblNewLabel6.setBounds(324, 287, 159, 25);
		getContentPane().add(lblNewLabel6);
		
		lblNewLabel7 = new JLabel("");
		lblNewLabel7.setBounds(0, 363, 159, 25);
		getContentPane().add(lblNewLabel7);
		getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(324, 395, 159, 25);
		btnCancelar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent except) {
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
					if (descripcionAct.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Ingrese la descripción de la actividad.", "Descripción no ingresado", JOptionPane.ERROR_MESSAGE);
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
								if (ciudadAct.isEmpty()) {
									JOptionPane.showMessageDialog(null, "Ingrese una ciudad.", "Ciudad no ingresada", JOptionPane.ERROR_MESSAGE);
									ans = false;
								} else
									if (listCategorias.getSelectedValuesList().isEmpty()) {
										JOptionPane.showMessageDialog(null, "Ingrese al menos una categoría.", "Categorías no ingresadas", JOptionPane.ERROR_MESSAGE);
										ans = false;
									} else {
										try {
								            int dur = Integer.parseInt(duracionAct);
								            if (dur <= 0) {
								            	JOptionPane.showMessageDialog(this, "La duración de la actividad debe ser un numero entero mayor que 0",  "Duracion no ingresada",
									                    JOptionPane.ERROR_MESSAGE);
								            	ans = false;
								            }
								        } catch (NumberFormatException except) {
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
							        } catch (NumberFormatException except) {
							            JOptionPane.showMessageDialog(this, "El costo de la actividad debe ser un numero mayor o igual a 0.0", "Costo no ingresado",
							                    JOptionPane.ERROR_MESSAGE);
							            ans = false;
							        }
								}
		return ans;
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
        comboProveedor.setSelectedItem(null);
    }
	
	public void cargarCategorias() {
		Set<String> categorias = iCtrlDepartamento.obtenerCategorias();
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (String s : categorias)
			model.addElement(s);
		listCategorias.setModel(model);
	}
}