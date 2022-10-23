package logica;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import logica.datatypes.DTPaquete;

public class Paquete {
	
	private String nombre;
	private  String descripcion;
	private int validez;
	private float descuento;
	private GregorianCalendar fechaAlta;
	private String linkImagen;
	
	private Map<String, Actividad> actividades;
	private Map<String, Categoria> categorias;
	private Set<Compra> compras;
	
	
	public Paquete(String nombre, String descripcion, int validez, float descuento, GregorianCalendar fechaAlta, String linkImagen) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setValidez(validez);
		this.setDescuento(descuento);
		this.setActividades(new HashMap<String, Actividad>());
		this.setCategorias(new HashMap<String, Categoria>());
		this.setCompras(new HashSet<Compra>());
		this.setFechaAlta(fechaAlta);
		this.setLinkImagen(linkImagen);
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

	public Map<String, Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(Map<String, Actividad> actividades) {
		this.actividades = actividades;
	}
	
	public void addActividad(Actividad act) {
		actividades.put(act.getNombre(), act);
	}

	public Map<String, Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(Map<String, Categoria> categorias) {
		this.categorias = categorias;
	}

	public DTPaquete getDatos() {
		return new DTPaquete(nombre, descripcion, validez, descuento, fechaAlta, linkImagen);
	}

	public Set<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	public void addCompra(Compra compra) {
		compras.add(compra);
	}

	public String getLinkImagen() {
		return linkImagen;
	}

	public void setLinkImagen(String linkImagen) {
		this.linkImagen = linkImagen;
	}


}
