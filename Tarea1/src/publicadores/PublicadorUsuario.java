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
import logica.Fabrica;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTUsuario;
import logica.datatypes.DTActividad;
import logica.datatypes.DTColecciones;
import logica.datatypes.DTCompra;
import logica.datatypes.DTSalida;
import logica.datatypes.DTInscripcion;

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
	public DTColecciones obtenerActividadesOfrecidas(String nickname) throws usuarioNoExisteException {
		String[] actividades =  contU.obtenerActividadesOfrecidas(nickname);
		DTColecciones col = new DTColecciones();
		Set<String> setActividades = new HashSet<String>();
		for (String act : actividades) {
			setActividades.add(act);
		}
		col.setSetString(setActividades);
		return col;
	}
	
	@WebMethod
	public DTActividad obtenerDatoActividadProveedor(String nickname, String nombreAct) throws usuarioNoExisteException, actividadNoExisteException {
		return contU.obtenerDatoActividadProveedor(nickname, nombreAct);
	}
	
	@WebMethod
	public DTColecciones obtenerSalidasDeActividad(String nickname, String nombreAct) throws usuarioNoExisteException, actividadNoExisteException {
		String[] salidas =  contU.obtenerSalidasDeActividad(nickname, nombreAct);
		Set<String> setSalidas = new HashSet<String>();
		for (String sal : salidas) {
			setSalidas.add(sal);
		}
		DTColecciones col = new DTColecciones();
		col.setSetString(setSalidas);	
		return col;
	}
	
	@WebMethod
	public DTSalida obtenerDatoSalidaProveedor(String nickname, String nombreAct, String nombreSal) throws usuarioNoExisteException, actividadNoExisteException {
		return contU.obtenerDatoSalidaProveedor(nickname, nombreAct, nombreSal);
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
