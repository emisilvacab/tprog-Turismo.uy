package logica;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import logica.datatypes.DTProveedor; 
import logica.datatypes.DTUsuario;

public class Proveedor extends Usuario{
	
	private String descripcion;
	private String link;
	
	private Map<String, Actividad> actividades;
	
	public Proveedor(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String contrasena,String descripcion) {
		super(nickname, nombre, apellido, correo, nacimiento, contrasena);
		this.setDescripcion(descripcion);
		this.setLink(new String());
		this.setActividades(new HashMap<String, Actividad>());
	}

	public Proveedor(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String contrasena,String descripcion, String link) {
		super(nickname, nombre, apellido, correo, nacimiento, contrasena);
		this.setDescripcion(descripcion);
		this.setLink(link);
		this.setActividades(new HashMap<String, Actividad>());

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

	public DTUsuario getDatos() {
		return new DTProveedor(this.getNickname(),this.getNombre(),this.getApellido(),this.getCorreo(),this.getNacimiento(),this.getContrasena(),this.getDescripcion(),this.getLink());
	}
}