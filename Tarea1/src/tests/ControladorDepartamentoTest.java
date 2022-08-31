package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.UsuarioRepetidoException;
import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.proveedorNoExisteException;
import logica.Actividad;
import logica.Departamento;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTActividad;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTSalida;
import logica.datatypes.DTTurista;
import logica.manejadores.ManejadorDepartamento;

class ControladorDepartamentoTest {
	
	private static IControladorUsuario icu;
	private static IControladorDepartamento icd;
	private static DTTurista userTurista1;
	private static DTTurista userTurista2;
	private static DTTurista userTurista3;
	private static DTTurista userTurista4;
	private static DTTurista userTurista5;
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
		userTurista1 = new DTTurista("gardelito", "Carlos", "Gardel", "porunacabeza@gmail.com", new GregorianCalendar(2001,6,5), "Desconocida");
		userTurista2 = new DTTurista("artaud123", "Luis", "Spinetta", "muchacha@gmail.com", new GregorianCalendar(2001,6,5), "Argentino");
		userTurista3 = new DTTurista("leomel", "Leonardo", "Melgar", "leomel@gmail.com", new GregorianCalendar(2001,6,5), "Uruguaya");
		userTurista4 = new DTTurista("leomel", "Leonardo", "Melgar", "leomel@gmail.com", new GregorianCalendar(2001,6,5), "Uruguaya");
		userTurista5 = new DTTurista("leomel", "Leonardo", "Melgar", "leomel@gmail.com", new GregorianCalendar(2001,6,5), "Uruguaya");
		userProveedor1 = new DTProveedor("artaud123", "Luis", "Spinetta", "muchacha@gmail.com", new GregorianCalendar(2001,6,5), "Argentino");
		userProveedor2 = new DTProveedor("elPadrino", "Marlon", "Brando", "marBra@gmail.com", new GregorianCalendar(2001,6,5), "Italiana");
		userProveedor3 = new DTProveedor("balonDeOro", "Lionel", "Messi", "lio@gmail.com", new GregorianCalendar(2001,6,5), "Uruguaya");
		userProveedor4 = new DTProveedor("bocanada1999", "Gustavo", "Cerati", "dietetico@gmail.com", new GregorianCalendar(2001,6,5), "Uruguaya");
		userProveedor5 = new DTProveedor("gardelito", "Carlos", "Gardel", "porunacabeza@gmail.com", new GregorianCalendar(2001,6,5), "Desconocida");
	}
	
	@Test
	void testAltaActividad() {
		icd.ingresarDepartamento("Montevideo","Capital de Uruguay", "mvdeo.com.uy");
		icd.ingresarDepartamento("Maldonado","Donde encuentras Piriapolis y Punta del Este", "maldonado.com.uy");
		icd.ingresarDepartamento("Canelones","Segundo departamento mas poblado", "can.com.uy");
		icd.ingresarDepartamento("Artigas","En honor al proser", "artigas.com.uy");
		icd.ingresarDepartamento("Rocha","Donde esta la Pedrera", "rocha.com.uy");
		
		String departamentos[] = {"Montevideo","Maldonado","Canelones", "Artigas", "Rocha"};
		
		//test de obtenerDepartamentos()
		Set<String> dptos = icd.obtenerDepartamentos();
		for (String d : departamentos) {
			assertEquals(dptos.contains(d),true);
		}
		
		assertEquals(dptos.size(),5);
		
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
			ingresado = icd.ingresarDatosActividad("Caza de brujas", "Como en la inquisicion pero en 2022", 2, 1, "Cadiz",fechaAlta , "gardelito", "Maldonado");
			assertEquals(ingresado, false);
			
			ingresado = icd.ingresarDatosActividad("Caza de brujas", "Como en la inquisicion pero en 2022", 2, 1, "Cadiz", fechaAlta, "gardelito", "Maldonado");
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
		
}
