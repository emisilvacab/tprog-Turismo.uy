package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.datatypes.DTActividad;
import logica.datatypes.DTSalida;

/**
 * Servlet implementation class VerDatosSalida
 */
@WebServlet("/VerDatosSalida")
public class VerDatosSalida extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerDatosSalida() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Fabrica fact = Fabrica.getInstance();
    	IControladorDepartamento ctrlDepartamentos = fact.getIControladorDepartamento();
    	
    	String salidaSeleccionada = request.getParameter("salSeleccionada");
    	try {
			DTSalida salida = ctrlDepartamentos.obtenerDatosSalida(salidaSeleccionada);
			request.setAttribute("salida", salida);
		} catch (Exception salNoExiste) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "salidaNoExiste");
		}
    	
    	try {
			String nombreActividad = ctrlDepartamentos.obtenerNombreActividadDeSalida(salidaSeleccionada);
			DTActividad actividad = ctrlDepartamentos.obtenerDatosActividad(nombreActividad);
			
			request.setAttribute("actividad", actividad);
		} catch (Exception salNoExiste) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "salidaNoExiste");
		}
    	
    	request.setAttribute("dptos", ctrlDepartamentos.obtenerDepartamentos());
		request.setAttribute("cats", ctrlDepartamentos.obtenerCategorias());
		
		request.getRequestDispatcher("/pages/verDatosSalida.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
