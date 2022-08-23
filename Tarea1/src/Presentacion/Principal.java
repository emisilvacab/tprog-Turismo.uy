package Presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorPaquete;
import logica.controladores.IControladorUsuario;

/**
* Clase principal (Frame) con el método Main.
* @author leonardo.melgar
*
*/

public class Principal {
	private JFrame frmReservas_UY;
	private IControladorUsuario ICU;
	private IControladorPaquete ICP;
	private IControladorDepartamento ICD;
	private ConsultarUsuario conUsrInternalFrame;
	
	
	
	
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal window = new Principal();
                    window.frmReservas_UY.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public Principal() {
    	initialize();
    	
    	// Inicialización
        Fabrica fabrica = Fabrica.getInstance();
        ICU = fabrica.getIControladorUsuario();
        ICD = fabrica.getIControladorDepartamento();
        ICP = fabrica.getIControladorPaquete();
        
        conUsrInternalFrame = new ConsultarUsuario(ICU);
        conUsrInternalFrame.setVisible(false);
        frmReservas_UY.getContentPane().add(conUsrInternalFrame);

    }
    
    private void initialize() {
        
        // Se crea el Frame con las dimensiones indicadas.
        frmReservas_UY = new JFrame();
        frmReservas_UY.setTitle("Reservas_UY admin");
        frmReservas_UY.setBounds(100, 100, 450, 400);
        frmReservas_UY.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Se crea una barra de menú (JMenuBar) con dos menú (JMenu) desplegables.
        // Cada menú contiene diferentes opciones (JMenuItem), los cuales tienen un 
        // evento asociado que permite realizar una acción una vez se seleccionan. 
        JMenuBar menuBar = new JMenuBar();
        frmReservas_UY.setJMenuBar(menuBar);

        JMenu menuSistema = new JMenu("Sistema");
        menuBar.add(menuSistema);

        JMenuItem menuSalir = new JMenuItem("Salir");
        menuSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Salgo de la aplicación
                frmReservas_UY.setVisible(false);
                frmReservas_UY.dispose();
            }
        });
        menuSistema.add(menuSalir);
        JMenu menuUsuarios = new JMenu("Usuarios");
        menuBar.add(menuUsuarios);
        
        JMenuItem menuItemConsultaUsuario = new JMenuItem("Consultar");
        menuItemConsultaUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para ver información de un usuario
                conUsrInternalFrame.setVisible(true);
            }
        });
        menuUsuarios.add(menuItemConsultaUsuario);
    }
    
    

}
