package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.UsuarioRepetidoException;
import excepciones.actividadNoExisteException;
import excepciones.categoriaYaExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.proveedorNoExisteException;
import excepciones.salidaNoExisteException;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTActividad;
import logica.datatypes.DTProveedor;

class ControladorDepartamentoTest {
	
	private static IControladorUsuario icu;
	private static IControladorDepartamento icd;

	private static DTProveedor userProveedor1;
	private static DTProveedor userProveedor2;
	private static DTProveedor userProveedor3;
	private static DTProveedor userProveedor4;
	private static DTProveedor userProveedor5;
	
	@BeforeAll
	public static void iniciar() {
		Fabrica fabrica = Fabrica.getInstance();
		icu = fabrica.getIControladorUsuario();
		icd = fabrica.getIControladorDepartamento();
		userProveedor1 = new DTProveedor("artaud123", "Luis", "Spinetta", "muchacha@gmail.com", new GregorianCalendar(2001,6,5), "12345", "Argentino");
		userProveedor2 = new DTProveedor("elPadrino", "Marlon", "Brando", "marBra@gmail.com", new GregorianCalendar(2001,6,5), "12345", "Italiana");
		userProveedor3 = new DTProveedor("balonDeOro", "Lionel", "Messi", "lio@gmail.com", new GregorianCalendar(2001,6,5), "12345", "Uruguaya");
		userProveedor4 = new DTProveedor("bocanada1999", "Gustavo", "Cerati", "dietetico@gmail.com", new GregorianCalendar(2001,6,5), "12345", "Uruguaya");
		userProveedor5 = new DTProveedor("gardelito", "Carlos", "Gardel", "porunacabeza@gmail.com", new GregorianCalendar(2001,6,5), "12345", "Desconocida");
	}
	
	@Test
	void testAltaActividad() {
		HashSet<String> categorias = new HashSet<String>();//HAY QUE TESTEAR ESTO (AGREGAR CATEGORIAS A ACTIVIDAD)

		icd.ingresarDepartamento("Montevideo","Capital de Uruguay", "mvdeo.com.uy");
		icd.ingresarDepartamento("Maldonado","Donde encuentras Piriapolis y Punta del Este", "maldonado.com.uy");
		icd.ingresarDepartamento("Canelones","Segundo departamento mas poblado", "can.com.uy");
		icd.ingresarDepartamento("Artigas","En honor al proser", "artigas.com.uy");
		icd.ingresarDepartamento("Rocha","Donde esta la Pedrera", "rocha.com.uy");
		icd.ingresarDepartamento("Colonia", "La propuesta del Departamento de Colonia divide en cuatro actos su espectáculo anual. Cada acto tiene su magia. Desde su naturaleza y playas hasta sus tradiciones y el patrimonio	mundial. Todo el año se disfruta.", "https://colonia.gub.uy/turismo/");

		
		String departamentos[] = {"Montevideo","Maldonado","Canelones", "Artigas", "Rocha", "Colonia"};
		
		//test de obtenerDepartamentos()
		Set<String> dptos = icd.obtenerDepartamentos();
		for (String d : departamentos) {
			assertEquals(dptos.contains(d),true);
		}
		
		assertEquals(dptos.size(),6);
		
		try {
			icu.altaUsuario(userProveedor1);
			icu.altaUsuario(userProveedor2);
			icu.altaUsuario(userProveedor3);
			icu.altaUsuario(userProveedor4);
			icu.altaUsuario(userProveedor5);
		}
		catch(UsuarioRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		assertThrows(UsuarioRepetidoException.class, ()->{icu.altaUsuario(userProveedor5);});
		
		String proveedores[] = {"artaud123","elPadrino","balonDeOro", "bocanada1999", "gardelito"};
		
		int j;
		//test obtenerProveedores
		String provs[] = icu.obtenerProveedores();
		for (String sal: provs) {
			j = 0;
			for (String res: proveedores) {
				j++;
				if (sal == res)
					break;
			}
			assertEquals(j <= 5, true);
		}
		GregorianCalendar fechaAlta = GregorianCalendar.from(ZonedDateTime.now());
		boolean ingresado;
		try {			
			ingresado = icd.ingresarDatosActividad("Caza de brujas", "Como en la inquisicion pero en 2022", 2, 1, "Cadiz",fechaAlta , "gardelito", "Maldonado", categorias);
			assertEquals(ingresado, false);
			assertThrows(departamentoNoExisteException.class, () -> {icd.ingresarDatosActividad("Caza", "Como en la inquisicion pero en 2022", 2, 1, "Cadiz",fechaAlta , "gardelito", "CABA", categorias);});
			assertThrows(proveedorNoExisteException.class, () -> {icd.ingresarDatosActividad("Caza", "Como en la inquisicion pero en 2022", 2, 1, "Cadiz",fechaAlta , "sinProv", "Maldonado", categorias);});
			ingresado = icd.ingresarDatosActividad("Caza de brujas", "Como en la inquisicion pero en 2022", 2, 1, "Cadiz", fechaAlta, "gardelito", "Maldonado", categorias);
			assertEquals(ingresado, true);
		
		} catch (proveedorNoExisteException | departamentoNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//test obtenerDatosActividad
		try {
			DTActividad obt = icd.obtenerDatosActividad("Caza de brujas");
			assertEquals(obt.getNombre(), "Caza de brujas");
			assertEquals(obt.getDescripcion(), "Como en la inquisicion pero en 2022");
			assertEquals(obt.getDuracion(), 2);
			assertEquals(obt.getCosto(), 1);
			assertEquals(obt.getCiudad(), "Cadiz");
			assertEquals(obt.getAlta(), fechaAlta);
		}
		catch (actividadNoExisteException anee) {
			fail(anee.getMessage());
			anee.printStackTrace();
		}
		assertThrows(actividadNoExisteException.class, ()->{icd.obtenerDatosActividad("Ir al estadio del Huesca");});
		
		
		//test obtenerDeptoActividad
		String nomDep = icd.obtenerDeptoActividad("Caza de brujas");
		assertEquals(nomDep, "Maldonado");
	}
	@Test
	void testobtenerDatosSalidasParaActividad() {
		HashSet<String> categorias = new HashSet<String>();//HAY QUE TESTEAR ESTO (AGREGAR CATEGORIAS A ACTIVIDAD)

		GregorianCalendar fechaAlta = GregorianCalendar.from(ZonedDateTime.now());
		icd.ingresarDepartamento("Maldonado","Donde encuentras Piriapolis y Punta del Este", "maldonado.com.uy");
		try {
			icd.ingresarDatosActividad("Caza de brujas", "Como en la inquisicion pero en 2022", 2, 1, "Cadiz",fechaAlta , "gardelito", "Maldonado", categorias);
			try {
				icd.ingresarDatosSalida( "salida de prueba", 5, fechaAlta, fechaAlta, 0, "fing", "Maldonado", "Caza de brujas", null);
				
			} catch (actividadNoExisteException e) {
				e.printStackTrace();
			}
		} catch (proveedorNoExisteException | departamentoNoExisteException e) {
			e.printStackTrace();
		}
		try {
			icd.obtenerDatosSalidasParaActividad("Caza de brujas");
			assertThrows(actividadNoExisteException.class, () -> {icd.obtenerDatosSalidasParaActividad("No Existe"); });
		} catch (actividadNoExisteException e) {
			e.printStackTrace();
		}
		assertThrows(actividadNoExisteException.class, () -> {icd.ingresarDatosSalida( "salida de prueba", 5, fechaAlta, fechaAlta, 0, "fing", "Maldonado", "No actividad", null);});
		assertThrows(departamentoNoExisteException.class, () -> {icd.ingresarDatosSalida( "salida de prueba", 5, fechaAlta, fechaAlta, 0, "fing", "No", "Caza de brujas", null);});
	}
	
	@Test
	void testobtenerlugaresDisponibles() {
		HashSet<String> categorias = new HashSet<String>();//HAY QUE TESTEAR ESTO (AGREGAR CATEGORIAS A ACTIVIDAD)

		GregorianCalendar fechaAlta = GregorianCalendar.from(ZonedDateTime.now());
		icd.ingresarDepartamento("Maldonado","Donde encuentras Piriapolis y Punta del Este", "maldonado.com.uy");
		try {
			icd.ingresarDatosActividad("Caza de brujas", "Como en la inquisicion pero en 2022", 2, 1, "Cadiz",fechaAlta , "gardelito", "Maldonado", categorias);
			try {
				icd.ingresarDatosSalida( "salida de prueba", 5, fechaAlta, fechaAlta, 0, "fing", "Maldonado", "Caza de brujas", null);
			} catch (actividadNoExisteException e) {
				e.printStackTrace();
			}
		} catch (proveedorNoExisteException | departamentoNoExisteException e) {
			e.printStackTrace();
		}
		assertThrows(salidaNoExisteException.class, () -> {icd.obtenerlugaresDisponibles("Salida inexistente");});
		try {
			assertEquals(icd.obtenerlugaresDisponibles("salida de prueba"), 5);
		} catch (salidaNoExisteException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testIngresarDatosCategoria() {
		try {
			icd.ingresarDatosCategoria("Gastronomía");
			icd.ingresarDatosCategoria("Fotografía");
			icd.ingresarDatosCategoria("Paseos");
			String categorias[] = {"Gastronomía","Fotografía","Paseos"};	
			Set<String> cats = icd.obtenerCategorias();
			for (String c : categorias) {
				assertEquals(cats.contains(c),true);
			}
			
		}
		catch (categoriaYaExisteException e) {
			e.printStackTrace();
		}
		assertThrows(categoriaYaExisteException.class, () -> {icd.ingresarDatosCategoria("Gastronomía");});
	}
	
		
}
