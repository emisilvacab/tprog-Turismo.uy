package logica.controladores;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import logica.datatypes.DTActividad;
import logica.datatypes.DTSalida;

public interface IControladorDepartamento {
	
	public abstract Set<String> obtenerDepartamentos();
	
	public abstract HashSet<DTActividad> obtenerDatosActividadesAsociadas(String nombreDpto) throws departamentoNoExisteException;
	
	public abstract HashSet<DTSalida> obtenerDatosSalidasVigentes(String nombreAct, String nombreDpto) throws departamentoNoExisteException, actividadNoExisteException;
	
	public abstract boolean ingresarDatosActividad(String nombreAct, String descripcion, int duracion, float costo, String ciudad, GregorianCalendar fecha, String nicknameProv, String nombreDep) throws excepciones.proveedorNoExisteException, departamentoNoExisteException;
	
	public abstract boolean ingresarDatosSalida(String nombre, int maxTuristas, GregorianCalendar fechaAlta, GregorianCalendar fechaSalida, int horaSalida, String lugarSalida, String nombreDpto, String nombreAct) throws excepciones.proveedorNoExisteException, actividadNoExisteException;
	
	public abstract void ingresarDepartamento(String nombre, String descripcion, String url);

	public abstract DTActividad obtenerDatosActividad(String actividadSeleccionada) throws actividadNoExisteException;

	public abstract String obtenerDeptoActividad(String actividad);

	public abstract HashSet<DTSalida> obtenerDatosSalidasParaActividad(String nombreAct) throws actividadNoExisteException;
	
}
