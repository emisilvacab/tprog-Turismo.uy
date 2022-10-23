package logica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import excepciones.actividadNoExisteException;
import logica.datatypes.DTActividad;
import logica.datatypes.DTSalida;

public class Departamento{
	
	private String nombre;
	private String descripcion;
	private String url;
	
	private Map<String, Actividad> actividades;

	
	public Departamento(String nombre, String descripcion, String url) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setUrl(url);
		this.setActividades(new HashMap<String, Actividad>());
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
	
	public Set<DTActividad> obtenerDatosActividades(){
		Set<DTActividad> res = new HashSet<DTActividad>();
		actividades.forEach((key, value)-> {
			res.add(actividades.get(key).getDatos());
		});
		return res;
	}

	public Set<DTSalida> obtenerDatosSalidasVigentes(String nombreAct) throws actividadNoExisteException {
		Actividad act = actividades.get(nombreAct);
		if (act == null)
			throw new actividadNoExisteException("No se encontró una actividad con el nombre ingresado");
		return act.obtenerSalidasVigentes();
	}
	
	public Actividad obtenerActividad(String nombreAct) {
		return actividades.get(nombreAct);
	}  

	public Set<Actividad> getActividadesConfirmadas() {
		Set<Actividad> res = new HashSet<Actividad>();
		for (Actividad act : actividades.values()) {
	        if (act.getEstado() == Estado.CONFIRMADA)
	        	res.add(act);
	    }
		return res;
	}
	
	public void modificarEstadoActividad(String nombreAct, Estado estado) {
		actividades.get(nombreAct).setEstado(estado);
	}
	
}