package controllers;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.paqueteNoExisteException;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorPaquete;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTActividad;


/**
 * Servlet implementation class DetallePaquete
 */
@WebServlet("/DetallePaquete")
public class DetallePaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetallePaquete() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void cargarPaquete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	Fabrica fact = Fabrica.getInstance();
    	IControladorPaquete ctrlPaq = fact.getIControladorPaquete();
    	IControladorDepartamento ctrlDep = fact.getIControladorDepartamento();
    	
    	String nombre = (String) request.getParameter("detallePaqueteNombre");
    	
    	DTPaquete paquete = null;
    	try {
    		paquete = ctrlPaq.obtenerDatosPaquete(nombre);
    		
    	} catch (paqueteNoExisteException paqueteNoExiste) {
			request.setAttribute("error", "paquete-no-existe");
    	}
    	
    	HashSet<DTActividad> actConfirmadas = new HashSet<DTActividad>();
    	try {
    		actConfirmadas = ctrlPaq.obtenerActividadesPaquete(nombre);
    	} catch (paqueteNoExisteException paqueteNoExiste) {
    		
    	}
    	
    	HashSet<String> categorias = new HashSet<String>();
    	try {
    		categorias = ctrlPaq.obtenerCategoriasPaquete(nombre);
    	} catch (paqueteNoExisteException paqueteNoExiste) {
    		
    	}

    	request.setAttribute("dptos", ctrlDep.obtenerDepartamentos());
		request.setAttribute("cats", ctrlDep.obtenerCategorias());
    	
		request.setAttribute("detallePaqueteCategorias",categorias);
		request.setAttribute("detallePaqueteActividades",actConfirmadas);
    	request.setAttribute("detallePaquete",paquete);
		request.getRequestDispatcher("/pages/detallePaquete.jsp").forward(request, response);

    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		cargarPaquete(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
