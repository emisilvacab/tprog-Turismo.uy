package logica;

import java.util.GregorianCalendar;
import java.util.Vector;

public class Turista extends Usuario{
	
	private String nacionalidad;
	
	private Vector<Inscripcion> inscripciones;
	
	public Turista(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String nacionalidad) {
		super(nickname, nombre, apellido, correo, nacimiento);
		this.setNacionalidad(nacionalidad);
		this.setInscripciones(new Vector<Inscripcion>());
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
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
}