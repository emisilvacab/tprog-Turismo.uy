package logica.datatypes;

public class DTPaquete{
	
	private String nombre;
	private String descripcion;
	private int validez;
	private float descuento;
	
	public DTPaquete() {
		this.setNombre(new String());
		this.setDescripcion(new String());
		this.setValidez(0);
		this.setDescuento(0);
	}
	
	public DTPaquete(String nombre, String descripcion, int validez, float descuento) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setValidez(validez);
		this.setDescuento(descuento);
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

}