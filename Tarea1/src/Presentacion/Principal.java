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
* Clase Principal (Frame) con el método Main.
* @author leonardo.melgar
*
*/

public class Principal {
	private JFrame frmReservasUY;
	private IControladorUsuario iCU;
	private IControladorDepartamento iCD;
	private IControladorPaquete iCP;
	private ConsultarUsuario conUsrInternalFrame;
	private AltaUsuario crearUsrInternalFrame;
	private InscripcionASalida inscASalInternalFrame;
	private AltaSalida altaSalidaInternalFrame;
	private ConsultaDeSalida consultaSalidaInternalFrame;
	private AltaActividad altaActividadInternalFrame;
	private ConsultaDeActividad consultaActividadInternalFrame;
	private AceptarORechazarActividad aceptarORechazarActividadInternalFrame;
	private AltaCategoria altaCategoriaInternalFrame;
	
	
	
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Principal window = new Principal();
                window.frmReservasUY.setVisible(true);
            }
        });
    }
    
    public Principal() {
    	initialize();
    	
    	// Inicialización
        Fabrica fabrica = Fabrica.getInstance();
        iCU = fabrica.getIControladorUsuario();
        iCD = fabrica.getIControladorDepartamento();
        iCP = fabrica.getIControladorPaquete();
        
        frmReservasUY.getContentPane().setLayout(null);
        
        conUsrInternalFrame = new ConsultarUsuario(iCU, iCD);
        conUsrInternalFrame.setVisible(false);
        frmReservasUY.getContentPane().add(conUsrInternalFrame);
        
        crearUsrInternalFrame = new AltaUsuario(iCU);
        crearUsrInternalFrame.setVisible(false);
        frmReservasUY.getContentPane().add(crearUsrInternalFrame);
        
        inscASalInternalFrame = new InscripcionASalida(iCU, iCD);
        inscASalInternalFrame.setVisible(false);
        frmReservasUY.getContentPane().add(inscASalInternalFrame);
        
        altaSalidaInternalFrame = new AltaSalida(iCD);
        altaSalidaInternalFrame.setVisible(false);
        frmReservasUY.getContentPane().add(altaSalidaInternalFrame);
        
        
        consultaSalidaInternalFrame = new ConsultaDeSalida(iCD);
        consultaSalidaInternalFrame.setVisible(false);
        frmReservasUY.getContentPane().add(consultaSalidaInternalFrame);
        
        altaActividadInternalFrame = new AltaActividad(iCD, iCU);
        altaActividadInternalFrame.setVisible(false);
        frmReservasUY.getContentPane().add(altaActividadInternalFrame);
        
        consultaActividadInternalFrame = new ConsultaDeActividad(iCD);
        consultaActividadInternalFrame.setVisible(false);
        frmReservasUY.getContentPane().add(consultaActividadInternalFrame);
        
        aceptarORechazarActividadInternalFrame = new AceptarORechazarActividad(iCD);
        aceptarORechazarActividadInternalFrame.setVisible(false);
        frmReservasUY.getContentPane().add(aceptarORechazarActividadInternalFrame);
        
        altaCategoriaInternalFrame = new AltaCategoria(iCD);
        altaCategoriaInternalFrame.setVisible(false);
        frmReservasUY.getContentPane().add(altaCategoriaInternalFrame);
    }
    
    
    private void initialize() {
        
        // Se crea el Frame con las dimensiones indicadas.
        frmReservasUY = new JFrame();
        frmReservasUY.setTitle("Reservas_UY admin");
        frmReservasUY.setBounds(150, 100, 1000, 1000);
        //frmReservas_UY.setBounds(100, 100, 569, 450);
        frmReservasUY.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Se crea una barra de menú (JMenuBar) con dos menú (JMenu) desplegables.
        // Cada menú contiene diferentes opciones (JMenuItem), los cuales tienen un 
        // evento asociado que permite realizar una acción una vez se seleccionan. 
        
        //MENU SISTEMA
        
        JMenuBar menuBar = new JMenuBar();
        frmReservasUY.setJMenuBar(menuBar);

        JMenu menuSistema = new JMenu("Sistema");
        menuBar.add(menuSistema);

        JMenuItem menuSalir = new JMenuItem("Salir");
        menuSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Salgo de la aplicación
                frmReservasUY.setVisible(false);
                frmReservasUY.dispose();
            }
        });
        menuSistema.add(menuSalir);
        
        JMenuItem menuCargarDatos = new JMenuItem("Cargar datos");
        menuCargarDatos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                // Cargo los datos
            	CargarDatos carga = new CargarDatos(iCU, iCD, iCP);
            }
        });
        menuSistema.add(menuCargarDatos);
        
        // MENU USUARIOS
        
        JMenu menuUsuarios = new JMenu("Usuarios");
        menuBar.add(menuUsuarios);
        
        JMenuItem menuItemConsultaUsuario = new JMenuItem("Consultar");
        menuItemConsultaUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	conUsrInternalFrame.cargarUsuarios();
                conUsrInternalFrame.setVisible(true);
            }
        });
        menuUsuarios.add(menuItemConsultaUsuario);
        
        JMenuItem menuItemAltaUsuario = new JMenuItem("Alta");
        menuItemAltaUsuario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		crearUsrInternalFrame.setVisible(true);
        	}
        });
        menuUsuarios.add(menuItemAltaUsuario);
        
        JMenuItem menuItemInscripcionASalida = new JMenuItem("Inscribir a salida");
        menuItemInscripcionASalida.setToolTipText("Seleccione esta opción si desea inscribir un usuario a una salida");
        menuItemInscripcionASalida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	inscASalInternalFrame.cargarDptos();
                inscASalInternalFrame.setVisible(true);
            }
        });
        menuUsuarios.add(menuItemInscripcionASalida);
        
        //MENU DE ACTIVIDADES
        
        JMenu menuActividades = new JMenu("Actividades");
        menuBar.add(menuActividades);
        
        JMenuItem menuItemAltaSalida = new JMenuItem("Alta de Salida");
        menuItemAltaSalida.setToolTipText("Seleccione esta opción si desea dar de alta una salida");
        menuItemAltaSalida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	altaSalidaInternalFrame.cargarDptos();
            	altaSalidaInternalFrame.setVisible(true);
            }
        });
        menuActividades.add(menuItemAltaSalida);
        
        JMenuItem menuItemConsultaSalida = new JMenuItem("Consulta de Salida");
        menuItemConsultaSalida.setToolTipText("Seleccione esta opción si desea consultar una salida");
        menuItemConsultaSalida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	consultaSalidaInternalFrame.cargarDepartamentos();
            	consultaSalidaInternalFrame.setVisible(true);
            }
        });
        
        
        
        
        menuActividades.add(menuItemConsultaSalida);
        
        JMenuItem menuItemAltaActividad = new JMenuItem("Alta de Actividad");
        menuItemAltaActividad.setToolTipText("Seleccione esta opción si desea dar de alta una actividad");
        menuItemAltaActividad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	altaActividadInternalFrame.cargarDepartamentos();
            	altaActividadInternalFrame.cargarProveedores();
            	altaActividadInternalFrame.cargarCategorias();
            	altaActividadInternalFrame.setVisible(true);
            }
        });
        menuActividades.add(menuItemAltaActividad);
        
        JMenuItem menuItemConsultaActividad = new JMenuItem("Consulta de Actividad");
        menuActividades.add(menuItemConsultaActividad);
        menuItemConsultaActividad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	consultaActividadInternalFrame.cargarDepartamentos();
            	consultaActividadInternalFrame.setVisible(true);
            }
        });
        
        JMenuItem menuItemAceptarORechazar = new JMenuItem("Aceptar/Rechazar Actividad");
        menuItemAceptarORechazar.setToolTipText("Seleccione esta opción si desea cambiar el estado de una actividad");
        menuItemAceptarORechazar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	aceptarORechazarActividadInternalFrame.cargarActividades();
            	aceptarORechazarActividadInternalFrame.setVisible(true);
            }
        });
        
        
        
        
        menuActividades.add(menuItemAceptarORechazar);
        
        JMenuItem menuItemAltaCategoria = new JMenuItem("Alta de Categoria");
        menuActividades.add(menuItemAltaCategoria);
        menuItemAltaCategoria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	altaCategoriaInternalFrame.setVisible(true);
            }
        });
 		
    }
    
    
}
