package datatypes;

import java.util.Map;

public class DTDepartamento{
	
	private String nombre;
	private String descripcion;
	private String url;
	
	private Map<String, DTActividad> actividades;

	
	public DTDepartamento(String nombre, String descripcion, String url) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
		this.actividades = null;
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
	public Map<String, DTActividad> getDTActividades() {
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
	
	public void setDTActividades(Map<String, DTActividad> actividades) {
		this.actividades = actividades;
	}
	
	public void addDTActividad(DTActividad actividad) {
		this.actividades.put(actividad.getNombre(), actividad);
	}
	
}