package logica;

import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import logica.datatypes.DTActividad;
import logica.datatypes.DTSalida;

public class Actividad{
	
	private String nombre;
	private String descripcion;
	private int duracion;
	private float costo;
	private String ciudad;
	private GregorianCalendar alta;
	private Estado estado;
	
	private Departamento departamento;
	private Proveedor proveedor;
	private Map<String, Salida> salidas;
	private Map<String, Paquete> paquetes;
	
	public Actividad(String nombre, String descripcion, int duracion, float costo, String ciudad, GregorianCalendar alta, Departamento departamento, Proveedor proveedor) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setDuracion(duracion);
		this.setCosto(costo);
		this.setCiudad(ciudad);
		this.setAlta(alta);
		this.setDepartamento(departamento);
		this.setProveedor(proveedor);
		this.setSalidas(new HashMap<String, Salida>());
		this.setPaquetes(new HashMap<String,Paquete>());
		this.setEstado(Estado.AGREGADA);
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
	
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
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

	public Map<String, Paquete> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(Map<String, Paquete> paquetes) {
		this.paquetes = paquetes;
	}
	
	public void addPaquete(Paquete paquete) {
		this.paquetes.put(paquete.getNombre(), paquete);
	}

	public DTActividad getDatos() {
		DTActividad res = new DTActividad(nombre,descripcion,duracion,costo,ciudad,alta, estado);
		return res;
	}

	public HashSet<DTSalida> obtenerSalidasVigentes() {
		HashSet<DTSalida> res = new HashSet<DTSalida>();
		salidas.forEach((key,value)->{
			if(value.getFechaSalida().after(GregorianCalendar.from(ZonedDateTime.now())))
				res.add(value.getDatos());				
					});
		return res;
	}

	public boolean existeSalida(String nombre) {
		Salida sal = salidas.get(nombre);
		if (sal == null)
			return false;
		else 
			return true;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}