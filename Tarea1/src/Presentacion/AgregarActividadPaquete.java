package Presentacion;

import javax.swing.JInternalFrame;

import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorPaquete;
import logica.datatypes.DTActividad;
import logica.datatypes.DTPaquete;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.paqueteNoExisteException;

import javax.swing.JButton;

import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import javax.swing.JComboBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AgregarActividadPaquete extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	private IControladorPaquete ctrlPaquete;
	private IControladorDepartamento ctrlDepartamento;
	private JComboBox<String> comboPaquete;
	private JLabel labelPaquete;
	private JComboBox<String> comboDepartamento;
	private JLabel labelDepartamento;
	private JComboBox<String> comboActividad;
	private JLabel labelActividad;
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	public AgregarActividadPaquete(IControladorPaquete controladorPaquete, IControladorDepartamento controladorDepartamento) {
		ctrlPaquete = controladorPaquete;
		ctrlDepartamento = controladorDepartamento;
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setMaximizable(true);
		setResizable(true);
		setTitle("Agregar Actividad Turística a Paquete");
		addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
            	limpiarCampos();
				setVisible(false);
            }
        });
		getContentPane().setLayout(null);
		
		comboPaquete = new JComboBox<String>();
		comboPaquete.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent cambioPaquete) {
				if (comboDepartamento.getSelectedItem() != null && comboPaquete.getSelectedItem() != null) {
					try {
						comboActividad.removeAllItems();
						HashSet<DTActividad> actividades = ctrlPaquete.obtenerDatosActividadesConfirmadasNoPaquete((String) comboDepartamento.getSelectedItem(), (String) comboPaquete.getSelectedItem());
						for (DTActividad actividad: actividades)
							comboActividad.addItem(actividad.getNombre());
						comboActividad.setSelectedItem(null);
					} catch (departamentoNoExisteException dptoNoExiste) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "El departamento ingresado no existe", "Departamento no existe", JOptionPane.ERROR_MESSAGE);
					} catch (paqueteNoExisteException paqNoExiste) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "El paquete ingresado no existe", "Paquete no existe", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		comboPaquete.setBounds(139, 40, 170, 22);
		getContentPane().add(comboPaquete);
		
		labelPaquete = new JLabel("Paquete:");
		labelPaquete.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPaquete.setBounds(61, 38, 73, 22);
		getContentPane().add(labelPaquete);
		
		comboDepartamento = new JComboBox<String>();
		comboDepartamento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent cambioDepartamento) {
				if (comboPaquete.getSelectedItem() != null && comboDepartamento.getSelectedItem() != null) {
					try {
						comboActividad.removeAllItems();
						HashSet<DTActividad> actividades = ctrlPaquete.obtenerDatosActividadesConfirmadasNoPaquete((String) comboDepartamento.getSelectedItem(), (String) comboPaquete.getSelectedItem());
						for (DTActividad actividad: actividades)
							comboActividad.addItem(actividad.getNombre());
						comboActividad.setSelectedItem(null);
					} catch (departamentoNoExisteException dptoNoExiste) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "El departamento ingresado no existe", "Departamento no existe", JOptionPane.ERROR_MESSAGE);
					} catch (paqueteNoExisteException paqNoExiste) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "El paquete ingresado no existe", "Paquete no existe", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		comboDepartamento.setBounds(139, 85, 170, 22);
		getContentPane().add(comboDepartamento);
		
		labelDepartamento = new JLabel("Departamento:");
		labelDepartamento.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDepartamento.setBounds(61, 85, 73, 22);
		getContentPane().add(labelDepartamento);
		
		comboActividad = new JComboBox<String>();
		comboActividad.setBounds(139, 130, 170, 22);
		getContentPane().add(comboActividad);
		
		labelActividad = new JLabel("Actividad:");
		labelActividad.setHorizontalAlignment(SwingConstants.RIGHT);
		labelActividad.setBounds(61, 134, 73, 14);
		getContentPane().add(labelActividad);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nombreDepartamento = (String) comboDepartamento.getSelectedItem();
				String nombrePaquete = (String) comboPaquete.getSelectedItem();
				String nombreActividad = (String) comboActividad.getSelectedItem();
				if (verificarCampos()) {
					try {
						ctrlPaquete.agregarActividadPaquete(nombreDepartamento, nombrePaquete, nombreActividad);
					} catch (departamentoNoExisteException dptoNoExiste) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "El departamento ingresado no existe", "Departamento no existe", JOptionPane.ERROR_MESSAGE);
					} catch (paqueteNoExisteException paqNoExiste) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "El paquete ingresado no existe", "Paquete no existe", JOptionPane.ERROR_MESSAGE);
					} catch (actividadNoExisteException actNoExiste) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "La actividad ingresada no existe", "Actividad no existe", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAceptar.setBounds(61, 200, 89, 23);
		getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Agregar actividad a paquete cancelado!", "Agregar actividad turística a paquete", JOptionPane.INFORMATION_MESSAGE);
        		limpiarCampos();
				setVisible(false);
			}
		});
		btnCancelar.setBounds(299, 200, 89, 23);
		getContentPane().add(btnCancelar);
		
		
	}
	
	private boolean verificarCampos() {
		boolean ans = true;
		if (comboPaquete.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Seleccione un paquete.", "Paquete no seleccionado", JOptionPane.ERROR_MESSAGE);
			ans = false;
		} else {
			if (comboDepartamento.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Seleccione un departamento.", "Departamento no seleccionado", JOptionPane.ERROR_MESSAGE);
				ans = false;
			} else {
				if (comboActividad.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Seleccione una actividad.", "Actividad no seleccionada", JOptionPane.ERROR_MESSAGE);
					ans = false;
				}
			}
		}
		return ans;
	}
	
	private void limpiarCampos() {
		comboDepartamento.removeAllItems();
		comboPaquete.removeAllItems();
		comboActividad.removeAllItems();
	}
	
	public void cargarDepartamentos() {
	Set<String> departamentos = ctrlDepartamento.obtenerDepartamentos();
	for (String departamento: departamentos)
		comboDepartamento.addItem(departamento);
	comboDepartamento.setSelectedItem(null);
	}
	
	public void cargarPaquetesNoComprados() {
	HashSet<DTPaquete> paquetes = ctrlPaquete.obtenerPaquetesNoComprados();
	for (DTPaquete paquete: paquetes)
		comboPaquete.addItem(paquete.getNombre());
	comboPaquete.setSelectedItem(null);
	}
}