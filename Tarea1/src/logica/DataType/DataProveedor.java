package logica.DataType;

import java.util.GregorianCalendar;

public class DataProveedor extends DataUsuario{
	private String descripcion;
	private String link;
	
	public DataProveedor(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String descripcion, String link) {
		super(nickname, nombre, apellido, correo, nacimiento);
		this.setDescripcion(descripcion);
		this.setLink("");
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
