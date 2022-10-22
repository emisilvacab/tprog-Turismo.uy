package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import logica.datatypes.DTActividad;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTUsuario;
import excepciones.UsuarioRepetidoException;
import excepciones.actividadNoExisteException;
import excepciones.compraExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.paqueteNoExisteException;
import excepciones.paqueteYaExisteException;
import excepciones.proveedorNoExisteException;
import excepciones.usuarioNoExisteException;
import logica.Estado;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorPaquete;
import logica.controladores.IControladorUsuario;

import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

class ControladorPaqueteTest {
	
	private static IControladorUsuario icu;
	private static IControladorDepartamento icd;
	private static IControladorPaquete icp;
	
	@BeforeAll
	public static void iniciar() {
		Fabrica fabrica = Fabrica.getInstance();
		icu = fabrica.getIControladorUsuario();
		icd = fabrica.getIControladorDepartamento();
		icp = fabrica.getIControladorPaquete();
		DTUsuario user;
		try {	
			user = new DTProveedor("washington", "Washington", "Rocha", "washington@turismorocha.gub.uy", new GregorianCalendar(1970, 8, 14), "Hola! me llamo Washington y soy el encargado del portal de turismo del departamento de Rocha - Uruguay" , "http://turismorocha.gub.uy/");
			icu.altaUsuario(user);
			
			user = new DTProveedor("eldiez", "Pablo", "Bengoechea", "eldiez@socfomturriv.org.uy", new GregorianCalendar(1965, 5, 27), "Pablo es el presidente de la Sociedad de Fomento Turístico de Rivera (conocida como Socfomturriv)", "http://wwww.socfomturriv.org.uy");
			icu.altaUsuario(user);
			
			user = new DTProveedor("meche", "Mercedes", "Venn", "meche@colonia.gub.uy", new GregorianCalendar(1990, 11, 31), "Departamento de Turismo del Departamento de Colonia", "https://colonia.gub.uy/turismo/");
			icu.altaUsuario(user);

		} catch (UsuarioRepetidoException e) {
			JOptionPane.showMessageDialog( null, "Los datos ya fueron cargados previamente", "Cargar datos", JOptionPane.ERROR_MESSAGE);
		}
		
		icd.ingresarDepartamento("Rocha", "La Organización de Gestión del Destino (OGD) Rocha es un Ámbito de articulación público - privada en el sector turístico que integran la Corporación Rochense de Turismo y la Intendencia de Rocha a través de su Dirección de Turismo.", "www.turismorocha.gub.uy");
		icd.ingresarDepartamento("Colonia", "La propuesta del Departamento de Colonia divide en cuatro actos su espectáculo anual. Cada acto tiene su magia. Desde su naturaleza y playas hasta sus tradiciones y el patrimonio	mundial. Todo el año se disfruta.", "https://colonia.gub.uy/turismo/");
		
		try { 
			HashSet<String> categorias = new HashSet<String>(); //HAY QUE TESTEAR ESTO (AGREGAR CATEGORIAS A ACTIVIDAD)

			icd.ingresarDatosActividad("Degusta",  "Festival gastronómico de productos locales en Rocha", 3, 800, "Rocha", new GregorianCalendar(2022, 6, 20), "washington", "Rocha", categorias);
			icd.ingresarDatosActividad("Teatro con Sabores",  "En el mes aniversario del Club Deportivo Unión de Rocha te invitamos a una merienda deliciosa.", 3, 500, "Rocha", new GregorianCalendar(2022, 6, 21), "washington", "Rocha", categorias);

			icd.ingresarDatosActividad("Tour por Colonia del Sacramento",  "Con guía especializado y en varios idiomas. Varios circuitos posibles.", 2, 400, "Colonia del Sacramento", new GregorianCalendar(2022, 7, 1), "meche", "Colonia", categorias);
			icd.ingresarDatosActividad("Almuerzo en el Real de San Carlos",  "Restaurante en la renovada Plaza de Toros con menú internacional.", 2, 800, "Colonia del Sacramento", new GregorianCalendar(2022, 7, 1), "meche", "Colonia", categorias);
		}
		catch(departamentoNoExisteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "El departamento seleccionado no está registrado en el sistema", JOptionPane.ERROR_MESSAGE);
		}
		catch(proveedorNoExisteException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "El proveedor ingresado no está registrado en el sistema", JOptionPane.ERROR_MESSAGE);
		}
		
	}
		
	
		
		
	@Test
	void ingresarObtenerDatosPaqueteTest() {
	
		try {
			icp.ingresarDatosPaquete("Disfrutar Rocha", "Actividades para hacer en familia y disfrutar arte y gastronomía", 60, 20, new GregorianCalendar(2022, 7, 10), null);
			icp.ingresarDatosPaquete("Disfrutar", "Actividades para hacer en familia y disfrutar arte y gastronomía", 60, 20, new GregorianCalendar(2022, 7, 10), null);
			icp.ingresarDatosPaquete("Un día en Colonia", "Paseos por el casco histórico y se puede terminar con Almuerzo en la Plaza de Toros", 45, 15, new GregorianCalendar(2022, 7, 1), null);
		}
		catch(paqueteYaExisteException ep) {
			JOptionPane.showMessageDialog(null, ep.getMessage(), "Paquete ya existe", JOptionPane.ERROR_MESSAGE);
		}
		
		try {
			Set<String> paquetes = new HashSet<String>();
			paquetes.add("Disfrutar Rocha");
			paquetes.add("Disfrutar");
			paquetes.add("Un día en Colonia");
			DTPaquete paq1 = icp.obtenerDatosPaquete("Disfrutar Rocha");
			DTPaquete paq2 = icp.obtenerDatosPaquete("Un día en Colonia");
			assertEquals(paq1.getNombre(), "Disfrutar Rocha");
			assertEquals(paq1.getDescripcion(), "Actividades para hacer en familia y disfrutar arte y gastronomía");
			assertEquals(paq1.getValidez(), 60);
			assertEquals(paq1.getDescuento(), (float) 20);
			assertEquals(paq1.getFechaAlta(), new GregorianCalendar(2022, 7, 10));
			assertEquals(paq2.getNombre(), "Un día en Colonia");
			assertEquals(paq2.getDescripcion(), "Paseos por el casco histórico y se puede terminar con Almuerzo en la Plaza de Toros");
			assertEquals(paq2.getValidez(), 45);
			assertEquals(paq2.getDescuento(), (float) 15);
			assertEquals(paq2.getFechaAlta(), new GregorianCalendar(2022, 7, 1));
			assertThrows(paqueteNoExisteException.class, () -> {
				icp.obtenerDatosPaquete("A");
			});
			HashSet<DTPaquete> paquetesDT = icp.obtenerPaquetesNoComprados();
			for (DTPaquete p : paquetesDT) {
				assertEquals(paquetes.contains(p.getNombre()), true);
			}

		}
		catch(paqueteNoExisteException ep) {
			JOptionPane.showMessageDialog(null, ep.getMessage(), "Paquete no existe", JOptionPane.ERROR_MESSAGE);
		}
		assertThrows(paqueteYaExisteException.class, () -> {
			icp.ingresarDatosPaquete("Disfrutar Rocha", "Actividades para hacer en familia y disfrutar arte y gastronomía", 60, 20, new GregorianCalendar(2022, 7, 10), null);
		});
		
	}
	
	@Test
	void agregarActividadPaqueteTest() {
		try {
			Set<String> paquetes = new HashSet<String>();
			paquetes.add("Disfrutar Rocha");
			paquetes.add("Un día en Colonia");
			icp.agregarActividadPaquete("Rocha", "Disfrutar Rocha", "Degusta");
			icp.agregarActividadPaquete("Rocha", "Disfrutar Rocha", "Teatro con Sabores");
			icp.agregarActividadPaquete("Colonia", "Un día en Colonia", "Tour por Colonia del Sacramento");
			icp.agregarActividadPaquete("Colonia", "Un día en Colonia", "Almuerzo en el Real de San Carlos");
			HashSet<DTPaquete> paquetesDT = icp.obtenerPaquetesConActividades();
			for (DTPaquete p : paquetesDT) {
				assertEquals(paquetes.contains(p.getNombre()), true);
			}
			
		}
		catch(paqueteNoExisteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Paquete no existe", JOptionPane.ERROR_MESSAGE);
		}
		catch(departamentoNoExisteException e2 ) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "Departamento no existe", JOptionPane.ERROR_MESSAGE);
		}
		catch(actividadNoExisteException e3) {
			JOptionPane.showMessageDialog(null, e3.getMessage(), "Actividad no existe", JOptionPane.ERROR_MESSAGE);
		}
		//test obtenerDatosActividadesConfirmadasNoPaquete 
		icd.modificarEstadoActividad("Degusta", Estado.CONFIRMADA);
		try {
			HashSet<DTActividad> actsConfNoPaq = icp.obtenerDatosActividadesConfirmadasNoPaquete("Rocha", "Un día en Colonia");
			Set<String> actsConf = new HashSet<String>();
			actsConf.add("Degusta");
			for (DTActividad p : actsConfNoPaq) {
				assertEquals(actsConf.contains(p.getNombre()), true);
			}
		} catch (departamentoNoExisteException | paqueteNoExisteException e) {
			e.printStackTrace();
		}
		
		//comprarPaquete
		
		try {
			icp.comprarPaquete("leomel", "Disfrutar Rocha",  GregorianCalendar.from(ZonedDateTime.now()), 1);
			assertThrows(compraExisteException.class, () -> {icp.comprarPaquete("leomel", "Disfrutar Rocha",  GregorianCalendar.from(ZonedDateTime.now()), 3); });

		} catch (usuarioNoExisteException | paqueteNoExisteException | compraExisteException e) {
			e.printStackTrace();
		}

	}
}