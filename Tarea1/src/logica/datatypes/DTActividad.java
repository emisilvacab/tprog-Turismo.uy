package logica.datatypes;

import java.util.GregorianCalendar;

import logica.Estado;

public class DTActividad{
	
	private String nombre;
	private String descripcion;
	private int duracion;
	private float costo;
	private String ciudad;
	private GregorianCalendar alta;
	private Estado estado;
	private String linkImagen;
	private String linkVideo;
	
	
	public DTActividad(String nombre, String descripcion, int duracion, float costo, String ciudad, GregorianCalendar alta, Estado estado, String linkImagen, String linkVideo) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setDuracion(duracion);
		this.setCosto(costo);
		this.setCiudad(ciudad);
		this.setAlta(alta);
		this.setEstado(estado);
		this.setLinkImagen(linkImagen);
		this.setLinkVideo(linkVideo);
	}
	
	public String getLinkVideo() {
		return linkVideo;
	}

	public void setLinkVideo(String linkVideo) {
		this.linkVideo = linkVideo;
	}

	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
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

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public GregorianCalendar getAlta() {
		return alta;
	}

	public void setAlta(GregorianCalendar alta) {
		this.alta = alta;
	}

	public String getLinkImagen() {
		return linkImagen;
	}

	public void setLinkImagen(String linkImagen) {
		this.linkImagen = linkImagen;
	}
	
}