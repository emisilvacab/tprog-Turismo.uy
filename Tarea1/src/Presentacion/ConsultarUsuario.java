package Presentacion;
import javax.swing.JInternalFrame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import excepciones.usuarioNoExisteException;
import datatypes.DTProveedor;
import datatypes.DTTurista;
import datatypes.DTUsuario;
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
	private DTUsuario userSelected;
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
	private JLabel nacionalidadTag;
	private JLabel nacionalidadUsuario;
	private Set<String> salidas;
	private JComboBox<String> comboSalidasInscripto;
	private JLabel descripcionTag;
	private JLabel descripcionProv;
	private JLabel linkTag;
	private JLabel linkProv;
	private JComboBox<String> comboActividadesOfrecidas;
	private JComboBox<String> comboSalidasAsociadas;

	public ConsultarUsuario(IControladorUsuario icu) {
		contUser = icu;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consultar un Usuario");
        setBounds(30, 30, 502, 424);
		
		Set<String> ciUsers = contUser.obtenerUsuarios();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{303, 0, 0, 0, 0, 0, 0, 0, 73, 0};
		gridBagLayout.rowHeights = new int[]{27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 178, 0, 0, 29, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
				gbc_listaUsuarios.anchor = GridBagConstraints.NORTHWEST;
				gbc_listaUsuarios.insets = new Insets(0, 0, 5, 0);
				gbc_listaUsuarios.gridwidth = 9;
				gbc_listaUsuarios.gridx = 1;
				gbc_listaUsuarios.gridy = 0;
				getContentPane().add(listaUsuarios, gbc_listaUsuarios);
				listaUsuarios.setVisible(true);
				
				listaUsuarios.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String nicknameSelected = (String) listaUsuarios.getSelectedItem();
						
						try {
							userSelected = contUser.obtenerUsuario(nicknameSelected);
							cargarInfoUsuario(userSelected);
							
							
							
						} catch (usuarioNoExisteException e1) {
							JOptionPane.showMessageDialog( null, e1.getMessage(), "Consultar Usuario", JOptionPane.ERROR_MESSAGE);
						
						} 
						
					}
	
				});
				JLabel lblInfoUsuario = new JLabel("Información de Usuario");
			    lblInfoUsuario.setForeground(Color.BLUE);
			    lblInfoUsuario.setVerticalAlignment(SwingConstants.TOP);
			    lblInfoUsuario.setBounds(104, 70, 180, 14);
			    GridBagConstraints gbc_lblInfoUsuario = new GridBagConstraints();
			    gbc_lblInfoUsuario.anchor = GridBagConstraints.NORTH;
			    gbc_lblInfoUsuario.gridwidth = 10;
			    gbc_lblInfoUsuario.insets = new Insets(0, 0, 5, 0);
			    gbc_lblInfoUsuario.gridx = 0;
			    gbc_lblInfoUsuario.gridy = 1;
			    getContentPane().add(lblInfoUsuario, gbc_lblInfoUsuario);
			    
			    nicknameTag = new JLabel("Nickname: ");
			    GridBagConstraints gbc_nicknameTag = new GridBagConstraints();
			    gbc_nicknameTag.anchor = GridBagConstraints.EAST;
			    gbc_nicknameTag.insets = new Insets(0, 0, 5, 5);
			    gbc_nicknameTag.gridx = 0;
			    gbc_nicknameTag.gridy = 2;
			    getContentPane().add(nicknameTag, gbc_nicknameTag);
			
			   
				
				
				
				
				btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        listaUsuarios.removeAllItems();
				        setVisible(false);
				    }
				});
				
					nombreTag = new JLabel("Nombre: ");
					GridBagConstraints gbc_nombreTag = new GridBagConstraints();
					gbc_nombreTag.anchor = GridBagConstraints.EAST;
					gbc_nombreTag.insets = new Insets(0, 0, 5, 5);
					gbc_nombreTag.gridx = 0;
					gbc_nombreTag.gridy = 3;
					getContentPane().add(nombreTag, gbc_nombreTag);
				
				
				apellidoTag = new JLabel("Apellido: ");
				GridBagConstraints gbc_apellidoTag = new GridBagConstraints();
				gbc_apellidoTag.anchor = GridBagConstraints.EAST;
				gbc_apellidoTag.insets = new Insets(0, 0, 5, 5);
				gbc_apellidoTag.gridx = 0;
				gbc_apellidoTag.gridy = 4;
				getContentPane().add(apellidoTag, gbc_apellidoTag);
				
			
				
				correoTag = new JLabel("Correo: ");
				GridBagConstraints gbc_correoTag = new GridBagConstraints();
				gbc_correoTag.anchor = GridBagConstraints.EAST;
				gbc_correoTag.insets = new Insets(0, 0, 5, 5);
				gbc_correoTag.gridx = 0;
				gbc_correoTag.gridy = 5;
				getContentPane().add(correoTag, gbc_correoTag);
		
		
		
		nacimientoTag = new JLabel("Nacimiento: ");
		GridBagConstraints gbc_nacimientoTag = new GridBagConstraints();
		gbc_nacimientoTag.anchor = GridBagConstraints.EAST;
		gbc_nacimientoTag.insets = new Insets(0, 0, 5, 5);
		gbc_nacimientoTag.gridx = 0;
		gbc_nacimientoTag.gridy = 6;
		getContentPane().add(nacimientoTag, gbc_nacimientoTag);
				
			
			
				
				
		
		tipoUsuario = new JLabel("El usuario es un turista: ");
		tipoUsuario.setForeground(Color.BLUE);
		GridBagConstraints gbc_tipoUsuario = new GridBagConstraints();
		gbc_tipoUsuario.gridwidth = 10;
		gbc_tipoUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_tipoUsuario.gridx = 0;
		gbc_tipoUsuario.gridy = 7;
		getContentPane().add(tipoUsuario, gbc_tipoUsuario);
		tipoUsuario.setVisible(false);
		
		descripcionTag = new JLabel("Descripcion: ");
		GridBagConstraints gbc_descripcionTag = new GridBagConstraints();
		gbc_descripcionTag.anchor = GridBagConstraints.EAST;
		gbc_descripcionTag.insets = new Insets(0, 0, 5, 5);
		gbc_descripcionTag.gridx = 0;
		gbc_descripcionTag.gridy = 8;
		getContentPane().add(descripcionTag, gbc_descripcionTag);
		descripcionTag.setVisible(false);
		
		descripcionProv = new JLabel("New label");
		GridBagConstraints gbc_descripcionProv = new GridBagConstraints();
		gbc_descripcionProv.insets = new Insets(0, 0, 5, 5);
		gbc_descripcionProv.gridx = 7;
		gbc_descripcionProv.gridy = 8;
		getContentPane().add(descripcionProv, gbc_descripcionProv);
		descripcionProv.setVisible(false);
		
		linkTag = new JLabel("Link: ");
		GridBagConstraints gbc_linkTag = new GridBagConstraints();
		gbc_linkTag.anchor = GridBagConstraints.EAST;
		gbc_linkTag.insets = new Insets(0, 0, 5, 5);
		gbc_linkTag.gridx = 0;
		gbc_linkTag.gridy = 9;
		getContentPane().add(linkTag, gbc_linkTag);
		linkTag.setVisible(false);
		
		linkProv = new JLabel("New label");
		GridBagConstraints gbc_linkProv = new GridBagConstraints();
		gbc_linkProv.insets = new Insets(0, 0, 5, 5);
		gbc_linkProv.gridx = 7;
		gbc_linkProv.gridy = 9;
		getContentPane().add(linkProv, gbc_linkProv);
		linkProv.setVisible(false);
		
		comboActividadesOfrecidas = new JComboBox<String>();
		GridBagConstraints gbc_comboActividadesOfrecidas = new GridBagConstraints();
		gbc_comboActividadesOfrecidas.anchor = GridBagConstraints.WEST;
		gbc_comboActividadesOfrecidas.gridwidth = 7;
		gbc_comboActividadesOfrecidas.insets = new Insets(0, 0, 5, 5);
		gbc_comboActividadesOfrecidas.gridx = 2;
		gbc_comboActividadesOfrecidas.gridy = 11;
		getContentPane().add(comboActividadesOfrecidas, gbc_comboActividadesOfrecidas);
		comboActividadesOfrecidas.setVisible(false);
		
		comboSalidasAsociadas = new JComboBox<String>();
		GridBagConstraints gbc_comboSalidasAsociadas = new GridBagConstraints();
		gbc_comboSalidasAsociadas.anchor = GridBagConstraints.WEST;
		gbc_comboSalidasAsociadas.gridwidth = 7;
		gbc_comboSalidasAsociadas.insets = new Insets(0, 0, 5, 5);
		gbc_comboSalidasAsociadas.gridx = 2;
		gbc_comboSalidasAsociadas.gridy = 12;
		getContentPane().add(comboSalidasAsociadas, gbc_comboSalidasAsociadas);
		comboSalidasAsociadas.setVisible(false);
		
		
		
					
		    
		
		GridBagConstraints gbc_btnCerrar = new GridBagConstraints();
		gbc_btnCerrar.anchor = GridBagConstraints.NORTH;
		gbc_btnCerrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCerrar.gridwidth = 10;
		gbc_btnCerrar.gridx = 0;
		gbc_btnCerrar.gridy = 14;
		getContentPane().add(btnCerrar, gbc_btnCerrar);
		
		
		
		  
		
	}
	
	private void cargarInfoUsuario(DTUsuario userSelected) {
		nicknameUsuario = new JLabel(userSelected.getNickname());
	    nicknameUsuario.setHorizontalAlignment(SwingConstants.LEFT);
	    nicknameUsuario.setBounds(10, 114, 65, 14);
	    GridBagConstraints gbc_nicknameUsuario = new GridBagConstraints();
	    gbc_nicknameUsuario.anchor = GridBagConstraints.EAST;
	    gbc_nicknameUsuario.fill = GridBagConstraints.VERTICAL;
	    gbc_nicknameUsuario.insets = new Insets(0, 0, 5, 5);
	    gbc_nicknameUsuario.gridx = 7;
	    gbc_nicknameUsuario.gridy = 2;
	    getContentPane().add(nicknameUsuario, gbc_nicknameUsuario);
	    
	    nombreUsuario = new JLabel(userSelected.getNombre());
		nombreUsuario.setBounds(10, 114, 65, 14);
		GridBagConstraints gbc_nombreUsuario = new GridBagConstraints();
		gbc_nombreUsuario.anchor = GridBagConstraints.EAST;
		gbc_nombreUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_nombreUsuario.gridx = 7;
		gbc_nombreUsuario.gridy = 3;
		getContentPane().add(nombreUsuario, gbc_nombreUsuario);
		
		apellidoUsuario = new JLabel(userSelected.getApellido());
		apellidoUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		apellidoUsuario.setBounds(10, 114, 65, 14);
		GridBagConstraints gbc_apellidoUsuario = new GridBagConstraints();
		gbc_apellidoUsuario.anchor = GridBagConstraints.EAST;
		gbc_apellidoUsuario.fill = GridBagConstraints.VERTICAL;
		gbc_apellidoUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_apellidoUsuario.gridx = 7;
		gbc_apellidoUsuario.gridy = 4;
		getContentPane().add(apellidoUsuario, gbc_apellidoUsuario);
		
		correoUsuario = new JLabel(userSelected.getCorreo());
		correoUsuario.setBounds(10, 114, 65, 14);
		GridBagConstraints gbc_correoUsuario = new GridBagConstraints();
		gbc_correoUsuario.anchor = GridBagConstraints.EAST;
		gbc_correoUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_correoUsuario.gridx = 7;
		gbc_correoUsuario.gridy = 5;
		getContentPane().add(correoUsuario, gbc_correoUsuario);
		
		nacimientoUsuario = new JLabel(userSelected.getNacimiento().toString());
		GridBagConstraints gbc_nacimientoUsuario = new GridBagConstraints();
		gbc_nacimientoUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_nacimientoUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_nacimientoUsuario.gridx = 7;
		gbc_nacimientoUsuario.gridy = 6;
		getContentPane().add(nacimientoUsuario, gbc_nacimientoUsuario);
		
		if (userSelected.getClass() == DTTurista.class) {
			nacionalidadUsuario = new JLabel("Uruguay");
			GridBagConstraints gbc_nacionalidadUsuario = new GridBagConstraints();
			gbc_nacionalidadUsuario.insets = new Insets(0, 0, 5, 5);
			gbc_nacionalidadUsuario.gridx = 4;
			gbc_nacionalidadUsuario.gridy = 8;
			getContentPane().add(nacionalidadUsuario, gbc_nacionalidadUsuario);
			
			nacionalidadTag = new JLabel("Nacionalidad: ");
			GridBagConstraints gbc_nacionalidadTag = new GridBagConstraints();
			gbc_nacionalidadTag.insets = new Insets(0, 0, 5, 5);
			gbc_nacionalidadTag.gridx = 2;
			gbc_nacionalidadTag.gridy = 8;
			getContentPane().add(nacionalidadTag, gbc_nacionalidadTag);
			nacionalidadTag.setVisible(false);
			
			comboSalidasInscripto = new JComboBox<String>();
			GridBagConstraints gbc_comboSalidasInscripto = new GridBagConstraints();
			gbc_comboSalidasInscripto.gridwidth = 4;
			gbc_comboSalidasInscripto.insets = new Insets(0, 0, 5, 5);
			gbc_comboSalidasInscripto.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboSalidasInscripto.gridx = 1;
			gbc_comboSalidasInscripto.gridy = 9;
			getContentPane().add(comboSalidasInscripto, gbc_comboSalidasInscripto);
			comboSalidasInscripto.setVisible(false);
			
			tipoUsuario.setText("El usuario es un Turista");
			tipoUsuario.setVisible(true);
			DTTurista tur = (DTTurista)userSelected;
			nacionalidadUsuario.setText(tur.getNacionalidad());
			nacionalidadUsuario.setVisible(true);
			nacionalidadTag.setVisible(true);
			try {
				salidas = contUser.obtenerSalidasInscripto(tur.getNickname());
			} catch (usuarioNoExisteException e1) {
				JOptionPane.showMessageDialog( null, e1.getMessage(), "Consultar Usuario", JOptionPane.ERROR_MESSAGE);
			}
			for (String salida: salidas) {
				comboSalidasInscripto.addItem(salida);
			}
			comboSalidasInscripto.setVisible(true);
			comboSalidasInscripto.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String salidaSelected = (String)comboSalidasInscripto.getSelectedItem();
					//referencias a consulta de salida, con la salida indicada
					
				}
				
			});
			
			
			
			
		}else {
			tipoUsuario.setText("El usuario es un Proveedor");
			tipoUsuario.setVisible(true);
			DTProveedor prov = (DTProveedor)userSelected;
			linkProv.setText(prov.getLink());
			linkProv.setVisible(true);
			descripcionProv.setText(prov.getDescripcion());
			descripcionProv.setVisible(true);
			linkTag.setVisible(true);
			descripcionTag.setVisible(true);
			Set<String> actividadesOfrecidas = new HashSet<String>();
			try {
				actividadesOfrecidas = contUser.mostrarActividadesOfrecidas(prov.getNickname());
			} catch (usuarioNoExisteException e1) {
				JOptionPane.showMessageDialog( null, e1.getMessage(), "Consultar Usuario", JOptionPane.ERROR_MESSAGE);
			}
			Set<String> salidasAsociadas = contUser.mostrarSalidasAsociadas(actividadesOfrecidas);
			for (String actividad: actividadesOfrecidas) {
				comboActividadesOfrecidas.addItem(actividad);
			}
			comboActividadesOfrecidas.setVisible(true);
			for (String salida: salidasAsociadas) {
				comboActividadesOfrecidas.addItem(salida);
			}
			comboSalidasAsociadas.setVisible(true);
			
			
			
		}
		
		
	}
	

}