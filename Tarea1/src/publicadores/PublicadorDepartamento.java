package publicadores;

import java.util.HashSet;
import java.util.Set;

import excepciones.actividadNoExisteException;
import excepciones.categoriaNoExisteException;
import excepciones.departamentoNoExisteException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.Fabrica;
import logica.controladores.IControladorDepartamento;
import logica.datatypes.DTActividad;
import logica.datatypes.DTColecciones;
import logica.datatypes.DTSalida;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorDepartamento {
	
	private Endpoint endpoint = null;
	private Fabrica fabrica = Fabrica.getInstance();
	private IControladorDepartamento contD = fabrica.getIControladorDepartamento();
	
	@WebMethod(exclude = true) //el exclude = true hace que no se publique ese método
    public void publicar(){
         endpoint = Endpoint.publish("http://localhost:2023/publicadorDepartamento", this);
    }
	
	@WebMethod(exclude = true)
    public Endpoint getEndpoint() {
         return endpoint;
    }
	
	public DTColecciones obtenerDatosActividadesConfirmadasDpto(String nombreDpto) throws departamentoNoExisteException {
		HashSet<DTActividad> acts = (HashSet<DTActividad>) contD.obtenerDatosActividadesConfirmadasDpto(nombreDpto);
		Set<String> res = new HashSet<String>();
		for (DTActividad act : acts) {
			res.add(act.getNombre());
		}
		DTColecciones col = new DTColecciones();
		col.setSetString(res);
		return col;
	}
	
	public DTColecciones obtenerDatosActividadesConfirmadasCat(String nombreDpto) throws categoriaNoExisteException {
		HashSet<DTActividad> acts = (HashSet<DTActividad>) contD.obtenerDatosActividadesConfirmadasCat(nombreDpto);
		Set<String> res = new HashSet<String>();
		for (DTActividad act : acts) {
			res.add(act.getNombre());
		}
		DTColecciones col = new DTColecciones();
		col.setSetString(res);
		return col;
	}
	
	public DTColecciones obtenerDatosSalidasVigentes(String nombreAct) throws actividadNoExisteException {
		HashSet<DTSalida> sals = (HashSet<DTSalida>) contD.obtenerDatosSalidasVigentes(nombreAct);
		Set<String> salsInsc = new HashSet<String>();
		for (DTSalida sal : sals) {
			salsInsc.add(sal.getNombre());
		}
		DTColecciones col = new DTColecciones();
		col.setSetString(salsInsc);
		return col;
	}

}
