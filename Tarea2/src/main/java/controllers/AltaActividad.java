package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sun.tools.ws.processor.model.Service;

import excepciones.departamentoNoExisteException;
import excepciones.proveedorNoExisteException;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.datatypes.DTUsuario;
import publicadores.DepartamentoNoExisteException_Exception;
import publicadores.DtColecciones;
import publicadores.DtUsuario;
import publicadores.ProveedorNoExisteException_Exception;
import publicadores.PublicadorDepartamento;
import publicadores.PublicadorDepartamentoService;

/**
 * Servlet implementation class AltaActividad
 */

@WebServlet("/altaActividad") @MultipartConfig
public class AltaActividad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaActividad() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected String guardarImagen(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	String pathFiles = this.getServletContext().getRealPath("/resources/img");
    	
    	File uploads = new File(pathFiles);
    	
    	Part part = request.getPart("imgAct"); //obtengo el part que se mandó por el request
		Path path = Paths.get(part.getSubmittedFileName()); 
		
		String fileName = path.getFileName().toString(); //obtengo el nombre del archivo
		String extension = fileName.substring(fileName.lastIndexOf('.'));
		String nombre  = request.getParameter("nombreAct"); //Le pongo el nombre de la actividad a la imagen
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
    
    protected void errorActividad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HashSet<String> categorias = new HashSet<String>();//categorias falta hacer multiselect en pagina
		String[] cates = request.getParameterValues("catsAct");
		for (String cat: cates) {
			cat = cat.replace("+"," ");
			categorias.add(cat);
		}
		request.setAttribute("catsMarcadas", categorias);
		request.setAttribute("depAct", request.getParameter("depAct"));
		request.setAttribute("nombreAct", request.getParameter("nombreAct"));
		request.setAttribute("descripcionAct", request.getParameter("descripcionAct"));
		request.setAttribute("durAct", request.getParameter("durAct"));
		request.setAttribute("costoAct", request.getParameter("costoAct"));
		request.setAttribute("ciudadAct", request.getParameter("ciudadAct"));
		request.setAttribute("linkVideo", request.getParameter("linkVideo"));
		request.setAttribute("imgAct", request.getParameter("imgAct"));
		request.setAttribute("catsAct", request.getParameter("catsAct"));
		request.getRequestDispatcher("/pages/altaActividad.jsp").forward(request, response);
 
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/pages/altaActividad.jsp").forward(request, response);	
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublicadorDepartamentoService serviceD = new PublicadorDepartamentoService();
		PublicadorDepartamento portD = serviceD.getPublicadorDepartamentoPort();
		
		DtUsuario usr = (DtUsuario) request.getSession().getAttribute("usuario_logueado");
		String nicknameProv = usr.getNickname();//nickname del proveedor que tiene la sesion inciada
		
		DtColecciones colCats = new DtColecciones();
		List<String> categorias = colCats.getSetString();
		String[] cates = request.getParameterValues("catsAct");
		for (String cat: cates) {
			cat = cat.replace("+"," ");
			categorias.add(cat);
		}
    	try { 
    		String linkImagen = null;
			String nuevoNombre = null;
    		Part part = request.getPart("imgAct");
			if(part.getContentType().contains("image") && part.getInputStream() != null) {
				nuevoNombre = guardarImagen(request,response);
				linkImagen = "resources/img/" + nuevoNombre;
			}
			else
				linkImagen = "sin";
			String linkVideo = request.getParameter("linkVideo");
			if (linkVideo == null)
				linkVideo = "sin";
			boolean existe = portD.ingresarDatosActividad(request.getParameter("nombreAct"), request.getParameter("descripcionAct"), Integer.parseInt(request.getParameter("durAct")), Float.parseFloat(request.getParameter("costoAct")), request.getParameter("ciudadAct"), DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(ZonedDateTime.now())), nicknameProv, request.getParameter("depAct"), colCats, linkImagen, linkVideo);
			if (existe) {
				if(part.getContentType().contains("image") && part.getInputStream() != null) { 
					File file = new File(new File(this.getServletContext().getRealPath("/resources/img")), nuevoNombre);
					file.delete();
				}
				request.setAttribute("error", "actividad-repetida");
				errorActividad(request,response);
			}
			else
				request.getRequestDispatcher("index.jsp").forward(request, response);
    	} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ProveedorNoExisteException_Exception e) {
			e.printStackTrace();
		} catch (DepartamentoNoExisteException_Exception e) {
			e.printStackTrace();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}    
	}

}























