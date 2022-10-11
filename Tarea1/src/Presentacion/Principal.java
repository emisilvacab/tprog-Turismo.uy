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
	private JFrame frmReservas_UY;
	private IControladorUsuario ICU;
	private IControladorDepartamento ICD;
	private IControladorPaquete ICP;
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
        
        frmReservas_UY.getContentPane().setLayout(null);
        
        conUsrInternalFrame = new ConsultarUsuario(ICU,ICD);
        conUsrInternalFrame.setVisible(false);
        frmReservas_UY.getContentPane().add(conUsrInternalFrame);
        
        crearUsrInternalFrame = new AltaUsuario(ICU);
        crearUsrInternalFrame.setVisible(false);
        frmReservas_UY.getContentPane().add(crearUsrInternalFrame);
        
        inscASalInternalFrame = new InscripcionASalida(ICU,ICD);
        inscASalInternalFrame.setVisible(false);
        frmReservas_UY.getContentPane().add(inscASalInternalFrame);
        
        altaSalidaInternalFrame = new AltaSalida(ICD);
        altaSalidaInternalFrame.setVisible(false);
        frmReservas_UY.getContentPane().add(altaSalidaInternalFrame);
        
        
        consultaSalidaInternalFrame = new ConsultaDeSalida(ICD);
        consultaSalidaInternalFrame.setVisible(false);
        frmReservas_UY.getContentPane().add(consultaSalidaInternalFrame);
        
        altaActividadInternalFrame = new AltaActividad(ICD,ICU);
        altaActividadInternalFrame.setVisible(false);
        frmReservas_UY.getContentPane().add(altaActividadInternalFrame);
        
        consultaActividadInternalFrame = new ConsultaDeActividad(ICD);
        consultaActividadInternalFrame.setVisible(false);
        frmReservas_UY.getContentPane().add(consultaActividadInternalFrame);
        
        aceptarORechazarActividadInternalFrame = new AceptarORechazarActividad(ICD);
        aceptarORechazarActividadInternalFrame.setVisible(false);
        frmReservas_UY.getContentPane().add(aceptarORechazarActividadInternalFrame);
        
        altaCategoriaInternalFrame = new AltaCategoria(ICD);
        altaCategoriaInternalFrame.setVisible(false);
        frmReservas_UY.getContentPane().add(altaCategoriaInternalFrame);
    }
    
    
    private void initialize() {
        
        // Se crea el Frame con las dimensiones indicadas.
        frmReservas_UY = new JFrame();
        frmReservas_UY.setTitle("Reservas_UY admin");
        frmReservas_UY.setBounds(150, 100, 1000, 1000);
        //frmReservas_UY.setBounds(100, 100, 569, 450);
        frmReservas_UY.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Se crea una barra de menú (JMenuBar) con dos menú (JMenu) desplegables.
        // Cada menú contiene diferentes opciones (JMenuItem), los cuales tienen un 
        // evento asociado que permite realizar una acción una vez se seleccionan. 
        
        //MENU SISTEMA
        
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
        
        JMenuItem menuCargarDatos = new JMenuItem("Cargar datos");
        menuCargarDatos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                // Cargo los datos
            	CargarDatos carga = new CargarDatos(ICU, ICD, ICP);
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
