package logica;

import java.util.GregorianCalendar;
import java.util.Map;

public class Proveedor extends Usuario{
	
	private String descripcion;
	private String link;
	
	private Map<String, Actividad> actividades;
	
	public Proveedor(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String descripcion) {
		super(nickname, nombre, apellido, correo, nacimiento);
		this.descripcion = descripcion;
		this.link = "";
		this.actividades = null;
	}

	public Proveedor(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String descripcion, String link) {
		super(nickname, nombre, apellido, correo, nacimiento);
		this.descripcion = descripcion;
		this.link = link;
		this.actividades = null;

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

	public Map<String, Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(Map<String, Actividad> actividades) {
		this.actividades = actividades;
	}
	
	public void addActividad(Actividad actividad) {
		this.actividades.put(actividad.getNombre(), actividad);
	}
}