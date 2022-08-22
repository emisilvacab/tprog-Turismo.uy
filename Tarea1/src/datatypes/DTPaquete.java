package datatypes;

import java.util.Map;
import java.util.Vector;

public class DTPaquete{
	
	private String nombre;
	private String descripcion;
	private int validez;
	private float descuento;
	
	private Vector<DTCompra> compras;//no se si es necesario
	private Map<String, DTActividad> actividades;
	
	public DTPaquete(String nombre, String descripcion, int validez, float descuento, Map<String, DTActividad> actividades) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.validez = validez;
		this.descuento = descuento;
		this.actividades = actividades;
		this.compras = null;
	}
	
	public DTPaquete(String nombre, String descripcion, int validez, float descuento) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.validez = validez;
		this.descuento = descuento;
		this.actividades = null;
		this.compras = null;
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

	public Vector<DTCompra> getDTCompras() {
		return compras;
	}

	public void setDTCompras(Vector<DTCompra> compras) {
		this.compras = compras;
	}
	
	public void addDTCompra(DTCompra compra) {
		this.compras.add(compra);
	}

	public Map<String, DTActividad> getDTActividades() {
		return actividades;
	}

	public void setDTActividades(Map<String, DTActividad> actividades) {
		this.actividades = actividades;
	}
	
	public void addDTActividad(DTActividad actividad) {
		this.actividades.put(actividad.getNombre(), actividad);
	}
}