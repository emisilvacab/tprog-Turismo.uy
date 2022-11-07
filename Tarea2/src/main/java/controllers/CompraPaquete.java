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

import publicadores.CompraExisteException_Exception;
import publicadores.DtUsuario;
import publicadores.PaqueteNoExisteException_Exception;
import publicadores.PublicadorPaquete;
import publicadores.PublicadorPaqueteService;
import publicadores.UsuarioNoExisteException_Exception;

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
    	PublicadorPaqueteService serviceP = new PublicadorPaqueteService();
        PublicadorPaquete portP = serviceP.getPublicadorPaquetePort();
    	
		Set<String> paqsCompra = new HashSet<String>(portP.obtenerPaquetesConActividades().getSetString());
		request.setAttribute("paqsCompra",paqsCompra); //volvemos a cargar los paquetes 
		
		DtUsuario usr = (DtUsuario) request.getSession().getAttribute("usuario_logueado");
    	 
    	try {
    		portP.comprarPaquete(usr.getNickname(), request.getParameter("paq"), DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(ZonedDateTime.now())), Integer.parseInt(request.getParameter("cant")));
			request.setAttribute("exito","comprado"); //seteamos el exito
			request.getRequestDispatcher("/pages/comprarPaquete.jsp").forward(request, response);
    	} catch (UsuarioNoExisteException_Exception e) {
    		e.printStackTrace(); //solo pasaría con datos desactualizados
		} catch (PaqueteNoExisteException_Exception e) {
			e.printStackTrace(); //solo pasaría con datos desactualizados
		} catch (CompraExisteException_Exception e) {
			request.setAttribute("paq",request.getParameter("paq")); //seteamos el paquete y la cantidad que se mandaron
			request.setAttribute("cant",request.getParameter("cant"));
			request.setAttribute("error","existe"); //seteamos el error
			request.getRequestDispatcher("/pages/comprarPaquete.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublicadorPaqueteService serviceP = new PublicadorPaqueteService();
        PublicadorPaquete portP = serviceP.getPublicadorPaquetePort(); 
		
		Set<String> paqsCompra = new HashSet<String>(portP.obtenerPaquetesConActividades().getSetString());
		
		request.setAttribute("paqsCompra",paqsCompra);
		request.getRequestDispatcher("/pages/comprarPaquete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		comprar(request,response);
	}

}
