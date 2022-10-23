package controllers;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.UsuarioRepetidoException;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTTurista;

/**
 * Servlet implementation class AltaUsuario
 */
@WebServlet("/registrar")
public class AltaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Fabrica fact = Fabrica.getInstance();
    	IControladorUsuario cu = fact.getIControladorUsuario();
    	IControladorDepartamento cd = fact.getIControladorDepartamento(); 
    	request.setAttribute("cats",cd.obtenerCategorias());

    	String[] parts =  request.getParameter("nacimientoPersona").split("-");
    	String part1 = parts[0]; 
    	GregorianCalendar fecha = new GregorianCalendar(Integer.parseInt(parts[0]),Integer.parseInt(parts[1])-1,Integer.parseInt(parts[2]));
    	
    	DTTurista nuevo = new DTTurista(request.getParameter("nickname"), request.getParameter("nombrePersona"), request.getParameter("apellido"), request.getParameter("correoPersona"), fecha,request.getParameter("contrasena"), null/* imagen */, request.getParameter("nacionalidad"));
    	//Image img = (Image) request.getParameter("imgPersona"); 
    	//nuevo.setFigura(img);
    	try {
			cu.altaUsuario(nuevo);
			request.getRequestDispatcher("/pages/IniciarSesion.jsp").forward(request, response);
		} catch (UsuarioRepetidoException e) {
			request.setAttribute("error", "usuario-repetido");
			request.getRequestDispatcher("/pages/altaUsuario.jsp").forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Fabrica fact = Fabrica.getInstance();
    	IControladorDepartamento cd = fact.getIControladorDepartamento(); 
    	request.setAttribute("cats",cd.obtenerCategorias());
		request.getRequestDispatcher("/pages/altaUsuario.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		registrarUsuario(request,response);
	}

}
