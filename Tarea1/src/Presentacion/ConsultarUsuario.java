package Presentacion;
import javax.swing.JInternalFrame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import excepciones.usuarioNoExisteException;
import logica.DataType.DataTurista;
import logica.DataType.DataUsuario;
import logica.controladores.IControladorUsuario;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

public class ConsultarUsuario extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IControladorUsuario contUser;
	private JComboBox<String> listaUsuarios;
	private DataUsuario userSelected;
	private JButton btnCerrar;
	private JLabel nombreUsuario;
	private JLabel nicknameUsuario;
	private JLabel apellidoUsuario;
	private JLabel correoUsuario;
	private JLabel nacimientoUsuario;
	private JLabel nicknameTag;
	private JLabel nombreTag;
	private JLabel apellidoTag;
	private JLabel correoTag;
	private JLabel nacimientoTag;
	private JLabel tipoUsuario;

	public ConsultarUsuario(IControladorUsuario icu) {
		contUser = icu;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consultar un Usuario");
        setBounds(30, 30, 400, 350);
		
		Set<String> ciUsers = contUser.obtenerUsuarios();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{303, 0, 0, 73, 0};
		gridBagLayout.rowHeights = new int[]{27, 0, 0, 0, 0, 0, 0, 0, 178, 29, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		//		Set<String> ciUsers = new HashSet<String>();
		//		ciUsers.add("joaquin");
		//		ciUsers.add("emi");
		//		
				
				
				listaUsuarios = new JComboBox<String>();
				for (String nickname: ciUsers) {
					listaUsuarios.addItem(nickname);
				}
				GridBagConstraints gbc_listaUsuarios = new GridBagConstraints();
				gbc_listaUsuarios.anchor = GridBagConstraints.NORTH;
				gbc_listaUsuarios.insets = new Insets(0, 0, 5, 5);
				gbc_listaUsuarios.gridwidth = 4;
				gbc_listaUsuarios.gridx = 0;
				gbc_listaUsuarios.gridy = 0;
				getContentPane().add(listaUsuarios, gbc_listaUsuarios);
				listaUsuarios.setVisible(true);
				
				listaUsuarios.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String nicknameSelected = (String) listaUsuarios.getSelectedItem();
						try {
							userSelected = contUser.obtenerUsuario(nicknameSelected);		
							
							
						} catch (usuarioNoExisteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
					}

	
				});
		    
		    JLabel lblInfoUsuario = new JLabel("Información de Usuario");
		    lblInfoUsuario.setForeground(Color.BLUE);
		    lblInfoUsuario.setVerticalAlignment(SwingConstants.TOP);
		    lblInfoUsuario.setBounds(104, 70, 180, 14);
		    GridBagConstraints gbc_lblInfoUsuario = new GridBagConstraints();
		    gbc_lblInfoUsuario.anchor = GridBagConstraints.NORTH;
		    gbc_lblInfoUsuario.gridwidth = 5;
		    gbc_lblInfoUsuario.insets = new Insets(0, 0, 5, 0);
		    gbc_lblInfoUsuario.gridx = 0;
		    gbc_lblInfoUsuario.gridy = 1;
		    getContentPane().add(lblInfoUsuario, gbc_lblInfoUsuario);
		    
		    nicknameTag = new JLabel("Nickname: ");
		    GridBagConstraints gbc_nicknameTag = new GridBagConstraints();
		    gbc_nicknameTag.insets = new Insets(0, 0, 5, 5);
		    gbc_nicknameTag.gridx = 0;
		    gbc_nicknameTag.gridy = 2;
		    getContentPane().add(nicknameTag, gbc_nicknameTag);
		
		    nicknameUsuario = new JLabel(userSelected.getNickname());
		    nicknameUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		    nicknameUsuario.setBounds(10, 114, 65, 14);
		    GridBagConstraints gbc_nicknameUsuario = new GridBagConstraints();
		    gbc_nicknameUsuario.fill = GridBagConstraints.BOTH;
		    gbc_nicknameUsuario.insets = new Insets(0, 0, 5, 5);
		    gbc_nicknameUsuario.gridx = 2;
		    gbc_nicknameUsuario.gridy = 2;
		    getContentPane().add(nicknameUsuario, gbc_nicknameUsuario);
		
		nombreTag = new JLabel("Nombre: ");
		GridBagConstraints gbc_nombreTag = new GridBagConstraints();
		gbc_nombreTag.insets = new Insets(0, 0, 5, 5);
		gbc_nombreTag.gridx = 0;
		gbc_nombreTag.gridy = 3;
		getContentPane().add(nombreTag, gbc_nombreTag);
		
		
		

		
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        listaUsuarios.removeAllItems();
		        setVisible(false);
		    }
		});
		
		nombreUsuario = new JLabel(userSelected.getNombre());
		nombreUsuario.setBounds(10, 114, 65, 14);
		GridBagConstraints gbc_nombreUsuario = new GridBagConstraints();
		gbc_nombreUsuario.anchor = GridBagConstraints.WEST;
		gbc_nombreUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_nombreUsuario.gridx = 2;
		gbc_nombreUsuario.gridy = 3;
		getContentPane().add(nombreUsuario, gbc_nombreUsuario);
		
		apellidoTag = new JLabel("Apellido: ");
		GridBagConstraints gbc_apellidoTag = new GridBagConstraints();
		gbc_apellidoTag.insets = new Insets(0, 0, 5, 5);
		gbc_apellidoTag.gridx = 0;
		gbc_apellidoTag.gridy = 4;
		getContentPane().add(apellidoTag, gbc_apellidoTag);
		
		apellidoUsuario = new JLabel(userSelected.getApellido());
		apellidoUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		apellidoUsuario.setBounds(10, 114, 65, 14);
		GridBagConstraints gbc_apellidoUsuario = new GridBagConstraints();
		gbc_apellidoUsuario.fill = GridBagConstraints.BOTH;
		gbc_apellidoUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_apellidoUsuario.gridx = 2;
		gbc_apellidoUsuario.gridy = 4;
		getContentPane().add(apellidoUsuario, gbc_apellidoUsuario);
		
		correoTag = new JLabel("Correo: ");
		GridBagConstraints gbc_correoTag = new GridBagConstraints();
		gbc_correoTag.insets = new Insets(0, 0, 5, 5);
		gbc_correoTag.gridx = 0;
		gbc_correoTag.gridy = 5;
		getContentPane().add(correoTag, gbc_correoTag);
		
		correoUsuario = new JLabel(userSelected.getCorreo());
		correoUsuario.setBounds(10, 114, 65, 14);
		GridBagConstraints gbc_correoUsuario = new GridBagConstraints();
		gbc_correoUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_correoUsuario.gridx = 2;
		gbc_correoUsuario.gridy = 5;
		getContentPane().add(correoUsuario, gbc_correoUsuario);
		
		nacimientoTag = new JLabel("Fecha de nacimiento: ");
		GridBagConstraints gbc_nacimientoTag = new GridBagConstraints();
		gbc_nacimientoTag.insets = new Insets(0, 0, 5, 5);
		gbc_nacimientoTag.gridx = 0;
		gbc_nacimientoTag.gridy = 6;
		getContentPane().add(nacimientoTag, gbc_nacimientoTag);
		
		nacimientoUsuario = new JLabel(userSelected.getNacimiento().toString());
		GridBagConstraints gbc_nacimientoUsuario = new GridBagConstraints();
		gbc_nacimientoUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_nacimientoUsuario.gridx = 2;
		gbc_nacimientoUsuario.gridy = 6;
		getContentPane().add(nacimientoUsuario, gbc_nacimientoUsuario);
		
		tipoUsuario = new JLabel();
		GridBagConstraints gbc_tipoUsuario = new GridBagConstraints();
		gbc_tipoUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_tipoUsuario.gridx = 1;
		gbc_tipoUsuario.gridy = 7;
		getContentPane().add(tipoUsuario, gbc_tipoUsuario);
		tipoUsuario.setVisible(false);
		
		if (userSelected.getClass() == DataTurista.class) {
			tipoUsuario.setText("El usuario es un Turista");
			tipoUsuario.setVisible(true);
			
			
		}
		
		GridBagConstraints gbc_btnCerrar = new GridBagConstraints();
		gbc_btnCerrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCerrar.anchor = GridBagConstraints.NORTH;
		gbc_btnCerrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCerrar.gridwidth = 4;
		gbc_btnCerrar.gridx = 0;
		gbc_btnCerrar.gridy = 9;
		getContentPane().add(btnCerrar, gbc_btnCerrar);
		
		
		
		
		
		
	
        
        
		
	}
	

}