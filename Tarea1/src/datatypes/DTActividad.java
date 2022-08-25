package datatypes;

import java.util.GregorianCalendar;
import java.util.Map;

public class DTActividad{
	
	private String nombre;
	private String descripcion;
	private int duracion;
	private Float costo;
	private String ciudad;
	private GregorianCalendar alta;
	
	
	
	public DTActividad(String nombre, String descripcion, int duracion, Float costo, String ciudad, GregorianCalendar alta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.costo = costo;
		this.ciudad = ciudad;
		this.alta = alta;
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

	public Float getCosto() {
		return costo;
	}

	public void setCosto(Float costo) {
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