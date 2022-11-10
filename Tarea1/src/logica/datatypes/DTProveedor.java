package logica.datatypes;

import java.util.GregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DTProveedor extends DTUsuario{
	
	private String descripcion;
	private String link;
	
	public DTProveedor() {}
	
	public DTProveedor(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String contrasena, String linkImagen, String descripcion) {
		super(nickname, nombre, apellido, correo, nacimiento, contrasena, linkImagen);
		this.setDescripcion(descripcion);
		this.setLink(new String());
	}

	public DTProveedor(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String contrasena, String linkImagen, String descripcion, String link) {
		super(nickname, nombre, apellido, correo, nacimiento, contrasena, linkImagen);
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