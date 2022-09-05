package logica;

import java.util.GregorianCalendar;
import java.util.HashMap;

public class Paquete {
	
	public String nombre;
	public String descripcion;
	public int validez;
	public float descuento;
	public GregorianCalendar fechaAlta;
	
	public HashMap<String,Actividad> actividades;
	
	public Paquete(String nombre, String descripcion, int validez, float descuento, GregorianCalendar fechaAlta) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setValidez (validez);
		this.setDescuento (descuento);
		this.setActividades (new HashMap<String, Actividad>());
		this.setFechaAlta(fechaAlta);
	}

	public GregorianCalendar getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(GregorianCalendar fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getValidez() {
		return validez;
	}

	public void setValidez(int validez) {
		this.validez = validez;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public HashMap<String, Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(HashMap<String, Actividad> actividades) {
		this.actividades = actividades;
	}
	
	public void addActividad(Actividad act) {
		actividades.put(act.getNombre(), act);
	}

}