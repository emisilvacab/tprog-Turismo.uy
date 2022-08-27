package Presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import javax.swing.SwingConstants;

import excepciones.UsuarioNoExisteException;
import logica.DataUsuario;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;


public class AltaSalida extends JInternalFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private IControladorDepartamento icd;
	
	public AltaSalida(IControladorDepartamento icd) {
		this.icd = icd;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Alta de Salida");
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		getContentPane().setLayout(new GridLayout(7, 3, 3, 15));
		
		JLabel lblSeleccioneDepartamento = new JLabel("Departamento:");
		lblSeleccioneDepartamento.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblSeleccioneDepartamento);
		
		JComboBox comboBox = new JComboBox<String>(); //Combo box de nombres de Departamentos
		getContentPane().add(comboBox);
		
		JLabel label_1 = new JLabel("");
		getContentPane().add(label_1);
		
		JLabel lblNewLabel = new JLabel("Actividad:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblNewLabel);
		
		JComboBox comboBox_1 = new JComboBox<String>(); //Combobox con nombres de actividades dentro de departamentos
		getContentPane().add(comboBox_1);
		
		JLabel label_2 = new JLabel("");
		getContentPane().add(label_2);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_3 = new JLabel("");
		getContentPane().add(label_3);
		
		JLabel lblNewLabel_2 = new JLabel("Cantidad de Turistas:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		getContentPane().add(textField_1);
		
		JLabel label_4 = new JLabel("");
		getContentPane().add(label_4);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha de Salida:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		getContentPane().add(textField_2);
		
		JLabel label_5 = new JLabel("");
		getContentPane().add(label_5);
		
		JLabel lblNewLabel_4 = new JLabel("Lugar de Salida:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		getContentPane().add(textField_3);
		
		JLabel label_6 = new JLabel("");
		getContentPane().add(label_6);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JLabel lblNewLabel_5 = new JLabel("");
		getContentPane().add(lblNewLabel_5);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	    		JOptionPane.showMessageDialog(null, "Alta cancelada!", "Alta de Salida",
	                    JOptionPane.INFORMATION_MESSAGE);
	    		setVisible(false);
	    		limpiarFormulario(); //Tengo q hacer una de estas
			}
		});
		getContentPane().add(btnCancelar);
	}
	
	public void cargarDptos() {
		Set<String> dptos = icd.obtenerDepartamentos();
		for (String s : dptos)
			listaDptos.addItem(s);
		listaDptos.setSelectedItem(null);
		listaActs.setSelectedItem(null);
		listaSals.setSelectedItem(null);
	}	
}
