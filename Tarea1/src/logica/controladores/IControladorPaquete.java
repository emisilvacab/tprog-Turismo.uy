package logica.controladores;

import java.util.GregorianCalendar;
import java.util.HashSet;

import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.paqueteNoExisteException;
import excepciones.paqueteYaExisteException;
import logica.datatypes.DTPaquete;

public interface IControladorPaquete {
	
	public abstract void agregarActividadPaquete(String nombreDpto, String nombrePaq, String nombreAct) throws paqueteNoExisteException, departamentoNoExisteException, actividadNoExisteException;

	public abstract void ingresarDatosPaquete(String nombrePaq, String descripcion, int validez, float descuento, GregorianCalendar fechaAlta) throws paqueteYaExisteException;
	
	public abstract DTPaquete obtenerDatosPaquete(String nombrePaq) throws paqueteNoExisteException;
	
	public abstract HashSet<DTPaquete> obtenerPaquetesDisponibles(String nickname, String nombreSalida, int cantTuristas);
}
