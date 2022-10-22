package logica;

import java.awt.Image;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import logica.datatypes.DTPaquete;

public class Paquete {
	
	private String nombre;
	private  String descripcion;
	private int validez;
	private float descuento;
	private GregorianCalendar fechaAlta;
	private Image figura;
	
	private HashMap<String, Actividad> actividades;
	private HashMap<String, Categoria> categorias;
	private Vector<Compra> compras;
	
	
	public Paquete(String nombre, String descripcion, int validez, float descuento, GregorianCalendar fechaAlta) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setValidez(validez);
		this.setDescuento(descuento);
		this.setActividades(new HashMap<String, Actividad>());
		this.setCategorias(new HashMap<String, Categoria>());
		this.setCompras(new Vector<Compra>());
		this.setFechaAlta(fechaAlta);
		this.setFigura(null);
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

	public HashMap<String, Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(HashMap<String, Categoria> categorias) {
		this.categorias = categorias;
	}

	public DTPaquete getDatos() {
		return new DTPaquete(nombre, descripcion, validez, descuento, fechaAlta);
	}

	public Vector<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Vector<Compra> compras) {
		this.compras = compras;
	}

	public void addCompra(Compra compra) {
		compras.add(compra);
	}

	public Image getFigura() {
		return figura;
	}

	public void setFigura(Image figura) {
		this.figura = figura;
	}

}
