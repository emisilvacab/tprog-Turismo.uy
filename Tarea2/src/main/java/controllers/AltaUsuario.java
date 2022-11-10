package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import excepciones.UsuarioRepetidoException;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTProveedor;
import publicadores.DtProveedor;
import publicadores.DtTurista;
import publicadores.DtUsuario;
import publicadores.PublicadorUsuario;
import publicadores.PublicadorUsuarioService;
import publicadores.UsuarioRepetidoException_Exception;

/**
 * Servlet implementation class AltaUsuario
 */
@WebServlet("/registrar")  @MultipartConfig
public class AltaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected String guardarImagen(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	String pathFiles = this.getServletContext().getRealPath("/resources/img");
    	
    	File uploads = new File(pathFiles);
    	
    	Part part = request.getPart("imgPersona"); //obtengo el part que se mandó por el request
		Path path = Paths.get(part.getSubmittedFileName()); 
		
		String fileName = path.getFileName().toString(); //obtengo el nombre del archivo
		String extension = fileName.substring(fileName.lastIndexOf('.'));
		String nombre  = request.getParameter("nickname"); //Le pongo el nickname de la persona a la imagen
		String save = nombre + "." + extension;
		
		InputStream input = part.getInputStream(); //obtengo el archivo
		File file = new File(uploads, save);
		int num = 0;
		while (file.exists()) { //le agrega 1 al numero del nombre si ya existe
			save = nombre + (num++) + "." + extension;
			file = new File(uploads, save);
		}
		Files.copy(input, file.toPath()); //guardo el archivo en la carpeta img
		
		return save;
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
       
    	String[] parts =  request.getParameter("nacimientoPersona").split("-");
    	GregorianCalendar fecha = new GregorianCalendar(Integer.parseInt(parts[0]),Integer.parseInt(parts[1])-1,Integer.parseInt(parts[2]));
    	String nickname = request.getParameter("nickname");
    	String nombre = request.getParameter("nombrePersona");
    	String apellido = request.getParameter("apellido");
    	String correo = request.getParameter("correoPersona");
    	String contrasena = request.getParameter("contrasena");
		String linkImagen = null;
		String nuevoNombre = null;
		Part part = request.getPart("imgPersona");
		if(part.getContentType().contains("image") && part.getInputStream() != null) { //Solo guardo la imagen si la seteó el usuario
			nuevoNombre = guardarImagen(request,response);
			linkImagen = "resources/img/" + nuevoNombre;
		}else {
			linkImagen = "null";
		}
		
    	if (request.getParameter("btnAceptarTur") != null) {
    		request.setAttribute("tipo", "turista");
    		String nacionalidad = request.getParameter("nacionalidad");
    		DtTurista nuevo = setAttributesTur(nickname, nombre, apellido, correo, fecha, contrasena, linkImagen);
    		nuevo.setNacionalidad(nacionalidad);
    		enviarTurista(nuevo, request, response, part, nuevoNombre);
    	}else {
    		request.setAttribute("tipo", "proveedor");
    		String descripcion = request.getParameter("descripcionProv");
    		String link = request.getParameter("linkProv");
    		DtProveedor nuevo = (DtProveedor) setAttributesProv(nickname, nombre, apellido, correo, fecha, contrasena, linkImagen); 
    		if (link == null)
    			link = "null";
    		nuevo.setLink(link);
    		enviarProveedor(nuevo, request, response, part, nuevoNombre);
    		
    	}
    	
    

	}
    //dos funciones iguales porque no andan los casteos
    
    private void enviarTurista(DtTurista nuevo, HttpServletRequest request, HttpServletResponse response, Part part, String nuevoNombre) throws ServletException, IOException {
    	PublicadorUsuarioService service = new PublicadorUsuarioService();
        PublicadorUsuario port = service.getPublicadorUsuarioPort();
    	try {
    		port.altaTurista(nuevo);	
			request.getRequestDispatcher("/pages/IniciarSesion.jsp").forward(request, response);
		} catch (UsuarioRepetidoException_Exception e) {
			if(part.getContentType().contains("image") && part.getInputStream() != null) { 
				File file = new File(new File(this.getServletContext().getRealPath("/resources/img")), nuevoNombre);
				file.delete();
			}
			if (e.getMessage().contains("correo")) {
				request.setAttribute("error", "usuario-repetido-correo");
			}else {
				request.setAttribute("error", "usuario-repetido-nickname");
			}
			errorAltaUsuario(request, response);
		}
    	
    }
    
    private void enviarProveedor(DtProveedor nuevo, HttpServletRequest request, HttpServletResponse response, Part part, String nuevoNombre) throws ServletException, IOException {
    	PublicadorUsuarioService service = new PublicadorUsuarioService();
        PublicadorUsuario port = service.getPublicadorUsuarioPort();
    	try {
    		port.altaProveedor(nuevo);	
			request.getRequestDispatcher("/pages/IniciarSesion.jsp").forward(request, response);
		} catch (UsuarioRepetidoException_Exception e) {
			if(part.getContentType().contains("image") && part.getInputStream() != null) { 
				File file = new File(new File(this.getServletContext().getRealPath("/resources/img")), nuevoNombre);
				file.delete();
			}
			if (e.getMessage().contains("correo")) {
				request.setAttribute("error", "usuario-repetido-correo");
			}else {
				request.setAttribute("error", "usuario-repetido-nickname");
			}
			errorAltaUsuario(request, response);
		}
    	
    }
    
    private DtTurista setAttributesTur(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String contrasena, String linkImagen) {
    	DtTurista nuevo = new DtTurista();
    	nuevo.setApellido(apellido);
    	nuevo.setNickname(nickname);
    	nuevo.setNombre(nombre);
    	nuevo.setContrasena(contrasena);
    	nuevo.setCorreo(correo);
    	nuevo.setLinkImagen(linkImagen);
    	try {
			nuevo.setNacimiento(DatatypeFactory.newInstance().newXMLGregorianCalendar(nacimiento));
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return nuevo;
    }
    
    private DtProveedor setAttributesProv(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String contrasena, String linkImagen) {
    	DtProveedor nuevo = new DtProveedor();
    	nuevo.setApellido(apellido);
    	nuevo.setNickname(nickname);
    	nuevo.setNombre(nombre);
    	nuevo.setContrasena(contrasena);
    	nuevo.setCorreo(correo);
    	nuevo.setLinkImagen(linkImagen);
    	try {
			nuevo.setNacimiento(DatatypeFactory.newInstance().newXMLGregorianCalendar(nacimiento));
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return nuevo;
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/pages/altaUsuario.jsp").forward(request, response);
		System.out.println("se llamo ajax get");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		registrarUsuario(request,response);
		System.out.println("se llamo ajax post");
		System.out.println(request.getParameter("nicknameDinamico"));

	}

}
