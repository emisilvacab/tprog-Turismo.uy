package logica.datatypes;


import java.awt.Image;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class DTPaquete {
	
	private String nombre;
	private String descripcion;
	private int validez;
	private float descuento;
	private GregorianCalendar fechaAlta;
	private Image figura;
	
	public DTPaquete(String nombre, String descripcion, int validez, float descuento, GregorianCalendar fechaAlta) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setValidez (validez);
		this.setDescuento (descuento);
		this.setFechaAlta(fechaAlta);
		this.setFigura(null);
	}

	public Image getFigura() {
		return figura;
	}

	public void setFigura(Image figura) {
		this.figura = figura;
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

}
