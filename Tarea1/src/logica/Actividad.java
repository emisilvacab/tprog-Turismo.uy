package logica;

import java.awt.Image;
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
	private Estado estado;
	private Image figura;
	private String linkImagen;
	private String linkVideo;
	
	private Departamento departamento;
	private Proveedor proveedor;
	private Map<String, Salida> salidas;
	private Map<String, Paquete> paquetes;
	private Set<Cuponera> cuponeras;
	private Map<String, Categoria> categorias;
	
	public Actividad(String nombre, String descripcion, int duracion, float costo, String ciudad, GregorianCalendar alta, Departamento departamento, Proveedor proveedor, String linkImagen, String linkVideo) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setDuracion(duracion);
		this.setCosto(costo);
		this.setCiudad(ciudad);
		this.setAlta(alta);
		this.setDepartamento(departamento);
		this.setProveedor(proveedor);
		this.setSalidas(new HashMap<String, Salida>());
		this.setPaquetes(new HashMap<String, Paquete>());
		this.setCuponeras(new HashSet<Cuponera>());
		this.setEstado(Estado.AGREGADA);
		this.setCategorias(new HashMap<String, Categoria>());
		this.setLinkImagen(linkImagen);
		this.setLinkVideo(linkVideo);
	}

	public String getLinkVideo() {
		return linkVideo;
	}

	public void setLinkVideo(String linkVideo) {
		this.linkVideo = linkVideo;
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
		DTActividad res = new DTActividad(nombre, descripcion, duracion, costo, ciudad, alta, estado, linkImagen, linkVideo);
		return res;
	}

	public Set<DTSalida> obtenerSalidasVigentes() {
		HashSet<DTSalida> res = new HashSet<DTSalida>();
		salidas.forEach((key, value)-> {
			if (value.getFechaSalida().after(GregorianCalendar.from(ZonedDateTime.now())))
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

	public Map<String, Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(Map<String, Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public void addCategoria(Categoria categoria) {
		this.categorias.put(categoria.getNombre(), categoria);
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void addCuponera(Cuponera cup) {
		this.cuponeras.add(cup);
	}

	public Set<Cuponera> getCuponeras() {
		return cuponeras;
	}

	public void setCuponeras(Set<Cuponera> cuponeras) {
		this.cuponeras = cuponeras;
	}

	public Image getFigura() {
		return figura;
	}

	public void setFigura(Image figura) {
		this.figura = figura;
	}

	public String getLinkImagen() {
		return linkImagen;
	}

	public void setLinkImagen(String linkImagen) {
		this.linkImagen = linkImagen;
	}
	
}