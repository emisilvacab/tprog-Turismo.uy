package datatypes;

import java.util.GregorianCalendar;
import java.util.Vector;

import logica.Compra;
import logica.Inscripcion;

public class DTTurista extends DTUsuario{
	
	private String nacionalidad;
	
	private Vector<Compra> compras;
	private Vector<Inscripcion> inscripciones;
	
	public DTTurista(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String nacionalidad) {
		super(nickname, nombre, apellido, correo, nacimiento);
		this.nacionalidad = nacionalidad;
		this.compras = null;
		this.inscripciones = null;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Vector<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Vector<Compra> compras) {
		this.compras = compras;
	}
	
	public void addCompra(Compra compra) {
		this.compras.add(compra);
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
