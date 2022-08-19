package logica;

import java.util.GregorianCalendar;
import java.util.Vector;
//import java.util.Map;

public class Salida{
	
	private String nombre;
	private int maxTuristas;
	private GregorianCalendar alta;
	private GregorianCalendar fechaSalida;
	private String lugarSalida;
	
	private Actividad actividad;
	private Vector<Inscripcion> inscripciones;
	//private Map<String, Inscripcion> inscripciones; MAP O VECTOR?

	public Salida(String nombre, int maxTuristas, GregorianCalendar alta, GregorianCalendar fechaSalida, String lugarSalida, Actividad actividad) {
		this.nombre = nombre;
		this.maxTuristas = maxTuristas;//Al inscribir turistas decrementamos este numero o agregamos atributo con cantidadInscriptos
		this.alta = alta;
		this.fechaSalida = fechaSalida;
		this.lugarSalida = lugarSalida;
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

	public GregorianCalendar getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(GregorianCalendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getLugarSalida() {
		return lugarSalida;
	}

	public void setLugarSalida(String lugarSalida) {
		this.lugarSalida = lugarSalida;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Vector<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(Vector<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}
	
	public void addInscripcion(Inscripcion inscripcion) {
		//fijarse si se puede inscribir esto no se si se hace aca o en controlador/manejador
		this.inscripciones.add(inscripcion);
	}
	
}