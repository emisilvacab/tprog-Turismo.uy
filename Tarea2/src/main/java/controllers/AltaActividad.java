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
import javax.swing.JOptionPane;

import excepciones.departamentoNoExisteException;
import excepciones.proveedorNoExisteException;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorPaquete;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTActividad;
import logica.datatypes.DTUsuario;

/**
 * Servlet implementation class AltaActividad
 */
@WebServlet("/altaActividad")
public class AltaActividad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaActividad() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void errorActividad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      	Fabrica fact = Fabrica.getInstance();
    	IControladorDepartamento cd = fact.getIControladorDepartamento();
		request.setAttribute("depAct", request.getParameter("depAct"));
		request.setAttribute("nombreAct", request.getParameter("nombreAct"));
		request.setAttribute("descripcionAct", request.getParameter("descripcionAct"));
		request.setAttribute("durAct", request.getParameter("durAct"));
		request.setAttribute("costoAct", request.getParameter("costoAct"));
		request.setAttribute("ciudadAct", request.getParameter("ciudadAct"));
		request.setAttribute("imgAct", request.getParameter("imgAct"));
		request.setAttribute("catsAct", request.getParameter("catsAct"));
		request.setAttribute("dptos",cd.obtenerDepartamentos());
		request.setAttribute("cats",cd.obtenerCategorias());
		request.getRequestDispatcher("/pages/altaActividad.jsp").forward(request, response);

    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Fabrica fact = Fabrica.getInstance();
    	IControladorDepartamento cd = fact.getIControladorDepartamento(); 
    	request.setAttribute("cats",cd.obtenerCategorias());
    	request.setAttribute("dptos",cd.obtenerDepartamentos());
		request.getRequestDispatcher("/pages/altaActividad.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Fabrica fact = Fabrica.getInstance();
    	IControladorDepartamento cd = fact.getIControladorDepartamento();
    	
    	request.setAttribute("cats",cd.obtenerCategorias());
    	request.setAttribute("dptos",cd.obtenerDepartamentos());
    	
		DTUsuario usr = (DTUsuario) request.getSession().getAttribute("usuario_logueado");
		String nicknameProv = usr.getNickname();//Sacar el nickname del proveedor que tiene la sesion inciada
    	GregorianCalendar fechaAct = GregorianCalendar.from(ZonedDateTime.now());//Fecha actual
		HashSet<String> categorias = new HashSet<String>();//categorias falta hacer multiselect en pagina
		String[] cates = request.getParameterValues("catsAct");
		for (String cat: cates) {
			cat = cat.replace("+"," ");
			categorias.add(cat);
			System.out.println(cat);
		}
		//String catSeleccionadas = request.getParameter("catsAct");

    	try {
			boolean existe = cd.ingresarDatosActividad(request.getParameter("nombreAct"), request.getParameter("descripcionAct"), Integer.parseInt(request.getParameter("durAct")), Float.parseFloat(request.getParameter("costoAct")), request.getParameter("ciudadAct"), fechaAct, nicknameProv, request.getParameter("depAct"), categorias);
			if (existe) {
				request.setAttribute("error", "actividad-repetida");
				errorActividad(request,response);
			}
			else
				request.getRequestDispatcher("index.jsp").forward(request, response);
    	} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (proveedorNoExisteException e) {
			e.printStackTrace();
		} catch (departamentoNoExisteException e) {
			e.printStackTrace();
		}
    	//Falta asociar categorias
    
	}

}























