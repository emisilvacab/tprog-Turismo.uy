package publicadores;

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
	
}
