package Presentacion;
import javax.swing.JInternalFrame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import logica.controladores.IControladorUsuario;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class ConsultarUsuario extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IControladorUsuario contUser;
	private JComboBox<String> listaUsuarios;
	private JButton btnCerrar;

	public ConsultarUsuario(IControladorUsuario icu) {
		contUser = icu;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consultar un Usuario");
        setBounds(30, 30, 400, 280);
        
        
        
		getContentPane().setLayout(new BorderLayout());
		
		//Set<String> ciUsers = contUser.obtenerUsuarios();
		Set<String> ciUsers = new HashSet<String>();
		ciUsers.add("joaquin");
		ciUsers.add("emi");
		
		
		
		listaUsuarios = new JComboBox<String>();
		getContentPane().add(listaUsuarios, BorderLayout.CENTER);
		
		for (String nickname: ciUsers) {
			listaUsuarios.addItem(nickname);
		}
		listaUsuarios.setVisible(true);
		
		btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listaUsuarios.removeAllItems();
                setVisible(false);
            }
        });
        getContentPane().add(btnCerrar, BorderLayout.SOUTH);
        
		
		
	}

}