package controllers;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import logica.Departamento;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.datatypes.DTActividad;
import logica.datatypes.DTSalida;
import logica.manejadores.ManejadorDepartamentoCategoria;

/**
 * Servlet implementation class verDatosActividad
 */
@WebServlet("/VerDatosActividad")
public class verDatosActividad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public verDatosActividad() {
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
    	String NombreAct = (String) request.getParameter("actSeleccionada");
    	DTActividad actividad;
		try {
			actividad = ctrlDepartamentos.obtenerDatosActividad(NombreAct);
			request.setAttribute("actividad", actividad);
			GregorianCalendar fechaAlta = actividad.getAlta();
			request.setAttribute("fechaAltaDia", fechaAlta.get(fechaAlta.DAY_OF_MONTH));
			request.setAttribute("fechaAltaMes", fechaAlta.get(fechaAlta.MONTH) + 1);
			request.setAttribute("fechaAltaAño", fechaAlta.get(fechaAlta.YEAR));
		} catch (actividadNoExisteException noExisteActividad) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "actividadNoExiste");
		}
    	
    	request.setAttribute("departamento", ctrlDepartamentos.obtenerDeptoActividad(NombreAct));
    	
    	try {
			HashSet<DTSalida> listaSalidas = ctrlDepartamentos.obtenerDatosSalidasParaActividad(NombreAct);
			request.setAttribute("salidas", listaSalidas);
		} catch (actividadNoExisteException noExisteAct) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "actividadNoExiste");
		}
    	
		request.setAttribute("dptos", ctrlDepartamentos.obtenerDepartamentos());
		request.setAttribute("cats", ctrlDepartamentos.obtenerCategorias());
		System.out.println();
		request.getRequestDispatcher("/pages/verDatosActividad.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}