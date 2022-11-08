package publicadores;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import excepciones.compraExisteException;
import excepciones.paqueteNoExisteException;
import excepciones.usuarioNoExisteException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.Fabrica;
import logica.controladores.IControladorPaquete;
import logica.datatypes.DTColecciones;
import logica.datatypes.DTPaquete;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorPaquete {
	
	private Endpoint endpoint = null;
	private Fabrica fabrica = Fabrica.getInstance();
	private IControladorPaquete contP = fabrica.getIControladorPaquete();
	
	@WebMethod(exclude = true) //el exclude = true hace que no se publique ese método
    public void publicar(){
         endpoint = Endpoint.publish("http://localhost:2023/publicadorPaquete", this);
    }
	
	@WebMethod(exclude = true)
    public Endpoint getEndpoint() {
         return endpoint;
    }

	@WebMethod
	public DTColecciones obtenerPaquetesDisponibles(String nickname, String nombreSalida, int cantTuristas) throws usuarioNoExisteException {
		Set<DTPaquete> paqs = contP.obtenerPaquetesDisponibles(nickname, nombreSalida, cantTuristas);
		Set<String> paqsInsc = new HashSet<String>();
		for (DTPaquete paq : paqs) {
			paqsInsc.add(paq.getNombre());
		}
		DTColecciones col = new DTColecciones();
		col.setSetString(paqsInsc);
		return col;
	}

	@WebMethod
	public DTColecciones obtenerPaquetesConActividades() {
		HashSet<DTPaquete> paqs = (HashSet<DTPaquete>) contP.obtenerPaquetesConActividades();
		Set<String> paqsCompra = new HashSet<String>();
		for (DTPaquete paq : paqs) {
			paqsCompra.add(paq.getNombre());
		}
		DTColecciones col = new DTColecciones();
		col.setSetString(paqsCompra);
		return col;
	}

	@WebMethod
	public void comprarPaquete(String nickname, String nombrePaq, GregorianCalendar fechaCompra, int cantidadTuristas) throws usuarioNoExisteException, paqueteNoExisteException, compraExisteException {
		contP.comprarPaquete(nickname, nombrePaq, fechaCompra, cantidadTuristas);
	}

	@WebMethod
	public DTColecciones obtenerDatosPaquetesParaActividad(String nombreAct) {
		Set<DTPaquete> paquetes = (HashSet<DTPaquete>) contP.obtenerDatosPaquetesParaActividad(nombreAct);
		DTColecciones coleccion = new DTColecciones();
		coleccion.setSetDtPaquete(paquetes);
		return coleccion;
	}
	
}
