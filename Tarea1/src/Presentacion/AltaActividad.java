package Presentacion;

import javax.swing.JInternalFrame;

import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.JComboBox;

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
		setMaximizable(true);
		setClosable(true);
		setBounds(0,0,500,350);
		getContentPane().setLayout(new GridLayout(8, 3, 3, 15));
		
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
		
		lblNewLabel_3 = new JLabel("");
		getContentPane().add(lblNewLabel_3);
		
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
						
						boolean existeAct = iCtrlDepartamento.ingresarDatosActividad(nombre,  descripcion, duracion, costo, ciudad, fecha, proveedor, departamento);
					
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