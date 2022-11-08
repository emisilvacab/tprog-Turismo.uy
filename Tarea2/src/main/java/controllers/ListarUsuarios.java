package controllers;

import java.util.Set;
import java.util.HashSet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.usuarioNoExisteException;
import logica.Fabrica;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTUsuario;

import publicadores.DtColecciones;
import publicadores.PublicadorUsuario;
import publicadores.PublicadorUsuarioService;
import publicadores.UsuarioNoExisteException_Exception;

/**
 * Servlet implementation class ListarUsuarios
 */
@WebServlet("/ListarUsuarios")
public class ListarUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void cargarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	//Fabrica fact = Fabrica.getInstance();
    	//IControladorUsuario ctrlUsr = fact.getIControladorUsuario();
        
    	PublicadorUsuarioService service = new PublicadorUsuarioService();
        PublicadorUsuario port = service.getPublicadorUsuarioPort();
        
        DtColecciones usuarios = new DtColecciones();
		try {
			usuarios = port.obtenerUsuariosDT();
		} catch (UsuarioNoExisteException_Exception usuarioNoExiste) {
			request.setAttribute("error", "usuario-no-existe");
		}
    	
    	//String[] usrsNombres = ctrlUsr.obtenerUsuarios();
    	//Set<DTUsuario> usuarios = new HashSet<DTUsuario>();
    	
    	/*for(String usr : usrsNombres) {
    		DTUsuario nuevo = null;
    		try {
				nuevo = ctrlUsr.obtenerUsuario(usr);
			} catch (usuarioNoExisteException usuarioNoExiste) {
				// TODO Auto-generated catch block
				request.setAttribute("error", "usuario-no-existe");
				//request.getRequestDispatcher("/pages/algo.jsp").forward(request, response);
			}
    		usuarios.add(nuevo);
    		
    	}*/
    	
    	request.setAttribute("usuarios", usuarios);
    	request.getRequestDispatcher("/pages/listarUsuarios.jsp").forward(request, response);
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		cargarUsuarios(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
