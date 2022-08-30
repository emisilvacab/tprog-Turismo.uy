package Presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import logica.Proveedor;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import excepciones.usuarioNoExisteException;
import javax.swing.JButton;

public class ConsultarUsuario extends JInternalFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IControladorUsuario contUser;
	private JComboBox<String> listaUsuarios;
	private JLabel nicknameText;
	private JLabel nombreText;
	private JLabel apellidoText;
	private JLabel correoText;
	private JLabel nacimientoText;
	private JLabel tipoUsuarioText;
	private JLabel descripcionTag;
	private JLabel descripcionText;
	private JLabel linkTag;
	private JLabel linkText;
	private JComboBox<String> actividadesOfrecidasBox;
	private JLabel actividadesOfrecidasTag;
	private JLabel salidasAsociadasTag;
	private JComboBox<String> salidasAsociadasBox;
	private String[] actividadesOfrecidas;
	private JLabel nacionalidadTag;
	private JLabel nacionalidadText;
	private JLabel salidasInscriptoTag;
	private JComboBox<String> salidasInscriptoBox;
	private JButton buttonVerActividad;
	private ConsultaDeActividad consultaDeActividad;
	
	

	public ConsultarUsuario(IControladorUsuario icu, IControladorDepartamento icd) {
		
		consultaDeActividad = new ConsultaDeActividad(icd);
		this.getContentPane().add(consultaDeActividad);
		consultaDeActividad.setVisible(false);
		
		contUser = icu;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consultar un Usuario");
        setBounds(30, 30, 502, 424);
        
        listaUsuarios = new JComboBox<String>();
        listaUsuarios.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cargarInfoUsuario((String) listaUsuarios.getSelectedItem());
        		cargarUsuarios();
        	}
        });
        
        JLabel infoUsuario = new JLabel("Información del Usuario:");
        
        JLabel nicknameTag = new JLabel("Nickname: ");
        
        nicknameText = new JLabel("New label");
        nicknameText.setVisible(false);
        
        JLabel nombreTag = new JLabel("Nombre: ");
        
        nombreText = new JLabel("New label");
        nombreText.setVisible(false);
        
        JLabel apellidoTag = new JLabel("Apellido: ");
        
        apellidoText = new JLabel("New label");
        apellidoText.setVisible(false);
        
        JLabel correoTag = new JLabel("Correo: ");
        
        correoText = new JLabel("New label");
        correoText.setVisible(false);
        
        JLabel nacimientoLabel = new JLabel("Nacimiento: ");
        
        nacimientoText = new JLabel("New label");
        nacimientoText.setVisible(false);
        
        tipoUsuarioText = new JLabel("New label");
        tipoUsuarioText.setVisible(false);
        
        descripcionTag = new JLabel("Descripción: ");
        descripcionTag.setVisible(false);
        
        descripcionText = new JLabel("New label");
        descripcionText.setVisible(false);
        
        linkTag = new JLabel("Link: ");
        linkTag.setVisible(false);
        
        linkText = new JLabel("New label");
        linkText.setVisible(false);
        
        actividadesOfrecidasBox = new JComboBox<String>();
        actividadesOfrecidasBox.setVisible(false);
        actividadesOfrecidasBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//caso de uso consulta de actividad
        	}
        });
        
        actividadesOfrecidasTag = new JLabel("Actividades ofrecidas: ");
        actividadesOfrecidasTag.setVisible(false);
        
        salidasAsociadasTag = new JLabel("Salidas asociadas: ");
        salidasAsociadasTag.setVisible(false);
        
        salidasAsociadasBox = new JComboBox<String>();
        salidasAsociadasBox.setVisible(false);
        salidasAsociadasBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		     //llamar a consulta de actividad   
        	}
        });
        
        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        		limpiarFormulario();
        	}
        });
        
        nacionalidadTag = new JLabel("Nacionalidad: ");
        nacionalidadTag.setVisible(false);
        
        nacionalidadText = new JLabel("New label");
        nacionalidadText.setVisible(false);
        
        salidasInscriptoTag = new JLabel("SalidasInscripto: ");
        salidasInscriptoTag.setVisible(false);
       
        
        salidasInscriptoBox = new JComboBox<String>();
        salidasInscriptoBox.setVisible(false);
        salidasInscriptoBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//llamar caso de uso consulta salida
        	}
        });
        
        buttonVerActividad = new JButton("Ver");
        
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(136)
        			.addComponent(listaUsuarios, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(144, Short.MAX_VALUE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(151)
        			.addComponent(infoUsuario, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
        			.addGap(129))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(157)
        			.addComponent(tipoUsuarioText)
        			.addContainerGap(331, Short.MAX_VALUE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(36)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(salidasInscriptoTag)
        					.addGap(32)
        					.addComponent(salidasInscriptoBox, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
        					.addComponent(cerrarButton, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
        				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
        					.addGroup(groupLayout.createSequentialGroup()
        						.addComponent(nacimientoLabel)
        						.addPreferredGap(ComponentPlacement.RELATED)
        						.addComponent(nacimientoText, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
        					.addGroup(groupLayout.createSequentialGroup()
        						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        							.addGroup(groupLayout.createSequentialGroup()
        								.addComponent(nicknameTag)
        								.addPreferredGap(ComponentPlacement.RELATED)
        								.addComponent(nicknameText, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
        							.addGroup(groupLayout.createSequentialGroup()
        								.addComponent(apellidoTag)
        								.addGap(18)
        								.addComponent(apellidoText)))
        						.addGap(45)
        						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        							.addGroup(groupLayout.createSequentialGroup()
        								.addComponent(nombreTag)
        								.addPreferredGap(ComponentPlacement.RELATED)
        								.addComponent(nombreText))
        							.addGroup(groupLayout.createSequentialGroup()
        								.addComponent(correoTag)
        								.addPreferredGap(ComponentPlacement.UNRELATED)
        								.addComponent(correoText))))
        					.addGroup(groupLayout.createSequentialGroup()
        						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        							.addGroup(groupLayout.createSequentialGroup()
        								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        									.addComponent(descripcionTag)
        									.addComponent(linkTag))
        								.addPreferredGap(ComponentPlacement.RELATED)
        								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        									.addGroup(groupLayout.createSequentialGroup()
        										.addComponent(linkText)
        										.addGap(60)
        										.addComponent(nacionalidadTag)
        										.addPreferredGap(ComponentPlacement.RELATED)
        										.addComponent(nacionalidadText, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
        									.addComponent(descripcionText, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)))
        							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
        								.addComponent(actividadesOfrecidasTag)
        								.addPreferredGap(ComponentPlacement.RELATED)
        								.addComponent(actividadesOfrecidasBox, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
        								.addPreferredGap(ComponentPlacement.UNRELATED)
        								.addComponent(buttonVerActividad, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
        							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
        								.addComponent(salidasAsociadasTag)
        								.addPreferredGap(ComponentPlacement.UNRELATED)
        								.addComponent(salidasAsociadasBox, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)))
        						.addPreferredGap(ComponentPlacement.RELATED))))
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(21)
        			.addComponent(listaUsuarios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(infoUsuario)
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(nicknameTag)
        				.addComponent(nicknameText)
        				.addComponent(nombreTag)
        				.addComponent(nombreText))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(apellidoTag)
        				.addComponent(apellidoText)
        				.addComponent(correoTag)
        				.addComponent(correoText))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(nacimientoLabel)
        				.addComponent(nacimientoText))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(tipoUsuarioText)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(descripcionTag)
        				.addComponent(descripcionText))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(linkTag)
        				.addComponent(linkText)
        				.addComponent(nacionalidadTag)
        				.addComponent(nacionalidadText))
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(actividadesOfrecidasTag)
        				.addComponent(actividadesOfrecidasBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(buttonVerActividad))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(salidasAsociadasTag)
        				.addComponent(salidasAsociadasBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
        					.addComponent(cerrarButton)
        					.addContainerGap())
        				.addGroup(groupLayout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(salidasInscriptoTag)
        						.addComponent(salidasInscriptoBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addContainerGap())))
        );
        getContentPane().setLayout(groupLayout);
        
        
        
	}
	protected void limpiarFormulario() {
		listaUsuarios.removeAllItems();
		nombreText.setVisible(false);
		nicknameText.setVisible(false);
		apellidoText.setVisible(false);
		correoText.setVisible(false);
		nacimientoText.setVisible(false);
		actividadesOfrecidasBox.removeAllItems();
		salidasAsociadasBox.removeAllItems();
		actividadesOfrecidasBox.setVisible(false);
		actividadesOfrecidasTag.setVisible(false);
		salidasAsociadasTag.setVisible(false);
		salidasAsociadasBox.setVisible(false);
		descripcionTag.setVisible(false);
		linkTag.setVisible(false);
		linkText.setVisible(false);
		descripcionText.setVisible(false);
		nacionalidadTag.setVisible(false);
		nacionalidadText.setVisible(false);
		salidasInscriptoBox.removeAllItems();
		salidasInscriptoBox.setVisible(false);
		salidasInscriptoTag.setVisible(false);
		tipoUsuarioText.setVisible(false);
	}
	protected void cargarInfoUsuario(String nickname) {
		try {
			DTUsuario user = contUser.obtenerUsuario(nickname);
			nombreText.setText(user.getNombre());
			nicknameText.setText(user.getNickname());
			apellidoText.setText(user.getApellido());
			correoText.setText(user.getCorreo());
			nacimientoText.setText(user.getNacimiento().getTime().toLocaleString());
			nombreText.setVisible(true);
			nicknameText.setVisible(true);
			apellidoText.setVisible(true);
			correoText.setVisible(true);
			nacimientoText.setVisible(true);
			
			if (user.getClass() == DTProveedor.class) {
				tipoUsuarioText.setText("El usuario es proveedor!");
				tipoUsuarioText.setVisible(true);
				DTProveedor prov = (DTProveedor) user;
				cargarActividadesOfrecidas(user.getNickname());
				//cargarSalidasAsociadas();
				actividadesOfrecidasBox.setVisible(true);
				actividadesOfrecidasTag.setVisible(true);
				salidasAsociadasTag.setVisible(true);
				salidasAsociadasBox.setVisible(true);
				descripcionTag.setVisible(true);
				descripcionText.setText(prov.getDescripcion());
				linkText.setText(prov.getLink());
				linkTag.setVisible(true);
				linkText.setVisible(true);
				descripcionText.setVisible(true);
				
			}
			else {
				tipoUsuarioText.setText("El usuario es turista!");
				tipoUsuarioText.setVisible(true);
				DTTurista tur = (DTTurista) user;
				cargarSalidasInscripto(tur.getNickname());
				nacionalidadText.setText(tur.getNacionalidad());
				nacionalidadText.setVisible(true);
				nacionalidadTag.setVisible(true);
				salidasInscriptoTag.setVisible(true);
				salidasInscriptoBox.setVisible(true);
				
			}
		} catch (usuarioNoExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Consultar Usuario", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	public void cargarUsuarios() {
        DefaultComboBoxModel<String> model;
        model = new DefaultComboBoxModel<String>(contUser.obtenerUsuarios());
        listaUsuarios.setModel(model);
    }
	
	public void cargarActividadesOfrecidas(String nickname) {
        DefaultComboBoxModel<String> model;
        try {
        	actividadesOfrecidas = contUser.obtenerActividadesOfrecidas(nickname);
			model = new DefaultComboBoxModel<String>(actividadesOfrecidas);
			listaUsuarios.setModel(model);
		} catch (usuarioNoExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Consultar Usuario", JOptionPane.ERROR_MESSAGE);
		}
    }
	/*
	public void cargarSalidasAsociadas() throws usuarioNoExisteException {
		DefaultComboBoxModel<String> model;
        model = new DefaultComboBoxModel<String>(conPaq.mostrarSalidasAsociadas(actividadesOfrecidas));
		listaUsuarios.setModel(model);
	}
	*/
	public void cargarSalidasInscripto(String nickname) {
		DefaultComboBoxModel<String> model;
        try {
			model = new DefaultComboBoxModel<String>(contUser.obtenerSalidasInscripto(nickname));
			listaUsuarios.setModel(model);
		} catch (usuarioNoExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Consultar Usuario", JOptionPane.ERROR_MESSAGE);
			
		}
	}
}
