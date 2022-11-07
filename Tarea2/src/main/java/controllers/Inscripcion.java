package controllers;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import publicadores.ActividadNoExisteException_Exception;
import publicadores.CategoriaNoExisteException_Exception;
import publicadores.DepartamentoNoExisteException_Exception;
import publicadores.DtUsuario;
import publicadores.InscripcionExisteException_Exception;
import publicadores.LimiteSuperadoException_Exception;
import publicadores.PaqueteNoExisteException_Exception;
import publicadores.PublicadorDepartamento;
import publicadores.PublicadorDepartamentoService;
import publicadores.PublicadorPaquete;
import publicadores.PublicadorPaqueteService;
import publicadores.PublicadorUsuario;
import publicadores.PublicadorUsuarioService;
import publicadores.SalidaNoExisteException_Exception;
import publicadores.UsuarioNoExisteException_Exception;


/**
 * Servlet implementation class Inscripcion
 */
@WebServlet("/inscribir")
public class Inscripcion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscripcion() {
        super();
    }
    protected void errorInscripcion(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException  {
    	
    	PublicadorDepartamentoService serviceD = new PublicadorDepartamentoService();
        PublicadorDepartamento portD = serviceD.getPublicadorDepartamentoPort();
        
        PublicadorPaqueteService serviceP = new PublicadorPaqueteService();
        PublicadorPaquete portP = serviceP.getPublicadorPaquetePort();
    	
    	//MANDO TODOS LOS ATRIBUTOS RECIBIDOS EN EL REQUEST 
		if (request.getParameter("dpto") != null)
			request.setAttribute("dpto", request.getParameter("dpto"));
		else 
			request.setAttribute("cat", request.getParameter("cat"));
		request.setAttribute("act", request.getParameter("act"));
		request.setAttribute("cant", request.getParameter("cant"));
		request.setAttribute("sal", request.getParameter("sal"));
		request.setAttribute("paq", request.getParameter("paq"));
		
		//MANDO TODOS LOS ELEMENTOS DE LOS SELECT
		try { //cargo actividades
			Set<String> res;
			if (request.getParameter("dpto") != null)
				res = new HashSet<String>(portD.obtenerDatosActividadesConfirmadasDpto(request.getParameter("dpto")).getSetString());
			else 
				res = new HashSet<String>(portD.obtenerDatosActividadesConfirmadasCat(request.getParameter("cat")).getSetString());
			request.setAttribute("actsInsc", res);
		} catch (DepartamentoNoExisteException_Exception  | CategoriaNoExisteException_Exception  exc1) {
			exc1.printStackTrace(); //solo pasaría con datos desactualizados  
		}
		
		try { //cargo salidas
			Set<String> res = new HashSet<String>(portD.obtenerDatosSalidasVigentes(request.getParameter("act")).getSetString());
			request.setAttribute("salsInsc", res);
		} catch (ActividadNoExisteException_Exception e) {
			e.printStackTrace(); //solo pasaría con datos desactualizados  
		}
		
		try { //cargo paquetes
			DtUsuario usr = (DtUsuario) request.getSession().getAttribute("usuario_logueado");
			Set<String> res = new HashSet<String>(portP.obtenerPaquetesDisponibles(usr.getNickname(), request.getParameter("sal"), Integer.parseInt(request.getParameter("cant"))).getSetString());
			request.setAttribute("paqsInsc", res);
		} catch (UsuarioNoExisteException_Exception  e) {
			e.printStackTrace(); //solo pasaría con datos desactualizados 
		}
		request.getRequestDispatcher("/pages/inscripcionASalida.jsp").forward(request, response);
    }
    
    protected void llenarComboboxes(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException  {

        PublicadorDepartamentoService serviceD = new PublicadorDepartamentoService();
        PublicadorDepartamento portD = serviceD.getPublicadorDepartamentoPort();
        
        PublicadorPaqueteService serviceP = new PublicadorPaqueteService();
        PublicadorPaquete portP = serviceP.getPublicadorPaquetePort();
        
    	String cambio = request.getParameter("cambio");
    	switch(cambio) {
    		case "dpto": {
    			try {
					Set<String> res = new HashSet<String>(portD.obtenerDatosActividadesConfirmadasDpto(request.getParameter("dpto")).getSetString());
					request.setAttribute("dpto", request.getParameter("dpto"));
					request.setAttribute("actsInsc", res);
					request.getRequestDispatcher("/pages/inscripcionASalida.jsp").forward(request, response);
					
				} catch (DepartamentoNoExisteException_Exception  exc1) {
					exc1.printStackTrace(); //solo pasaría con datos desactualizados  
				}
    			break;
    		}
    		case "cat": {
    			try {
    	    		Set<String> res = new HashSet<String>(portD.obtenerDatosActividadesConfirmadasCat(request.getParameter("cat")).getSetString());
					request.setAttribute("cat", request.getParameter("cat"));
					request.setAttribute("actsInsc", res);
					request.getRequestDispatcher("/pages/inscripcionASalida.jsp").forward(request, response);
					
				} catch (CategoriaNoExisteException_Exception  exc1) {
					exc1.printStackTrace(); //solo pasaría con datos desactualizados  
				}
    			break;
    		}
    		case "act": {
				try {
					Set<String> salsInsc = new HashSet<String>(portD.obtenerDatosSalidasVigentes(request.getParameter("act")).getSetString());
					request.setAttribute("salsInsc", salsInsc); // relleno las salidas
					
					if (request.getParameter("dpto") != null) { 
						Set<String> res = new HashSet<String>(portD.obtenerDatosActividadesConfirmadasDpto(request.getParameter("dpto")).getSetString());
						request.setAttribute("dpto", request.getParameter("dpto"));
						request.setAttribute("actsInsc", res);
					}
					else {
						Set<String> res = new HashSet<String>(portD.obtenerDatosActividadesConfirmadasCat(request.getParameter("cat")).getSetString());
						request.setAttribute("cat", request.getParameter("cat"));
						request.setAttribute("actsInsc", res); // relleno las actividades por cat
					}
					request.setAttribute("act", request.getParameter("act"));
					request.getRequestDispatcher("/pages/inscripcionASalida.jsp").forward(request, response);
	    		} catch (ActividadNoExisteException_Exception  | DepartamentoNoExisteException_Exception  | CategoriaNoExisteException_Exception  exc1) {
	    			exc1.printStackTrace(); //solo pasaría con datos desactualizados  
				}
				break;
    		}
    		case "sal": {
    			try {
    	    		DtUsuario usr = (DtUsuario) request.getSession().getAttribute("usuario_logueado");
    	    		
					Set<String> paqsInsc = new HashSet<String>(portP.obtenerPaquetesDisponibles(usr.getNickname(), request.getParameter("sal"), Integer.parseInt(request.getParameter("cant"))).getSetString());
					request.setAttribute("paqsInsc", paqsInsc); //relleno los paquetes
					
					Set<String> salsInsc = new HashSet<String>(portD.obtenerDatosSalidasVigentes(request.getParameter("act")).getSetString());
					request.setAttribute("salsInsc", salsInsc); // relleno las salidas
					
					if (request.getParameter("dpto") != null) { 
						Set<String> res = new HashSet<String>(portD.obtenerDatosActividadesConfirmadasDpto(request.getParameter("dpto")).getSetString());
						request.setAttribute("dpto", request.getParameter("dpto"));
						request.setAttribute("actsInsc", res);
					}
					else {
						Set<String> res = new HashSet<String>(portD.obtenerDatosActividadesConfirmadasCat(request.getParameter("cat")).getSetString());
						request.setAttribute("cat", request.getParameter("cat"));
						request.setAttribute("actsInsc", res); // relleno las actividades por cat
					}
					request.setAttribute("act", request.getParameter("act"));
					request.setAttribute("cant", request.getParameter("cant"));
					request.setAttribute("sal", request.getParameter("sal"));
					request.getRequestDispatcher("/pages/inscripcionASalida.jsp").forward(request, response);
	    		} catch (UsuarioNoExisteException_Exception  | DepartamentoNoExisteException_Exception  | CategoriaNoExisteException_Exception  | ActividadNoExisteException_Exception  exc1) {
	    			exc1.printStackTrace(); //solo pasaría con datos desactualizados  
				}
				break;
    		}
    		case "consulta":{
    			try {
		    		DtUsuario usr = (DtUsuario) request.getSession().getAttribute("usuario_logueado");
		    		
		    		Set<String> paqsInsc = new HashSet<String>(portP.obtenerPaquetesDisponibles(usr.getNickname(), request.getParameter("sal"), 1).getSetString());
					request.setAttribute("paqsInsc", paqsInsc); //relleno los paquetes
					
					Set<String> salsInsc =  new HashSet<String>(portD.obtenerDatosSalidasVigentes(request.getParameter("act")).getSetString());
					request.setAttribute("salsInsc", salsInsc); // relleno las salidas
					
					Set<String> actsInsc = new HashSet<String>(portD.obtenerDatosActividadesConfirmadasDpto(portD.obtenerDeptoActividad(request.getParameter("act"))).getSetString());
					request.setAttribute("actsInsc", actsInsc); // relleno las actividades por Dpto
					
					request.setAttribute("dpto", portD.obtenerDeptoActividad(request.getParameter("act")));
					request.setAttribute("act", request.getParameter("act"));
					request.setAttribute("cant", "1");
					request.setAttribute("sal", request.getParameter("sal"));
					request.getRequestDispatcher("/pages/inscripcionASalida.jsp").forward(request, response);
	    		} catch (UsuarioNoExisteException_Exception | DepartamentoNoExisteException_Exception |ActividadNoExisteException_Exception   exc1) {
	    			exc1.printStackTrace(); //solo pasaría con datos desactualizados  
				}
				break;
    		}
    		default: {
                System.out.println("Parametros incorrectos");
            }
    	}
    }
    
    protected void inscribir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
    	PublicadorUsuarioService service = new PublicadorUsuarioService();
        PublicadorUsuario port = service.getPublicadorUsuarioPort();
		
		DtUsuario usr = (DtUsuario) request.getSession().getAttribute("usuario_logueado");
		
		try {
			if (request.getParameter("paq") != null)
				port.ingresarDatosInscripcionPaq(usr.getNickname(), request.getParameter("sal"),  Integer.parseInt(request.getParameter("cant")), DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(ZonedDateTime.now())), request.getParameter("paq"));
			else
				port.ingresarDatosInscripcionPaq(usr.getNickname(), request.getParameter("sal"),  Integer.parseInt(request.getParameter("cant")), DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(ZonedDateTime.now())), "Inscripcion sin paquete");
			request.setAttribute("exito","inscripto"); //seteamos el exito
			request.getRequestDispatcher("/pages/inscripcionASalida.jsp").forward(request, response);
		} catch (NumberFormatException | DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SalidaNoExisteException_Exception  e) {
			e.printStackTrace(); //solo pasaría con datos desactualizados 
		} catch (UsuarioNoExisteException_Exception  e) {
			e.printStackTrace(); //solo pasaría con datos desactualizados
		} catch (PaqueteNoExisteException_Exception  e) {
			e.printStackTrace(); //solo pasaría con datos desactualizados
		} catch (InscripcionExisteException_Exception  e) {
			request.setAttribute("error","existe");
			errorInscripcion(request,response);
		} catch (LimiteSuperadoException_Exception  e) {
			request.setAttribute("error","lleno");
			errorInscripcion(request,response);
		}
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException  {
		String cambio = request.getParameter("cambio");
		if (cambio.equals("iniciar")) {
			request.getRequestDispatcher("/pages/inscripcionASalida.jsp").forward(request, response);
		}
		else {
			llenarComboboxes(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException  {
		inscribir(request,response);
	}

}
