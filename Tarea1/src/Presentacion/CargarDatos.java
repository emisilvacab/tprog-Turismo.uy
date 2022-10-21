package Presentacion;

import excepciones.UsuarioRepetidoException;
import excepciones.actividadNoExisteException;
import excepciones.categoriaYaExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.paqueteNoExisteException;
import excepciones.paqueteYaExisteException;
import excepciones.proveedorNoExisteException;
import excepciones.salidaNoExisteException;
import excepciones.usuarioNoExisteException;

import java.util.GregorianCalendar;
import java.util.HashSet;

import javax.swing.JOptionPane;

import logica.Estado;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorPaquete;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;

public class CargarDatos {
	private IControladorUsuario icu;
	private IControladorDepartamento icd;
	private IControladorPaquete icp;
	
	//Estan todas las fechas con el mes 1 menos porque enero = 0
	public CargarDatos(IControladorUsuario picu, IControladorDepartamento picd, IControladorPaquete picp) {
		icd = picd;
		icu = picu;
		icp = picp;
		DTUsuario user;
		user = new DTTurista("lachiqui", "Rosa María", "Martínez", "mirtha.legrand.ok@hotmail.com.ar", new GregorianCalendar(1927, 1, 23), "awdrg543", "argentina");
		try {
			icu.altaUsuario(user);
			
			user = new DTTurista("isabelita", "Elizabeth", "Windsor", "isabelita@thecrown.co.uk", new GregorianCalendar(1926, 3, 21),"r5t6y7u8", "inglesa");
			icu.altaUsuario(user);
			
			user = new DTTurista("anibal", "Aníbal", "Lecter", "anibal@fing.edu.uy", new GregorianCalendar(1937, 11, 31),"edrft543", "lituana");
			icu.altaUsuario(user);
			
			user = new DTTurista("waston", "Emma", "Waston", "e.waston@gmail.com", new GregorianCalendar(1990, 3, 15), "poiuy987", "inglesa");
			icu.altaUsuario(user);
			
			user = new DTTurista("elelvis", "Elvis", "Lacio", "suavemente@hotmail.com", new GregorianCalendar(1971, 6, 30), "45idgaf67", "estadounidense");
			icu.altaUsuario(user);
			
			user = new DTTurista("eleven11", "Eleven", "Once", "eleven11@gmail.com", new GregorianCalendar(2004, 1, 19), "xdrgb657", "española");
			icu.altaUsuario(user);
			
			user = new DTTurista("bobesponja", "Bob", "Esponja", "bobesponja@nickelodeon.com ", new GregorianCalendar(1999, 4, 01), "sbsplol1", "japonesa");
			icu.altaUsuario(user);
			
			user = new DTTurista("tony", "Antonio", "Pacheco", "eltony@manya.org.uy", new GregorianCalendar(1976, 3, 11), "okmnji98", "uruguaya");
			icu.altaUsuario(user);
			
			user = new DTTurista("chino", "Álvaro", "Recoba", "chino@trico.org.uy", new GregorianCalendar(1976, 2, 17), "qsxcdw43", "uruguaya");
			icu.altaUsuario(user);
			
			user = new DTTurista("mastropiero", "Johann Sebastian", "Mastropiero", "johann.sebastian@gmail.com", new GregorianCalendar(1922, 1, 07), "qpwoei586", "austríaca");
			icu.altaUsuario(user);
			
			user = new DTProveedor("washington", "Washington", "Rocha", "washington@turismorocha.gub.uy", new GregorianCalendar(1970, 8, 14), "asdfg654", "Hola! me llamo Washington y soy el encargado del portal de turismo del departamento de Rocha - Uruguay", "http://turismorocha.gub.uy/");
			icu.altaUsuario(user);
			
			user = new DTProveedor("eldiez", "Pablo", "Bengoechea", "eldiez@socfomturriv.org.uy", new GregorianCalendar(1965, 5, 27), "ytrewq10", "Pablo es el presidente de la Sociedad de Fomento Turístico de Rivera (conocida como Socfomturriv)", "http://wwww.socfomturriv.org.uy");
			icu.altaUsuario(user);
			
			user = new DTProveedor("meche", "Mercedes", "Venn", "meche@colonia.gub.uy", new GregorianCalendar(1990, 11, 31), "mnjkiu89", "Departamento de Turismo del Departamento de Colonia", "https://colonia.gub.uy/turismo/");
			icu.altaUsuario(user);
			
			
//			U1 awdrg543 tinyurl.com/2e3s66tw
//			U2 r5t6y7u8 tinyurl.com/ycy8mbrn
//			U3 edrft543 tinyurl.com/y2u3tybh
//			U4 poiuy987 tinyurl.com/2p9ed8et
//			U5 45idgaf67 tinyurl.com/mtwppxxz
//			U6 xdrgb657 tinyurl.com/3ztpasya
//			U7 sbsplol1 tinyurl.com/43zymcch
//			U8 okmnji98 tinyurl.com/mr3a38w4
//			U9 qsxcdw43 tinyurl.com/2b556k7t
//			U10 qpwoei586 tinyurl.com/3mbeyawm
//			U11 asdfg654 tinyurl.com/3whe8372
//			U12 ytrewq10 tinyurl.com/mu4jeas3
//			U13 mnjkiu89 tinyurl.com/4hs4v9c5

			
		} catch (UsuarioRepetidoException e) {
			JOptionPane.showMessageDialog( null, "Los datos ya fueron cargados previamente", "Cargar datos", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		icd.ingresarDepartamento("Canelones", "División Turismo de la Intendencia", "https://www.imcanelones.gub.uy/es");
		icd.ingresarDepartamento("Maldonado", "División Turismo de la Intendencia", " https://www.maldonado.gub.uy/");
		icd.ingresarDepartamento("Rocha", "La Organización de Gestión del Destino (OGD) Rocha es un Ámbito de articulación público - privada en el sector turístico que integran la Corporación Rochense de Turismo y la Intendencia de Rocha a través de su Dirección de Turismo.", "www.turismorocha.gub.uy");
		icd.ingresarDepartamento("Treinta y Tres", "División Turismo de la Intendencia", "https://treintaytres.gub.uy/");
		icd.ingresarDepartamento("Cerro Largo", "División Turismo de la Intendencia", "https://www.gub.uy/intendenciacerro-largo/");
		icd.ingresarDepartamento("Rivera", "Promociona e implementa proyectos e iniciativas sostenibles de interés turístico con la participación institucional pública - privada en bien del desarrollo socioeconómico de la comunidad.", "www.rivera.gub.uy/social/turismo/");
		icd.ingresarDepartamento("Artigas", "División Turismo de la Intendencia", "http://www.artigas.gub.uy");
		icd.ingresarDepartamento("Salto", "División Turismo de la Intendencia", "https://www.salto.gub.uy");
		icd.ingresarDepartamento("Paysandú", "División Turismo de la Intendencia", "https://www.paysandu.gub.uy");
		icd.ingresarDepartamento("Río Negro", "División Turismo de la Intendencia", "https://www.rionegro.gub.uy");
		icd.ingresarDepartamento("Soriano", "División Turismo de la Intendencia", "https://www.soriano.gub.uy");
		icd.ingresarDepartamento("Colonia", "La propuesta del Departamento de Colonia divide en cuatro actos su espectáculo anual. Cada acto tiene su magia. Desde su naturaleza y playas hasta sus tradiciones y el patrimonio	mundial. Todo el año se disfruta.", "https://colonia.gub.uy/turismo/");
		icd.ingresarDepartamento("San José", "División Turismo de la Intendencia", "https://sanjose.gub.uy");
		icd.ingresarDepartamento("Flores", "División Turismo de la Intendencia", "https://flores.gub.uy");
		icd.ingresarDepartamento("Florida", "División Turismo de la Intendencia", "http://www.florida.gub.uy");
		icd.ingresarDepartamento("Lavalleja", "División Turismo de la Intendencia", "http://www.lavalleja.gub.uy");
		icd.ingresarDepartamento("Durazno", "División Turismo de la Intendencia", "https://durazno.uy");
		icd.ingresarDepartamento("Tacuarembó", "División Turismo de la Intendencia", "https://tacuarembo.gub.uy");
		icd.ingresarDepartamento("Montevideo", "División Turismo de la Intendencia", "https://montevideo.gub.uy/areastematicas/turismo");
		
		try {
			
			icd.ingresarDatosCategoria("Aventura y Deporte");
			icd.ingresarDatosCategoria("Campo y Naturaleza");
			icd.ingresarDatosCategoria("Cultura y Patrimonio");
			icd.ingresarDatosCategoria("Gastronomia");
			icd.ingresarDatosCategoria("Turismo Playas");
			
		} catch (categoriaYaExisteException e4) {
			JOptionPane.showMessageDialog(null, e4.getMessage(),"La categoría ingresada ya está en el sistema", JOptionPane.ERROR_MESSAGE);
		}
		
		try { 
			HashSet<String> categorias = new HashSet<String>();
			Estado estado = Estado.CONFIRMADA;
			
			categorias.add("Gastronomia");
			icd.ingresarDatosActividad("Degusta",  "Festival gastronómico de productos locales en Rocha", 3, 800, "Rocha", new GregorianCalendar(2022, 6, 20), "washington", "Rocha", categorias);
			icd.modificarEstadoActividad("Degusta", estado);
					
			categorias.add("Cultura y Patrimonio");
			icd.ingresarDatosActividad("Teatro con Sabores",  "En el mes aniversario del Club Deportivo Unión de Rocha te invitamos a una merienda deliciosa.", 3, 500, "Rocha", new GregorianCalendar(2022, 6, 21), "washington", "Rocha", categorias);
			icd.modificarEstadoActividad("Teatro con Sabores", estado);
			
			categorias.remove("Gastronomia");
			icd.ingresarDatosActividad("Tour por Colonia del Sacramento",  "Con guía especializado y en varios idiomas. Varios circuitos posibles.", 2, 400, "Colonia del Sacramento", new GregorianCalendar(2022, 7, 1), "meche", "Colonia", categorias);
			icd.modificarEstadoActividad("Tour por Colonia del Sacramento", estado);
			
			categorias.add("Gastronomia");
			categorias.remove("Cultura y Patrimonio");
			icd.ingresarDatosActividad("Almuerzo en el Real de San Carlos",  "Restaurante en la renovada Plaza de Toros con menú internacional.", 2, 800, "Colonia del Sacramento", new GregorianCalendar(2022, 7, 1), "meche", "Colonia", categorias);
			icd.modificarEstadoActividad("Almuerzo en el Real de San Carlos", estado);
			
			categorias.add("Campo y Naturaleza");
			icd.ingresarDatosActividad("Almuerzo en Valle del Lunarejo",  "Almuerzo en la Posada con ticket fijo. Menú que incluye bebida y postre casero.", 2, 300, "Tranqueras", new GregorianCalendar(2022, 7, 1), "eldiez", "Rivera", categorias);
			icd.modificarEstadoActividad("Almuerzo en Valle del Lunarejo", estado);
			
			categorias.remove("Gastronomia");
			icd.ingresarDatosActividad("Cabalgata en Valle del Lunarejo",  "Cabalgata por el Área protegida. Varios recorridos para elegir.", 2, 150, "Tranqueras", new GregorianCalendar(2022, 7, 1), "eldiez", "Rivera", categorias);
			icd.modificarEstadoActividad("Cabalgata en Valle del Lunarejo", estado);

			categorias.add("Cultura y Patrimonio");
			categorias.remove("Campo y Naturaleza");
			icd.ingresarDatosActividad("Bus turístico Colonia",  "Recorrida por los principales atractivos de la ciudad", 3, 600, "Colonia del Sacramento", new GregorianCalendar(2022, 8, 1), "meche", "Colonia", categorias);
			estado = Estado.AGREGADA;
			icd.modificarEstadoActividad("Teatro con Sabores", estado);
			
			icd.ingresarDatosActividad("Colonia Premium Tour",  "Visita lugares exclusivos y relevantes", 4, 2600, "Colonia del Sacramento", new GregorianCalendar(2022, 8, 3), "meche", "Colonia", categorias);
			estado = Estado.RECHAZADA;
			icd.modificarEstadoActividad("Teatro con Sabores", estado);

			categorias.add("Aventura y Deporte");
			categorias.add("Turismo Playas");
			categorias.remove("Cultura y Patrimonio");
			icd.ingresarDatosActividad("Deportes náuticos sin uso de motor",  "kitsurf - windsurf - kayakismo - canotaje en Rocha", 3, 1200, "Rocha", new GregorianCalendar(2022, 8, 5), "washington", "Rocha", categorias);
			estado = Estado.AGREGADA;
			icd.modificarEstadoActividad("Teatro con Sabores", estado);

			categorias.remove("Turismo Playas");
			icd.ingresarDatosActividad("Descubre Rivera",  "Rivera es un departamento de extraordinaria riqueza natural patrimonial y cultural con una ubicación geográfica privilegiada", 2, 650, "Rivera", new GregorianCalendar(2022, 8, 16), "eldiez", "Rivera", categorias);
			estado = Estado.RECHAZADA;
			icd.modificarEstadoActividad("Teatro con Sabores", estado);

//			A1 tinyurl.com/bdehz9bb
//			A2 tinyurl.com/58fnr5j7
//			A3 tinyurl.com/3rp2vvjf
//			A4 tinyurl.com/2yeu66vb
//			A5 tinyurl.com/4yrs8y2c
//			A6 tinyurl.com/2vjd382t
			
//			A7 tinyurl.com/bdzyrm93
//			A8 tinyurl.com/284kr973
//			A9 tinyurl.com/yck2a92h
//			A10 tinyurl.com/y4vbc4xc
		}
		catch(departamentoNoExisteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "El departamento seleccionado no está registrado en el sistema", JOptionPane.ERROR_MESSAGE);
		}
		catch(proveedorNoExisteException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "El proveedor ingresado no está registrado en el sistema", JOptionPane.ERROR_MESSAGE);
		}

		try {
			icd.ingresarDatosSalida("Degusta Agosto", 20, new GregorianCalendar(2022, 6, 21), new GregorianCalendar(2022, 7, 20), 1700, "Sociedad Agropecuaria de Rocha", "Rocha", "Degusta", null);
			icd.ingresarDatosSalida("Degusta Setiembre", 20, new GregorianCalendar(2022, 6, 22), new GregorianCalendar(2022, 8, 3), 1700, "Sociedad Agropecuaria de Rocha", "Rocha", "Degusta", null);
			
			icd.ingresarDatosSalida("Teatro con Sabores 1", 30, new GregorianCalendar(2022, 6, 23), new GregorianCalendar(2022, 8, 4), 1800, "Club Deportivo Unión", "Rocha", "Teatro con Sabores", null);
			icd.ingresarDatosSalida("Teatro con Sabores 2", 30, new GregorianCalendar(2022, 6, 23), new GregorianCalendar(2022, 8, 11), 1800, "Club Deportivo Unión", "Rocha", "Teatro con Sabores", null);
			
			icd.ingresarDatosSalida("Tour Colonia del Sacramento 11-09", 5, new GregorianCalendar(2022, 7, 5), new GregorianCalendar(2022, 8, 11), 1000, "Encuentro en la base del Faro", "Colonia", "Tour por Colonia del Sacramento", null);
			icd.ingresarDatosSalida("Tour Colonia del Sacramento 18-09", 5, new GregorianCalendar(2022, 7, 5), new GregorianCalendar(2022, 8, 18), 1000, "Encuentro en la base del Faro", "Colonia", "Tour por Colonia del Sacramento", null);
			
			icd.ingresarDatosSalida("Almuerzo 1", 5, new GregorianCalendar(2022, 7, 4), new GregorianCalendar(2022, 8, 18), 1200, "Restaurante de la Plaza de Toros", "Colonia", "Almuerzo en el Real de San Carlos", null);
			icd.ingresarDatosSalida("Almuerzo 2", 5, new GregorianCalendar(2022, 7, 4), new GregorianCalendar(2022, 8, 25), 1200, "Restaurante de la Plaza de Toros", "Colonia", "Almuerzo en el Real de San Carlos", null);
			
			icd.ingresarDatosSalida("Almuerzo 3", 4, new GregorianCalendar(2022, 7, 15), new GregorianCalendar(2022, 8, 10), 1200, "Posada del Lunarejo", "Rivera", "Almuerzo en Valle del Lunarejo", null);
			icd.ingresarDatosSalida("Almuerzo 4", 4, new GregorianCalendar(2022, 7, 15), new GregorianCalendar(2022, 8, 11), 1200, "Posada del Lunarejo", "Rivera", "Almuerzo en Valle del Lunarejo", null);
			
			icd.ingresarDatosSalida("Cabalgata 1", 4, new GregorianCalendar(2022, 7, 15), new GregorianCalendar(2022, 8, 10), 1600, "Posada del Lunarejo", "Rivera", "Cabalgata en Valle del Lunarejo", null);
			icd.ingresarDatosSalida("Cabalgata 2", 4, new GregorianCalendar(2022, 7, 15), new GregorianCalendar(2022, 8, 11), 1600, "Posada del Lunarejo", "Rivera", "Cabalgata en Valle del Lunarejo", null);
			
			icd.ingresarDatosSalida("Degusta Octubre", 20, new GregorianCalendar(2022, 8, 22), new GregorianCalendar(2022, 9, 30), 1700, "Sociedad Agropecuaria de Rocha", "Rocha", "Degusta", null);
			icd.ingresarDatosSalida("Degusta Noviembre", 20, new GregorianCalendar(2022, 1, 10), new GregorianCalendar(2022, 10, 5), 1700, "Sociedad Agropecuaria de Rocha", "Rocha", "Degusta", null);
		
			icd.ingresarDatosSalida("Teatro con Sabores 3", 30, new GregorianCalendar(2022, 7, 25), new GregorianCalendar(2022, 10, 11), 1800, "Club Deportivo Unión", "Rocha", "Teatro con Sabores", null);
			icd.ingresarDatosSalida("Tour Colonia del Sacramento 30-10", 10, new GregorianCalendar(2022, 8, 7), new GregorianCalendar(2022, 9, 30), 1000, "Encuentro en la base del Faro", "Colonia", "Tour por Colonia del Sacramento", null);

			icd.ingresarDatosSalida("Cabalgata Extrema", 4, new GregorianCalendar(2022, 8, 15), new GregorianCalendar(2022, 9, 30), 1600, "Posada del Lunarejo", "Rivera", "Cabalgata en Valle del Lunarejo", null);
			icd.ingresarDatosSalida("Almuerzo en el Real 1", 10, new GregorianCalendar(2022, 9, 10), new GregorianCalendar(2022, 9, 30), 1200, "Restaurante de la Plaza de Toros", "Colonia", "Almuerzo en el Real de San Carlos", null);

//			S1 tinyurl.com/4jwed4jx
//			S2 tinyurl.com/2maxmx6c
//			S3 tinyurl.com/2zturssk
//			S4 tinyurl.com/5d5vm953
//			S5 tinyurl.com/5n7ud8e7
//			S6 tinyurl.com/583b3mbs
//			S7
//			S8
//			S9
//			S10
//			S11 tinyurl.com/2p9he77w
//			S12
//			S13 tinyurl.com/mryhyr5f
//			S14 tinyurl.com/yzz6b7et
//			S15
//			S16 tinyurl.com/mv7etjx2
//			S17 tinyurl.com/3vwzthcr
//			S18

		}
		catch(actividadNoExisteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "La actividad seleccionada no está registrada en el sistema", JOptionPane.ERROR_MESSAGE);
		}
		catch(departamentoNoExisteException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "El proveedor seleccionado no está registrado en el sistema", JOptionPane.ERROR_MESSAGE);
		}	
		
		
		
		
		try {
			icu.ingresarDatosInscripcion("lachiqui","Degusta Agosto", 3, new GregorianCalendar(2022, 7, 15)); //costo 2400
			icu.ingresarDatosInscripcion("elelvis","Degusta Agosto", 5, new GregorianCalendar(2022, 7, 16)); // costo 4000
			
			icu.ingresarDatosInscripcion("lachiqui","Tour Colonia del Sacramento 18-09", 3, new GregorianCalendar(2022, 7, 18)); //costo 1200
			icu.ingresarDatosInscripcion("isabelita","Tour Colonia del Sacramento 18-09", 1, new GregorianCalendar(2022, 7, 19));// costo 400
			
			icu.ingresarDatosInscripcion("mastropiero","Almuerzo 2", 2, new GregorianCalendar(2022, 7, 19));// costo 1600
			icu.ingresarDatosInscripcion("chino","Teatro con Sabores 1", 1, new GregorianCalendar(2022, 7, 19)); //costo 500
			
			icu.ingresarDatosInscripcion("chino","Teatro con Sabores 2", 10, new GregorianCalendar(2022, 7, 20)); //costo 5000
			icu.ingresarDatosInscripcion("bobesponja","Teatro con Sabores 2", 2, new GregorianCalendar(2022, 7, 20));// costo 1000
			
			icu.ingresarDatosInscripcion("anibal","Teatro con Sabores 2", 1, new GregorianCalendar(2022, 7, 21));// costo 500
			icu.ingresarDatosInscripcion("tony","Degusta Setiembre", 11, new GregorianCalendar(2022, 7, 21)); //costo 8800
			
		}
		catch(salidaNoExisteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "La salida seleccionada no está registrada en el sistema", JOptionPane.ERROR_MESSAGE);
		}
		catch(usuarioNoExisteException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "El usuario ingresado no está registrado en el sistema", JOptionPane.ERROR_MESSAGE);
		}	
		
		try {
			icp.ingresarDatosPaquete("Disfrutar Rocha", "Actividades para hacer en familia y disfrutar arte y gastronomía", 60, 20, new GregorianCalendar(2022, 7, 10), null);
			icp.ingresarDatosPaquete("Un día en Colonia", "Paseos por el casco histórico y se puede terminar con Almuerzo en la Plaza de Toros", 45, 15, new GregorianCalendar(2022, 7, 1), null);
			icp.ingresarDatosPaquete("Valle del Lunarejo", "Visite un área protegida con un paisaje natural hermoso", 60, 15, new GregorianCalendar(2022, 8, 15), null);
		
//			tinyurl.com/3ppwdca4
//			tinyurl.com/4yzrdt8b
//			tinyurl.com/mvteyv6y
		}
		catch(paqueteYaExisteException ep) {
			JOptionPane.showMessageDialog(null, ep.getMessage(), "Paquete ya existe", JOptionPane.ERROR_MESSAGE);

		}
		
		try {
			icp.agregarActividadPaquete("Rocha", "Disfrutar Rocha", "Degusta");
			icp.agregarActividadPaquete("Rocha", "Disfrutar Rocha", "Teatro con Sabores");
			
			icp.agregarActividadPaquete("Colonia", "Un día en Colonia", "Tour por Colonia del Sacramento");
			icp.agregarActividadPaquete("Colonia", "Un día en Colonia", "Almuerzo en el Real de San Carlos");
			
			icp.agregarActividadPaquete("Rivera", "Valle del Lunarejo", "Almuerzo en Valle del Lunarejo");
			icp.agregarActividadPaquete("Rivera", "Valle del Lunarejo", "Cabalgata en Valle del Lunarejo");
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
		/*
		try {
			icp.comprarPaquete("lachiqui", "Disfrutar Rocha", new GregorianCalendar(2022, 7, 15), 2);//costo = 2080 vencimiento = 14/10/2022
			icp.comprarPaquete("lachiqui", "Un día en Colonia", new GregorianCalendar(2022, 7, 20), 5);//costo = 5100 vencimiento = 04/10/2022
			
			icp.comprarPaquete("waston", "Un día en Colonia", new GregorianCalendar(2022, 8, 15), 1);//costo = 1020 vencimiento = 30/10/2022
			icp.comprarPaquete("elelvis", "Disfrutar Rocha", new GregorianCalendar(2022, 8, 1), 10);//costo = 10400 vencimiento = 31/10/2022
			
			icp.comprarPaquete("elelvis", "Un día en Colonia", new GregorianCalendar(2022, 8, 18), 2);//costo = 2040 vencimiento = 2/11/2022
			icp.comprarPaquete("mastropiero", "Un día en Colonia", new GregorianCalendar(2022, 8, 2), 6);//costo = 6120  vencimiento = 17/10/2022
		}
		catch(usuarioNoExisteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Usuario no existe", JOptionPane.ERROR_MESSAGE);
		}
		catch(paqueteNoExisteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Paquete no existe", JOptionPane.ERROR_MESSAGE);
		}
		catch(compraExisteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Compra ya existe", JOptionPane.ERROR_MESSAGE);
		}
	
		try {
			icu.ingresarDatosInscripcionPaq("lachiqui", "Degusta Noviembre", 2, new GregorianCalendar(2022, 8, 15), "Disfrutar Rocha");//costo = 1280
			icu.ingresarDatosInscripcionPaq("lachiqui", "Teatro con Sabores 3", 2, new GregorianCalendar(2022, 8, 15), "Disfrutar Rocha");//costo = 800
			
			icu.ingresarDatosInscripcionPaq("elelvis", "Degusta Setiembre", 5, new GregorianCalendar(2022, 8, 2), "Disfrutar Rocha");//costo = 3200
			icu.ingresarDatosInscripcionPaq("elelvis", "Teatro con Sabores 1", 5, new GregorianCalendar(2022, 8, 2), "Disfrutar Rocha");//costo = 200
			
			icu.ingresarDatosInscripcionPaq("lachiqui", "Tour Colonia del Sacramento 11-09", 5, new GregorianCalendar(2022, 8, 3), "Un día en Colonia");//costo = 1700
			icu.ingresarDatosInscripcionPaq("lachiqui", "Almuerzo 1", 5, new GregorianCalendar(2022, 8, 3), "Un día en Colonia");//costo = 3400
			
			icu.ingresarDatosInscripcionPaq("waston", "Tour Colonia del Sacramento 18-09", 1, new GregorianCalendar(2022, 8, 5), "Un día en Colonia");//costo = 340
			icu.ingresarDatosInscripcionPaq("waston", "Almuerzo 2", 1, new GregorianCalendar(2022, 8, 5), "Un día en Colonia");//costo = 680
			
			icu.ingresarDatosInscripcionPaq("elelvis", "Tour Colonia del Sacramento 30-10", 2, new GregorianCalendar(2022, 9, 2), "Un día en Colonia");//costo = 680
			icu.ingresarDatosInscripcionPaq("elelvis", "Almuerzo en el Real 1", 2, new GregorianCalendar(2022, 9, 2), "Un día en Colonia");//costo = 1360
			
			icu.ingresarDatosInscripcionPaq("mastropiero", "Tour Colonia del Sacramento 30-10", 4, new GregorianCalendar(2022, 9, 5), "Un día en Colonia");//costo = 1360
			icu.ingresarDatosInscripcionPaq("mastropiero", "Almuerzo en el Real 1", 4, new GregorianCalendar(2022, 9, 5), "Un día en Colonia");//costo = 2720
			
		}
		catch(salidaNoExisteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Salida no existe", JOptionPane.ERROR_MESSAGE);
		}
		catch(usuarioNoExisteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Usuario no existe", JOptionPane.ERROR_MESSAGE);

		}
		catch(paqueteNoExisteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Paquete no existe", JOptionPane.ERROR_MESSAGE);
		}
		catch(inscripcionExisteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Inscripción ya existe", JOptionPane.ERROR_MESSAGE);
		}
		catch(limiteSuperadoException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Límite superado", JOptionPane.ERROR_MESSAGE);
		}
		*/
		JOptionPane.showMessageDialog(null, "Datos cargados con éxito!", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	
}
