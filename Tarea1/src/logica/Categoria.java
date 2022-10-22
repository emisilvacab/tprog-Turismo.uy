package logica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import excepciones.actividadNoExisteException;
import logica.datatypes.DTSalida;

public class Categoria {
	
	private String nombre;
	private Map<String, Actividad> actividades;
	private Map<String, Paquete> paquetes;
	
	public Categoria(String nombre) {
		this.setNombre(nombre);
		this.setActividades(new HashMap<String, Actividad>());
		this.setPaquetes(new HashMap<String, Paquete>());
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Map<String, Actividad> getActividades() {
		return actividades;
	}
	
	public void setActividades(Map<String, Actividad> actividades) {
		this.actividades = (HashMap<String, Actividad>) actividades;
	}
	
	public Map<String, Paquete> getPaquetes() {
		return paquetes;
	}
	
	public void setPaquetes(Map<String, Paquete> paquetes) {
		this.paquetes = (HashMap<String, Paquete>) paquetes;
	}
	
	public void addPaquete(Paquete paquete) {
		paquetes.put(paquete.getNombre(), paquete);
	}
	
	public void addActividad(Actividad actividad) {
		actividades.put(actividad.getNombre(), actividad);
	}

	public Set<Actividad> getActividadesConfirmadas() {
		Set<Actividad> res = new HashSet<Actividad>();
		for (Actividad act : actividades.values()) {
	        if (act.getEstado() == Estado.CONFIRMADA)
	        	res.add(act);
	    }
		return res;
	}

	public Set<DTSalida> obtenerDatosSalidasVigentes(String nombreAct) throws actividadNoExisteException {
		Actividad act = actividades.get(nombreAct);
		if (act == null)
			throw new actividadNoExisteException("No se encontró una actividad con el nombre ingresado");
		return act.obtenerSalidasVigentes();
	}
}
