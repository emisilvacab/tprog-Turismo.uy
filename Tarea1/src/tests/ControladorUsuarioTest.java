package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import logica.datatypes.DTActividad;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTSalida;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;
import excepciones.UsuarioRepetidoException;
import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.proveedorNoExisteException;
import excepciones.salidaNoExisteException;
import excepciones.usuarioNoExisteException;
import logica.Departamento;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;

import java.lang.reflect.Array;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ControladorUsuarioTest {
	static //si agregan algun usuario avisenme joaco
	DTTurista userU1 = new DTTurista("leomel", "Leonardo", "Melgar", "leomel@gmail.com", new GregorianCalendar(2001,6,5), "Uruguaya");
	static DTTurista userU2 = new DTTurista("leomel2", "Leonardo", "Melgar", "leomel@gmail.com", new GregorianCalendar(2001,6,5), "Uruguaya");
	static DTProveedor userU3 = new DTProveedor("joaco", "Leonardo", "Melgar", "leomel@gmail.com", new GregorianCalendar(2001,6,5), "lol", "superlol");
	String[] usuariosCargados = new String[]{"leomel", "leomel2", "joaco", "wason", "pepe"};
	static DTProveedor userP1 = new DTProveedor("wason","Ignacio","Nunez","wason@gmail.com", new GregorianCalendar(2001,3,2),"Proveedor desde 2010");
	static DTTurista userU4 = new DTTurista("pepe", "Leonardo", "Melgar", "leomel@gmail.com", new GregorianCalendar(2001,6,5), "Uruguaya");
	

	
	private static IControladorUsuario icu;
	private static IControladorDepartamento icd;
	
	@BeforeAll
	public static void iniciar() {
		Fabrica fabrica = Fabrica.getInstance();
		icu = fabrica.getIControladorUsuario();
		icd = fabrica.getIControladorDepartamento();
		
		try {
			icu.altaUsuario(userU1);
			icu.altaUsuario(userU3);
			icu.altaUsuario(userU2);
			icu.altaUsuario(userU4);
			} catch (UsuarioRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		icd.ingresarDepartamento("Montevideo","Capital de Uruguay", "mvdeo.com.uy");
		try {
			icu.altaUsuario(userP1);
		}
		catch(UsuarioRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		try {
			icd.ingresarDatosActividad("Paseo por Parque Rodo", "Recorrido", 4, 100, "Parque Rodo", new GregorianCalendar(2012,11,1), "wason", "Montevideo");
		}
		catch(proveedorNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		catch (departamentoNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			icd.ingresarDatosSalida("Juegos", 6, new GregorianCalendar(2015,10,2),new GregorianCalendar(2022,11,8), 15,"Playa Ramirez", "Montevideo", "Paseo por Parque Rodo");
			icd.ingresarDatosSalida("Juegos_vencida",6, new GregorianCalendar(2015,10,2), new GregorianCalendar(2021,8,13), 15, "Playa Ramirez", "Montevideo", "Paseo por Parque Rodo");
		}
		catch(proveedorNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		catch (actividadNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			icu.ingresarDatosInscripcion("pepe", "Juegos", 1, new GregorianCalendar(2015,10,2));
		} catch (salidaNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (usuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		

	}
	
	@Test
	void testAltaUsuario() {
		GregorianCalendar nacimiento = new GregorianCalendar(2001,6,5);
				try {
					DTUsuario userObtenido = icu.obtenerUsuario("leomel");
					DTUsuario userObtenido2 = icu.obtenerUsuario("joaco");
					DTTurista turObtenido = (DTTurista) userObtenido;
					DTProveedor provObtenido = (DTProveedor) userObtenido2;
					assertEquals(turObtenido.getNickname(), "leomel");
					assertEquals(turObtenido.getNombre(), "Leonardo");
					assertEquals(turObtenido.getApellido(), "Melgar");
					assertEquals(turObtenido.getCorreo(), "leomel@gmail.com");
					assertEquals(turObtenido.getNacimiento(), nacimiento);
					assertEquals(turObtenido.getNacionalidad(), "Uruguaya");
					assertEquals(provObtenido.getNickname(), "joaco");
					assertEquals(provObtenido.getNombre(), "Leonardo");
					assertEquals(provObtenido.getApellido(), "Melgar");
					assertEquals(provObtenido.getCorreo(), "leomel@gmail.com");
					assertEquals(provObtenido.getNacimiento(), nacimiento);
					assertEquals(provObtenido.getDescripcion(), "lol");
					assertEquals(provObtenido.getLink(), "superlol");
				} catch (usuarioNoExisteException e) {
					fail(e.getMessage());
					e.printStackTrace();
				}
	}
	
	private boolean contiene(String[] arreglo, String palabra) {
		boolean found = false;
		for (String nombre: arreglo) {
			if (nombre == palabra) {
				found = true;
			}
		}
		return found;
	}
	
	
	@Test
	void testObtenerUsuarios() {
		String[] usuarios = icu.obtenerUsuarios();
		assertEquals(usuarios.length, usuariosCargados.length);
		for (String nickname: usuariosCargados) {
			if (!contiene (usuarios, nickname)) {
				fail("el usuario " + nickname + " no fue cargado");
			}
		}
	}
	
	@Test
	void obtenerSalidasInscripto() {
			try {
				String[] salidas = icu.obtenerSalidasInscripto("pepe");
				assertEquals(salidas.length, 1);
				assertEquals(salidas[0], "Juegos");
				
			} catch (usuarioNoExisteException e) {
				fail(e.getMessage());
				e.printStackTrace();
			}
			
		
	}
	@Test 
	void testObtenerActividadesOfrecidas() {
		try {
			String[] actividades = icu.obtenerActividadesOfrecidas("wason");
			assertEquals(actividades.length, 1);
			assertEquals(actividades[0], "Paseo por Parque Rodo");
			
		} catch (usuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		
	}

	@Test
	void testInscripcionASalida() {
	
		//testeo de obtenerDepartamentos
		Set<String> dptos = icd.obtenerDepartamentos();
		for (String d : dptos) {
			assertEquals(d,"Montevideo");
		}
		
		//testeo de los datos de la actividad registrada
		try {
			HashSet<DTActividad> acts = icd.obtenerDatosActividadesAsociadas("Montevideo");
			for(DTActividad a : acts) {
				assertEquals(a.getNombre(),"Paseo por Parque Rodo");
				assertEquals(a.getDuracion(),4);
				assertEquals(a.getDescripcion(),"Recorrido");
				assertEquals(a.getCosto(),(float) 100, 0);
				assertEquals(a.getCiudad(),"Parque Rodo");
				assertEquals(a.getAlta().get(Calendar.DAY_OF_MONTH),1);
				assertEquals(a.getAlta().get(Calendar.MONTH),11);
				assertEquals(a.getAlta().get(Calendar.YEAR),2012);
			}
		}
		catch (departamentoNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		//testeo de excepciones en obtenerDatosActividadesAsociadas
		//dpto erroneo
			
		assertThrows(departamentoNoExisteException.class , ()->{HashSet<DTActividad> acts = icd.obtenerDatosActividadesAsociadas("Canelones");});
		
		
		//testeo de si se devuelve unicamente la salida vigente
		try {
			HashSet<DTSalida> salidasVigentes = icd.obtenerDatosSalidasVigentes("Paseo por Parque Rodo", "Montevideo");
			for (DTSalida s : salidasVigentes) {
				assertEquals(s.getNombre(),"Juegos");
				assertEquals(s.getMaxTuristas(),6);
				assertEquals(s.getFechaDTSalida().get(Calendar.DAY_OF_MONTH),8);
				assertEquals(s.getFechaDTSalida().get(Calendar.MONTH),11);
				assertEquals(s.getFechaDTSalida().get(Calendar.YEAR),2022);
				assertEquals(s.getLugarDTSalida(),"Playa Ramirez");
			}
		}
		catch (departamentoNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		catch (actividadNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	
		
		//testeo de si se devuelven excepciones en obtenerDatosSalidasVigentes
		//actividad erronea
		assertThrows(actividadNoExisteException.class, ()->{HashSet<DTSalida> salidasVigentes = icd.obtenerDatosSalidasVigentes("Bus turistico", "Montevideo");});
		
		//departamento erroneo
		assertThrows(departamentoNoExisteException.class, ()->{HashSet<DTSalida> salidasVigentes = icd.obtenerDatosSalidasVigentes("Paseo por Parque Rodo", "Canelones");});
		
		
		//TESTEO DE ingresarDatosInscripcion
		
		//testeo de inscripcion correcta
		try {
			String problema = icu.ingresarDatosInscripcion("leomel", "Juegos", 1, new GregorianCalendar(2022,8,29));
			assertEquals(problema,"no");
		}
		catch (salidaNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		catch (usuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		//testeo del problema que el usuario ya esté inscripto
		try {
			String problema = icu.ingresarDatosInscripcion("leomel", "Juegos", 1, new GregorianCalendar(2022,8,29));
			assertEquals(problema,"existe");
		}
		catch (salidaNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		catch (usuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		//testeo del problema de capacidad superada
		try {
			String problema = icu.ingresarDatosInscripcion("leomel2", "Juegos", 10, new GregorianCalendar(2022,8,29));
			assertEquals(problema,"lleno");
		}
		catch (salidaNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		catch (usuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		//testeo excepcion ingreso de usuario que no existe
		assertThrows(usuarioNoExisteException.class, ()->{String problema = icu.ingresarDatosInscripcion("leomel3", "Juegos", 10, new GregorianCalendar(2022,8,29));});
		
		//testeo excepcion ingreso de salida que no existe
		assertThrows(salidaNoExisteException.class, ()->{String problema = icu.ingresarDatosInscripcion("leomel2", "Rio", 10, new GregorianCalendar(2022,8,29));});
	}
	@Test
	void obtenerConsultaUsuario() {
		//test de obtenerSalidasDeActividad
		String[] ans;
		try {
			ans = icu.obtenerSalidasDeActividad("wason", "Paseo por Parque Rodo");
			assertEquals(ans[0], "Juegos");
			assertEquals(ans[1], "Juegos_vencida");
		} catch (usuarioNoExisteException | actividadNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertThrows(usuarioNoExisteException.class, ()->{String[] erroneo = icu.obtenerSalidasDeActividad(null, "Paseo por Parque Rodo");});
		assertThrows(actividadNoExisteException.class, ()->{String[] erroneo = icu.obtenerSalidasDeActividad("wason", "cosopum");});
	
		//test de obtenerSalidasDeActividad
		try {
			DTSalida datosSalida = icu.obtenerDatoSalidaProveedor("wason", "Paseo por Parque Rodo", "Juegos");
			assertEquals(datosSalida.getNombre(), "Juegos");
			assertEquals(datosSalida.getAlta().get(Calendar.DAY_OF_MONTH),2);
			assertEquals(datosSalida.getAlta().get(Calendar.MONTH),10);
			assertEquals(datosSalida.getAlta().get(Calendar.YEAR),2015);
			assertEquals(datosSalida.getFechaDTSalida().get(Calendar.DAY_OF_MONTH),8);
			assertEquals(datosSalida.getFechaDTSalida().get(Calendar.MONTH),11);
			assertEquals(datosSalida.getFechaDTSalida().get(Calendar.YEAR),2022);
			assertEquals(datosSalida.getHora(),15);
			assertEquals(datosSalida.getLugarDTSalida(),"Playa Ramirez");
			//"Juegos", 6, new GregorianCalendar(2015,10,2),new GregorianCalendar(2022,11,8), 15,"Playa Ramirez", "Montevideo", "Paseo por Parque Rodo"
		} catch (usuarioNoExisteException | actividadNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertThrows(usuarioNoExisteException.class, ()->{DTSalida erroneo = icu.obtenerDatoSalidaProveedor("joker", "Paseo por Parque Rodo", "Juegos");});
		assertThrows(usuarioNoExisteException.class, ()->{DTSalida erroneo = icu.obtenerDatoSalidaProveedor("wason", "Paseo por lago ness", "Juegos");});
	}
}