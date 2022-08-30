package logica;

import java.util.GregorianCalendar;
import java.util.Vector;
//import java.util.Map;

import logica.datatypes.DTSalida;

public class Salida{
	
	private String nombre;
	private int maxTuristas;
	private GregorianCalendar alta;
	private GregorianCalendar fechaSalida;
	private int hora;
	private String lugarSalida;
	
	private Actividad actividad;
	private Vector<Inscripcion> inscripciones;
	//private Map<String, Inscripcion> inscripciones; MAP O VECTOR?

	public Salida(String nombre, int maxTuristas, GregorianCalendar alta, GregorianCalendar fechaSalida, int hora, String lugarSalida, Actividad actividad) {
		this.nombre = nombre;
		this.maxTuristas = maxTuristas;//Al inscribir turistas decrementamos este numero o agregamos atributo con cantidadInscriptos
		this.alta = alta;
		this.fechaSalida = fechaSalida;
		this.hora = hora;
		this.lugarSalida = lugarSalida;
		this.actividad = actividad;
		this.inscripciones = new Vector<Inscripcion>();
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
		this.inscripciones.add(inscripcion);
	}

	public boolean admiteCapacidad(int capacidad) {
		int total = 0;
		for(int i = 0; i < inscripciones.size(); i++) {
			total += inscripciones.get(i).getCantTuristas();
		}
		return (total + capacidad <= maxTuristas);
	}

	public boolean existeInscripcion(String nickname) {
		int i = 0;
		while (i < inscripciones.size() && (inscripciones.get(i).getNicknameInscripto() != nickname))
			i++;
		return (i < inscripciones.size());
	}

	public DTSalida getDatos() {
		DTSalida res = new DTSalida(nombre,maxTuristas,alta,fechaSalida,hora,lugarSalida);
		return res;
	}

	public float getCostoActividad() {
		return actividad.getCosto();
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}
	
}