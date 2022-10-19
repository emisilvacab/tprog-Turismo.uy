package tests;

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
import logica.Actividad;
import logica.Departamento;
import logica.Fabrica;
import logica.Proveedor;
import logica.Salida;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

class ControladorUsuarioTest {
	static //si agregan algun usuario avisenme joaco
	DTTurista userU1 = new DTTurista("leomel", "Leonardo", "Melgar", "leomel@gmail.com", new GregorianCalendar(2001,6,5), "contra", "Uruguaya");
	static DTTurista userU2 = new DTTurista("leomel2", "Leonardo", "Melgar", "leomel2@gmail.com", new GregorianCalendar(2001,6,5), "contra", "Uruguaya");
	static DTProveedor userU3 = new DTProveedor("joaco", "Leonardo", "Melgar", "joaco@gmail.com", new GregorianCalendar(2001,6,5), "contra", "lol", "superlol");
	String[] usuariosCargados = new String[]{"leomel", "leomel2", "joaco", "wason", "pepe"};
	static DTProveedor userP1 = new DTProveedor("wason","Ignacio","Nunez","wason@gmail.com", new GregorianCalendar(2001,3,2), "contra", "Proveedor desde 2010");
	static DTTurista userU4 = new DTTurista("pepe", "Leonardo", "Melgar", "pepe@gmail.com", new GregorianCalendar(2001,6,5), "contra", "Uruguaya");
	

	
	private static IControladorUsuario icu;
	private static IControladorDepartamento icd;
	
	@BeforeAll
	public static void iniciar() {
		HashSet<String> categorias = new HashSet<String>();//HAY QUE TESTEAR ESTO (AGREGAR CATEGORIAS A ACTIVIDAD)

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
			icd.ingresarDatosActividad("Paseo por Parque Rodo", "Recorrido", 4, 100, "Parque Rodo", new GregorianCalendar(2012,11,1), "wason", "Montevideo", categorias);
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
			icd.ingresarDatosSalida("Juegos", 6, new GregorianCalendar(2015,10,2),new GregorianCalendar(2022,11,8), 15,"Playa Ramirez", "Montevideo", "Paseo por Parque Rodo", null);
			icd.ingresarDatosSalida("Juegos_vencida",6, new GregorianCalendar(2015,10,2), new GregorianCalendar(2021,8,13), 15, "Playa Ramirez", "Montevideo", "Paseo por Parque Rodo", null);
		}
		catch(departamentoNoExisteException e) {
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
					assertThrows(UsuarioRepetidoException.class, () -> {icu.altaUsuario(new DTTurista("gervasio", "Leonardo", "Melgar", "leomel@gmail.com", new GregorianCalendar(2001,6,5), "contra", "Uruguaya"));});
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
					assertEquals(provObtenido.getCorreo(), "joaco@gmail.com");
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
			HashSet<DTSalida> salidasVigentes = icd.obtenerDatosSalidasVigentesDpto("Paseo por Parque Rodo", "Montevideo");
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
	
		
		//testeo de si se devuelven excepciones en obtenerDatosSalidasVigentesDpto
		//actividad erronea
		assertThrows(actividadNoExisteException.class, ()->{HashSet<DTSalida> salidasVigentes = icd.obtenerDatosSalidasVigentesDpto("Bus turistico", "Montevideo");});
		
		//departamento erroneo
		assertThrows(departamentoNoExisteException.class, ()->{HashSet<DTSalida> salidasVigentes = icd.obtenerDatosSalidasVigentesDpto("Paseo por Parque Rodo", "Canelones");});
		
		
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
	
	@Test
	void obtenerDatoActividadProveedorTest() {
		
		try {
			DTActividad act = icu.obtenerDatoActividadProveedor("wason", "Paseo por Parque Rodo");
			assertEquals(act.getNombre(),"Paseo por Parque Rodo");
			assertEquals(act.getDuracion(),4);
			assertEquals(act.getDescripcion(),"Recorrido");
			assertEquals(act.getCosto(),(float) 100, 0);
			assertEquals(act.getCiudad(),"Parque Rodo");
			assertEquals(act.getAlta().get(Calendar.DAY_OF_MONTH),1);
			assertEquals(act.getAlta().get(Calendar.MONTH),11);
			assertEquals(act.getAlta().get(Calendar.YEAR),2012);
			
		} 
		catch (usuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		catch (actividadNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		assertThrows(usuarioNoExisteException.class, ()->{DTActividad act = icu.obtenerDatoActividadProveedor("Diegu", "Paseo por Parque Rodo");});
		assertThrows(actividadNoExisteException.class, ()->{DTActividad act = icu.obtenerDatoActividadProveedor("wason", "Ir al estadio del Huesca");});
	}
	@Test
	void clasesTest() {
		Proveedor provPrueba = new Proveedor("elProser", "Jose", "Artigas", "artigas@gmail.com", new GregorianCalendar(2001,6,5), "contra", "El de la Batalla de Las Piedras");
		assertEquals(provPrueba.getNickname(), "elProser");
		assertEquals(provPrueba.getNombre(), "Jose");
		assertEquals(provPrueba.getApellido(), "Artigas");
		assertEquals(provPrueba.getCorreo(), "artigas@gmail.com");
		assertEquals(provPrueba.getNacimiento().get(Calendar.DAY_OF_MONTH),5);
		assertEquals(provPrueba.getNacimiento().get(Calendar.MONTH),6);
		assertEquals(provPrueba.getNacimiento().get(Calendar.YEAR),2001);
		assertEquals(provPrueba.getDescripcion(), "El de la Batalla de Las Piedras");
		Departamento pruebaDep = new Departamento("Tacuarembo", "Carpinchos", "capibara.com");
		Actividad pruebaAct = new Actividad("Paseo por Parque Rodo", "Recorrido", 4, 100, "Parque Rodo", new GregorianCalendar(2012,11,1), pruebaDep, provPrueba);
		assertEquals(pruebaAct.getDescripcion(), "Recorrido");
		assertEquals(pruebaAct.getDuracion(), 4);
		assertEquals(pruebaAct.getDepartamento(), pruebaDep);
		assertEquals(pruebaAct.getCiudad(), "Parque Rodo");
		assertEquals(pruebaAct.getAlta().get(Calendar.DAY_OF_MONTH),1);
		assertEquals(pruebaAct.getAlta().get(Calendar.MONTH),11);
		assertEquals(pruebaAct.getAlta().get(Calendar.YEAR),2012);
		assertEquals(pruebaAct.getProveedor(), provPrueba);
		provPrueba.addActividad(pruebaAct);
		assertEquals(provPrueba.getActividades().get("Paseo por Parque Rodo") != null, true);
		pruebaDep.addActividad(pruebaAct);
		assertEquals(pruebaDep.getActividades().get("Paseo por Parque Rodo") != null, true);
		assertEquals(pruebaDep.getUrl(),"capibara.com");
		assertEquals(pruebaDep.getDescripcion(),"Carpinchos");
		
		Salida pruebaSalida = new Salida("Tirarse al lago", 4, new GregorianCalendar(2023,6,5), new GregorianCalendar(2023,6,7), 12, "Puerta del liceo Zorrilla", pruebaAct);
		pruebaAct.addSalida(pruebaSalida);
		assertEquals(pruebaAct.existeSalida(pruebaSalida.getNombre()), true);
		assertEquals(pruebaSalida.getMaxTuristas(), 4);
		assertEquals(pruebaSalida.getAlta().get(Calendar.DAY_OF_MONTH),5);
		assertEquals(pruebaSalida.getAlta().get(Calendar.MONTH),6);
		assertEquals(pruebaSalida.getAlta().get(Calendar.YEAR),2023);
		assertEquals(pruebaSalida.getLugarSalida(), "Puerta del liceo Zorrilla");
		assertEquals(pruebaSalida.getHora(), 12);
		assertEquals(pruebaSalida.getActividad(), pruebaAct);
	}
	
	
	@Test
	void obtenerSalidaInscripto() {
		DTSalida sal = icu.obtenerSalidaInscripto("Juegos" , "pepe");
		assertEquals(sal.getNombre(), "Juegos");
		assertEquals(sal.getHora(), 15);
		assertEquals(sal.getLugarDTSalida(), "Playa Ramirez");
		assertEquals(sal.getMaxTuristas(), 6);
		assertEquals(sal.getFechaDTSalida(), new GregorianCalendar(2022,11,8));
		assertEquals(sal.getAlta(), new GregorianCalendar(2015,10,2));
	}

}