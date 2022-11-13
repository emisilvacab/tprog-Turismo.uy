package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EstadoSesion;
import publicadores.DtUsuario;
import publicadores.IngresoInvalidoException_Exception;
import publicadores.PublicadorUsuario;
import publicadores.PublicadorUsuarioService;

/**
 * Servlet implementation class Sesion
 */
@WebServlet("/log")
public class Sesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sesion() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PublicadorUsuarioService service = new PublicadorUsuarioService();
        PublicadorUsuario port = service.getPublicadorUsuarioPort();

    	try {
			DtUsuario usr = port.iniciarSesion(request.getParameter("id_logging"),request.getParameter("password"));
			if (usr.getClass() == DtProveedor.class) {
				throw new Exception("No hay niingun turista con ese nickname o correo");
			}
			request.getSession().setAttribute("estado_sesion", EstadoSesion.LOGIN_CORRECTO);
    		request.getSession().setAttribute("usuario_logueado", usr);
    		request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (IngresoInvalidoException_Exception e) {
			request.getSession().setAttribute("estado_sesion", EstadoSesion.LOGIN_INCORRECTO);
    		request.getRequestDispatcher("/pages/IniciarSesion.jsp").forward(request, response);
		} 
    	

    }
    
    protected void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getSession().setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
    	request.getSession().setAttribute("usuario_logueado", null);
    	request.getRequestDispatcher("index.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("iniciar");
		if (accion != null && accion.equals("si")) { //si iniciar es si entonces vamos a iniciar Sesion 
			request.getRequestDispatcher("/pages/IniciarSesion.jsp").forward(request, response); 
		}
		else
			cerrarSesion(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id_logging");
		if (id != null)
			iniciarSesion(request,response);
	}

}
