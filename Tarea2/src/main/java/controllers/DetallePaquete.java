package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicadores.DtColecciones;
import publicadores.DtPaquete;
import publicadores.PaqueteNoExisteException_Exception;
import publicadores.PublicadorPaquete;
import publicadores.PublicadorPaqueteService;


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
    	//Fabrica fact = Fabrica.getInstance();
    	//IControladorPaquete ctrlPaq = fact.getIControladorPaquete();
    	
    	PublicadorPaqueteService service = new PublicadorPaqueteService();
        PublicadorPaquete port = service.getPublicadorPaquetePort();
    	
    	String nombre = (String) request.getParameter("detallePaqueteNombre");
    	
    	DtPaquete paquete = null;    
    	try {
			paquete = port.obtenerDatosPaquete(nombre);
		} catch (PaqueteNoExisteException_Exception paqueteNoExiste) {
			request.setAttribute("error", "paquete-no-existe");
		}		
    	
    	DtColecciones actividades = new DtColecciones();
    	
    	try {
			actividades = port.obtenerActividadesPaquete(nombre);
    	} catch (PaqueteNoExisteException_Exception paqueteNoExiste) {
			request.setAttribute("error", "paquete-no-existe");
		}	
    	
    	DtColecciones categorias = new DtColecciones();
    	
    	try {
			categorias = port.obtenerCategoriasPaquete(nombre);
    	} catch (PaqueteNoExisteException_Exception paqueteNoExiste) {
			request.setAttribute("error", "paquete-no-existe");
		}
    	
		request.setAttribute("detallePaqueteCategorias", categorias);
		request.setAttribute("detallePaqueteActividades", actividades);
    	request.setAttribute("detallePaquete", paquete);
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
