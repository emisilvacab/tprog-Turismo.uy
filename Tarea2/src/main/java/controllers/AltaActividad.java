package controllers;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import excepciones.departamentoNoExisteException;
import excepciones.proveedorNoExisteException;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;

/**
 * Servlet implementation class AltaActividad
 */
@WebServlet("/altaActividad")
public class AltaActividad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaActividad() {
        super();
        // TODO Auto-generated constructor stub
    }
        
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Fabrica fact = Fabrica.getInstance();
    	IControladorDepartamento cd = fact.getIControladorDepartamento(); 
    	request.setAttribute("cats",cd.obtenerCategorias());
    	request.setAttribute("dptos",cd.obtenerDepartamentos());
		request.getRequestDispatcher("/pages/altaActividad.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Fabrica fact = Fabrica.getInstance();
    	IControladorDepartamento cd = fact.getIControladorDepartamento(); 
    	request.setAttribute("cats",cd.obtenerCategorias());
    	
    	//Pedir y mostrar lista de departamentos
    	
    	GregorianCalendar fechaAct = GregorianCalendar.from(ZonedDateTime.now());//Fecha actual
    	String nicknameProv = "";//Sacar el nickname del proveedor que tiene la sesion inciada
    	
    	try {
			HashSet<String> categorias = new HashSet<String>();//categorias falta hacer multiselect en pagina
			//String catSeleccionadas = request.getParameter("catsAct");
			String[] cates = request.getParameterValues("catsAct");
			System.out.println(cates[0]);
			System.out.println(cates[1]);
			System.out.println(cates[2]);
			boolean existe = cd.ingresarDatosActividad(request.getParameter("nombreAct"), request.getParameter("descripcionAct"), Integer.parseInt(request.getParameter("durAct")), Float.parseFloat(request.getParameter("costoAct")), request.getParameter("ciudadAct"), fechaAct, nicknameProv, request.getParameter("depAct"), categorias);
			if (existe) {
				//Tirar excepcion de que ya existe actividad
				request.setAttribute("error", "actividad-repetida");
				request.getRequestDispatcher("/pages/altaActividad.jsp").forward(request, response);
			}
			request.getRequestDispatcher("/pages/altaActividad.jsp").forward(request, response);
    	} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (proveedorNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (departamentoNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//Falta asociar categorias
    
	}

}























