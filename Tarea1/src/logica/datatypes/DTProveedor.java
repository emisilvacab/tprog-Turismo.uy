package logica.datatypes;

import java.util.GregorianCalendar;

public class DTProveedor extends DTUsuario{
	
	private String descripcion;
	private String link;
	
	public DTProveedor(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String contrasena, String descripcion) {
		super(nickname, nombre, apellido, correo, nacimiento, contrasena);
		this.setDescripcion(descripcion);
		this.setLink(new String());
	}

	public DTProveedor(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String contrasena, String descripcion, String link) {
		super(nickname, nombre, apellido, correo, nacimiento, contrasena);
		this.setDescripcion(descripcion);
		this.setLink(link);
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