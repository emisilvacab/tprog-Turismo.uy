package Presentacion;

import datatypes.DTProveedor;
import datatypes.DTTurista;
import datatypes.DTUsuario;
import excepciones.UsuarioRepetidoException;
import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.proveedorNoExisteException;
import excepciones.salidaNoExisteException;
import excepciones.usuarioNoExisteException;

import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;

public class CargarDatos {
	private IControladorUsuario icu;
	private IControladorDepartamento icd;
	//Estan todas las fechas con el mes 1 menos porque enero = 0
	public CargarDatos(IControladorUsuario picu, IControladorDepartamento picd) {
		icd = picd;
		icu = picu;
		DTUsuario user;
		user = new DTTurista("lachiqui", "Rosa MarÃ­a", "MartÃ­nez", "mirtha.legrand.ok@hotmail.com.ar", new GregorianCalendar(1927, 1, 23), "argentina");
		try {
			icu.altaUsuario(user);
			
			user = new DTTurista("isabelita", "Elizabeth", "Windsor", "isabelita@thecrown.co.uk", new GregorianCalendar(1926, 3, 21), "inglesa");
			icu.altaUsuario(user);
			
			user = new DTTurista("anibal", "AnÃ­bal", "Lecter", "anibal@fing.edu.uy", new GregorianCalendar(1937, 11, 31), "lituana");
			icu.altaUsuario(user);
			
			user = new DTTurista("waston", "Emma", "Waston", "e.waston@gmail.com", new GregorianCalendar(1990, 3, 15), "inglesa");
			icu.altaUsuario(user);
			
			user = new DTTurista("elelvis", "Elvis", "Lacio", "suavemente@hotmail.com", new GregorianCalendar(1971, 6, 30), "estadounidense");
			icu.altaUsuario(user);
			
			user = new DTTurista("eleven11", "Eleven", "Once", "eleven11@gmail.com", new GregorianCalendar(2004, 1, 19), "espaÃ±ola");
			icu.altaUsuario(user);
			
			user = new DTTurista("bobesponja", "Bob", "Esponja", "bobesponja@nickelodeon.com ", new GregorianCalendar(1999, 4, 01), "japonesa");
			icu.altaUsuario(user);
			
			user = new DTTurista("tony", "Antonio", "Pacheco", "eltony@manya.org.uy", new GregorianCalendar(1976, 3, 11), "uruguaya");
			icu.altaUsuario(user);
			
			user = new DTTurista("chino", "Ã�lvaro", "Recoba", "chino@trico.org.uy", new GregorianCalendar(1976, 2, 17), "uruguaya");
			icu.altaUsuario(user);
			
			user = new DTTurista("mastropiero", "Johann Sebastian", "Mastropiero", "johann.sebastian@gmail.com", new GregorianCalendar(1922, 1, 07), "austrÃ­aca");
			icu.altaUsuario(user);
			
			user = new DTProveedor("washington", "Washington", "Rocha", "washington@turismorocha.gub.uy", new GregorianCalendar(1970, 8, 14), "Hola! me llamo Washington y soy el encargado del portal de turismo del departamento de Rocha - Uruguay" , "http://turismorocha.gub.uy/");
			icu.altaUsuario(user);
			
			user = new DTProveedor("eldiez", "Pablo", "Bengoechea", "eldiez@socfomturriv.org.uy", new GregorianCalendar(1965, 5, 27), "Pablo es el presidente de la Sociedad de Fomento TurÃ­stico de Rivera (conocida como Socfomturriv)", "http://wwww.socfomturriv.org.uy");
			icu.altaUsuario(user);
			
			user = new DTProveedor("meche", "Mercedes", "Venn", "meche@colonia.gub.uy", new GregorianCalendar(1990, 11, 31), "Departamento de Turismo del Departamento de Colonia", "https://colonia.gub.uy/turismo/");
			icu.altaUsuario(user);
		} catch (UsuarioRepetidoException e) {
			JOptionPane.showMessageDialog( null, "Los datos ya fueron cargados previamente", "Cargar datos", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		icd.ingresarDepartamento("Canelones", "DivisiÃ³n Turismo de la Intendencia", "https://www.imcanelones.gub.uy/es");
		icd.ingresarDepartamento("Maldonado", "DivisiÃ³n Turismo de la Intendencia", " https://www.maldonado.gub.uy/");
		icd.ingresarDepartamento("Rocha", "La OrganizaciÃ³n de GestiÃ³n del Destino (OGD) Rocha es un Ã¡mbito de articulaciÃ³n pÃºblico â€“ privada en el sector turÃ­stico que integran la CorporaciÃ³n Rochense de Turismo y la Intendencia de Rocha a travÃ©s de su DirecciÃ³n de Turismo.", "www.turismorocha.gub.uy");
		icd.ingresarDepartamento("Treinta y Tres", "DivisiÃ³n Turismo de la Intendencia", "https://treintaytres.gub.uy/");
		icd.ingresarDepartamento("Cerro Largo", "DivisiÃ³n Turismo de la Intendencia", "https://www.gub.uy/intendenciacerro-largo/");
		icd.ingresarDepartamento("Rivera", "Promociona e implementa proyectos e iniciativas sostenibles de interÂ´es turÃ­stico con la participaciÃ³n institucional pÃ³blica â€“ privada en bien del desarrollo socioeconÃ³mico de la comunidad.", "www.rivera.gub.uy/social/turismo/");
		icd.ingresarDepartamento("Artigas", "DivisiÃ³n Turismo de la Intendencia", "http://www.artigas.gub.uy");
		icd.ingresarDepartamento("Salto", "DivisiÃ³n Turismo de la Intendencia", "https://www.salto.gub.uy");
		icd.ingresarDepartamento("PaysandÃº", "DivisiÃ³n Turismo de la Intendencia", "https://www.paysandu.gub.uy");
		icd.ingresarDepartamento("RÃ­o Negro", "DivisiÃ³n Turismo de la Intendencia", "https://www.rionegro.gub.uy");
		icd.ingresarDepartamento("Soriano", "DivisiÃ³n Turismo de la Intendencia", "https://www.soriano.gub.uy");
		icd.ingresarDepartamento("Colonia", "La propuesta del Departamento de Colonia divide en cuatro actos su espectÃ¡culo anual. Cada acto tiene su magia. Desde su naturaleza y playas hasta sus tradiciones y el patrimonio	mundial. Todo el aÃ±o se disfruta.", "https://colonia.gub.uy/turismo/");
		icd.ingresarDepartamento("San JosÃ©", "DivisiÃ³n Turismo de la Intendencia", "https://sanjose.gub.uy");
		icd.ingresarDepartamento("Flores", "DivisiÃ³n Turismo de la Intendencia", "https://flores.gub.uy");
		icd.ingresarDepartamento("Florida", "DivisiÃ³n Turismo de la Intendencia", "http://www.florida.gub.uy");
		icd.ingresarDepartamento("Lavalleja", "DivisiÃ³n Turismo de la Intendencia", "http://www.lavalleja.gub.uy");
		icd.ingresarDepartamento("Durazno", "DivisiÃ³n Turismo de la Intendencia", "https://durazno.uy");
		icd.ingresarDepartamento("TacuarembÃ³", "DivisiÃ³n Turismo de la Intendencia", "https://tacuarembo.gub.uy");
		icd.ingresarDepartamento("Montevideo", "DivisiÃ³n Turismo de la Intendencia", "https://montevideo.gub.uy/areastematicas/turismo");
		
		try { 
			icd.ingresarDatosActividad("Degusta",  "Festival gastronÃ³mico de productos locales en Rocha", 3, 800, "Rocha", new GregorianCalendar(2022, 6, 20), "washington", "Rocha");
			icd.ingresarDatosActividad("Teatro con Sabores",  "En el mes aniversario del Club Deportivo UniÃ³n de Rocha te invitamos a una merienda deliciosa.", 3, 500, "Rocha", new GregorianCalendar(2022, 6, 21), "washington", "Rocha");

			icd.ingresarDatosActividad("Tour por Colonia del Sacramento",  "Con guÃ­a especializado y en varios idiomas. Varios circuitos posibles.", 2, 400, "Colonia del Sacramento", new GregorianCalendar(2022, 7, 1), "meche", "Colonia");
			icd.ingresarDatosActividad("Almuerzo en el Real de San Carlos",  "Restaurante en la renovada Plaza de Toros con menÃº internacional", 2, 800, "Colonia del Sacramento", new GregorianCalendar(2022, 7, 1), "meche", "Colonia");
			
			icd.ingresarDatosActividad("Almuerzo en Valle del Lunarejo",  "Almuerzo en la Posada con ticket fijo. MenÃº que incluye bebida y postre casero.", 2, 300, "Tranqueras", new GregorianCalendar(2022, 7, 1), "eldiez", "Rivera");
			icd.ingresarDatosActividad("Cabalgata en Valle del Lunarejo",  "Cabalgata por el Ã¡rea protegida. Varios recorridos para elegir.", 2, 150, "Tranqueras", new GregorianCalendar(2022, 7, 1), "eldiez", "Rivera");
		}
		catch(departamentoNoExisteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "El departamento seleccionado no estÃƒÂ¡ registrado en el sistema", JOptionPane.ERROR_MESSAGE);
		}
		catch(proveedorNoExisteException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "El proveedor ingresado no estÃƒÂ¡ registrado en el sistema", JOptionPane.ERROR_MESSAGE);
		}

		try {
			icd.ingresarDatosSalida("Degusta Agosto", 20, new GregorianCalendar(2022, 6, 21), new GregorianCalendar(2022, 7, 20), 17, "Sociedad Agropecuaria de Rocha", "Rocha", "Degusta");
			icd.ingresarDatosSalida("Degusta Setiembre", 20, new GregorianCalendar(2022, 6, 22), new GregorianCalendar(2022, 8, 3), 17, "Sociedad Agropecuaria de Rocha", "Rocha", "Degusta");
			
			icd.ingresarDatosSalida("Teatro con Sabores 1", 30, new GregorianCalendar(2022, 6, 23), new GregorianCalendar(2022, 8, 4), 18, "Club Deportivo UniÃ³n", "Rocha", "Teatro con Sabores");
			icd.ingresarDatosSalida("Teatro con Sabores 2", 30, new GregorianCalendar(2022, 6, 23), new GregorianCalendar(2022, 8, 11), 18, "Club Deportivo UniÃ³n", "Rocha", "Teatro con Sabores");
			
			icd.ingresarDatosSalida("Tour Colonia del Sacramento 11-09", 5, new GregorianCalendar(2022, 7, 5), new GregorianCalendar(2022, 8, 11), 10, "Encuentro en la base del Faro", "Colonia", "Tour por Colonia del Sacramento");
			icd.ingresarDatosSalida("Tour Colonia del Sacramento 18-09", 5, new GregorianCalendar(2022, 7, 5), new GregorianCalendar(2022, 8, 18), 10, "Encuentro en la base del Faro", "Colonia", "Tour por Colonia del Sacramento");
			
			icd.ingresarDatosSalida("Almuerzo 1", 5, new GregorianCalendar(2022, 7, 4), new GregorianCalendar(2022, 8, 18), 12, "Restaurante de la Plaza de Toros", "Colonia", "Almuerzo en el Real de San Carlos");
			icd.ingresarDatosSalida("Almuerzo 2", 5, new GregorianCalendar(2022, 7, 4), new GregorianCalendar(2022, 8, 25), 12, "Restaurante de la Plaza de Toros", "Colonia", "Almuerzo en el Real de San Carlos");
			
			icd.ingresarDatosSalida("Almuerzo 3", 4, new GregorianCalendar(2022, 7, 15), new GregorianCalendar(2022, 8, 10), 12, "Posada del Lunarejo", "Rivera", "Almuerzo en Valle del Lunarejo");
			icd.ingresarDatosSalida("Almuerzo 4", 4, new GregorianCalendar(2022, 7, 15), new GregorianCalendar(2022, 8, 11), 12, "Posada del Lunarejo", "Rivera", "Almuerzo en Valle del Lunarejo");
			
			icd.ingresarDatosSalida("Cabalgata 1", 4, new GregorianCalendar(2022, 7, 15), new GregorianCalendar(2022, 8, 10), 16, "Posada del Lunarejo", "Rivera", "Cabalgata en Valle del Lunarejo");
			icd.ingresarDatosSalida("Cabalgata 2", 4, new GregorianCalendar(2022, 7, 15), new GregorianCalendar(2022, 8, 11), 16, "Posada del Lunarejo", "Rivera", "Cabalgata en Valle del Lunarejo");

		}
		catch(actividadNoExisteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "La actividad seleccionada no estÃƒÂ¡ registrada en el sistema", JOptionPane.ERROR_MESSAGE);
		}
		catch(proveedorNoExisteException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "El proveedor seleccionado no estÃƒÂ¡ registrado en el sistema", JOptionPane.ERROR_MESSAGE);
		}	
		
		
		try {
			icu.ingresarDatosInscripcion("lachiqui","Degusta Agosto", 3, new GregorianCalendar(2022, 7, 15)); //costo 2400 15/8/2022
			icu.ingresarDatosInscripcion("elelvis","Degusta Agosto", 5, new GregorianCalendar(2022, 7, 16)); // costo 4000 16/8/2022
			icu.ingresarDatosInscripcion("lachiqui","Almuerzo 2", 3, new GregorianCalendar(2022, 7, 18)); //costo 1200 18/8/2022
			icu.ingresarDatosInscripcion("isabelita","Almuerzo 2", 1, new GregorianCalendar(2022, 7, 19));// costo 400 19/8/2022
			icu.ingresarDatosInscripcion("mastropiero","Almuerzo 4", 2, new GregorianCalendar(2022, 7, 19));// costo 1600 19/8/2022
			icu.ingresarDatosInscripcion("chino","Teatro con Sabores 1", 1, new GregorianCalendar(2022, 7, 19)); //costo 500 19/8/2022
			icu.ingresarDatosInscripcion("chino","Teatro con Sabores 2", 10, new GregorianCalendar(2022, 7, 20)); //costo 5000 20/8/2022
			icu.ingresarDatosInscripcion("bobesponja","Teatro con Sabores 2", 2, new GregorianCalendar(2022, 7, 20));// costo 1000 20/8/2022
			icu.ingresarDatosInscripcion("anibal","Teatro con Sabores 2", 1, new GregorianCalendar(2022, 7, 21));// costo 500 21/8/2022
			icu.ingresarDatosInscripcion("tony","Degusta Setiembre", 11, new GregorianCalendar(2022, 7, 21)); //costo 8800 21/8/2022
			
		}
		catch(salidaNoExisteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "La salida seleccionada no estÃƒÂ¡ registrada en el sistema", JOptionPane.ERROR_MESSAGE);
		}
		catch(usuarioNoExisteException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "El usuario ingresado no estÃƒÂ¡ registrado en el sistema", JOptionPane.ERROR_MESSAGE);
		}	
		
	}
	
	
}