package Presentacion;

import javax.swing.JInternalFrame;

import logica.controladores.IControladorUsuario;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AltaUsuario extends JInternalFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IControladorUsuario contrUsers;
	private Boolean esTurista;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JLabel lblIngreseCi;
	private JTextField textFieldCI;
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	
	public AltaUsuario(IControladorUsuario icu) {
		contrUsers = icu;
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Agregar un usuario");
        setBounds(30, 30, 519, 362);
        
        JLabel tipoUserLabel = new JLabel("Indique el tipo de usuario");
        
        JRadioButton turistaButton = new JRadioButton("Turista");
        
        JRadioButton proveedorButton = new JRadioButton("Proveedor");
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(165)
        			.addComponent(tipoUserLabel, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(162, Short.MAX_VALUE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(51)
        			.addComponent(turistaButton)
        			.addPreferredGap(ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
        			.addComponent(proveedorButton)
        			.addGap(74))
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
        			.addContainerGap(245, Short.MAX_VALUE))
        );
        getContentPane().setLayout(groupLayout);
        
        //los agrupo para que solo se seleccione uno
        ButtonGroup turOProv = new ButtonGroup();
        turOProv.add(proveedorButton);
        turOProv.add(turistaButton);
        
        proveedorButton.addActionListener( new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		esTurista = false;
        	}
        });
        
        turistaButton.addActionListener( new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		esTurista = true;
        	}
        });
        
        
        JLabel lblIngreseNombre = new JLabel("Nombre:");
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblIngreseNombre = new GridBagConstraints();
        gbc_lblIngreseNombre.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseNombre.gridx = 0;
        gbc_lblIngreseNombre.gridy = 0;
        getContentPane().add(lblIngreseNombre, gbc_lblIngreseNombre);

        // Una campo de texto (JTextField) para ingresar el nombre del usuario. 
        // Por defecto es posible ingresar cualquier string.
        textFieldNombre = new JTextField();
        GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
        gbc_textFieldNombre.gridwidth = 2;
        gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldNombre.gridx = 1;
        gbc_textFieldNombre.gridy = 0;
        getContentPane().add(textFieldNombre, gbc_textFieldNombre);
        textFieldNombre.setColumns(10);

        // Una etiqueta (JLabel) indicandp que en el siguiente campo debe ingresarse 
        // el apellido del usuario. El texto está alineado horizontalmente a la derecha para
        // que quede casi pegado al campo de texto.
        JLabel lblIngreseApellido = new JLabel("Apellido:");
        lblIngreseApellido.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblIngreseApellido = new GridBagConstraints();
        gbc_lblIngreseApellido.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseApellido.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseApellido.gridx = 0;
        gbc_lblIngreseApellido.gridy = 1;
        getContentPane().add(lblIngreseApellido, gbc_lblIngreseApellido);

        // Una campo de texto (JTextField) para ingresar el apellido del usuario. 
        // Por defecto es posible ingresar cualquier string.
        textFieldApellido = new JTextField();
        GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
        gbc_textFieldApellido.gridwidth = 2;
        gbc_textFieldApellido.fill = GridBagConstraints.BOTH;
        gbc_textFieldApellido.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldApellido.gridx = 1;
        gbc_textFieldApellido.gridy = 1;
        getContentPane().add(textFieldApellido, gbc_textFieldApellido);
        textFieldApellido.setColumns(10);

        // Una etiqueta (JLabel) indicando que en el siguiente campo debe ingresarse 
        // la cédula del usuario. El texto está alineado horizontalmente a la derecha para
        // que quede casi pegado al campo de texto.
        lblIngreseCi = new JLabel("C.I.:");
        lblIngreseCi.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblIngreseCi = new GridBagConstraints();
        gbc_lblIngreseCi.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseCi.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseCi.gridx = 0;
        gbc_lblIngreseCi.gridy = 2;
        getContentPane().add(lblIngreseCi, gbc_lblIngreseCi);

        // Una campo de texto (JTextField) para ingresar la cédula del usuario. 
        // Por defecto es posible ingresar cualquier string.
        // Al campo se le incluye un Tooltip que, al pasar el mouse por encima, despliega un mensaje.
        textFieldCI = new JTextField();
        textFieldCI.setToolTipText("Ingrese un número sin puntos ni guiones");
        textFieldCI.setColumns(10);
        GridBagConstraints gbc_textFieldCI = new GridBagConstraints();
        gbc_textFieldCI.gridwidth = 2;
        gbc_textFieldCI.fill = GridBagConstraints.BOTH;
        gbc_textFieldCI.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldCI.gridx = 1;
        gbc_textFieldCI.gridy = 2;
        getContentPane().add(textFieldCI, gbc_textFieldCI);

        // Un botón (JButton) con un evento asociado que permite registrar el usuario.
        // Dado que el código de registro tiene cierta complejidad, conviene delegarlo
        // a otro método en lugar de incluirlo directamente de el método actionPerformed 
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                
            }
        });

        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.fill = GridBagConstraints.BOTH;
        gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
        gbc_btnAceptar.gridx = 1;
        gbc_btnAceptar.gridy = 3;
        getContentPane().add(btnAceptar, gbc_btnAceptar);

        // Un botón (JButton) con un evento asociado que permite cerrar el formulario (solo ocultarlo).
        // Dado que antes de cerrar se limpia el formulario, se invoca un método reutilizable para ello. 
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.fill = GridBagConstraints.BOTH;
        gbc_btnCancelar.gridx = 2;
        gbc_btnCancelar.gridy = 3;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
    }
        
        
        

}
