package logica.datatypes;

import java.util.GregorianCalendar;

public class DTTurista extends DTUsuario{
	
	private String nacionalidad;
	
	public DTTurista(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String contrasena, String linkImagen, String nacionalidad) {
		super(nickname, nombre, apellido, correo, nacimiento, contrasena, linkImagen);
		this.setNacionalidad(nacionalidad);
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
}
