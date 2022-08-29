package logica.datatypes;

import java.util.GregorianCalendar;

public class DTProveedor extends DTUsuario{
	
	private String descripcion;
	private String link;
	
	public DTProveedor() {
		super(new String(), new String(), new String(), new String(), new GregorianCalendar());
		this.setDescripcion(new String());
		this.setLink(new String());
	}
	
	public DTProveedor(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String descripcion) {
		super(nickname, nombre, apellido, correo, nacimiento);
		this.setDescripcion(descripcion);
		this.setLink(new String());
	}

	public DTProveedor(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String descripcion, String link) {
		super(nickname, nombre, apellido, correo, nacimiento);
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