package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import logica.Estado;
import logica.controladores.IControladorDepartamento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AceptarORechazarActividad extends JInternalFrame{
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBoxActividades;
	private IControladorDepartamento controladorDeptos;
	private JRadioButton aceptarButton;
	private JRadioButton rechazarButton;
	private ButtonGroup aceptarORechazar;
	
	public AceptarORechazarActividad(IControladorDepartamento icd) {
		
		controladorDeptos = icd;
		
		setMaximizable(true);
		setClosable(true);
		setResizable(true);
	    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Aceptar/Rechazar actividad");
		setBounds(100, 100, 499, 511);
		
		addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent except) {
				setVisible(false);
				limpiarFormulario();
            }
        });
		
		JLabel lblActividades = new JLabel("Actividades agregadas: ");
		
		comboBoxActividades = new JComboBox<String>();
		
		JLabel lblTitulo = new JLabel("Aceptar o confirmar actividad");
		
		aceptarButton = new JRadioButton("Aceptar");
		rechazarButton = new JRadioButton("Rechazar");
		
		aceptarORechazar = new ButtonGroup();
		aceptarORechazar.add(aceptarButton);
		aceptarORechazar.add(rechazarButton);
		
		JButton confirmarButton = new JButton("Confirmar");
		JButton cancelarButton = new JButton("Cancelar");
		
		cancelarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent except) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		
		confirmarButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent except) {
        		if (comboBoxActividades.getSelectedItem() != null) {
	        		String actividadSeleccionada = (String) comboBoxActividades.getSelectedItem();
	        		if (aceptarButton.isSelected()) {
	        			icd.modificarEstadoActividad(actividadSeleccionada, Estado.CONFIRMADA);
    					JOptionPane.showMessageDialog(null, "Actividad aceptada", "Aceptar/rechazar actividad", JOptionPane.INFORMATION_MESSAGE);
	        		}else {
	        			if (rechazarButton.isSelected()) {
		        			icd.modificarEstadoActividad(actividadSeleccionada, Estado.RECHAZADA);
	    					JOptionPane.showMessageDialog(null, "Actividad rechazada", "Aceptar/rechazar actividad", JOptionPane.INFORMATION_MESSAGE);
	        				
	        			}else {
	    					JOptionPane.showMessageDialog(null, "Debe seleccionara un estado para la actividad", "Aceptar/rechazar actividad", JOptionPane.ERROR_MESSAGE);
	        			}
	        		}
        		}else {
					JOptionPane.showMessageDialog(null, "Debe seleccionara una actividad", "Aceptar/rechazar actividad", JOptionPane.ERROR_MESSAGE);
        		}
        		aceptarORechazar.clearSelection();
            	cargarActividades();
        	}
        });
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(120)
							.addComponent(lblTitulo))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(62)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(cancelarButton)
								.addComponent(lblActividades)
								.addComponent(aceptarButton))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBoxActividades, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(36)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(confirmarButton)
										.addComponent(rechazarButton))))))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addComponent(lblTitulo)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblActividades)
						.addComponent(comboBoxActividades, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(aceptarButton)
						.addComponent(rechazarButton))
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(confirmarButton)
						.addComponent(cancelarButton))
					.addContainerGap(239, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	public void limpiarFormulario() {
		aceptarORechazar.clearSelection();

	}

	public void cargarActividades() {
		DefaultComboBoxModel<String> model;
		String[] actividades = controladorDeptos.obtenerActividadesAgregadas();
		comboBoxActividades.removeAllItems();
		model = new DefaultComboBoxModel<String>(actividades);
		comboBoxActividades.setModel(model);
	}
}
