package controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicadores.DtUsuario;
import publicadores.PublicadorUsuario;
import publicadores.PublicadorUsuarioService;
import publicadores.UsuarioNoExisteException_Exception;
import publicadores.DtTurista;
import publicadores.DtColecciones;
import publicadores.ActividadNoExisteException_Exception;
import publicadores.DtActividad;
import publicadores.DtSalida;
import publicadores.Estado;

/**
 * Servlet implementation class DetalleUsuario
 */
@WebServlet("/DetalleUsuario")
public class DetalleUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetalleUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    protected void cargarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	
    	PublicadorUsuarioService service = new PublicadorUsuarioService();
        PublicadorUsuario port = service.getPublicadorUsuarioPort();
    	
    	String nickname = request.getParameter("usuarioDetalleNickname");
    	DtUsuario usuario = null;
    	try {
			usuario = port.obtenerUsuario(nickname);
		} catch (UsuarioNoExisteException_Exception usuarioNoExiste) {
			request.setAttribute("error", "usuario-no-existe");
		}
    	
    	if (usuario instanceof DtTurista) {			

    		DtColecciones inscripciones = null;
    		try{
    			inscripciones = port.obtenerInscripcionesTurista(nickname);
    		} catch (UsuarioNoExisteException_Exception usuarioNoExiste) {
    			request.setAttribute("error", "usuario-no-existe");
    		}

    		DtColecciones compras = null;
    		try{
    			compras = port.obtenerComprasTurista(nickname);
    		} catch (UsuarioNoExisteException_Exception usuarioNoExiste) {
    			request.setAttribute("error", "usuario-no-existe");
    		}
    		
    		request.setAttribute("usuarioDetalle", usuario);
    		request.setAttribute("usuarioDetalleTipo", "turista");
    		request.setAttribute("usuarioDetalleInscripciones", inscripciones);
    		request.setAttribute("usuarioDetalleCompras", compras);
    		
    	}else {
    		
    		DtColecciones actividadesConfirmadas = new DtColecciones();
			try {
				actividadesConfirmadas = port.obtenerActividadesOfrecidasConfirmadasDT(nickname);
			} catch (ActividadNoExisteException_Exception | UsuarioNoExisteException_Exception usuarioActividadNoExiste) {
				request.setAttribute("error", "usuario-actividad-no-existe");
			}
    
    		DtColecciones actividadesOfrecidas = new DtColecciones();
			try {
				actividadesOfrecidas = port.obtenerActividadesOfrecidasDT(nickname);
			} catch (ActividadNoExisteException_Exception | UsuarioNoExisteException_Exception usuarioActividadNoExiste) {
				request.setAttribute("error", "usuario-actividad-no-existe");
			}
    		
			DtColecciones salidasConfirmadas = new DtColecciones();
    		try {
				salidasConfirmadas = port.obtenerSalidasConfirmadasDT(nickname);
			} catch (ActividadNoExisteException_Exception | UsuarioNoExisteException_Exception usuarioActividadNoExiste) {
				request.setAttribute("error", "usuario-actividad-no-existe");
			}
    		
    		DtColecciones salidasOfrecidas = new DtColecciones();
    		try {
				salidasOfrecidas = port.obtenerSalidasOfrecidasDT(nickname);
			} catch (ActividadNoExisteException_Exception | UsuarioNoExisteException_Exception usuarioActividadNoExiste) {
				request.setAttribute("error", "usuario-actividad-no-existe");
			}
    		/*
    		Set<DtSalida> salidasProveedor = new HashSet<DtSalida>();
    		for(String act : actividadesNombres) {
    			try {
    				Set<String> salidasAct = new HashSet<String>(port.obtenerSalidasDeActividad(nickname, act).getSetString());
    				for(String sal : salidasAct) {
    					DtSalida nueva = port.obtenerDatoSalidaProveedor(nickname, act, sal);
    					salidasProveedor.add(nueva);
    				}
    							
    			} catch(UsuarioNoExisteException_Exception | ActividadNoExisteException_Exception usuarioActividadNoExiste) {
    				request.setAttribute("error", "usuario-actividad-no-existe"); 	
    			}
    		}
    		
    		Set<DtSalida> salidasConfirmadas = new HashSet<DtSalida>();
    		for(String act : actividadesNombres) {
    			try {
    				DtActividad actividad = port.obtenerDatoActividadProveedor(nickname, act);
    			
    				if(actividad.getEstado().equals(Estado.CONFIRMADA)){
    					Set<String> salidasAct = new HashSet<String>(port.obtenerSalidasDeActividad(nickname, act).getSetString());
    					for(String sal : salidasAct) {
    						DtSalida nueva = port.obtenerDatoSalidaProveedor(nickname, act, sal);
    						salidasConfirmadas.add(nueva);
    					}	
    				}	
    							
    				} catch(UsuarioNoExisteException_Exception | ActividadNoExisteException_Exception usuarioActividadNoExiste) {
    					request.setAttribute("error", "usuario-actividad-no-existe"); 	
    				
    			}
    		}
    		*/
    		
    		request.setAttribute("usuarioDetalleSalidasConfirmadas", salidasConfirmadas);
    		request.setAttribute("usuarioDetalleSalidasOfrecidas", salidasOfrecidas);
    		request.setAttribute("usuarioDetalleActividadesOfrecidas", actividadesOfrecidas);
    		request.setAttribute("usuarioDetalleActividadesConfirmadas", actividadesConfirmadas);
    		request.setAttribute("usuarioDetalle", usuario);
    		request.setAttribute("usuarioDetalleTipo", "proveedor");

    	}
    	
		request.getRequestDispatcher("/pages/detalleUsuario.jsp").forward(request, response);
   	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		cargarUsuario(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}