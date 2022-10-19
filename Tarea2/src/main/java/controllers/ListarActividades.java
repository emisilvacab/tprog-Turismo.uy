package controllers;

import java.awt.Image;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.categoriaNoExisteException;
import excepciones.departamentoNoExisteException;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTActividad;

/* import Presentacion.CargarDatos;
import logica.controladores.IControladorPaquete; */

/**
 * Servlet implementation class ListarActividades
 */
@WebServlet("/ListarActividades")
public class ListarActividades extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarActividades() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private void cargarActividades(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	Fabrica fact = Fabrica.getInstance();
    	IControladorDepartamento ctrlDepartamentos = fact.getIControladorDepartamento();
    	
    	/* IControladorUsuario ctrlUsuario = fact.getIControladorUsuario();
    	IControladorPaquete ctrlPaquete = fact.getIControladorPaquete();
    	CargarDatos cacaDatos = new CargarDatos(ctrlUsuario, ctrlDepartamentos, ctrlPaquete); */
    	
    	String tipo = request.getParameter("tipoPedidoActividad");
    	HashSet<DTActividad> actividades = null;
    	if (tipo.equals("departamento")) {
    		try {
				actividades = ctrlDepartamentos.obtenerDatosActividadesConfirmadasDpto(request.getParameter("nombreTipoActividad"));
			} catch (departamentoNoExisteException deptoNoExiste) {
				// TODO Auto-generated catch block
				request.setAttribute("error", "departamentoNoExiste");
				//request.getRequestDispatcher("/pages/algo.jsp").forward(request, response);
			}
    		request.setAttribute("actividades", actividades);
    		
    		request.setAttribute("dptos", ctrlDepartamentos.obtenerDepartamentos());
    		request.setAttribute("cats", ctrlDepartamentos.obtenerCategorias());
    		
    		request.getRequestDispatcher("/pages/listarActividades.jsp").forward(request, response);
    	} else if (tipo.equals("categoria")) {
    		try {
				actividades = ctrlDepartamentos.obtenerDatosActividadesConfirmadasCat(request.getParameter("nombreTipoActividad"));
			} catch (categoriaNoExisteException catNoExiste) {
				// TODO Auto-generated catch block
				request.setAttribute("error", "categoriaNoExiste");
				//request.getRequestDispatcher("/pages/algo.jsp").forward(request, response);
			}
    		request.setAttribute("actividades", actividades);
    		
    		request.setAttribute("dptos", ctrlDepartamentos.obtenerDepartamentos());
    		request.setAttribute("cats", ctrlDepartamentos.obtenerCategorias());
    		
			request.getRequestDispatcher("/pages/listarActividades.jsp").forward(request, response);
    	}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		cargarActividades(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		cargarActividades(request, response);
	}

}
