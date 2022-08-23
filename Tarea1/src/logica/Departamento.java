package logica;

import java.util.HashMap;
import java.util.Map;

public class Departamento{
	
	private String nombre;
	private String descripcion;
	private String url;
	
	private Map<String, Actividad> actividades;

	
	public Departamento(String nombre, String descripcion, String url) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
		this.actividades = new HashMap<String, Actividad>();
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getUrl() {
		return url;
	}
	
	//SE PUEDE MODIFICAR PARA QUE DEVUELVA SOLO NOMBRES/IDENTIFICADORES
	public Map<String, Actividad> getActividades() {
		return actividades;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setActividades(Map<String, Actividad> actividades) {
		this.actividades = actividades;
	}
	
	public void addActividad(Actividad actividad) {
		this.actividades.put(actividad.getNombre(), actividad);
	}
	
}