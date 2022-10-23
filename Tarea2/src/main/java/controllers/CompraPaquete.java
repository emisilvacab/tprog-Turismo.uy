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

import excepciones.compraExisteException;
import excepciones.paqueteNoExisteException;
import excepciones.usuarioNoExisteException;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorPaquete;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTUsuario;

/**
 * Servlet implementation class compraPaquete
 */
@WebServlet("/comprar")
public class CompraPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompraPaquete() {
        super();
    }
     
    protected void comprar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Fabrica fact = Fabrica.getInstance();
    	IControladorPaquete cPaq = fact.getIControladorPaquete();
    	IControladorDepartamento cDpto = fact.getIControladorDepartamento(); 
    	
    	request.setAttribute("cats",cDpto.obtenerCategorias());
		request.setAttribute("dptos",cDpto.obtenerDepartamentos());
		
		HashSet<DTPaquete> paqs = (HashSet<DTPaquete>) cPaq.obtenerPaquetesConActividades();
		Set<String> paqsCompra = new HashSet<String>();
		for (DTPaquete paq : paqs) {
			paqsCompra.add(paq.getNombre());
		}
		request.setAttribute("paqsCompra",paqsCompra); //volvemos a cargar los paquetes 
		
		DTUsuario usr = (DTUsuario) request.getSession().getAttribute("usuario_logueado");
    	 
    	try {
			cPaq.comprarPaquete(usr.getNickname(), request.getParameter("paq"), GregorianCalendar.from(ZonedDateTime.now()), Integer.parseInt(request.getParameter("cant")));
			request.setAttribute("exito","comprado"); //seteamos el exito
			request.getRequestDispatcher("/pages/comprarPaquete.jsp").forward(request, response);
    	} catch (usuarioNoExisteException e) {
    		e.printStackTrace(); //solo pasaría con datos desactualizados
		} catch (paqueteNoExisteException e) {
			e.printStackTrace(); //solo pasaría con datos desactualizados
		} catch (compraExisteException e) {
			request.setAttribute("paq",request.getParameter("paq")); //seteamos el paquete y la cantidad que se mandaron
			request.setAttribute("cant",request.getParameter("cant"));
			request.setAttribute("error","existe"); //seteamos el error
			request.getRequestDispatcher("/pages/comprarPaquete.jsp").forward(request, response);
		}
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Fabrica fact = Fabrica.getInstance();
		IControladorDepartamento cDpto = fact.getIControladorDepartamento(); 
		IControladorPaquete cPaq = fact.getIControladorPaquete(); 
		
		HashSet<DTPaquete> paqs = (HashSet<DTPaquete>) cPaq.obtenerPaquetesConActividades();
		Set<String> paqsCompra = new HashSet<String>();
		for (DTPaquete paq : paqs) {
			paqsCompra.add(paq.getNombre());
		}
		
		request.setAttribute("paqsCompra",paqsCompra);
		request.setAttribute("cats",cDpto.obtenerCategorias());
		request.setAttribute("dptos",cDpto.obtenerDepartamentos());
		request.getRequestDispatcher("/pages/comprarPaquete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		comprar(request,response);
	}

}
