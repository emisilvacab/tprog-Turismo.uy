package datatypes;

import java.util.GregorianCalendar;

public class DTActividad{
	
	private String nombre;
	private String descripcion;
	private int duracion;
	private float costo;
	private String ciudad;
	private GregorianCalendar alta;
	
	
	public DTActividad() {
		this.setNombre(new String());
		this.setDescripcion(new String());
		this.setDuracion(0);
		this.setCosto(0);
		this.setCiudad(new String());
		this.setAlta(new GregorianCalendar());
	}
	
	public DTActividad(String nombre, String descripcion, int duracion, Float costo, String ciudad, GregorianCalendar alta) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setDuracion(duracion);
		this.setCosto(costo);
		this.setCiudad(ciudad);
		this.setAlta(alta);
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
	
}