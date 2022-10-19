package logica.controladores;

import java.awt.Image;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import excepciones.actividadNoExisteException;
import excepciones.categoriaNoExisteException;
import excepciones.categoriaYaExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.salidaNoExisteException;
import logica.Estado;
import logica.datatypes.DTActividad;
import logica.datatypes.DTSalida;

public interface IControladorDepartamento {
	
	public abstract Set<String> obtenerDepartamentos();
	
	public abstract HashSet<DTActividad> obtenerDatosActividadesAsociadas(String nombreDpto) throws departamentoNoExisteException;
	
	public abstract HashSet<DTSalida> obtenerDatosSalidasVigentes(String nombreAct) throws actividadNoExisteException;
	
	public abstract boolean ingresarDatosActividad(String nombreAct, String descripcion, int duracion, float costo, String ciudad, GregorianCalendar fecha, String nicknameProv, String nombreDep, Set<String> categorias) throws excepciones.proveedorNoExisteException, departamentoNoExisteException;
	
	public abstract boolean ingresarDatosSalida(String nombre, int maxTuristas, GregorianCalendar fechaAlta, GregorianCalendar fechaSalida, int horaSalida, String lugarSalida, String nombreDpto, String nombreAct, Image figura) throws excepciones.departamentoNoExisteException, actividadNoExisteException;
	
	public abstract void ingresarDepartamento(String nombre, String descripcion, String url);

	public abstract DTActividad obtenerDatosActividad(String actividadSeleccionada) throws actividadNoExisteException;

	public abstract String obtenerDeptoActividad(String actividad);

	public abstract HashSet<DTSalida> obtenerDatosSalidasParaActividad(String nombreAct) throws actividadNoExisteException;
	
	public abstract int obtenerlugaresDisponibles(String nombreSal) throws salidaNoExisteException; 
	
	public abstract String[] obtenerActividadesAgregadas();

	public abstract void modificarEstadoActividad(String actividadSeleccionada, Estado estado);
	
	public abstract HashSet<DTActividad> obtenerDatosActividadesConfirmadasDpto(String nombreDpto) throws departamentoNoExisteException;

	public abstract HashSet<DTActividad> obtenerDatosActividadesConfirmadasCat(String nombreCat) throws categoriaNoExisteException;

	public abstract void ingresarDatosCategoria(String nombre) throws categoriaYaExisteException;
	
	public abstract Set<String> obtenerCategorias();
}
