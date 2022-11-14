package controllers;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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

import javax.imageio.ImageIO;
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
import publicadores.PublicadorImagenes;
import publicadores.PublicadorImagenesService;

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
    	PublicadorImagenesService serviceI = new PublicadorImagenesService();
		PublicadorImagenes portI = serviceI.getPublicadorImagenesPort();
		
		Part filePart = request.getPart("imgSal");

	    InputStream inputS = null;
	    ByteArrayOutputStream os = null;
	    
        inputS = filePart.getInputStream();

        // Escalar la imagen
        BufferedImage imageBuffer = ImageIO.read(inputS);
        Image tmp = imageBuffer.getScaledInstance(640, 640, BufferedImage.SCALE_FAST);
        BufferedImage buffered = new BufferedImage(640, 640, BufferedImage.TYPE_INT_RGB);
        buffered.getGraphics().drawImage(tmp, 0, 0, null);

        os = new ByteArrayOutputStream();
        ImageIO.write(buffered, "jpg", os);
        String name = portI.guardarImagen(os.toByteArray(), request.getParameter("nombreSal"));
        return name;
    }
    
    protected void errorSalida(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PublicadorDepartamentoService serviceD = new PublicadorDepartamentoService();
        PublicadorDepartamento portD = serviceD.getPublicadorDepartamentoPort();
    
		request.setAttribute("dpto", request.getParameter("dpto"));
		request.setAttribute("act", request.getParameter("act"));
		request.setAttribute("nombreSal", request.getParameter("nombreSal"));
		request.setAttribute("fechaSal", request.getParameter("fechaSal"));
		request.setAttribute("horaSal", request.getParameter("horaSal"));
		request.setAttribute("lugarSal", request.getParameter("lugarSal"));
		request.setAttribute("cantTurSal", request.getParameter("cantTurSal"));
 		try {
			Set<String> acts = new HashSet<String>(portD.obtenerDatosActividadesConfirmadasDpto(request.getParameter("dpto")).getSetString());
			request.setAttribute("actsSal", acts);
			request.getRequestDispatcher("/pages/altaSalida.jsp").forward(request, response);
			
		} catch (DepartamentoNoExisteException_Exception exc1) {
			exc1.printStackTrace(); //solo pasaría con datos desactualizados  
		}
    }
    
    protected void llenarComboboxes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PublicadorDepartamentoService serviceD = new PublicadorDepartamentoService();
        PublicadorDepartamento portD = serviceD.getPublicadorDepartamentoPort();
	        	 	
	 	String cambio = request.getParameter("cambio");
	 	
	 	if(cambio.equals("dpto")) {
	 		try {
	 			System.out.println("0");
	 			Set<String> acts = new HashSet<String>(portD.obtenerDatosActividadesConfirmadasDpto(request.getParameter("dpto")).getSetString());
				request.setAttribute("dpto", request.getParameter("dpto"));
				System.out.println("1");
				request.setAttribute("actsSal", acts);
				System.out.println("2");
				request.getRequestDispatcher("/pages/altaSalida.jsp").forward(request, response);
			} catch (DepartamentoNoExisteException_Exception e) {
				e.printStackTrace();
			}
	 	}
	 	else if(cambio.equals("act")) {
	 		try {
				Set<String> acts = new HashSet<String>(portD.obtenerDatosActividadesConfirmadasDpto(request.getParameter("dpto")).getSetString());
				request.setAttribute("dpto", request.getParameter("dpto"));
				request.setAttribute("actsSal", acts);
				request.setAttribute("act", request.getParameter("act"));
				request.getRequestDispatcher("/pages/altaSalida.jsp").forward(request, response);
    		} catch (DepartamentoNoExisteException_Exception exc1) {
    			exc1.printStackTrace(); //solo pasaría con datos desactualizados  
			}
	 	}
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				linkImagen = "/Tarea2/img?id=" + nuevoNombre;
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
