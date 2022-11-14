package presentacion;

import excepciones.UsuarioRepetidoException;
import excepciones.actividadNoExisteException;
import excepciones.categoriaYaExisteException;
import excepciones.compraExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.inscripcionExisteException;
import excepciones.limiteSuperadoException;
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
	
	public CargarDatos(IControladorUsuario picu, IControladorDepartamento picd, IControladorPaquete picp) {
		icd = picd;
		icu = picu;
		icp = picp;
		DTUsuario user;
		user = new DTTurista("lachiqui", "Rosa María", "Martínez", "mirtha.legrand.ok@hotmail.com.ar", new GregorianCalendar(1927, 1, 23), "awdrg543", "https://pbs.twimg.com/media/EOHAP9zWoAsnkiM?format=jpg&name=small", "argentina");
		try {
			icu.altaUsuario(user);
			
			user = new DTTurista("isabelita", "Elizabeth", "Windsor", "isabelita@thecrown.co.uk", new GregorianCalendar(1926, 3, 21), "r5t6y7u8", "https://ca-times.brightspotcdn.com/dims4/default/2413d30/2147483647/strip/true/crop/720x1024+0+0/resize/840x1195!/format/webp/quality/90/?url=https%3A%2F%2Fcalifornia-times-brightspot.s3.amazonaws.com%2F9b%2Fe3%2F0973ea3441e396271b457c1e0b31%2Fap22146719572803.jpg", "inglesa");
			icu.altaUsuario(user);
			
			user = new DTTurista("anibal", "Aníbal", "Lecter", "anibal@fing.edu.uy", new GregorianCalendar(1937, 11, 31), "edrft543", "https://es.web.img2.acsta.net/c_310_420/pictures/14/01/20/09/27/059623.jpg", "lituana");
			icu.altaUsuario(user);
			
			user = new DTTurista("waston", "Emma", "Waston", "e.waston@gmail.com", new GregorianCalendar(1990, 3, 15), "poiuy987", "https://i.pinimg.com/originals/88/aa/b9/88aab93b1948c13d6acb878ced5e182e.jpg", "inglesa");
			icu.altaUsuario(user);
			
			user = new DTTurista("elelvis", "Elvis", "Lacio", "suavemente@hotmail.com", new GregorianCalendar(1971, 6, 30), "45idgaf67", "https://sc2.elpais.com.uy/files/article_default_content/uploads/2021/11/03/61830c08f3881.jpeg", "estadounidense");
			icu.altaUsuario(user);
			
			user = new DTTurista("eleven11", "Eleven", "Once", "eleven11@gmail.com", new GregorianCalendar(2004, 1, 19), "xdrgb657", "https://static.zerochan.net/Eleven.%28Stranger.Things%29.full.2671625.jpg", "española");
			icu.altaUsuario(user);
			
			user = new DTTurista("bobesponja", "Bob", "Esponja", "bobesponja@nickelodeon.com ", new GregorianCalendar(1999, 4, 01), "sbsplol1", "https://smoda.elpais.com/wp-content/uploads/2020/06/bob-esponja-591x447.jpg", "japonesa");
			icu.altaUsuario(user);
			
			user = new DTTurista("tony", "Antonio", "Pacheco", "eltony@manya.org.uy", new GregorianCalendar(1976, 3, 11), "okmnji98", "https://vinostonypacheco.com/images/500x500_2.jpg", "uruguaya");
			icu.altaUsuario(user);
			
			user = new DTTurista("chino", "Álvaro", "Recoba", "chino@trico.org.uy", new GregorianCalendar(1976, 2, 17), "qsxcdw43", "https://www.doblealturadeco.com/wp-content/uploads/2020/08/02-ChinoRecoba.jpg", "uruguaya");
			icu.altaUsuario(user);
			
			user = new DTTurista("mastropiero", "Johann Sebastian", "Mastropiero", "johann.sebastian@gmail.com", new GregorianCalendar(1922, 1, 07), "qpwoei586", "https://static.lesluthiers.org/fotos/Personajes/mastropiero.png", "austríaca");
			icu.altaUsuario(user);
			
			user = new DTProveedor("washington", "Washington", "Rocha", "washington@turismorocha.gub.uy", new GregorianCalendar(1970, 8, 14), "asdfg654", "https://c.wallhere.com/photos/55/39/safe_house_cia_agent_tobin_frost_denzel_washington-584127.jpg!d", "Hola! me llamo Washington y soy el encargado del portal de turismo del departamento de Rocha - Uruguay" , "http://turismorocha.gub.uy/");
			icu.altaUsuario(user);
			
			user = new DTProveedor("eldiez", "Pablo", "Bengoechea", "eldiez@socfomturriv.org.uy", new GregorianCalendar(1965, 5, 27), "ytrewq10", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSI6iVebQtM32FoIZFXUPSIhJlQo2asvOouzw&usqp=CAU", "Pablo es el presidente de la Sociedad de Fomento Turístico de Rivera (conocida como Socfomturriv)", "http://wwww.socfomturriv.org.uy");
			icu.altaUsuario(user);
			
			user = new DTProveedor("meche", "Mercedes", "Venn", "meche@colonia.gub.uy", new GregorianCalendar(1990, 11, 31), "mnjkiu89", "https://pbs.twimg.com/media/EmLg8pkXYAAkuGY?format=jpg&name=small", "Departamento de Turismo del Departamento de Colonia", "https://colonia.gub.uy/turismo/");
			icu.altaUsuario(user);
			
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
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
			e4.printStackTrace();
		}
		
		try { //FALTA AGREGAR LINK DE VIDEO AL FINAL CUANDO PUBLIQUEN CARGAR DATOS
			HashSet<String> categorias = new HashSet<String>();
			Estado estado = Estado.CONFIRMADA;
			
			categorias.add("Gastronomia");
			icd.ingresarDatosActividad("Degusta",  "Festival gastronómico de productos locales en Rocha", 3, 800, "Rocha", new GregorianCalendar(2022, 6, 20), "washington", "Rocha", categorias, "https://s3.amazonaws.com/turismorocha/eventos/2569/cover/degusta-048968300-1659558891.jpg", "https://www.youtube.com/embed/zQjSMQ6uV1g");
			icd.modificarEstadoActividad("Degusta", estado);
					
			categorias.add("Cultura y Patrimonio");
			icd.ingresarDatosActividad("Teatro con Sabores",  "En el mes aniversario del Club Deportivo Unión de Rocha te invitamos a una merienda deliciosa.", 3, 500, "Rocha", new GregorianCalendar(2022, 6, 21), "washington", "Rocha", categorias, "https://s3.amazonaws.com/turismorocha/eventos/2579/cover/teatro-con-sabores-008950900-1659638152.jpg", "https://www.youtube.com/embed/Lxit3xvKShc");
			icd.modificarEstadoActividad("Teatro con Sabores", estado);
			
			categorias.remove("Gastronomia");
			icd.ingresarDatosActividad("Tour por Colonia del Sacramento",  "Con guía especializado y en varios idiomas. Varios circuitos posibles.", 2, 400, "Colonia del Sacramento", new GregorianCalendar(2022, 7, 1), "meche", "Colonia", categorias, "https://www.coloniauy.com/wp-content/uploads/2014/10/city-tour-por-colonia-del-sacramento-2.jpg", "https://www.youtube.com/embed/zVDGjURCBz8");
			icd.modificarEstadoActividad("Tour por Colonia del Sacramento", estado);
			
			categorias.add("Gastronomia");
			categorias.remove("Cultura y Patrimonio");
			icd.ingresarDatosActividad("Almuerzo en el Real de San Carlos",  "Restaurante en la renovada Plaza de Toros con menú internacional.", 2, 800, "Colonia del Sacramento", new GregorianCalendar(2022, 7, 1), "meche", "Colonia", categorias, "https://www.colonia.gub.uy/uploads/noticia_9539a26b2ab84e8e5f3dc136206ffa24857f2850.jpg", "https://www.youtube.com/embed/wfyDxicM1PQ");
			icd.modificarEstadoActividad("Almuerzo en el Real de San Carlos", estado);
			
			categorias.add("Campo y Naturaleza");
			icd.ingresarDatosActividad("Almuerzo en Valle del Lunarejo",  "Almuerzo en la Posada con ticket fijo. Menú que incluye bebida y postre casero.", 2, 300, "Tranqueras", new GregorianCalendar(2022, 7, 1), "eldiez", "Rivera", categorias, "https://www.gub.uy/ministerio-ambiente/sites/ministerio-ambiente/files/inline-images/Lunarejo.jpg", "https://www.youtube.com/embed/5uaEdiQVEEE");
			icd.modificarEstadoActividad("Almuerzo en Valle del Lunarejo", estado);
			
			categorias.remove("Gastronomia");
			icd.ingresarDatosActividad("Cabalgata en Valle del Lunarejo",  "Cabalgata por el Área protegida. Varios recorridos para elegir.", 2, 150, "Tranqueras", new GregorianCalendar(2022, 7, 1), "eldiez", "Rivera", categorias, "http://elgavilan-valledellunarejo.com.uy/wp-content/uploads/2020/12/WhatsApp-Image-2020-12-21-at-4.28.25-PM.jpeg", "https://www.youtube.com/embed/dlUb22YfXDg");
			icd.modificarEstadoActividad("Cabalgata en Valle del Lunarejo", estado);

			categorias.add("Cultura y Patrimonio");
			categorias.remove("Campo y Naturaleza");
			icd.ingresarDatosActividad("Bus turístico Colonia",  "Recorrida por los principales atractivos de la ciudad", 3, 600, "Colonia del Sacramento", new GregorianCalendar(2022, 8, 1), "meche", "Colonia", categorias, "https://viajeacoloniauruguay.com/wp-content/uploads/2019/04/Bus_Turistico_Colonia.jpg", "https://www.youtube.com/embed/FCFoe4ibkk8");
			estado = Estado.AGREGADA;
			icd.modificarEstadoActividad("Bus turístico Colonia", estado);
			
			icd.ingresarDatosActividad("Colonia Premium Tour",  "Visita lugares exclusivos y relevantes", 4, 2600, "Colonia del Sacramento", new GregorianCalendar(2022, 8, 3), "meche", "Colonia", categorias, "https://media-cdn.tripadvisor.com/media/attractions-splice-spp-720x480/07/9d/ed/fd.jpg", null);
			estado = Estado.RECHAZADA;
			icd.modificarEstadoActividad("Colonia Premium Tour", estado);

			categorias.add("Aventura y Deporte");
			categorias.add("Turismo Playas");
			categorias.remove("Cultura y Patrimonio");
			icd.ingresarDatosActividad("Deportes náuticos sin uso de motor",  "kitsurf - windsurf - kayakismo - canotaje en Rocha", 3, 1200, "Rocha", new GregorianCalendar(2022, 8, 5), "washington", "Rocha", categorias, "https://s3.amazonaws.com/turismorocha/operadores/202/med/la-barrita-de-la-laguna-032637600-1546992923.jpg", "https://www.youtube.com/embed/a7Lfx4Flb28");
			estado = Estado.AGREGADA;
			icd.modificarEstadoActividad("Deportes náuticos sin uso de motor", estado);

			categorias.remove("Turismo Playas");
			icd.ingresarDatosActividad("Descubre Rivera",  "Rivera es un departamento de extraordinaria riqueza natural patrimonial y cultural con una ubicación geográfica privilegiada", 2, 650, "Rivera", new GregorianCalendar(2022, 8, 16), "eldiez", "Rivera", categorias, "https://upload.wikimedia.org/wikipedia/commons/6/6e/Obelisco_-_Plaza_Internacional_-_Frontera_de_la_Paz_-_Livramento_-_Rivera.jpeg", null);
			estado = Estado.RECHAZADA;
			icd.modificarEstadoActividad("Descubre Rivera", estado);
			
		}
		catch(departamentoNoExisteException e1) {
			e1.printStackTrace();
		}
		catch(proveedorNoExisteException e2) {
			e2.printStackTrace();
		}

		try {
			icd.ingresarDatosSalida("Degusta Agosto", 20, new GregorianCalendar(2022, 6, 21), new GregorianCalendar(2022, 7, 20), 1700, "Sociedad Agropecuaria de Rocha", "Rocha", "Degusta", "https://city.woow.com.uy/media/catalog/product/cache/dcf64a24127a43d9ce9fe76e3e5f8061/n/u/nueva2_3_1.jpg");
			icd.ingresarDatosSalida("Degusta Setiembre", 20, new GregorianCalendar(2022, 6, 22), new GregorianCalendar(2022, 8, 3), 1700, "Sociedad Agropecuaria de Rocha", "Rocha", "Degusta", "https://s3.amazonaws.com/turismorocha/operadores/1/med/bahia-resto-053888900-1458674720.JPG");
			
			icd.ingresarDatosSalida("Teatro con Sabores 1", 30, new GregorianCalendar(2022, 6, 23), new GregorianCalendar(2022, 8, 4), 1800, "Club Deportivo Unión", "Rocha", "Teatro con Sabores", "https://thumbs.dreamstime.com/b/teatro-de-la-cena-3857878.jpg");
			icd.ingresarDatosSalida("Teatro con Sabores 2", 30, new GregorianCalendar(2022, 6, 23), new GregorianCalendar(2022, 8, 11), 1800, "Club Deportivo Unión", "Rocha", "Teatro con Sabores", "https://www.teatroreal.es/sites/default/files/2019-09/MESITAS%20%281%29.jpg");
			
			icd.ingresarDatosSalida("Tour Colonia del Sacramento 11-09", 5, new GregorianCalendar(2022, 7, 5), new GregorianCalendar(2022, 8, 11), 1000, "Encuentro en la base del Faro", "Colonia", "Tour por Colonia del Sacramento", "https://upload.wikimedia.org/wikipedia/commons/4/42/Colonia_de_Sacramento.jpg");
			icd.ingresarDatosSalida("Tour Colonia del Sacramento 18-09", 5, new GregorianCalendar(2022, 7, 5), new GregorianCalendar(2022, 8, 18), 1000, "Encuentro en la base del Faro", "Colonia", "Tour por Colonia del Sacramento", "https://www.surfingtheplanet.com/wp-content/uploads/2012/04/DSC4945.jpg");
			
			icd.ingresarDatosSalida("Almuerzo 1", 5, new GregorianCalendar(2022, 7, 4), new GregorianCalendar(2022, 8, 18), 1200, "Restaurante de la Plaza de Toros", "Colonia", "Almuerzo en el Real de San Carlos", null);
			icd.ingresarDatosSalida("Almuerzo 2", 5, new GregorianCalendar(2022, 7, 4), new GregorianCalendar(2022, 8, 25), 1200, "Restaurante de la Plaza de Toros", "Colonia", "Almuerzo en el Real de San Carlos", null);
			
			icd.ingresarDatosSalida("Almuerzo 3", 4, new GregorianCalendar(2022, 7, 15), new GregorianCalendar(2022, 8, 10), 1200, "Posada del Lunarejo", "Rivera", "Almuerzo en Valle del Lunarejo", null);
			icd.ingresarDatosSalida("Almuerzo 4", 4, new GregorianCalendar(2022, 7, 15), new GregorianCalendar(2022, 8, 11), 1200, "Posada del Lunarejo", "Rivera", "Almuerzo en Valle del Lunarejo", null);
			
			icd.ingresarDatosSalida("Cabalgata 1", 4, new GregorianCalendar(2022, 7, 15), new GregorianCalendar(2022, 8, 10), 1600, "Posada del Lunarejo", "Rivera", "Cabalgata en Valle del Lunarejo", "https://volemos.nyc3.cdn.digitaloceanspaces.com/blog/wp-content/uploads/2021/12/14132221/cabalgatas-valle-del-Lunarejo.jpg");
			icd.ingresarDatosSalida("Cabalgata 2", 4, new GregorianCalendar(2022, 7, 15), new GregorianCalendar(2022, 8, 11), 1600, "Posada del Lunarejo", "Rivera", "Cabalgata en Valle del Lunarejo", null);
			
			icd.ingresarDatosSalida("Degusta Octubre", 20, new GregorianCalendar(2022, 8, 22), new GregorianCalendar(2022, 9, 30), 1700, "Sociedad Agropecuaria de Rocha", "Rocha", "Degusta", "https://www.cucinare.tv/wp-content/uploads/2020/02/Carne-Hermanos-1024x579.jpg");
			icd.ingresarDatosSalida("Degusta Noviembre", 20, new GregorianCalendar(2022, 1, 10), new GregorianCalendar(2022, 10, 5), 1700, "Sociedad Agropecuaria de Rocha", "Rocha", "Degusta", "https://s3.amazonaws.com/turismorocha/operadores/156/med/maria-esther-089483400-1481752402.JPG");
		
			icd.ingresarDatosSalida("Teatro con Sabores 3", 30, new GregorianCalendar(2022, 7, 25), new GregorianCalendar(2022, 10, 11), 1800, "Club Deportivo Unión", "Rocha", "Teatro con Sabores", null);
			icd.ingresarDatosSalida("Tour Colonia del Sacramento 30-10", 10, new GregorianCalendar(2022, 8, 7), new GregorianCalendar(2022, 9, 30), 1000, "Encuentro en la base del Faro", "Colonia", "Tour por Colonia del Sacramento", "https://www.globeguide.ca/wp-content/uploads/2017/10/Uruguay-Colonia-52.jpg");

			icd.ingresarDatosSalida("Cabalgata Extrema", 4, new GregorianCalendar(2022, 8, 15), new GregorianCalendar(2022, 9, 30), 1600, "Posada del Lunarejo", "Rivera", "Cabalgata en Valle del Lunarejo", "http://elgavilan-valledellunarejo.com.uy/wp-content/uploads/2020/12/WhatsApp-Image-2020-12-21-at-4.28.25-PM.jpeg");
			icd.ingresarDatosSalida("Almuerzo en el Real 1", 10, new GregorianCalendar(2022, 9, 10), new GregorianCalendar(2022, 9, 30), 1200, "Restaurante de la Plaza de Toros", "Colonia", "Almuerzo en el Real de San Carlos", null);

			icd.ingresarDatosSalida("Degusta Diciembre", 20, new GregorianCalendar(2022, 10, 7), new GregorianCalendar(2022, 11, 2), 1700, "Sociedad Agropecuaria de Rocha", "Rocha", "Degusta", "https://s3.amazonaws.com/turismorocha/operadores/156/med/maria-esther-089483400-1481752402.JPG");
			icd.ingresarDatosSalida("Teatro con Sabores 4", 30, new GregorianCalendar(2022, 10, 7), new GregorianCalendar(2022, 11, 3), 1800, "Club Deportivo Unión", "Rocha", "Teatro con Sabores", null);
		}
		catch(actividadNoExisteException e1) {
			e1.printStackTrace();
		}
		catch(departamentoNoExisteException e2) {
			e2.printStackTrace();
		}	
		
		try {
			icu.ingresarDatosInscripcion("lachiqui", "Degusta Agosto", 3, new GregorianCalendar(2022, 7, 15));
			icu.ingresarDatosInscripcion("elelvis", "Degusta Agosto", 5, new GregorianCalendar(2022, 7, 16));
			
			icu.ingresarDatosInscripcion("lachiqui", "Tour Colonia del Sacramento 18-09", 3, new GregorianCalendar(2022, 7, 18));
			icu.ingresarDatosInscripcion("isabelita", "Tour Colonia del Sacramento 18-09", 1, new GregorianCalendar(2022, 7, 19));
			
			icu.ingresarDatosInscripcion("mastropiero", "Almuerzo 2", 2, new GregorianCalendar(2022, 7, 19));
			icu.ingresarDatosInscripcion("chino", "Teatro con Sabores 1", 1, new GregorianCalendar(2022, 7, 19));
			
			icu.ingresarDatosInscripcion("chino", "Teatro con Sabores 2", 10, new GregorianCalendar(2022, 7, 20));
			icu.ingresarDatosInscripcion("bobesponja", "Teatro con Sabores 2", 2, new GregorianCalendar(2022, 7, 20));
			
			icu.ingresarDatosInscripcion("anibal", "Teatro con Sabores 2", 1, new GregorianCalendar(2022, 7, 21));
			icu.ingresarDatosInscripcion("tony", "Degusta Setiembre", 11, new GregorianCalendar(2022, 7, 21));
			
			icu.ingresarDatosInscripcion("lachiqui", "Almuerzo 1", 5, new GregorianCalendar(2022, 8, 3));
			icu.ingresarDatosInscripcion("waston", "Almuerzo 2", 1, new GregorianCalendar(2022, 8, 5));
			icu.ingresarDatosInscripcion("elelvis", "Almuerzo en el Real 1", 2, new GregorianCalendar(2022, 9, 11));
			icu.ingresarDatosInscripcion("mastropiero", "Almuerzo en el Real 1", 4, new GregorianCalendar(2022, 9, 12));
			
		}
		catch(salidaNoExisteException e1) {
			e1.printStackTrace();
		}
		catch(usuarioNoExisteException e2) {
			e2.printStackTrace();
		}	
		
		try {
			icp.ingresarDatosPaquete("Disfrutar Rocha", "Actividades para hacer en familia y disfrutar arte y gastronomía", 60, 20, new GregorianCalendar(2022, 7, 10), "https://sites.google.com/site/areasprotegidasenuruguay/_/rsrc/1411660757953/algunas-de-las-areas-ingresadas-por-el-snap/laguna-de-rocha/Mapa_Rocha_BLOG.jpg?height=280&width=400");
			icp.ingresarDatosPaquete("Un día en Colonia", "Paseos por el casco histórico y se puede terminar con Almuerzo en la Plaza de Toros", 45, 15, new GregorianCalendar(2022, 7, 1), "https://www.lr21.com.uy/wp-content/uploads/2021/12/plaza-toros-colonia.jpg");
			icp.ingresarDatosPaquete("Valle del Lunarejo", "Visite un área protegida con un paisaje natural hermoso", 60, 15, new GregorianCalendar(2022, 8, 15), "http://www.indra.org.uy/images/inauguracion-03.jpg");
			icp.ingresarDatosPaquete("Rocha de Fiesta", "Para cerrar el año a lo grande en nuestro departamento más océanico", 45, 30, new GregorianCalendar(2022, 10, 7), null);
		}
		catch(paqueteYaExisteException ep) {
			ep.printStackTrace();
		}
		
		try {
			icp.agregarActividadPaquete("Rocha", "Disfrutar Rocha", "Degusta");
			icp.agregarActividadPaquete("Rocha", "Disfrutar Rocha", "Teatro con Sabores");
			
			icp.agregarActividadPaquete("Colonia", "Un día en Colonia", "Tour por Colonia del Sacramento");
			
			icp.agregarActividadPaquete("Rivera", "Valle del Lunarejo", "Almuerzo en Valle del Lunarejo");
			icp.agregarActividadPaquete("Rivera", "Valle del Lunarejo", "Cabalgata en Valle del Lunarejo");
		
			icp.agregarActividadPaquete("Rivera", "Valle del Lunarejo", "Almuerzo en Valle del Lunarejo");
			icp.agregarActividadPaquete("Rivera", "Valle del Lunarejo", "Cabalgata en Valle del Lunarejo");
			
			icp.agregarActividadPaquete("Rocha", "Rocha de Fiesta", "Degusta");
		}
		catch(paqueteNoExisteException e1) {
			e1.printStackTrace();
		}
		catch(departamentoNoExisteException e2 ) {
			e2.printStackTrace();
		}
		catch(actividadNoExisteException e3) {
			e3.printStackTrace();
		}
		
		try {
			icp.comprarPaquete("lachiqui", "Disfrutar Rocha", new GregorianCalendar(2022, 7, 15), 2);
			icp.comprarPaquete("lachiqui", "Un día en Colonia", new GregorianCalendar(2022, 7, 20), 5);
			
			icp.comprarPaquete("waston", "Un día en Colonia", new GregorianCalendar(2022, 8, 15), 1);
			icp.comprarPaquete("elelvis", "Disfrutar Rocha", new GregorianCalendar(2022, 8, 1), 10);
			
			icp.comprarPaquete("elelvis", "Un día en Colonia", new GregorianCalendar(2022, 8, 18), 2);
			icp.comprarPaquete("mastropiero", "Un día en Colonia", new GregorianCalendar(2022, 8, 2), 6);
		}
		catch(usuarioNoExisteException e1) {
			e1.printStackTrace();
		}
		catch(paqueteNoExisteException e1) {
			e1.printStackTrace();
		}
		catch(compraExisteException e1) {
			e1.printStackTrace();
		}
				
		try {
			icu.ingresarDatosInscripcionPaq("lachiqui", "Degusta Noviembre", 2, new GregorianCalendar(2022, 9, 3), "Disfrutar Rocha");
			icu.ingresarDatosInscripcionPaq("lachiqui", "Teatro con Sabores 3", 2, new GregorianCalendar(2022, 9, 3), "Disfrutar Rocha");
			
			icu.ingresarDatosInscripcionPaq("elelvis", "Degusta Setiembre", 5, new GregorianCalendar(2022, 8, 2), "Disfrutar Rocha");
			icu.ingresarDatosInscripcionPaq("elelvis", "Teatro con Sabores 1", 5, new GregorianCalendar(2022, 8, 2), "Disfrutar Rocha");
			
			icu.ingresarDatosInscripcionPaq("lachiqui", "Tour Colonia del Sacramento 11-09", 5, new GregorianCalendar(2022, 8, 3), "Un día en Colonia");
			
			icu.ingresarDatosInscripcionPaq("waston", "Tour Colonia del Sacramento 18-09", 1, new GregorianCalendar(2022, 8, 5), "Un día en Colonia");
			
			icu.ingresarDatosInscripcionPaq("elelvis", "Tour Colonia del Sacramento 30-10", 2, new GregorianCalendar(2022, 9, 2), "Un día en Colonia");
			
			icu.ingresarDatosInscripcionPaq("mastropiero", "Tour Colonia del Sacramento 30-10", 4, new GregorianCalendar(2022, 9, 12), "Un día en Colonia");
			
		}
		catch(salidaNoExisteException e1) {
			e1.printStackTrace();
		}
		catch(usuarioNoExisteException e1) {
			e1.printStackTrace();
		}
		catch(paqueteNoExisteException e1) {
			e1.printStackTrace();
		}
		catch(inscripcionExisteException e1) {
			e1.printStackTrace();
		}
		catch(limiteSuperadoException e1) {
			e1.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Datos cargados con éxito!", "Cargar datos", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	
}
