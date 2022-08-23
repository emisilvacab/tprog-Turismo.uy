package logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class Paquete{
	
	private String nombre;
	private String descripcion;
	private int validez;
	private float descuento;
	
	private Vector<Compra> compras;//no se si es necesario
	private Map<String, Actividad> actividades;
	
	public Paquete(String nombre, String descripcion, int validez, float descuento, Map<String, Actividad> actividades) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.validez = validez;
		this.descuento = descuento;
		this.actividades = actividades;
		this.compras = new Vector<Compra>();
	}
	
	public Paquete(String nombre, String descripcion, int validez, float descuento) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.validez = validez;
		this.descuento = descuento;
		this.actividades = new HashMap<String, Actividad>();
		this.compras = new Vector<Compra>();
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

	public Vector<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Vector<Compra> compras) {
		this.compras = compras;
	}
	
	public void addCompra(Compra compra) {
		this.compras.add(compra);
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

	public Set<String> obtenerNombresSalidasAsociadas(Set<String> actividades) {
		Set<String> salidas = new HashSet<String>();
		for (Actividad act: this.actividades.values()) {
			salidas.addAll(act.obtenerNombresSalidasAsociadas());
			}
				
		return salidas;
	
		
	}
}