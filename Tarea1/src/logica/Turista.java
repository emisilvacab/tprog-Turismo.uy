package logica;

import java.util.GregorianCalendar;
import java.util.Vector;

public class Turista extends Usuario{
	
	private String nacionalidad;
	
	private Vector<Inscripcion> inscripciones;
	
	//parece que se hace asi, si lo hago como abajo me tira que tengo que invocar al super (constructor de Usuario aunque sea abstracta Usuario)
	public Turista(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String nacionalidad) {
		super(nickname, nombre, apellido, correo, nacimiento);
		this.nacionalidad = nacionalidad;
		this.inscripciones = new Vector<Inscripcion>();
		// TODO Auto-generated constructor stub
	}

/*
	public Turista(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String nacionalidad) {
		this.setNickname(nickname);
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setCorreo(correo);
		this.setNacimiento(nacimiento);
		this.nacionalidad = nacionalidad;
	}
*/
	
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