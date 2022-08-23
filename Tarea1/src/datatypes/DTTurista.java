package datatypes;

import java.util.GregorianCalendar;
import java.util.Vector;

import logica.Compra;
import logica.Inscripcion;

public class DTTurista extends DTUsuario{
	
	private String nacionalidad;
	
	public DTTurista(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String nacionalidad) {
		super(nickname, nombre, apellido, correo, nacimiento);
		this.nacionalidad = nacionalidad;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
}
