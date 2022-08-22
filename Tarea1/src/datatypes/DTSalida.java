package datatypes;

import java.util.GregorianCalendar;
import java.util.Vector;

public class DTSalida{
	
	private String nombre;
	private int maxTuristas;
	private GregorianCalendar alta;
	private GregorianCalendar fechaDTSalida;
	private String lugarDTSalida;
	
	private DTActividad actividad;
	private Vector<DTInscripcion> inscripciones;

	public DTSalida(String nombre, int maxTuristas, GregorianCalendar alta, GregorianCalendar fechaDTSalida, String lugarDTSalida, DTActividad actividad) {
		this.nombre = nombre;
		this.maxTuristas = maxTuristas;
		this.alta = alta;
		this.fechaDTSalida = fechaDTSalida;
		this.lugarDTSalida = lugarDTSalida;
		this.actividad = actividad;
		this.inscripciones = null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMaxTuristas() {
		return maxTuristas;
	}

	public void setMaxTuristas(int maxTuristas) {
		this.maxTuristas = maxTuristas;
	}

	public GregorianCalendar getAlta() {
		return alta;
	}

	public void setAlta(GregorianCalendar alta) {
		this.alta = alta;
	}

	public GregorianCalendar getFechaDTSalida() {
		return fechaDTSalida;
	}

	public void setFechaDTSalida(GregorianCalendar fechaDTSalida) {
		this.fechaDTSalida = fechaDTSalida;
	}

	public String getLugarDTSalida() {
		return lugarDTSalida;
	}

	public void setLugarDTSalida(String lugarDTSalida) {
		this.lugarDTSalida = lugarDTSalida;
	}

	public DTActividad getDTActividad() {
		return actividad;
	}

	public void setDTActividad(DTActividad actividad) {
		this.actividad = actividad;
	}

	public Vector<DTInscripcion> getDTInscripciones() {
		return inscripciones;
	}

	public void setDTInscripciones(Vector<DTInscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}
	
	public void addDTInscripcion(DTInscripcion inscripcion) {
		//fijarse si se puede inscribir esto no se si se hace aca o en controlador/manejador
		this.inscripciones.add(inscripcion);
	}
	
}