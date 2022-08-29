package logica.datatypes;

public class DTDepartamento{
	
	private String nombre;
	private String descripcion;
	private String url;
	
	public DTDepartamento() {
		this.setNombre(new String());
		this.setDescripcion(new String());
		this.setUrl(new String());
	}
	
	public DTDepartamento(String nombre, String descripcion, String url) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setUrl(url);
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}