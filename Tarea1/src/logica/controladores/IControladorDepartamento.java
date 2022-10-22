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
	
	public abstract Set<DTActividad> obtenerDatosActividadesAsociadas(String nombreDpto) throws departamentoNoExisteException;
	
	public abstract Set<DTSalida> obtenerDatosSalidasVigentes(String nombreAct) throws actividadNoExisteException;
	
	public boolean ingresarDatosActividad(String nombreAct, String descripcion, int duracion, float costo, String ciudad, GregorianCalendar fecha, String nicknameProv, String nombreDep, Set<String> categorias, String linkImagen) throws excepciones.proveedorNoExisteException, departamentoNoExisteException;
	
	public abstract boolean ingresarDatosSalida(String nombre, int maxTuristas, GregorianCalendar fechaAlta, GregorianCalendar fechaSalida, int horaSalida, String lugarSalida, String nombreDpto, String nombreAct, Image figura) throws excepciones.departamentoNoExisteException, actividadNoExisteException;
	
	public abstract void ingresarDepartamento(String nombre, String descripcion, String url);

	public abstract DTActividad obtenerDatosActividad(String actividadSeleccionada) throws actividadNoExisteException;

	public abstract String obtenerDeptoActividad(String actividad);
	
	public abstract HashSet<String> obtenerCategoriasActividad(String actividad) throws actividadNoExisteException;

	public abstract Set<DTSalida> obtenerDatosSalidasParaActividad(String nombreAct) throws actividadNoExisteException;
	
	public abstract int obtenerlugaresDisponibles(String nombreSal) throws salidaNoExisteException; 
	
	public abstract String[] obtenerActividadesAgregadas();

	public abstract void modificarEstadoActividad(String actividadSeleccionada, Estado estado);
	
	public abstract Set<DTActividad> obtenerDatosActividadesConfirmadasDpto(String nombreDpto) throws departamentoNoExisteException;

	public abstract Set<DTActividad> obtenerDatosActividadesConfirmadasCat(String nombreCat) throws categoriaNoExisteException;

	public abstract void ingresarDatosCategoria(String nombre) throws categoriaYaExisteException;
	
	public abstract Set<String> obtenerCategorias();
	
	public abstract DTSalida obtenerDatosSalida(String nombreSalida) throws salidaNoExisteException;

	public abstract String obtenerNombreActividadDeSalida(String nombreSalida) throws salidaNoExisteException;
}
