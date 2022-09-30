package logica.controladores;

import java.util.GregorianCalendar;
import java.util.HashSet;

import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.paqueteNoExisteException;
import excepciones.paqueteYaExisteException;
import logica.Actividad;
import logica.Departamento;
import logica.Paquete;
import logica.Turista;

import logica.datatypes.DTPaquete;

import logica.manejadores.ManejadorDepartamentoCategoria;
import logica.manejadores.ManejadorPaquete;
import logica.manejadores.ManejadorUsuario;

public class ControladorPaquete implements IControladorPaquete {
	
	public void agregarActividadPaquete(String nombreDpto, String nombrePaq, String nombreAct) throws paqueteNoExisteException, departamentoNoExisteException, actividadNoExisteException {
		ManejadorDepartamentoCategoria md = ManejadorDepartamentoCategoria.getInstance();
		ManejadorPaquete mp = ManejadorPaquete.getInstance();
		
		Paquete paq = mp.getPaquete(nombrePaq);
		if (paq == null)
			throw new paqueteNoExisteException("No existe el paquete ingresado");
		
		Departamento dpto = md.getDepartamento(nombreDpto);
		if (dpto == null)
			throw new departamentoNoExisteException("No existe el departamento ingresado");
		
		Actividad act = dpto.getActividades().get(nombreAct);
		if (act == null)
			throw new actividadNoExisteException("No existe la actividad ingresada");
		
		paq.addActividad(act);
		act.addPaquete(paq);
	}
	
	public void ingresarDatosPaquete(String nombrePaq, String descripcion, int validez, float descuento, GregorianCalendar fechaAlta) throws paqueteYaExisteException{
		ManejadorPaquete mp = ManejadorPaquete.getInstance();
		Paquete existe = mp.getPaquete(nombrePaq);
		if (existe != null)
			throw new paqueteYaExisteException("Ya existe el paquete ingresado");
		Paquete nuevo = new Paquete(nombrePaq, descripcion, validez, descuento, fechaAlta);
		mp.addPaquete(nuevo);
	}
	
	public DTPaquete obtenerDatosPaquete(String nombrePaq) throws paqueteNoExisteException{
		ManejadorPaquete mp = ManejadorPaquete.getInstance();
		Paquete paq = mp.getPaquete(nombrePaq);
		if (paq == null)
			throw new paqueteNoExisteException("No existe el paquete ingresado");
		DTPaquete datos = new DTPaquete(paq.getNombre(), paq.getDescripcion(), paq.getValidez(), paq.getDescuento(), paq.getFechaAlta());
		return datos;
	}
	
	public HashSet<DTPaquete> obtenerPaquetesDisponibles(String nickname, String nombreSalida, int cantTuristas){
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Turista turista = mu.getTurista(nickname);
		return turista.obtenerPaquetesDisponibles(nombreSalida, cantTuristas);	
	}

}
























