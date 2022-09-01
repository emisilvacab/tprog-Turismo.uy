package Presentacion;

import excepciones.departamentoNoExisteException;
import excepciones.actividadNoExisteException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import logica.controladores.IControladorDepartamento;
import logica.datatypes.DTActividad;
import logica.datatypes.DTSalida;

public class ConsultaDeSalida extends JInternalFrame {
	private IControladorDepartamento cDpto;
	private JTextField textFieldNombre;
	private JTextField textFieldMaximo;
	private JTextField textFieldAlta;
	private JTextField textFieldSalida;
	private JTextField textFieldLugar;
	private JButton buttonCerrar;
	
	private JComboBox<String> comboBoxDepartamento;
	private JComboBox<String> comboBoxActividad;
	private JComboBox<String> comboBoxSalida;
	
	private JLabel tagDpto;
	private JLabel tagActividad;
	private JLabel tagSalida;
	private JLabel lblNewLabel;
	private JTextField textFieldHora;
	
	public ConsultaDeSalida(IControladorDepartamento icd) {
		
		cDpto = icd;
		
		setTitle("Consulta de Salida Turistica");
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 338, 347);
		
		
		addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
				setVisible(false);
				comboBoxDepartamento.removeAllItems();
				comboBoxActividad.removeAllItems();
				comboBoxSalida.removeAllItems();
            }
        });
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		tagDpto = new JLabel("Departamento");		
		GridBagConstraints gbc_tagDpto = new GridBagConstraints();
		gbc_tagDpto.anchor = GridBagConstraints.EAST;
		gbc_tagDpto.insets = new Insets(0, 0, 5, 5);
		gbc_tagDpto.gridx = 1;
		gbc_tagDpto.gridy = 1;
		getContentPane().add(tagDpto, gbc_tagDpto);
		
		comboBoxDepartamento = new JComboBox<String>();
			
		comboBoxDepartamento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxActividad.setEnabled(false);
				comboBoxSalida.setEnabled(false);
				
				comboBoxActividad.removeAllItems();
				comboBoxSalida.removeAllItems();
				
				if(comboBoxDepartamento.getSelectedItem() != null) {
					cargarActividades();
					if(comboBoxActividad.getSelectedItem() != null)
						comboBoxActividad.setEnabled(true);
				}
				limpiarFormulario();
				
				mostrarSalida();
			}
		});
		 			
		GridBagConstraints gbc_comboBoxDepartamento = new GridBagConstraints();
		gbc_comboBoxDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDepartamento.gridx = 2;
		gbc_comboBoxDepartamento.gridy = 1;
		getContentPane().add(comboBoxDepartamento, gbc_comboBoxDepartamento);
		
		tagActividad = new JLabel("Actividad");
		GridBagConstraints gbc_tagActividad = new GridBagConstraints();
		gbc_tagActividad.anchor = GridBagConstraints.EAST;
		gbc_tagActividad.insets = new Insets(0, 0, 5, 5);
		gbc_tagActividad.gridx = 1;
		gbc_tagActividad.gridy = 2;
		getContentPane().add(tagActividad, gbc_tagActividad);
		
		comboBoxActividad = new JComboBox<String>();
		
		comboBoxActividad.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent f) {

				comboBoxSalida.removeAllItems();
				
				if(comboBoxActividad.getSelectedItem() != null) {
					cargarSalidas();
					if(comboBoxSalida.getSelectedItem() != null)
					    comboBoxSalida.setEnabled(true);
				}
				limpiarFormulario();
				mostrarSalida();
			}
		});
		
		comboBoxActividad.setEnabled(false);
		GridBagConstraints gbc_comboBoxActividad = new GridBagConstraints();
		gbc_comboBoxActividad.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxActividad.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxActividad.gridx = 2;
		gbc_comboBoxActividad.gridy = 2;
		getContentPane().add(comboBoxActividad, gbc_comboBoxActividad);
		
		tagSalida = new JLabel("Salida");
		GridBagConstraints gbc_tagSalida = new GridBagConstraints();
		gbc_tagSalida.anchor = GridBagConstraints.EAST;
		gbc_tagSalida.insets = new Insets(0, 0, 5, 5);
		gbc_tagSalida.gridx = 1;
		gbc_tagSalida.gridy = 3;
		getContentPane().add(tagSalida, gbc_tagSalida);
		
		comboBoxSalida = new JComboBox<String>();
		
		comboBoxSalida.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent g) {
				if(comboBoxSalida.getSelectedItem() != null) {
					mostrarSalida();
				}else {
					limpiarFormulario();
				}
			
			}});
		
		comboBoxSalida.setEnabled(false);
		GridBagConstraints gbc_comboBoxSalida = new GridBagConstraints();
		gbc_comboBoxSalida.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxSalida.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSalida.gridx = 2;
		gbc_comboBoxSalida.gridy = 3;
		getContentPane().add(comboBoxSalida, gbc_comboBoxSalida);
		
		JLabel tagInfo = new JLabel("Informaci\u00F3n");
		GridBagConstraints gbc_tagInfo = new GridBagConstraints();
		gbc_tagInfo.insets = new Insets(0, 0, 5, 0);
		gbc_tagInfo.gridwidth = 4;
		gbc_tagInfo.gridx = 0;
		gbc_tagInfo.gridy = 4;
		getContentPane().add(tagInfo, gbc_tagInfo);
		
		JLabel tagNombre = new JLabel("Nombre");
		GridBagConstraints gbc_tagNombre = new GridBagConstraints();
		gbc_tagNombre.anchor = GridBagConstraints.EAST;
		gbc_tagNombre.insets = new Insets(0, 0, 5, 5);
		gbc_tagNombre.gridx = 1;
		gbc_tagNombre.gridy = 5;
		getContentPane().add(tagNombre, gbc_tagNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 2;
		gbc_textFieldNombre.gridy = 5;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel tagMaxino = new JLabel("Maximo");
		GridBagConstraints gbc_tagMaxino = new GridBagConstraints();
		gbc_tagMaxino.anchor = GridBagConstraints.EAST;
		gbc_tagMaxino.insets = new Insets(0, 0, 5, 5);
		gbc_tagMaxino.gridx = 1;
		gbc_tagMaxino.gridy = 6;
		getContentPane().add(tagMaxino, gbc_tagMaxino);
		
		textFieldMaximo = new JTextField();
		textFieldMaximo.setEditable(false);
		GridBagConstraints gbc_textFieldMaximo = new GridBagConstraints();
		gbc_textFieldMaximo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldMaximo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMaximo.gridx = 2;
		gbc_textFieldMaximo.gridy = 6;
		getContentPane().add(textFieldMaximo, gbc_textFieldMaximo);
		textFieldMaximo.setColumns(10);
		
		JLabel tagFechaAlta = new JLabel("Fecha de alta");
		GridBagConstraints gbc_tagFechaAlta = new GridBagConstraints();
		gbc_tagFechaAlta.anchor = GridBagConstraints.ABOVE_BASELINE_TRAILING;
		gbc_tagFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_tagFechaAlta.gridx = 1;
		gbc_tagFechaAlta.gridy = 7;
		getContentPane().add(tagFechaAlta, gbc_tagFechaAlta);
		
		textFieldAlta = new JTextField();
		textFieldAlta.setEditable(false);
		GridBagConstraints gbc_textFieldAlta = new GridBagConstraints();
		gbc_textFieldAlta.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAlta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAlta.gridx = 2;
		gbc_textFieldAlta.gridy = 7;
		getContentPane().add(textFieldAlta, gbc_textFieldAlta);
		textFieldAlta.setColumns(10);
		
		JLabel tagFechaSal = new JLabel("Fecha de Salida");
		GridBagConstraints gbc_tagFechaSal = new GridBagConstraints();
		gbc_tagFechaSal.anchor = GridBagConstraints.EAST;
		gbc_tagFechaSal.insets = new Insets(0, 0, 5, 5);
		gbc_tagFechaSal.gridx = 1;
		gbc_tagFechaSal.gridy = 8;
		getContentPane().add(tagFechaSal, gbc_tagFechaSal);
		
		textFieldSalida = new JTextField();
		textFieldSalida.setEditable(false);
		GridBagConstraints gbc_textFieldSalida = new GridBagConstraints();
		gbc_textFieldSalida.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSalida.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSalida.gridx = 2;
		gbc_textFieldSalida.gridy = 8;
		getContentPane().add(textFieldSalida, gbc_textFieldSalida);
		textFieldSalida.setColumns(10);
		
		JLabel tagLugarSalida = new JLabel("Lugar de salida");
		GridBagConstraints gbc_tagLugarSalida = new GridBagConstraints();
		gbc_tagLugarSalida.anchor = GridBagConstraints.EAST;
		gbc_tagLugarSalida.insets = new Insets(0, 0, 5, 5);
		gbc_tagLugarSalida.gridx = 1;
		gbc_tagLugarSalida.gridy = 9;
		getContentPane().add(tagLugarSalida, gbc_tagLugarSalida);
		
		textFieldLugar = new JTextField();
		textFieldLugar.setEditable(false);
		GridBagConstraints gbc_textFieldLugar = new GridBagConstraints();
		gbc_textFieldLugar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldLugar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldLugar.gridx = 2;
		gbc_textFieldLugar.gridy = 9;
		getContentPane().add(textFieldLugar, gbc_textFieldLugar);
		textFieldLugar.setColumns(10);
		
		
		
		buttonCerrar = new JButton("Cerrar");
		
		buttonCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
				limpiarFormulario();
				comboBoxDepartamento.removeAllItems();
				comboBoxActividad.removeAllItems();
				comboBoxSalida.removeAllItems();
				mostrarSalida();
            }
        });
		
		lblNewLabel = new JLabel("Hora");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 10;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		textFieldHora = new JTextField();
		textFieldHora.setEditable(false);
		GridBagConstraints gbc_textFieldHora = new GridBagConstraints();
		gbc_textFieldHora.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldHora.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHora.gridx = 2;
		gbc_textFieldHora.gridy = 10;
		getContentPane().add(textFieldHora, gbc_textFieldHora);
		textFieldHora.setColumns(10);
		
		GridBagConstraints gbc_buttonCerrar = new GridBagConstraints();
		gbc_buttonCerrar.gridwidth = 6;
		gbc_buttonCerrar.gridx = 0;
		gbc_buttonCerrar.gridy = 11;
		getContentPane().add(buttonCerrar, gbc_buttonCerrar);
		
		
		
	}
	
	private void limpiarFormulario() {
		textFieldNombre.setText("");	
        textFieldMaximo.setText("");     
        textFieldSalida.setText("");
        textFieldAlta.setText("");
        textFieldLugar.setText("");
        textFieldHora.setText("");
        
    }
	
	
	private void cargarActividades() {
		if (comboBoxDepartamento.getSelectedItem() != null) {
			try {
				HashSet<DTActividad> acts = cDpto.obtenerDatosActividadesAsociadas((String) comboBoxDepartamento.getSelectedItem());
				for (DTActividad a : acts)
					comboBoxActividad.addItem(a.getNombre());
			}catch(departamentoNoExisteException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Departamento invalido", JOptionPane.ERROR_MESSAGE);
			}
									
		}	
		
	}
	
	private void cargarSalidas() {
		if (comboBoxActividad.getSelectedItem() != null) {
			try {
				HashSet<DTSalida> sals = cDpto.obtenerDatosSalidasVigentes((String) comboBoxActividad.getSelectedItem(),(String) comboBoxDepartamento.getSelectedItem());
				for (DTSalida s : sals)
					comboBoxSalida.addItem(s.getNombre());
			}catch(actividadNoExisteException | departamentoNoExisteException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Actividad o departamento invalido", JOptionPane.ERROR_MESSAGE);
			}
									
		}
				
	}
	
	private void mostrarSalida() {
		if(comboBoxDepartamento.getSelectedItem() != null && comboBoxActividad.getSelectedItem() != null && comboBoxSalida.getSelectedItem() != null) {
		try {
			HashSet<DTSalida> sals = cDpto.obtenerDatosSalidasVigentes((String) comboBoxActividad.getSelectedItem(),(String) comboBoxDepartamento.getSelectedItem());
			DTSalida salida = null;
		    
			for(DTSalida it : sals) {
				if(it.getNombre() == comboBoxSalida.getSelectedItem())
					salida = it;
			}
							
			textFieldNombre.setText(salida.getNombre());
			Integer numero = salida.getMaxTuristas();
	        textFieldMaximo.setText(numero.toString());
	        
	        GregorianCalendar fechaSalida = salida.getFechaDTSalida();
	        GregorianCalendar fechaAlta = salida.getAlta();
	        
	        Integer diaS = fechaSalida.get(fechaSalida.DAY_OF_MONTH);
	        Integer mesS = fechaSalida.get(fechaSalida.MONTH) + 1;
	        Integer anioS = fechaSalida.get(fechaSalida.YEAR);
	        String fechaSalidaString = diaS.toString()+"/"+mesS.toString()+"/"+anioS.toString();
	        
	        Integer diaA = fechaAlta.get(fechaAlta.DAY_OF_MONTH);
	        Integer mesA = fechaAlta.get(fechaAlta.MONTH) + 1;
	        Integer anioA = fechaAlta.get(fechaAlta.YEAR);
	        String fechaAltaString = diaA.toString()+"/"+mesA.toString()+"/"+anioA.toString();
	        
	        textFieldSalida.setText(fechaSalidaString);
	        textFieldAlta.setText(fechaAltaString);
	        textFieldLugar.setText(salida.getLugarDTSalida());
	        String minS, horaS;
	        int min = salida.getHora() % 100;
	        int hora = (salida.getHora() - min) / 100;
	        if (min < 10)
	        	minS = "0" +  min;
	        else minS = String.valueOf(min);
	        if (hora < 10)
	        	horaS = "0" + hora;
	        else horaS = String.valueOf(hora);
	        textFieldHora.setText(horaS + ":" + minS + "hs");
	        
		}catch(actividadNoExisteException | departamentoNoExisteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Actividad o departamento invalido", JOptionPane.ERROR_MESSAGE);
		}
		}
	}
	
	public void cargarDepartamentos() {
		tagDpto.setVisible(true);
		tagActividad.setVisible(true);
		tagSalida.setVisible(true);
		
		comboBoxDepartamento.setVisible(true);
		comboBoxActividad.setVisible(true);
		comboBoxSalida.setVisible(true);
		
		comboBoxDepartamento.removeAllItems();
		comboBoxActividad.removeAllItems();
		comboBoxSalida.removeAllItems();
		
		Set<String> dptos = cDpto.obtenerDepartamentos();
		for (String s : dptos)
			comboBoxDepartamento.addItem(s);
		comboBoxDepartamento.setSelectedItem(null);
		comboBoxActividad.setSelectedItem(null);
		comboBoxSalida.setSelectedItem(null);
		
		
	    comboBoxActividad.setEnabled(false);
		comboBoxSalida.setEnabled(false);
		limpiarFormulario();
		
	}
	
	public void mostrar(String dptoNombre, String actNombre, String salNombre) {
		comboBoxDepartamento.removeAllItems();
		comboBoxActividad.removeAllItems();
		comboBoxSalida.removeAllItems();
		limpiarFormulario();
		
		tagDpto.setVisible(false);
		tagActividad.setVisible(false);
		tagSalida.setVisible(false);
		
		comboBoxDepartamento.setVisible(false);
		comboBoxActividad.setVisible(false);
		comboBoxSalida.setVisible(false);
		
		comboBoxDepartamento.addItem(dptoNombre);
		comboBoxActividad.addItem(actNombre);
		comboBoxSalida.addItem(salNombre);
		
		comboBoxDepartamento.setSelectedItem(dptoNombre);
		comboBoxActividad.setSelectedItem(actNombre);
		comboBoxSalida.setSelectedItem(salNombre);
		
		mostrarSalida();
		
	}
	
	public void mostrarDT(DTSalida salida) {
		comboBoxDepartamento.removeAllItems();
		comboBoxActividad.removeAllItems();
		comboBoxSalida.removeAllItems();
		limpiarFormulario();
		buttonCerrar.setVisible(true);
		
		tagDpto.setVisible(false);
		tagActividad.setVisible(false);
		tagSalida.setVisible(false);
		
		comboBoxDepartamento.setVisible(false);
		comboBoxActividad.setVisible(false);
		comboBoxSalida.setVisible(false);
		
		textFieldNombre.setText(salida.getNombre());
		Integer numero = salida.getMaxTuristas();
        textFieldMaximo.setText(numero.toString());
        
        GregorianCalendar fechaSalida = salida.getFechaDTSalida();
        GregorianCalendar fechaAlta = salida.getAlta();
        
        Integer diaS = fechaSalida.get(fechaSalida.DAY_OF_MONTH);
        Integer mesS = fechaSalida.get(fechaSalida.MONTH) + 1;
        Integer anioS = fechaSalida.get(fechaSalida.YEAR);
        String fechaSalidaString = diaS.toString()+"/"+mesS.toString()+"/"+anioS.toString();
        
        Integer diaA = fechaAlta.get(fechaAlta.DAY_OF_MONTH);
        Integer mesA = fechaAlta.get(fechaAlta.MONTH) + 1;
        Integer anioA = fechaAlta.get(fechaAlta.YEAR);
        String fechaAltaString = diaA.toString()+"/"+mesA.toString()+"/"+anioA.toString();
        
        textFieldSalida.setText(fechaSalidaString);
        textFieldAlta.setText(fechaAltaString);
        textFieldLugar.setText(salida.getLugarDTSalida());
        String minS, horaS;
        int min = salida.getHora() % 100;
        int hora = (salida.getHora() - min) / 100;
        if (min < 10)
        	minS = "0" +  min;
        else minS = String.valueOf(min);
        if (hora < 10)
        	horaS = "0" + hora;
        else horaS = String.valueOf(hora);
        textFieldHora.setText(horaS + ":" + minS + "hs");
		mostrarSalida();
		
	}
	
	
}
