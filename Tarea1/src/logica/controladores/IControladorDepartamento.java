package logica.controladores;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import datatypes.DTActividad;
import datatypes.DTSalida;
import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;

public interface IControladorDepartamento {
	
	public abstract Set<String> obtenerDepartamentos();
	
	public abstract HashSet<DTActividad> obtenerDatosActividadesAsociadas(String nombreDpto) throws departamentoNoExisteException;
	
	public abstract HashSet<DTSalida> obtenerDatosSalidasVigentes(String nombreAct, String nombreDpto) throws departamentoNoExisteException, actividadNoExisteException;
	
	public abstract boolean ingresarDatosActividad(String nombreAct, String descripcion, int duracion, float costo, String ciudad, GregorianCalendar fecha, String nicknameProv, String nombreDep);
}
