package publicadores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import excepciones.actividadNoExisteException;
import excepciones.actividadPerteneceAPaqueteException;
import excepciones.actividadTieneSalidasVigentesException;
import excepciones.categoriaNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.proveedorNoExisteException;
import excepciones.salidaNoExisteException;
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
		String home = System.getProperty("user.home");
		String filepath = home + "/.turismoUy/config.properties"; //linux, mac
		//String filepath = home + "\\.turismoUy\\config.properties"; //pc
		String port = "";
	    String dir = "";
	    
	    
	    Properties prop = new Properties();
	    try {
			FileInputStream input = new FileInputStream(filepath);
			try {
				prop.load(input);
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	    dir = prop.getProperty("ip");
	    port = prop.getProperty("port");
	    
        endpoint = Endpoint.publish(dir+port+"/publicadorDepartamento", this);
    }
	
	@WebMethod(exclude = true)
    public Endpoint getEndpoint() {
         return endpoint;
    }
	
	@WebMethod
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
	
	@WebMethod
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
	
	@WebMethod
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
	
	@WebMethod
	public String obtenerDeptoActividad(String actividad) {
		return contD.obtenerDeptoActividad(actividad);
	}

	@WebMethod
	public boolean ingresarDatosActividad(String nombreAct, String descripcion, int duracion, float costo, String ciudad, GregorianCalendar fecha, String nicknameProv, String nombreDep, DTColecciones colCats, String linkImagen, String linkVideo) throws excepciones.proveedorNoExisteException, departamentoNoExisteException{ 
		if (linkImagen.equals("sin"))
			linkImagen = null;
		if (linkVideo.equals("sin"))
			linkVideo = null;
		return contD.ingresarDatosActividad(nombreAct, descripcion, duracion, costo, ciudad, fecha, nicknameProv, nombreDep, colCats.getSetString(), linkImagen, linkVideo);
	}

	@WebMethod
	public boolean ingresarDatosSalida(String nombre, int maxTuristas, GregorianCalendar fechaAlta, GregorianCalendar fechaSalida, int horaSalida, String lugarSalida, String nombreDpto, String nombreAct, String linkImagen) throws excepciones.departamentoNoExisteException, actividadNoExisteException{
		if (linkImagen.equals("sin"))
			linkImagen = null;
		return contD.ingresarDatosSalida(nombre, maxTuristas, fechaAlta, fechaSalida, horaSalida, lugarSalida, nombreDpto, nombreAct, linkImagen);
	}

	@WebMethod
	public DTActividad obtenerDatosActividad(String actividadSeleccionada) throws actividadNoExisteException {
		return contD.obtenerDatosActividad(actividadSeleccionada);
	}

	@WebMethod
	public String obtenerNombreProveedorDeActividad(String nombreAct) throws actividadNoExisteException {
		return contD.obtenerNombreProveedorDeActividad(nombreAct);
	}

	@WebMethod
	public DTColecciones obtenerCategoriasActividad(String actividad) throws actividadNoExisteException {
		Set<String> salidas = (HashSet<String>) contD.obtenerCategoriasActividad(actividad);
		DTColecciones coleccion = new DTColecciones();
		coleccion.setSetString(salidas);
		return coleccion;
	}

	@WebMethod
	public DTColecciones obtenerDatosSalidasParaActividad(String nombreAct) throws actividadNoExisteException {
		Set<DTSalida> salidas = (HashSet<DTSalida>) contD.obtenerDatosSalidasParaActividad(nombreAct);
		DTColecciones coleccion = new DTColecciones();
		coleccion.setSetDtSalida(salidas);
		return coleccion;
	}

	@WebMethod
	public DTSalida obtenerDatosSalida(String nombreSalida) throws salidaNoExisteException {
		return contD.obtenerDatosSalida(nombreSalida);
	}

	@WebMethod
	public String obtenerNombreActividadDeSalida(String nombreSalida) throws salidaNoExisteException {
		return contD.obtenerNombreActividadDeSalida(nombreSalida);
	}
	
	@WebMethod
	public void finalizarActividad(String nombreAct) throws actividadTieneSalidasVigentesException, actividadPerteneceAPaqueteException {
		contD.finalizarActividad(nombreAct);
	}
	
	@WebMethod
	public boolean salidaEstaVigente(String nombreSal) throws salidaNoExisteException {
		return contD.salidaEstaVigente(nombreSal);
	}
	
	@WebMethod
	public DTColecciones obtenerCategorias() {
		Set<String> cats = (HashSet<String>) contD.obtenerCategorias();
		DTColecciones nuevo = new DTColecciones();
		nuevo.setSetString(cats);
		return nuevo;
	}
	
	@WebMethod
	public DTColecciones obtenerDepartamentos(){
		Set<String> dptos = (HashSet<String>) contD.obtenerDepartamentos();
		DTColecciones nuevo = new DTColecciones();
		nuevo.setSetString(dptos);
		return nuevo;
	}
}
