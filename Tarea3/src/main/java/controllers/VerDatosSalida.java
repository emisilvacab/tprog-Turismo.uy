package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicadores.ActividadNoExisteException_Exception;
import publicadores.SalidaNoExisteException_Exception;
import publicadores.DtActividad;
import publicadores.DtSalida;
import publicadores.PublicadorDepartamento;
import publicadores.PublicadorDepartamentoService;

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
		PublicadorDepartamentoService serviceDepartamento = new PublicadorDepartamentoService();
        PublicadorDepartamento portDepartamento = serviceDepartamento.getPublicadorDepartamentoPort();
    	
    	String salidaSeleccionada = request.getParameter("salSeleccionada");
    	try {
			DtSalida salida = portDepartamento.obtenerDatosSalida(salidaSeleccionada);
			request.setAttribute("salida", salida);
			boolean esVigente = portDepartamento.salidaEstaVigente(salidaSeleccionada);
			request.setAttribute("vigencia", esVigente);
		} catch (SalidaNoExisteException_Exception salNoExiste) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "salidaNoExiste");
		}
    	
    	try {
			String nombreActividad = portDepartamento.obtenerNombreActividadDeSalida(salidaSeleccionada);
			DtActividad actividad = portDepartamento.obtenerDatosActividad(nombreActividad);
			request.setAttribute("actividad", actividad);
		} catch (SalidaNoExisteException_Exception salNoExiste) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "salidaNoExiste");
		} catch (ActividadNoExisteException_Exception noExisteActividad) {
    	// TODO Auto-generated catch block
    		request.setAttribute("error", "salidaNoExiste");
    	}
		
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
