package controllers;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicadores.DtActividad;
import publicadores.DtPaquete;
import publicadores.DtSalida;
import publicadores.PublicadorDepartamento;
import publicadores.PublicadorDepartamentoService;
import publicadores.PublicadorPaquete;
import publicadores.PublicadorPaqueteService;
import publicadores.ActividadNoExisteException_Exception;

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
		PublicadorDepartamentoService serviceDepartamento = new PublicadorDepartamentoService();
        PublicadorDepartamento portDepartamento = serviceDepartamento.getPublicadorDepartamentoPort();
        PublicadorPaqueteService servicePaquete = new PublicadorPaqueteService();
        PublicadorPaquete portPaquete = servicePaquete.getPublicadorPaquetePort();
        
    	String NombreAct = (String) request.getParameter("actSeleccionada");
    	DtActividad actividad;
    	String nombreProveedor;
		try {
			actividad = portDepartamento.obtenerDatosActividad(NombreAct);
			request.setAttribute("actividad", actividad);
			GregorianCalendar fechaAlta = actividad.getAlta().toGregorianCalendar();
			request.setAttribute("fechaAltaDia", fechaAlta.get(fechaAlta.DAY_OF_MONTH));
			request.setAttribute("fechaAltaMes", fechaAlta.get(fechaAlta.MONTH) + 1);
			request.setAttribute("fechaAltaAño", fechaAlta.get(fechaAlta.YEAR));
			nombreProveedor = portDepartamento.obtenerNombreProveedorDeActividad(NombreAct);
			request.setAttribute("proveedor", nombreProveedor);
		} catch (ActividadNoExisteException_Exception noExisteActividad) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "actividadNoExiste");
		}
    	
    	request.setAttribute("departamento", portDepartamento.obtenerDeptoActividad(NombreAct));
    	
    	HashSet<String> listaCategorias;
		try {
			listaCategorias = new HashSet<String>(portDepartamento.obtenerCategoriasActividad(NombreAct).getSetString());
			request.setAttribute("categorias", listaCategorias);
		} catch (ActividadNoExisteException_Exception actNoExiste) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "actividadNoExiste");
		}
    	
		try {
    		HashSet<DtSalida> listaSalidas = new HashSet<DtSalida>();
			listaSalidas = new HashSet<DtSalida>(portDepartamento.obtenerDatosSalidasParaActividad(NombreAct).getSetDtSalida());
			request.setAttribute("salidas", listaSalidas);
		} catch (ActividadNoExisteException_Exception noExisteAct) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "actividadNoExiste");
		}
    	HashSet<DtPaquete> listaPaquetes = new HashSet<DtPaquete>();
    	listaPaquetes = new HashSet<DtPaquete>(portPaquete.obtenerDatosPaquetesParaActividad(NombreAct).getSetDtPaquete());
    	request.setAttribute("paquetes", listaPaquetes);
		
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
