package logica;

import java.util.HashMap;

public class Paquete {
	
	public String nombre;
	public String descripcion;
	public int validez;
	public float descuento;
	
	public HashMap<String,Actividad> actividades;
	
	public Paquete(String nombre, String descripcion, int validez, float descuento,HashMap<String, Actividad> actividades) {
		setNombre(nombre);
		setDescripcion(descripcion);
		setValidez (validez);
		setDescuento (descuento);
		setActividades (actividades);
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

}
