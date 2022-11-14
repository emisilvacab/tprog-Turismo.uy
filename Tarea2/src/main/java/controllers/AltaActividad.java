package controllers;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

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

import publicadores.DepartamentoNoExisteException_Exception;
import publicadores.DtColecciones;
import publicadores.DtUsuario;
import publicadores.ProveedorNoExisteException_Exception;
import publicadores.PublicadorDepartamento;
import publicadores.PublicadorDepartamentoService;
import publicadores.PublicadorImagenes;
import publicadores.PublicadorImagenesService;


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
    	PublicadorImagenesService serviceI = new PublicadorImagenesService();
		PublicadorImagenes portI = serviceI.getPublicadorImagenesPort();
		
		Part filePart = request.getPart("imgAct");

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
        String name = portI.guardarImagen(os.toByteArray(), request.getParameter("nombreAct"));
        return name;
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
				linkImagen = "img?id=" + nuevoNombre;
			}
			else
				linkImagen = "sin";
			String linkVideo = request.getParameter("linkVideo");
			if (linkVideo == null)
				linkVideo = "sin";
			boolean existe = portD.ingresarDatosActividad(request.getParameter("nombreAct"), request.getParameter("descripcionAct"), Integer.parseInt(request.getParameter("durAct")), Float.parseFloat(request.getParameter("costoAct")), request.getParameter("ciudadAct"), DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(ZonedDateTime.now())), nicknameProv, request.getParameter("depAct"), colCats, linkImagen, linkVideo);
			if (existe) {
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























