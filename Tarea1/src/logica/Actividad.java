package logica;

import java.util.GregorianCalendar;
import java.util.Map;

public class Actividad{
	
	private String nombre;
	private String descripcion;
	private int duracion;
	private Float costo;
	private String ciudad;
	private GregorianCalendar alta;
	
	private Departamento departamento;
	private Proveedor proveedor;
	private Map<String, Paquete> paquetes;	
	private Map<String, Salida> salidas;	
	
	public Actividad(String nombre, String descripcion, int duracion, Float costo, String ciudad, GregorianCalendar alta, Departamento departamento, Proveedor proveedor) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.costo = costo;
		this.ciudad = ciudad;
		this.alta = alta;
		this.departamento = departamento;
		this.proveedor = proveedor;
		this.paquetes = null;
		this.salidas = null;
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

	public Departamento getDepartamento() {
		return departamento;
	}
	
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
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
	
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Map<String, Paquete> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(Map<String, Paquete> paquetes) {
		this.paquetes = paquetes;
	}
	
	public void addPaquete(Paquete paquete) {
		this.paquetes.put(paquete.getNombre(), paquete);
	}

	public Map<String, Salida> getSalidas() {
		return salidas;
	}

	public void setSalidas(Map<String, Salida> salidas) {
		this.salidas = salidas;
	}

	public void addSalida(Salida salida) {
		this.salidas.put(salida.getNombre(), salida);
	}
	
}