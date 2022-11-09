package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicadores.PublicadorPaquete;
import publicadores.PublicadorPaqueteService;
import publicadores.DtColecciones;

/**
 * Servlet implementation class ListarUsuarios
 */
@WebServlet("/ListarPaquetes")
public class ListarPaquetes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarPaquetes() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void cargarPaquetes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	PublicadorPaqueteService service = new PublicadorPaqueteService();
        PublicadorPaquete port = service.getPublicadorPaquetePort();
        
        DtColecciones paquetes = port.obtenerPaquetesAll();
    	
    	request.setAttribute("listarPaquetes", paquetes);
		request.getRequestDispatcher("/pages/listarPaquetes.jsp").forward(request, response);

    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		cargarPaquetes(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
