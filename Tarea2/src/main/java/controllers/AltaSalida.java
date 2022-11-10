package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.datatypes.DTActividad;
import publicadores.ActividadNoExisteException_Exception;
import publicadores.DepartamentoNoExisteException_Exception;
import publicadores.PublicadorDepartamento;
import publicadores.PublicadorDepartamentoService;

/**
 * Servlet implementation class AltaSalida
 */
@WebServlet("/altaSalida") @MultipartConfig
public class AltaSalida extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaSalida() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected String guardarImagen(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	String pathFiles = this.getServletContext().getRealPath("/resources/img");
    	
    	File uploads = new File(pathFiles);
    	
    	Part part = request.getPart("imgSal"); //obtengo el part que se mandó por el request
		Path path = Paths.get(part.getSubmittedFileName()); 
		
		String fileName = path.getFileName().toString(); //obtengo el nombre del archivo
		String extension = fileName.substring(fileName.lastIndexOf('.'));
		String nombre  = request.getParameter("nombreSal"); //Le pongo el nombre de la salida a la imagen
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
    
    protected void errorSalida(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      	Fabrica fact = Fabrica.getInstance();
    	IControladorDepartamento cd = fact.getIControladorDepartamento();     
		request.setAttribute("dpto", request.getParameter("dpto"));
		request.setAttribute("act", request.getParameter("act"));
		request.setAttribute("nombreSal", request.getParameter("nombreSal"));
		request.setAttribute("fechaSal", request.getParameter("fechaSal"));
		request.setAttribute("horaSal", request.getParameter("horaSal"));
		request.setAttribute("lugarSal", request.getParameter("lugarSal"));
		request.setAttribute("cantTurSal", request.getParameter("cantTurSal"));
 		try {
			HashSet<DTActividad> acts = (HashSet<DTActividad>) cd.obtenerDatosActividadesConfirmadasDpto(request.getParameter("dpto"));
			Set<String> res = new HashSet<String>();
			for (DTActividad act : acts) {
				res.add(act.getNombre());
			}
			request.setAttribute("actsSal", res);
			request.getRequestDispatcher("/pages/altaSalida.jsp").forward(request, response);
			
		} catch (departamentoNoExisteException exc1) {
			exc1.printStackTrace(); //solo pasaría con datos desactualizados  
		}
    }
    
    protected void llenarComboboxes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	Fabrica fact = Fabrica.getInstance();
	 	IControladorDepartamento cd = fact.getIControladorDepartamento(); 
	 	
	 	String cambio = request.getParameter("cambio");
	 	
	 	if(cambio.equals("dpto")) {
	 		try {
	 				request.setAttribute("cats",cd.obtenerCategorias());
		    		request.setAttribute("dptos",cd.obtenerDepartamentos());
					HashSet<DTActividad> acts = (HashSet<DTActividad>) cd.obtenerDatosActividadesConfirmadasDpto(request.getParameter("dpto"));
					Set<String> res = new HashSet<String>();
					for (DTActividad act : acts) {
						res.add(act.getNombre());
					}
					request.setAttribute("dpto", request.getParameter("dpto"));
					request.setAttribute("actsSal", res);
					request.getRequestDispatcher("/pages/altaSalida.jsp").forward(request, response);
					
				} catch (departamentoNoExisteException exc1) {
					exc1.printStackTrace(); //solo pasaría con datos desactualizados  
				}
	 	}
	 	else if(cambio.equals("act")) {
	 		try {
				request.setAttribute("cats",cd.obtenerCategorias());
	    		request.setAttribute("dptos",cd.obtenerDepartamentos());
				
				request.setAttribute("dpto", request.getParameter("dpto"));
				HashSet<DTActividad> acts = (HashSet<DTActividad>) cd.obtenerDatosActividadesConfirmadasDpto(request.getParameter("dpto"));
				Set<String> actsInsc = new HashSet<String>();
				for (DTActividad act : acts) {
					actsInsc.add(act.getNombre());
				}
				request.setAttribute("actsSal", actsInsc); // relleno las actividades por Dpto
				request.setAttribute("act", request.getParameter("act"));
				request.getRequestDispatcher("/pages/altaSalida.jsp").forward(request, response);
    		} catch (departamentoNoExisteException exc1) {
    			exc1.printStackTrace(); //solo pasaría con datos desactualizados  
			}
	 	}
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cambio = request.getParameter("cambio");
		if (cambio.equals("iniciar")) {
			request.getRequestDispatcher("/pages/altaSalida.jsp").forward(request, response);
		}
		else {
			llenarComboboxes(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		//doGet(request, response);
    	PublicadorDepartamentoService serviceD = new PublicadorDepartamentoService();
		PublicadorDepartamento portD = serviceD.getPublicadorDepartamentoPort();
		
		String fecha = request.getParameter("fechaSal");
		String[] partsF = fecha.split("-");
		GregorianCalendar fechaSal = new GregorianCalendar(Integer.parseInt(partsF[0]), Integer.parseInt(partsF[1]) - 1, Integer.parseInt(partsF[2]));
		String hora = request.getParameter("horaSal");
		String[] partsH = hora.split(":");
		int horaSal = Integer.parseInt(partsH[0])*100 + Integer.parseInt(partsH[1]);
				 
		try {
			String linkImagen = null;
			String nuevoNombre = null;
    		Part part = request.getPart("imgSal");
			if(part.getContentType().contains("image") && part.getInputStream() != null) { //Solo guardo la imagen si la seteó el usuario
				nuevoNombre = guardarImagen(request,response);
				linkImagen = "resources/img/" + nuevoNombre;
			}
			else
				linkImagen = "sin";
			boolean existe = portD.ingresarDatosSalida(request.getParameter("nombreSal"), Integer.parseInt(request.getParameter("cantTurSal")), DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(ZonedDateTime.now())),  DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaSal), horaSal, request.getParameter("lugarSal"), request.getParameter("dpto"), request.getParameter("act"), linkImagen);
			if (existe) {
				if(part.getContentType().contains("image") && part.getInputStream() != null) { 
					File file = new File(new File(this.getServletContext().getRealPath("/resources/img")), nuevoNombre);
					file.delete();
				}
				request.setAttribute("error", "existe");
				errorSalida(request,response);
			}
			else
				request.getRequestDispatcher("index.jsp").forward(request, response);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DepartamentoNoExisteException_Exception e) {
			e.printStackTrace();
		} catch (ActividadNoExisteException_Exception e) {
			e.printStackTrace();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
	}

}
