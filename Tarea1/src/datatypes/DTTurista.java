package datatypes;

import java.util.GregorianCalendar;

public class DTTurista extends DTUsuario{
	
	private String nacionalidad;
	
	public DTTurista() {
		super(new String(), new String(), new String(), new String(), new GregorianCalendar());
		this.setNacionalidad(new String());
	}
	
	public DTTurista(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String nacionalidad) {
		super(nickname, nombre, apellido, correo, nacimiento);
		this.setNacionalidad(nacionalidad);
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
}
