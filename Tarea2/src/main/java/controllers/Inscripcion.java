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

import excepciones.actividadNoExisteException;
import excepciones.categoriaNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.inscripcionExisteException;
import excepciones.limiteSuperadoException;
import excepciones.paqueteNoExisteException;
import excepciones.salidaNoExisteException;
import excepciones.usuarioNoExisteException;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorPaquete;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTActividad;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTSalida;
import logica.datatypes.DTUsuario;

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
    protected void errorInscripcion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Fabrica fact = Fabrica.getInstance();
    	IControladorDepartamento cDpto = fact.getIControladorDepartamento(); 
    	IControladorPaquete cPaq = fact.getIControladorPaquete(); 
    	
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
			HashSet<DTActividad> acts;
			if (request.getParameter("dpto") != null)
				acts = (HashSet<DTActividad>) cDpto.obtenerDatosActividadesConfirmadasDpto(request.getParameter("dpto"));
			else 
				acts = (HashSet<DTActividad>) cDpto.obtenerDatosActividadesConfirmadasCat(request.getParameter("cat"));
			Set<String> res = new HashSet<String>();
			for (DTActividad act : acts) {
				res.add(act.getNombre());
			}
			request.setAttribute("actsInsc", res);
		} catch (departamentoNoExisteException | categoriaNoExisteException exc1) {
			exc1.printStackTrace(); //solo pasaría con datos desactualizados  
		}
		try { //cargo salidas
			HashSet<DTSalida> sals;
			sals = (HashSet<DTSalida>) cDpto.obtenerDatosSalidasVigentes(request.getParameter("act"));
			Set<String> res = new HashSet<String>();
			for (DTSalida sal : sals) {
				res.add(sal.getNombre());
			}
			request.setAttribute("salsInsc", res);
		} catch (actividadNoExisteException e) {
			e.printStackTrace(); //solo pasaría con datos desactualizados  
		}
		try { //cargo paquetes
			DTUsuario usr = (DTUsuario) request.getSession().getAttribute("usuario_logueado");
			Set<DTPaquete> paqs;
			paqs = cPaq.obtenerPaquetesDisponibles(usr.getNickname(), request.getParameter("sal"), Integer.parseInt(request.getParameter("cant")));
			Set<String> res = new HashSet<String>();
			for (DTPaquete paq : paqs) {
				res.add(paq.getNombre());
			}
			request.setAttribute("paqsInsc", res);
		} catch (usuarioNoExisteException e) {
			e.printStackTrace(); //solo pasaría con datos desactualizados 
		}
		request.getRequestDispatcher("/pages/inscripcionASalida.jsp").forward(request, response);
    }
    
    protected void llenarComboboxes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Fabrica fact = Fabrica.getInstance();
    	IControladorDepartamento cDpto = fact.getIControladorDepartamento(); 
    	IControladorPaquete cPaq = fact.getIControladorPaquete(); 
    	
    	String cambio = request.getParameter("cambio");
    	switch(cambio) {
    		case "dpto": {
    			try {
    				request.setAttribute("cats",cDpto.obtenerCategorias());
    	    		request.setAttribute("dptos",cDpto.obtenerDepartamentos());
					HashSet<DTActividad> acts = (HashSet<DTActividad>) cDpto.obtenerDatosActividadesConfirmadasDpto(request.getParameter("dpto"));
					Set<String> res = new HashSet<String>();
					for (DTActividad act : acts) {
						res.add(act.getNombre());
					}
					request.setAttribute("dpto", request.getParameter("dpto"));
					request.setAttribute("actsInsc", res);
					request.getRequestDispatcher("/pages/inscripcionASalida.jsp").forward(request, response);
					
				} catch (departamentoNoExisteException exc1) {
					exc1.printStackTrace(); //solo pasaría con datos desactualizados  
				}
    			break;
    		}
    		case "cat": {
    			try {
    				request.setAttribute("cats",cDpto.obtenerCategorias());
    	    		request.setAttribute("dptos",cDpto.obtenerDepartamentos());
					
    	    		HashSet<DTActividad> acts = (HashSet<DTActividad>) cDpto.obtenerDatosActividadesConfirmadasCat(request.getParameter("cat"));
					Set<String> res = new HashSet<String>();
					for (DTActividad act : acts) {
						res.add(act.getNombre());
					}
					request.setAttribute("cat", request.getParameter("cat"));
					request.setAttribute("actsInsc", res);
					request.getRequestDispatcher("/pages/inscripcionASalida.jsp").forward(request, response);
					
				} catch (categoriaNoExisteException exc1) {
					exc1.printStackTrace(); //solo pasaría con datos desactualizados  
				}
    			break;
    		}
    		case "act": {
				try {
					request.setAttribute("cats",cDpto.obtenerCategorias());
    	    		request.setAttribute("dptos",cDpto.obtenerDepartamentos());
    	    		
    	    		HashSet<DTSalida> sals = (HashSet<DTSalida>) cDpto.obtenerDatosSalidasVigentes(request.getParameter("act"));
					Set<String> salsInsc = new HashSet<String>();
					for (DTSalida sal : sals) {
						salsInsc.add(sal.getNombre());
					} 
					request.setAttribute("salsInsc", salsInsc); // relleno las salidas
					
					if (request.getParameter("dpto") != null) { 
						request.setAttribute("dpto", request.getParameter("dpto"));
						HashSet<DTActividad> acts = (HashSet<DTActividad>) cDpto.obtenerDatosActividadesConfirmadasDpto(request.getParameter("dpto"));
						Set<String> actsInsc = new HashSet<String>();
						for (DTActividad act : acts) {
							actsInsc.add(act.getNombre());
						}
						request.setAttribute("actsInsc", actsInsc); // relleno las actividades por Dpto
					}
					else {
						request.setAttribute("cat", request.getParameter("cat"));
						HashSet<DTActividad> acts = (HashSet<DTActividad>) cDpto.obtenerDatosActividadesConfirmadasCat(request.getParameter("cat"));
						Set<String> actsInsc = new HashSet<String>();
						for (DTActividad act : acts) {
							actsInsc.add(act.getNombre());
						}
						request.setAttribute("actsInsc", actsInsc); // relleno las actividades por cat
					}
					request.setAttribute("act", request.getParameter("act"));
					request.getRequestDispatcher("/pages/inscripcionASalida.jsp").forward(request, response);
	    		} catch (actividadNoExisteException | departamentoNoExisteException | categoriaNoExisteException exc1) {
	    			exc1.printStackTrace(); //solo pasaría con datos desactualizados  
				}
				break;
    		}
    		case "sal": {
    			try {
					request.setAttribute("cats",cDpto.obtenerCategorias());
    	    		request.setAttribute("dptos",cDpto.obtenerDepartamentos());
    	    		
    	    		DTUsuario usr = (DTUsuario) request.getSession().getAttribute("usuario_logueado");
    	    		Set<DTPaquete> paqs = cPaq.obtenerPaquetesDisponibles(usr.getNickname(), request.getParameter("sal"), Integer.parseInt(request.getParameter("cant")));
					Set<String> paqsInsc = new HashSet<String>();
					for (DTPaquete paq : paqs) {
						paqsInsc.add(paq.getNombre());
					}
					request.setAttribute("paqsInsc", paqsInsc); //relleno los paquetes
					
					HashSet<DTSalida> sals = (HashSet<DTSalida>) cDpto.obtenerDatosSalidasVigentes(request.getParameter("act"));
					Set<String> salsInsc = new HashSet<String>();
					for (DTSalida sal : sals) {
						salsInsc.add(sal.getNombre());
					} 
					request.setAttribute("salsInsc", salsInsc); // relleno las salidas
					
					if (request.getParameter("dpto") != null) { 
						request.setAttribute("dpto", request.getParameter("dpto"));
						HashSet<DTActividad> acts = (HashSet<DTActividad>) cDpto.obtenerDatosActividadesConfirmadasDpto(request.getParameter("dpto"));
						Set<String> actsInsc = new HashSet<String>();
						for (DTActividad act : acts) {
							actsInsc.add(act.getNombre());
						}
						request.setAttribute("actsInsc", actsInsc); // relleno las actividades por Dpto
					}
					else {
						request.setAttribute("cat", request.getParameter("cat"));
						HashSet<DTActividad> acts = (HashSet<DTActividad>) cDpto.obtenerDatosActividadesConfirmadasCat(request.getParameter("cat"));
						Set<String> actsInsc = new HashSet<String>();
						for (DTActividad act : acts) {
							actsInsc.add(act.getNombre());
						}
						request.setAttribute("actsInsc", actsInsc); // relleno las actividades por cat
					}
					
					request.setAttribute("act", request.getParameter("act"));
					request.setAttribute("cant", request.getParameter("cant"));
					request.setAttribute("sal", request.getParameter("sal"));
					request.getRequestDispatcher("/pages/inscripcionASalida.jsp").forward(request, response);
	    		} catch (usuarioNoExisteException | departamentoNoExisteException | categoriaNoExisteException | actividadNoExisteException exc1) {
	    			exc1.printStackTrace(); //solo pasaría con datos desactualizados  
				}
				break;
    		}
    		default: {
                System.out.println("Parametros incorrectos");
            }
    	}
    }
    
    protected void inscribir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Fabrica fact = Fabrica.getInstance();
    	IControladorDepartamento cDpto = fact.getIControladorDepartamento(); 
    	IControladorUsuario cUsu = fact.getIControladorUsuario(); 
    	
    	request.setAttribute("cats",cDpto.obtenerCategorias());
		request.setAttribute("dptos",cDpto.obtenerDepartamentos());
		
		DTUsuario usr = (DTUsuario) request.getSession().getAttribute("usuario_logueado");
		
		try {
			cUsu.ingresarDatosInscripcionPaq(usr.getNickname(), request.getParameter("sal"),  Integer.parseInt(request.getParameter("cant")), GregorianCalendar.from(ZonedDateTime.now()), request.getParameter("paq"));
			request.setAttribute("exito","inscripto"); //seteamos el exito
			request.getRequestDispatcher("/pages/inscripcionASalida.jsp").forward(request, response);
		} catch (salidaNoExisteException e) {
			e.printStackTrace(); //solo pasaría con datos desactualizados 
		} catch (usuarioNoExisteException e) {
			e.printStackTrace(); //solo pasaría con datos desactualizados
		} catch (paqueteNoExisteException e) {
			e.printStackTrace(); //solo pasaría con datos desactualizados
		} catch (inscripcionExisteException e) {
			request.setAttribute("error","existe");
			errorInscripcion(request,response);
			
		} catch (limiteSuperadoException e) {
			request.setAttribute("error","lleno");
			errorInscripcion(request,response);
		}
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cambio = request.getParameter("cambio");
		if (cambio.equals("iniciar")) {
			Fabrica fact = Fabrica.getInstance();
    		IControladorDepartamento cDpto = fact.getIControladorDepartamento(); 
    		request.setAttribute("cats",cDpto.obtenerCategorias());
    		request.setAttribute("dptos",cDpto.obtenerDepartamentos());
			request.getRequestDispatcher("/pages/inscripcionASalida.jsp").forward(request, response);
		}
		else {
			llenarComboboxes(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		inscribir(request,response);
	}

}
