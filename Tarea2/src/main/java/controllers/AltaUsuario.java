package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Calendar;
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
import logica.datatypes.DTProveedor;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;

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
    
    protected void errorAltaUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setAttribute("nickname", request.getParameter("nickname"));
    	request.setAttribute("nombre", request.getParameter("nombrePersona"));
    	request.setAttribute("apellido", request.getParameter("apellido"));
    	request.setAttribute("correoPersona", request.getParameter("correoPersona"));
    	request.setAttribute("nacimientoPersona", request.getParameter("nacimientoPersona"));
    	request.setAttribute("contrasena", request.getParameter("contrasena"));
    	request.setAttribute("imgPersona", request.getParameter("imgPersona"));
    	request.setAttribute("linkProv", request.getParameter("linkProv"));
    	request.setAttribute("descripcionProv", request.getParameter("descripcionProv"));
    	request.setAttribute("nacionalidad", request.getParameter("nacionalidad"));
    	
    	String btnTurista = request.getParameter("turistaButton");
    	if (btnTurista != null) {
    		request.setAttribute("nacionalidad", request.getParameter("nacionalidad"));
    	}else {
        	request.setAttribute("linkProv", request.getParameter("linkProv"));
        	request.setAttribute("descripcionProv", request.getParameter("descripcionProv"));
    	}
		request.getRequestDispatcher("/pages/altaUsuario.jsp").forward(request, response);
    }
   
    
    protected void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Fabrica fact = Fabrica.getInstance();
    	IControladorUsuario cu = fact.getIControladorUsuario();
    	IControladorDepartamento cd = fact.getIControladorDepartamento(); 
    	request.setAttribute("cats",cd.obtenerCategorias());

    	String[] parts =  request.getParameter("nacimientoPersona").split("-");
    	GregorianCalendar fecha = new GregorianCalendar(Integer.parseInt(parts[0]),Integer.parseInt(parts[1])-1,Integer.parseInt(parts[2]));
    	String nickname = request.getParameter("nickname");
    	String nombre = request.getParameter("nombrePersona");
    	String apellido = request.getParameter("apellido");
    	String correo = request.getParameter("correoPersona");
    	String contrasena = request.getParameter("contrasena");
    	String imagen = request.getParameter("imgPersona");
    	DTUsuario nuevo;
    	System.out.println(request.getAttribute("tipo"));
    	System.out.println(request.getParameter("turistaButton"));
    	System.out.println("descripcion: " + request.getParameter("descripcionProv"));
		System.out.println("link: " + request.getParameter("linkProv"));


    	if (request.getParameter("btnAceptarTur") != null) {
    		request.setAttribute("tipo", "turista");
    		String nacionalidad = request.getParameter("nacionalidad");
    		nuevo = new DTTurista(nickname, nombre, apellido, correo, fecha, contrasena, imagen, nacionalidad);
    		System.out.println("es turista");
    		System.out.println("nacionalidad: " + nacionalidad);
    	}else {
    		request.setAttribute("tipo", "proveedor");
    		String descripcion = request.getParameter("descripcionProv");
    		String link = request.getParameter("linkProv");
    		nuevo = new DTProveedor(nickname, nombre, apellido,correo,fecha,contrasena,imagen, descripcion, link);
    		System.out.println("es proveedor");
    		System.out.println("link: " + link);
    	}
    	//Image img = (Image) request.getParameter("imgPersona"); 
    	//nuevo.setFigura(img);
    	try {
			cu.altaUsuario(nuevo);
			request.getRequestDispatcher("/pages/IniciarSesion.jsp").forward(request, response);
		} catch (UsuarioRepetidoException e) {
			if (e.getMessage().contains("correo")) {
				request.setAttribute("error", "usuario-repetido-correo");
			}else {
				request.setAttribute("error", "usuario-repetido-nickname");
			}
			errorAltaUsuario(request, response);
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
