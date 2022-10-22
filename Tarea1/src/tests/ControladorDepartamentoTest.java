package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.UsuarioRepetidoException;
import excepciones.actividadNoExisteException;
import excepciones.categoriaNoExisteException;
import excepciones.categoriaYaExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.proveedorNoExisteException;
import excepciones.salidaNoExisteException;
import logica.Estado;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTActividad;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTSalida;

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
		userProveedor1 = new DTProveedor("artaud123", "Luis", "Spinetta", "muchacha@gmail.com", new GregorianCalendar(2001, 6, 5), "12345", null, "Argentino");
		userProveedor2 = new DTProveedor("elPadrino", "Marlon", "Brando", "marBra@gmail.com", new GregorianCalendar(2001, 6, 5), "12345", null, "Italiana");
		userProveedor3 = new DTProveedor("balonDeOro", "Lionel", "Messi", "lio@gmail.com", new GregorianCalendar(2001, 6, 5), "12345", null, "Uruguaya");
		userProveedor4 = new DTProveedor("bocanada1999", "Gustavo", "Cerati", "dietetico@gmail.com", new GregorianCalendar(2001, 6, 5), "12345", null, "Uruguaya");
		userProveedor5 = new DTProveedor("gardelito", "Carlos", "Gardel", "porunacabeza@gmail.com", new GregorianCalendar(2001, 6, 5), "12345", null, "Desconocida");
	}
	
	@Test
	void testAltaActividad() {
		HashSet<String> categorias = new HashSet<String>(); //HAY QUE TESTEAR ESTO (AGREGAR CATEGORIAS A ACTIVIDAD)
		
		//testIngresarDatosCategoria()
		try {
			icd.ingresarDatosCategoria("Gastronomía");
			icd.ingresarDatosCategoria("Fotografía");
			icd.ingresarDatosCategoria("Paseos");
			String categoriasArr[] = {"Gastronomía", "Fotografía", "Paseos"};	
			Set<String> cats = icd.obtenerCategorias();
			for (String c : categoriasArr) {
				assertEquals(cats.contains(c), true);
				categorias.add(c);
			}
		}
		catch (categoriaYaExisteException e) {
			e.printStackTrace();
		}
		assertThrows(categoriaYaExisteException.class, () -> {
			icd.ingresarDatosCategoria("Gastronomía");
		});	

		icd.ingresarDepartamento("Montevideo", "Capital de Uruguay", "mvdeo.com.uy");
		icd.ingresarDepartamento("Maldonado", "Donde encuentras Piriapolis y Punta del Este", "maldonado.com.uy");
		icd.ingresarDepartamento("Canelones", "Segundo departamento mas poblado", "can.com.uy");
		icd.ingresarDepartamento("Artigas", "En honor al proser", "artigas.com.uy");
		icd.ingresarDepartamento("Rocha", "Donde esta la Pedrera", "rocha.com.uy");
		icd.ingresarDepartamento("Colonia", "La propuesta del Departamento de Colonia divide en cuatro actos su espectáculo anual. Cada acto tiene su magia. Desde su naturaleza y playas hasta sus tradiciones y el patrimonio	mundial. Todo el año se disfruta.", "https://colonia.gub.uy/turismo/");

		String departamentos[] = {"Montevideo", "Maldonado", "Canelones", "Artigas", "Rocha", "Colonia"};
		
		//test de obtenerDepartamentos()
		Set<String> dptos = icd.obtenerDepartamentos();
		for (String d : departamentos) {
			assertEquals(dptos.contains(d), true);
		}
		
		assertEquals(dptos.size(), 6);
		
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
		
		assertThrows(UsuarioRepetidoException.class, ()-> {
			icu.altaUsuario(userProveedor5);
		});
		
		String proveedores[] = {"artaud123", "elPadrino", "balonDeOro", "bocanada1999", "gardelito"};
		
		int contador;
		//test obtenerProveedores
		String provs[] = icu.obtenerProveedores();
		for (String sal: provs) {
			contador = 0;
			for (String res: proveedores) {
				contador++;
				if (sal == res)
					break;
			}
			assertEquals(contador <= 5, true);
		}
		GregorianCalendar fechaAlta = GregorianCalendar.from(ZonedDateTime.now());
		boolean ingresado;
		try {			
			ingresado = icd.ingresarDatosActividad("Caza de brujas", "Como en la inquisicion pero en 2022", 2, 1, "Cadiz", fechaAlta , "gardelito", "Maldonado", categorias, null);
			assertEquals(ingresado, false);

			assertThrows(departamentoNoExisteException.class, () -> {
				icd.ingresarDatosActividad("Caza", "Como en la inquisicion pero en 2022", 2, 1, "Cadiz", fechaAlta, "gardelito", "CABA", categorias, null);
			});
			assertThrows(proveedorNoExisteException.class, () -> {
				icd.ingresarDatosActividad("Caza", "Como en la inquisicion pero en 2022", 2, 1, "Cadiz", fechaAlta, "sinProv", "Maldonado", categorias, null);
			});
			ingresado = icd.ingresarDatosActividad("Caza de brujas", "Como en la inquisicion pero en 2022", 2, 1, "Cadiz", fechaAlta, "gardelito", "Maldonado", categorias, null);

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
		assertThrows(actividadNoExisteException.class, ()-> {
			icd.obtenerDatosActividad("Ir al estadio del Huesca");
		});
		
		//test modificarEstadoActividad y obtenerDatosActividadesConfirmadasDpto
		
		try {
			HashSet<DTActividad> actividadesConfirmadas = (HashSet<DTActividad>) icd.obtenerDatosActividadesConfirmadasDpto("Maldonado");
			assertEquals(actividadesConfirmadas.size(), 0);
			
			icd.modificarEstadoActividad("Caza de brujas", Estado.CONFIRMADA);
			actividadesConfirmadas = (HashSet<DTActividad>) icd.obtenerDatosActividadesConfirmadasDpto("Maldonado");
			for (DTActividad a :actividadesConfirmadas) {
				assertEquals(a.getNombre(), "Caza de brujas");
			}
			actividadesConfirmadas = (HashSet<DTActividad>) icd.obtenerDatosActividadesConfirmadasCat("Gastronomía");
			for (DTActividad a :actividadesConfirmadas) {
				assertEquals(a.getNombre(), "Caza de brujas");
			}
			assertThrows(categoriaNoExisteException.class, ()-> {
				icd.obtenerDatosActividadesConfirmadasCat("AAAA");
			});
		} catch (departamentoNoExisteException | categoriaNoExisteException e) {
			e.printStackTrace();
		}
		
		//obtenerCategoriasActividad
		try {
			HashSet<String> catsAct = icd.obtenerCategoriasActividad("Caza de brujas");
			for (String c :catsAct) {
				assertEquals(categorias.contains(c), true);
			}
		} catch (actividadNoExisteException e) {
			e.printStackTrace();
		}
		
		//test obtenerDeptoActividad
		String nomDep = icd.obtenerDeptoActividad("Caza de brujas");
		assertEquals(nomDep, "Maldonado");
	}
	@Test
	void testobtenerDatosSalidasParaActividad() {
		HashSet<String> categorias = new HashSet<String>(); //HAY QUE TESTEAR ESTO (AGREGAR CATEGORIAS A ACTIVIDAD)

		GregorianCalendar fechaAlta = GregorianCalendar.from(ZonedDateTime.now());
		icd.ingresarDepartamento("Maldonado", "Donde encuentras Piriapolis y Punta del Este", "maldonado.com.uy");
		try {
			
			icd.ingresarDatosActividad("Caza de brujas", "Como en la inquisicion pero en 2022", 2, 1, "Cadiz", fechaAlta , "gardelito", "Maldonado", categorias, null);

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
			assertThrows(actividadNoExisteException.class, () -> {
				icd.obtenerDatosSalidasParaActividad("No Existe");
			});
		} catch (actividadNoExisteException e) {
			e.printStackTrace();
		}
		
		assertThrows(actividadNoExisteException.class, () -> {
			icd.ingresarDatosSalida( "salida de prueba", 5, fechaAlta, fechaAlta, 0, "fing", "Maldonado", "No actividad", null);
		});
		
		assertThrows(departamentoNoExisteException.class, () -> {
			icd.ingresarDatosSalida( "salida de prueba", 5, fechaAlta, fechaAlta, 0, "fing", "No", "Caza de brujas", null);
		});
		
		//obtenerDatosSalida
		
		try {
			DTSalida sal = icd.obtenerDatosSalida("salida de prueba");
			assertEquals(sal.getNombre(), "salida de prueba");
			assertEquals(sal.getMaxTuristas(), 5);
			assertEquals(sal.getFechaDTSalida(), fechaAlta);
			assertEquals(sal.getHora(), 0);
			assertEquals(sal.getLugarDTSalida(), "fing");
			assertEquals(sal.getAlta(), fechaAlta);
		} catch (salidaNoExisteException e) {
			e.printStackTrace();
		}
		
		assertThrows(salidaNoExisteException.class, () -> {
			icd.obtenerDatosSalida("asdasdwiqwdn");
		});
		
		//obtenerNombreActividadDeSalida
		
		try {
			String nomActSal = icd.obtenerNombreActividadDeSalida("salida de prueba");
			assertEquals(nomActSal, "Caza de brujas");
		} catch (salidaNoExisteException e) {
			e.printStackTrace();
		}

		assertThrows(salidaNoExisteException.class, () -> {
			icd.obtenerNombreActividadDeSalida("asdasdwiqwdn");
		});
		
	}
	
	@Test
	void testobtenerlugaresDisponibles() {
		HashSet<String> categorias = new HashSet<String>();
		
		GregorianCalendar fechaAlta = GregorianCalendar.from(ZonedDateTime.now());
		icd.ingresarDepartamento("Maldonado", "Donde encuentras Piriapolis y Punta del Este", "maldonado.com.uy");
		try {
			icd.ingresarDatosActividad("Caza de brujas", "Como en la inquisicion pero en 2022", 2, 1, "Cadiz", fechaAlta , "gardelito", "Maldonado", categorias, null);

			try {
				icd.ingresarDatosSalida( "salida de prueba", 5, fechaAlta, fechaAlta, 0, "fing", "Maldonado", "Caza de brujas", null);
			} catch (actividadNoExisteException e) {
				e.printStackTrace();
			}
		} catch (proveedorNoExisteException | departamentoNoExisteException e) {
			e.printStackTrace();
		}
		assertThrows(salidaNoExisteException.class, () -> {
			icd.obtenerlugaresDisponibles("Salida inexistente");
		});
		try {
			assertEquals(icd.obtenerlugaresDisponibles("salida de prueba"), 5);
		} catch (salidaNoExisteException e) {
			e.printStackTrace();
		}
		
	}

	
		
}
