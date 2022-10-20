package controllers;

import java.awt.Image;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.categoriaNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.usuarioNoExisteException;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTUsuario;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTTurista;
import logica.datatypes.DTSalida;


import java.util.Set;
import java.util.HashSet;

/**
 * Servlet implementation class DetalleUsuario
 */
@WebServlet("/DetalleUsuario")
public class DetalleUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetalleUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    protected void cargarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	Fabrica fact = Fabrica.getInstance();
    	IControladorUsuario ctrlUsr = fact.getIControladorUsuario();
    	IControladorDepartamento ctrlDpto = fact.getIControladorDepartamento();

    	
    	String nickname = request.getParameter("usuarioDetalleNickname");
    	DTUsuario usuario = null;
    	try {
    		usuario = ctrlUsr.obtenerUsuario(nickname);
    	} catch (usuarioNoExisteException usuarioNoExiste) {
			request.setAttribute("error", "usuario-no-existe"); 		
    	}
    	
    	if (usuario instanceof DTTurista) {	
    		
    		String[] salidas = null;
    		try {
    			salidas = ctrlUsr.obtenerSalidasInscripto(nickname);
    		} catch (usuarioNoExisteException usuarioNoExiste) {
				request.setAttribute("error", "usuario-no-existe"); 
    		}
    		    		    		
    		request.setAttribute("usuarioDetalle", usuario);
    		request.setAttribute("usuarioDetalleTipo", "turista");
    		request.setAttribute("usuarioDetalleSalidas", salidas);
    	}else {
    		request.setAttribute("usuarioDetalle", usuario);
    		request.setAttribute("usuarioDetalleTipo", "proveedor");
    	}
    	
		request.getRequestDispatcher("/pages/detalleUsuario.jsp").forward(request, response);
   	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		cargarUsuario(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}