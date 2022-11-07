package controllers;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import logica.Fabrica;
import logica.controladores.IControladorDepartamento;

/**
 * Servlet Filter implementation class Filtro
 */
@WebFilter(
		urlPatterns = { "/Filtro" }, 
		servletNames = { 
				"AltaActividad", 
				"AltaSalida", 
				"AltaUsuario", 
				"CargarDatos", 
				"CompraPaquete", 
				"DetallePaquete", 
				"DetalleUsuario", 
				"Home", 
				"Inscripcion", 
				"ListarActividades", 
				"ListarPaquetes", 
				"ListarUsuarios", 
				"Sesion", 
				"verDatosActividad", 
				"VerDatosSalida"
		})
public class Filtro extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public Filtro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		request.setCharacterEncoding("UTF-8");
		Fabrica fact = Fabrica.getInstance();//Cambia cuando tengamos publicadores
    	IControladorDepartamento cd = fact.getIControladorDepartamento();
    	request.setAttribute("cats",cd.obtenerCategorias());
    	request.setAttribute("dptos",cd.obtenerDepartamentos());

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

