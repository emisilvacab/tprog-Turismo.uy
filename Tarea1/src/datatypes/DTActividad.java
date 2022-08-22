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
	
	private DTDepartamento departamento;
	private DTProveedor proveedor;
	private Map<String, DTPaquete> paquetes;	
	private Map<String, DTSalida> salidas;	
	
	public DTActividad(String nombre, String descripcion, int duracion, Float costo, String ciudad, GregorianCalendar alta, DTDepartamento departamento, DTProveedor proveedor) {
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

	public DTDepartamento getDTDepartamento() {
		return departamento;
	}
	
	public void setDTDepartamento(DTDepartamento departamento) {
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
	
	public DTProveedor getDTProveedor() {
		return proveedor;
	}

	public void setDTProveedor(DTProveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Map<String, DTPaquete> getDTPaquetes() {
		return paquetes;
	}

	public void setDTPaquetes(Map<String, DTPaquete> paquetes) {
		this.paquetes = paquetes;
	}
	
	public void addDTPaquete(DTPaquete paquete) {
		this.paquetes.put(paquete.getNombre(), paquete);
	}

	public Map<String, DTSalida> getDTSalidas() {
		return salidas;
	}

	public void setDTSalidas(Map<String, DTSalida> salidas) {
		this.salidas = salidas;
	}

	public void addDTSalida(DTSalida salida) {
		this.salidas.put(salida.getNombre(), salida);
	}
	
}