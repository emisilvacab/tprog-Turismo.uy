package logica.controladores;

import java.util.GregorianCalendar;
import java.util.Set;

import excepciones.actividadNoExisteException;
import excepciones.compraExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.paqueteNoExisteException;
import excepciones.paqueteYaExisteException;
import excepciones.usuarioNoExisteException;
import logica.datatypes.DTActividad;
import logica.datatypes.DTPaquete;

public interface IControladorPaquete {
	
	public abstract void agregarActividadPaquete(String nombreDpto, String nombrePaq, String nombreAct) throws paqueteNoExisteException, departamentoNoExisteException, actividadNoExisteException;

	public abstract void ingresarDatosPaquete(String nombrePaq, String descripcion, int validez, float descuento, GregorianCalendar fechaAlta, String linkImagen) throws paqueteYaExisteException;
	
	public abstract DTPaquete obtenerDatosPaquete(String nombrePaq) throws paqueteNoExisteException;
	
	public abstract Set<DTPaquete> obtenerPaquetesDisponibles(String nickname, String nombreSalida, int cantTuristas) throws usuarioNoExisteException;

	public abstract Set<DTPaquete> obtenerPaquetesConActividades();
	
	public abstract void comprarPaquete(String nickname, String nombrePaq, GregorianCalendar fechaCompra, int cantidadTuristas) throws usuarioNoExisteException, paqueteNoExisteException, compraExisteException;
	
	public abstract Set<DTPaquete> obtenerPaquetesNoComprados();
	
	public abstract Set<DTActividad> obtenerDatosActividadesConfirmadasNoPaquete(String nombreDpto, String nombrePaq) throws departamentoNoExisteException, paqueteNoExisteException;

	public abstract Set<DTPaquete> obtenerDatosPaquetesParaActividad(String nombreAct);
	
	public abstract Set<DTPaquete> obtenerPaquetesAll();
	
	public abstract Set<DTActividad> obtenerActividadesPaquete(String nombrePaq) throws paqueteNoExisteException;
	
	public abstract Set<String> obtenerCategoriasPaquete(String nombrePaq) throws paqueteNoExisteException;

}
