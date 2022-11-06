package logica.datatypes;


import java.util.GregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTPaquete {
	
	private String nombre;
	private String descripcion;
	private int validez;
	private float descuento;
	private GregorianCalendar fechaAlta;
	private String linkImagen;
	
	public DTPaquete() {}
	
	public DTPaquete(String nombre, String descripcion, int validez, float descuento, GregorianCalendar fechaAlta, String linkImagen) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setValidez(validez);
		this.setDescuento(descuento);
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

	public String getLinkImagen() {
		return linkImagen;
	}

	public void setLinkImagen(String linkImagen) {
		this.linkImagen = linkImagen;
	}

}
