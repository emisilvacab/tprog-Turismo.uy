package publicadores;

import java.util.GregorianCalendar;

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
	public DTUsuario iniciarSesion(String id, String password) {
		return contU.iniciarSesion(id, password);
	}
	
	@WebMethod
	public void ingresarDatosInscripcionPaq(String nickname, String nombreSal, int cantidad, GregorianCalendar fecha, String nombrePaq) throws salidaNoExisteException, usuarioNoExisteException, paqueteNoExisteException, inscripcionExisteException, limiteSuperadoException {
		contU.ingresarDatosInscripcionPaq(nickname, nombreSal, cantidad, fecha, nombrePaq);
	}
	
}
