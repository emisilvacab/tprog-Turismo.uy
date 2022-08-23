package datatypes;

import java.util.GregorianCalendar;
import java.util.Map;

import logica.Actividad;

public class DTProveedor extends DTUsuario{
	
	private String descripcion;
	private String link;
	
	public DTProveedor(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String descripcion) {
		super(nickname, nombre, apellido, correo, nacimiento);
		this.descripcion = descripcion;
		this.link = "";
	}

	public DTProveedor(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String descripcion, String link) {
		super(nickname, nombre, apellido, correo, nacimiento);
		this.descripcion = descripcion;
		this.link = link;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}