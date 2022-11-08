package publicadores;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import excepciones.actividadNoExisteException;
import excepciones.categoriaNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.proveedorNoExisteException;
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
		col.setSetDtActividad(acts);
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
		col.setSetDtActividad(acts);
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
	
	public String obtenerDeptoActividad(String actividad) {
		return contD.obtenerDeptoActividad(actividad);
	}
	
	public boolean ingresarDatosActividad(String nombreAct, String descripcion, int duracion, float costo, String ciudad, GregorianCalendar fecha, String nicknameProv, String nombreDep, Set<String> categorias, String linkImagen, String linkVideo) throws excepciones.proveedorNoExisteException, departamentoNoExisteException{ 
		if (linkImagen.equals("sin"))
			linkImagen = null;
		if (linkVideo.equals("sin"))
			linkVideo = null;
		return contD.ingresarDatosActividad(nombreAct, descripcion, duracion, costo, ciudad, fecha, nicknameProv, nombreDep, categorias, linkImagen, linkVideo);
	}
	
	public boolean ingresarDatosSalida(String nombre, int maxTuristas, GregorianCalendar fechaAlta, GregorianCalendar fechaSalida, int horaSalida, String lugarSalida, String nombreDpto, String nombreAct, String linkImagen) throws excepciones.departamentoNoExisteException, actividadNoExisteException{
		if (linkImagen.equals("sin"))
			linkImagen = null;
		return contD.ingresarDatosSalida(nombre, maxTuristas, fechaAlta, fechaSalida, horaSalida, lugarSalida, nombreDpto, nombreAct, linkImagen);
	}
}
