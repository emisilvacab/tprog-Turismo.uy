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
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorPaquete;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTActividad;
import logica.datatypes.DTSalida;
import logica.datatypes.DTUsuario;

/**
 * Servlet implementation class AltaSalida
 */
@WebServlet("/altaSalida")
public class AltaSalida extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaSalida() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void errorSalida(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      	Fabrica fact = Fabrica.getInstance();
    	IControladorDepartamento cd = fact.getIControladorDepartamento();     
		request.setAttribute("dpto", request.getParameter("dpto"));
		request.setAttribute("act", request.getParameter("act"));
		request.setAttribute("nombreSal", request.getParameter("nombreSal"));
		request.setAttribute("fechaSal", request.getParameter("fechaSal"));
		request.setAttribute("horaSal", request.getParameter("horaSal"));
		request.setAttribute("lugarSal", request.getParameter("lugarSal"));
		request.setAttribute("cantTurSal", request.getParameter("cantTurSal"));
		request.setAttribute("imgSal", request.getParameter("imgSal"));
 		try {
			HashSet<DTActividad> acts = cd.obtenerDatosActividadesConfirmadasDpto(request.getParameter("dpto"));
			Set<String> res = new HashSet<String>();
			for (DTActividad act : acts) {
				res.add(act.getNombre());
			}
			request.setAttribute("actsSal", res);
			request.getRequestDispatcher("/pages/altaSalida.jsp").forward(request, response);
			
		} catch (departamentoNoExisteException exc1) {
			exc1.printStackTrace(); //solo pasaría con datos desactualizados  
		}
    }
    
    protected void llenarComboboxes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	Fabrica fact = Fabrica.getInstance();
	 	IControladorDepartamento cd = fact.getIControladorDepartamento(); 
	 	
	 	String cambio = request.getParameter("cambio");
	 	
	 	if(cambio.equals("dpto")) {
	 		try {
	 				request.setAttribute("cats",cd.obtenerCategorias());
		    		request.setAttribute("dptos",cd.obtenerDepartamentos());
					HashSet<DTActividad> acts = cd.obtenerDatosActividadesConfirmadasDpto(request.getParameter("dpto"));
					Set<String> res = new HashSet<String>();
					for (DTActividad act : acts) {
						res.add(act.getNombre());
					}
					request.setAttribute("dpto", request.getParameter("dpto"));
					request.setAttribute("actsSal", res);
					request.getRequestDispatcher("/pages/altaSalida.jsp").forward(request, response);
					
				} catch (departamentoNoExisteException exc1) {
					exc1.printStackTrace(); //solo pasaría con datos desactualizados  
				}
	 	}
	 	else if(cambio.equals("act")) {
	 		try {
				request.setAttribute("cats",cd.obtenerCategorias());
	    		request.setAttribute("dptos",cd.obtenerDepartamentos());
				
				request.setAttribute("dpto", request.getParameter("dpto"));
				HashSet<DTActividad> acts = cd.obtenerDatosActividadesConfirmadasDpto(request.getParameter("dpto"));
				Set<String> actsInsc = new HashSet<String>();
				for (DTActividad act : acts) {
					actsInsc.add(act.getNombre());
				}
				request.setAttribute("actsSal", actsInsc); // relleno las actividades por Dpto
				request.setAttribute("act", request.getParameter("act"));
				request.getRequestDispatcher("/pages/altaSalida.jsp").forward(request, response);
    		} catch (departamentoNoExisteException exc1) {
    			exc1.printStackTrace(); //solo pasaría con datos desactualizados  
			}
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
			request.getRequestDispatcher("/pages/altaSalida.jsp").forward(request, response);
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
		//doGet(request, response);
		Fabrica fact = Fabrica.getInstance();
    	IControladorDepartamento cd = fact.getIControladorDepartamento();  
		request.setAttribute("dptos",cd.obtenerDepartamentos());
		
		GregorianCalendar fechaAlta = GregorianCalendar.from(ZonedDateTime.now());
		
		String fecha = request.getParameter("fechaSal");
		String[] partsF = fecha.split("-");
		GregorianCalendar fechaSal = new GregorianCalendar(Integer.parseInt(partsF[0]), Integer.parseInt(partsF[1]) - 1, Integer.parseInt(partsF[2]));
		
		String hora = request.getParameter("horaSal");
		String[] partsH = hora.split(":");
		int horaSal = Integer.parseInt(partsH[0])*100 + Integer.parseInt(partsH[1]);
				
		try {
			boolean existe = cd.ingresarDatosSalida(request.getParameter("nombreSal"), Integer.parseInt(request.getParameter("cantTurSal")), fechaAlta, fechaSal, horaSal, request.getParameter("lugarSal"), request.getParameter("dpto"), request.getParameter("act"), null);
			if (existe) {
				request.setAttribute("error", "existe");
				errorSalida(request,response);
			}
			else
				request.getRequestDispatcher("index.jsp").forward(request, response);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (departamentoNoExisteException e) {
			e.printStackTrace();
		} catch (actividadNoExisteException e) {
			e.printStackTrace();
		}
	}

}
