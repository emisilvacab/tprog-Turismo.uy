package presentacion;

import javax.swing.JInternalFrame;

import logica.controladores.IControladorDepartamento;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import excepciones.categoriaYaExisteException;
import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AltaCategoria extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private IControladorDepartamento icd;
	
	private JTextField txfNombre;
	private JLabel labelNombre;
	private JLabel lblNewLabel13;
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	public AltaCategoria(IControladorDepartamento picd) {
		
		icd = picd;
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Alta de Categoría");
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(0, 0, 420, 240);
		
	    addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent except) {
            	limpiarFormulario();
				setVisible(false);
            }
        });
		
		labelNombre = new JLabel("Nombre de la categoría:");
		labelNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txfNombre = new JTextField();
		txfNombre.setColumns(10);
		
		lblNewLabel13 = new JLabel("");
		
		btnAceptar = new JButton("Aceptar");
		
		btnAceptar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent except) {
				if (txfNombre.getText().length() != 0) {
					try {
						String nombre = txfNombre.getText();
						icd.ingresarDatosCategoria(nombre);
						JOptionPane.showMessageDialog(null, "Categoría dada de alta con éxito!", "Nueva categoría ingresada", JOptionPane.INFORMATION_MESSAGE);
						limpiarFormulario();
						setVisible(false);
					}
					catch(categoriaYaExisteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "La categoría ingresada ya está en el sistema", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
		        	JOptionPane.showMessageDialog(null, "Ingrese nombre de la categoría", "Categoría no ingresada", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent except) {
        		JOptionPane.showMessageDialog(null, "Alta cancelada!", "Alta de categoría", JOptionPane.INFORMATION_MESSAGE);
        		limpiarFormulario();
				setVisible(false);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel13, GroupLayout.PREFERRED_SIZE, 484, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelNombre)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfNombre, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
							.addGap(92))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(btnAceptar, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)))
							.addGap(89)))
					.addGap(0))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelNombre, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(txfNombre, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addComponent(lblNewLabel13, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(37))
		);
		getContentPane().setLayout(groupLayout);
		
	}
	
	private void limpiarFormulario() {
		txfNombre.setText("");
    }
	
}