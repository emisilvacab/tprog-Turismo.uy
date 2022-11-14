package controllers;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicadores.CategoriaNoExisteException_Exception;
import publicadores.DepartamentoNoExisteException_Exception;
import publicadores.DtActividad;
import publicadores.PublicadorDepartamento;
import publicadores.PublicadorDepartamentoService;

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
    	PublicadorDepartamentoService serviceDepartamento = new PublicadorDepartamentoService();
        PublicadorDepartamento portDepartamento = serviceDepartamento.getPublicadorDepartamentoPort();
    	
    	String tipo = request.getParameter("tipoPedidoActividad");
    	HashSet<DtActividad> actividades = null;
    	if (tipo != null) {
    		if (tipo.equals("departamento")) {
	    		try {
					actividades = new HashSet<DtActividad>(portDepartamento.obtenerDatosActividadesConfirmadasDpto(request.getParameter("nombreTipoActividad")).getSetDtActividad());
				} catch (DepartamentoNoExisteException_Exception deptoNoExiste) {
					// TODO Auto-generated catch block
					request.setAttribute("error", "departamentoNoExiste");
					//request.getRequestDispatcher("/pages/algo.jsp").forward(request, response);
				}
	    		request.setAttribute("actividades", actividades);
	    	} else if (tipo.equals("categoria")) {
	    		try {
					actividades = new HashSet<DtActividad>(portDepartamento.obtenerDatosActividadesConfirmadasCat(request.getParameter("nombreTipoActividad")).getSetDtActividad());
				} catch (CategoriaNoExisteException_Exception catNoExiste) {
					// TODO Auto-generated catch block
					request.setAttribute("error", "categoriaNoExiste");
					//request.getRequestDispatcher("/pages/algo.jsp").forward(request, response);
				}
	    		request.setAttribute("actividades", actividades);
	    	}
    	}
    		request.setAttribute("dptos", new HashSet<String>(portDepartamento.obtenerDepartamentos().getSetString()));
    		request.setAttribute("cats", new HashSet<String>(portDepartamento.obtenerCategorias().getSetString()));
    		
			request.getRequestDispatcher("/pages/listarActividades.jsp").forward(request, response);
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
