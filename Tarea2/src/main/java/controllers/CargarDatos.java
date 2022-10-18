package controllers;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import excepciones.UsuarioRepetidoException;
import excepciones.actividadNoExisteException;
import excepciones.categoriaYaExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.paqueteNoExisteException;
import excepciones.paqueteYaExisteException;
import excepciones.proveedorNoExisteException;
import excepciones.salidaNoExisteException;
import excepciones.usuarioNoExisteException;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;
import logica.controladores.IControladorPaquete;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;

/**
 * Servlet implementation class CargarDatos
 */
@WebServlet("/CargarDatos")
public class CargarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarDatos() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private void ejecutarCargar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	Fabrica fact = Fabrica.getInstance();
    	IControladorDepartamento icd = fact.getIControladorDepartamento();
    	IControladorUsuario icu = fact.getIControladorUsuario();
    	IControladorPaquete icp = fact.getIControladorPaquete();
    	//CargarDatos cacaDatos = new CargarDatos(ctrlUsuario, ctrlDepartamentos, ctrlPaquete);
    	/* icd = picd;
		icu = picu;
		icp = picp; */
		
    	DTUsuario user;
		user = new DTTurista("lachiqui", "Rosa María", "Martínez", "mirtha.legrand.ok@hotmail.com.ar", new GregorianCalendar(1927, 1, 23), "awdrg543", "argentina");
		try {
			icu.altaUsuario(user);
			
			user = new DTTurista("isabelita", "Elizabeth", "Windsor", "isabelita@thecrown.co.uk", new GregorianCalendar(1926, 3, 21),"r5t6y7u8", "inglesa");
			icu.altaUsuario(user);
			
			user = new DTTurista("anibal", "Aníbal", "Lecter", "anibal@fing.edu.uy", new GregorianCalendar(1937, 11, 31),"edrft543", "lituana");
			icu.altaUsuario(user);
			
			user = new DTTurista("waston", "Emma", "Waston", "e.waston@gmail.com", new GregorianCalendar(1990, 3, 15), "12345", "inglesa");
			icu.altaUsuario(user);
			
			user = new DTTurista("elelvis", "Elvis", "Lacio", "suavemente@hotmail.com", new GregorianCalendar(1971, 6, 30), "12345", "estadounidense");
			icu.altaUsuario(user);
			
			user = new DTTurista("eleven11", "Eleven", "Once", "eleven11@gmail.com", new GregorianCalendar(2004, 1, 19), "12345", "española");
			icu.altaUsuario(user);
			
			user = new DTTurista("bobesponja", "Bob", "Esponja", "bobesponja@nickelodeon.com ", new GregorianCalendar(1999, 4, 01), "12345", "japonesa");
			icu.altaUsuario(user);
			
			user = new DTTurista("tony", "Antonio", "Pacheco", "eltony@manya.org.uy", new GregorianCalendar(1976, 3, 11), "12345", "uruguaya");
			icu.altaUsuario(user);
			
			user = new DTTurista("chino", "Álvaro", "Recoba", "chino@trico.org.uy", new GregorianCalendar(1976, 2, 17), "12345", "uruguaya");
			icu.altaUsuario(user);
			
			user = new DTTurista("mastropiero", "Johann Sebastian", "Mastropiero", "johann.sebastian@gmail.com", new GregorianCalendar(1922, 1, 07), "12345", "austríaca");
			icu.altaUsuario(user);
			
			user = new DTProveedor("washington", "Washington", "Rocha", "washington@turismorocha.gub.uy", new GregorianCalendar(1970, 8, 14), "asdfg654", "Hola! me llamo Washington y soy el encargado del portal de turismo del departamento de Rocha - Uruguay" , "http://turismorocha.gub.uy/");
			icu.altaUsuario(user);
			
			user = new DTProveedor("eldiez", "Pablo", "Bengoechea", "eldiez@socfomturriv.org.uy", new GregorianCalendar(1965, 5, 27), "ytrewq10", "Pablo es el presidente de la Sociedad de Fomento Turístico de Rivera (conocida como Socfomturriv)", "http://wwww.socfomturriv.org.uy");
			icu.altaUsuario(user);
			
			user = new DTProveedor("meche", "Mercedes", "Venn", "meche@colonia.gub.uy", new GregorianCalendar(1990, 11, 31), "mnjkiu89", "Departamento de Turismo del Departamento de Colonia", "https://colonia.gub.uy/turismo/");
			icu.altaUsuario(user);
		} catch (UsuarioRepetidoException e) {
			request.setAttribute("error", "Los datos ya fueron cargados previamente");
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
			icd.ingresarDatosCategoria("Gastronomía");
		} catch (categoriaYaExisteException e4) {
			request.setAttribute("error", "La categoría ingresada ya está en el sistema");
		}
		
		try { 
			icd.ingresarDatosActividad("Degusta",  "Festival gastronómico de productos locales en Rocha", 3, 800, "Rocha", new GregorianCalendar(2022, 6, 20), "washington", "Rocha");
			icd.ingresarDatosActividad("Teatro con Sabores",  "En el mes aniversario del Club Deportivo Unión de Rocha te invitamos a una merienda deliciosa.", 3, 500, "Rocha", new GregorianCalendar(2022, 6, 21), "washington", "Rocha");

			icd.ingresarDatosActividad("Tour por Colonia del Sacramento",  "Con guía especializado y en varios idiomas. Varios circuitos posibles.", 2, 400, "Colonia del Sacramento", new GregorianCalendar(2022, 7, 1), "meche", "Colonia");
			icd.ingresarDatosActividad("Almuerzo en el Real de San Carlos",  "Restaurante en la renovada Plaza de Toros con menú internacional.", 2, 800, "Colonia del Sacramento", new GregorianCalendar(2022, 7, 1), "meche", "Colonia");
			
			icd.ingresarDatosActividad("Almuerzo en Valle del Lunarejo",  "Almuerzo en la Posada con ticket fijo. Menú que incluye bebida y postre casero.", 2, 300, "Tranqueras", new GregorianCalendar(2022, 7, 1), "eldiez", "Rivera");
			icd.ingresarDatosActividad("Cabalgata en Valle del Lunarejo",  "Cabalgata por el Área protegida. Varios recorridos para elegir.", 2, 150, "Tranqueras", new GregorianCalendar(2022, 7, 1), "eldiez", "Rivera");
		}
		catch(departamentoNoExisteException e1) {
			request.setAttribute("error", "El departamento seleccionado no está registrado en el sistema");
		}
		catch(proveedorNoExisteException e2) {
			request.setAttribute("error", "El proveedor ingresado no está registrado en el sistema");
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

		}
		catch(actividadNoExisteException e1) {
			request.setAttribute("error", "La actividad seleccionada no está registrada en el sistema");
		}
		catch(departamentoNoExisteException e2) {
			request.setAttribute("error", "El proveedor seleccionado no está registrado en el sistema");
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
			request.setAttribute("error", "La salida seleccionada no está registrada en el sistema");
		}
		catch(usuarioNoExisteException e2) {
			request.setAttribute("error", "El usuario ingresado no está registrado en el sistema");
		}	
		
		try {
			icp.ingresarDatosPaquete("Disfrutar Rocha", "Actividades para hacer en familia y disfrutar arte y gastronomía", 60, 20, new GregorianCalendar(2022, 7, 10), null);
			icp.ingresarDatosPaquete("Un día en Colonia", "Paseos por el casco histórico y se puede terminar con Almuerzo en la Plaza de Toros", 45, 15, new GregorianCalendar(2022, 7, 1), null);
		
		}
		catch(paqueteYaExisteException ep) {
			request.setAttribute("error", "Paquete ya existe");
		}
		
		try {
			icp.agregarActividadPaquete("Rocha", "Disfrutar Rocha", "Degusta");
			icp.agregarActividadPaquete("Rocha", "Disfrutar Rocha", "Teatro con Sabores");
			icp.agregarActividadPaquete("Colonia", "Un día en Colonia", "Tour por Colonia del Sacramento");
			icp.agregarActividadPaquete("Colonia", "Un día en Colonia", "Almuerzo en el Real de San Carlos");
		}
		catch(paqueteNoExisteException e1) {
			request.setAttribute("error", "Paquete no existe");
		}
		catch(departamentoNoExisteException e2 ) {
			request.setAttribute("error", "Departamento no existe");
		}
		catch(actividadNoExisteException e3) {
			request.setAttribute("error", "Actividad no existe");
		}
		
		request.setAttribute("dptos", icd.obtenerDepartamentos());
		
		request.setAttribute("cats", icd.obtenerCategorias());
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ejecutarCargar(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ejecutarCargar(request, response);
	}

}
