package logica;

import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import logica.datatypes.DTActividad;
import logica.datatypes.DTSalida;

public class Actividad{
	
	private String nombre;
	private String descripcion;
	private int duracion;
	private float costo;
	private String ciudad;
	private GregorianCalendar alta;
	
	private Departamento departamento;
	private Proveedor proveedor;
	private Map<String, Salida> salidas;	
	
	public Actividad(String nombre, String descripcion, int duracion, float costo, String ciudad, GregorianCalendar alta, Departamento departamento, Proveedor proveedor) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.costo = costo;
		this.ciudad = ciudad;
		this.alta = alta;
		this.departamento = departamento;
		this.proveedor = proveedor;
		this.salidas = new HashMap<String, Salida>();
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

	public Map<String, Salida> getSalidas() {
		return salidas;
	}

	public void setSalidas(Map<String, Salida> salidas) {
		this.salidas = salidas;
	}

	public void addSalida(Salida salida) {
		this.salidas.put(salida.getNombre(), salida);
	}

	public Set<String> obtenerNombresSalidasAsociadas() {
		Set<String> res = new HashSet<String>();
		for (Salida sal: this.getSalidas().values()) {
			res.add(sal.getNombre());
			}
				
		return res;
	
	}

	public DTActividad getDatos() {
		DTActividad res = new DTActividad(nombre,descripcion,duracion,costo,ciudad,alta);
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
	
}