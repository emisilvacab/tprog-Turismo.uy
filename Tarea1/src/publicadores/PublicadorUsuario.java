package publicadores;

import java.util.GregorianCalendar;
import java.util.Set;
import java.util.HashSet;

import excepciones.actividadNoExisteException;
import excepciones.ingresoInvalidoException;
import excepciones.inscripcionExisteException;
import excepciones.limiteSuperadoException;
import excepciones.paqueteNoExisteException;
import excepciones.salidaNoExisteException;
import excepciones.usuarioNoExisteException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.Estado;
import logica.Fabrica;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTUsuario;
import logica.datatypes.DTActividad;
import logica.datatypes.DTColecciones;
import logica.datatypes.DTCompra;
import logica.datatypes.DTInscripcion;
import logica.datatypes.DTSalida;


@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorUsuario {
	
	private Endpoint endpoint = null;
	private Fabrica fabrica = Fabrica.getInstance();
	private IControladorUsuario contU = fabrica.getIControladorUsuario();
	
	@WebMethod(exclude = true) //el exclude = true hace que no se publique ese método
    public void publicar(){
         endpoint = Endpoint.publish("http://localhost:2023/publicadorUsuario", this);
    }
	
	@WebMethod(exclude = true)
    public Endpoint getEndpoint() {
         return endpoint;
    }
	
	@WebMethod
	public DTUsuario iniciarSesion(String id, String password) throws ingresoInvalidoException{
		return contU.iniciarSesion(id, password); 			
	}
	
	@WebMethod
	public void ingresarDatosInscripcionPaq(String nickname, String nombreSal, int cantidad, GregorianCalendar fecha, String nombrePaq) throws salidaNoExisteException, usuarioNoExisteException, paqueteNoExisteException, inscripcionExisteException, limiteSuperadoException {
		if (nombrePaq.equals("Inscripcion sin paquete"))
			contU.ingresarDatosInscripcionPaq(nickname, nombreSal, cantidad, fecha, null);
		else 
			contU.ingresarDatosInscripcionPaq(nickname, nombreSal, cantidad, fecha, nombrePaq);
	}
	
	@WebMethod
	public DTUsuario obtenerUsuario(String nickname) throws usuarioNoExisteException {
		return contU.obtenerUsuario(nickname);
	}
	
	@WebMethod
	public DTColecciones obtenerInscripcionesTurista(String nickname) throws usuarioNoExisteException {
		Set<DTInscripcion> inscripciones = (HashSet<DTInscripcion>) contU.obtenerInscripcionesTurista(nickname);
		DTColecciones col = new DTColecciones();
		col.setSetDtInscripcion(inscripciones);
		return col;
	}
	
	@WebMethod
	public DTColecciones obtenerComprasTurista(String nickname) throws usuarioNoExisteException {
		Set<DTCompra> compras = (HashSet<DTCompra>) contU.obtenerComprasTurista(nickname);
		DTColecciones col = new DTColecciones();
		col.setSetDtCompra(compras);
		return col;
	}
	
	@WebMethod
	public DTColecciones obtenerActividadesOfrecidasDT(String nickname) throws usuarioNoExisteException, actividadNoExisteException {
		String[] actividadNombres = contU.obtenerActividadesOfrecidas(nickname);
		Set<DTActividad> actividades = new HashSet<DTActividad>();
		for (String act : actividadNombres) {
			DTActividad nueva = contU.obtenerDatoActividadProveedor(nickname, act);
			actividades.add(nueva);
		}
		DTColecciones col = new DTColecciones();
		col.setSetDtActividad(actividades);
		return col;
	}
	
	@WebMethod
	public DTColecciones obtenerActividadesOfrecidasConfirmadasDT(String nickname) throws usuarioNoExisteException, actividadNoExisteException {
		String[] actividadNombres = contU.obtenerActividadesOfrecidas(nickname);
		Set<DTActividad> actividades = new HashSet<DTActividad>();
		for (String act : actividadNombres) {
			DTActividad nueva = contU.obtenerDatoActividadProveedor(nickname, act);
			if (nueva.getEstado().equals(Estado.CONFIRMADA)) {
				actividades.add(nueva);
			}
		}
		DTColecciones col = new DTColecciones();
		col.setSetDtActividad(actividades);
		return col;
	}
	
	@WebMethod
	public DTColecciones obtenerSalidasConfirmadasDT(String nickname) throws usuarioNoExisteException, actividadNoExisteException {
		String[] actividadNombres = contU.obtenerActividadesOfrecidas(nickname);
		Set<DTSalida> salidas = new HashSet<DTSalida>();
		for (String act : actividadNombres) {
			DTActividad nuevaSal = contU.obtenerDatoActividadProveedor(nickname, act);
			if (nuevaSal.getEstado().equals(Estado.CONFIRMADA)) {
				String[] salidaNombres = contU.obtenerSalidasDeActividad(nickname, act);
				for (String sal : salidaNombres) {
					DTSalida nuevaAct = contU.obtenerDatoSalidaProveedor(nickname, act, sal);
					salidas.add(nuevaAct);
				}
			}
		}
		DTColecciones col = new DTColecciones();
		col.setSetDtSalida(salidas);
		return col;
	}
	
	@WebMethod
	public DTColecciones obtenerSalidasOfrecidasDT(String nickname) throws usuarioNoExisteException, actividadNoExisteException {
		String[] actividadNombres = contU.obtenerActividadesOfrecidas(nickname);
		Set<DTSalida> salidas = new HashSet<DTSalida>();
		for (String act : actividadNombres) {	
			String[] salidaNombres = contU.obtenerSalidasDeActividad(nickname, act);
			for (String sal : salidaNombres) {
				DTSalida nuevaSal = contU.obtenerDatoSalidaProveedor(nickname, act, sal);
				salidas.add(nuevaSal);
			}		
		}
		DTColecciones col = new DTColecciones();
		col.setSetDtSalida(salidas);
		return col;
	}
	
	@WebMethod
	public DTColecciones obtenerUsuariosDT() throws usuarioNoExisteException {
		String[] nombreUsuarios = contU.obtenerUsuarios();
		Set<DTUsuario> setUsuarios = new HashSet<DTUsuario>();
		for (String usr : nombreUsuarios) {
			DTUsuario nuevo = contU.obtenerUsuario(usr);
			setUsuarios.add(nuevo);
		}
		DTColecciones col = new DTColecciones();
		col.setSetDtUsuario(setUsuarios);
		return col;
	}
	
}
