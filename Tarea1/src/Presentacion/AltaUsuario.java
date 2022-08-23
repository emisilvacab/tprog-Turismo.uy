package Presentacion;

import javax.swing.JInternalFrame;

import logica.controladores.IControladorUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;

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
	
	public AltaUsuario(IControladorUsuario icu) {
		contrUsers = icu;
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Agregar un usuario");
        setBounds(30, 30, 564, 362);
        
        JLabel tipoUserLabel = new JLabel("Indique el tipo de usuario");
        
        JRadioButton turistaButton = new JRadioButton("Turista");
        
        JRadioButton proveedorButton = new JRadioButton("Proveedor");
        
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
        
        descripcionField = new JTextField();
        descripcionField.setColumns(10);
        
        JLabel linkLabel = new JLabel("Link: ");
        
        linkField = new JTextField();
        linkField.setColumns(10);
        
        JButton cancelarButton = new JButton("Cancelar");
        
        JButton confirmarButton = new JButton("Confirmar");
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(165)
        			.addComponent(tipoUserLabel, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(207, Short.MAX_VALUE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(51)
        					.addComponent(turistaButton))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(23)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(groupLayout.createSequentialGroup()
        							.addComponent(nombreLabel)
        							.addGap(39)
        							.addComponent(nombreField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        							.addGroup(groupLayout.createSequentialGroup()
        								.addComponent(correoLabel)
        								.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
        								.addComponent(correoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        							.addGroup(groupLayout.createSequentialGroup()
        								.addComponent(nicknameLabel)
        								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        								.addComponent(nicknameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        							.addGroup(groupLayout.createSequentialGroup()
        								.addComponent(nacionalidadLabel)
        								.addPreferredGap(ComponentPlacement.RELATED)
        								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        									.addComponent(cancelarButton)
        									.addComponent(nacionalidadField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))))
        			.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(confirmarButton)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(descripcionLabel)
        						.addComponent(apellidoLabel)
        						.addComponent(linkLabel))
        					.addGap(18)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(linkField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(descripcionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(proveedorButton)
        						.addComponent(apellidoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        			.addGap(15))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(20)
        			.addComponent(tipoUserLabel)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(turistaButton)
        				.addComponent(proveedorButton))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(nombreLabel)
        				.addComponent(nombreField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(apellidoLabel)
        				.addComponent(apellidoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(nicknameLabel)
        				.addComponent(nicknameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(correoLabel)
        				.addComponent(correoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(descripcionLabel)
        				.addComponent(descripcionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(26)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(nacionalidadLabel)
        				.addComponent(nacionalidadField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(linkField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(linkLabel))
        			.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(cancelarButton)
        				.addComponent(confirmarButton))
        			.addContainerGap())
        );
        getContentPane().setLayout(groupLayout);
        
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
        
        
        
        
        
        
        
	}
}
