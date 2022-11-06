package logica.datatypes;

import java.util.GregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTSalida{
	
	private String nombre;
	private int maxTuristas;
	private GregorianCalendar alta;
	private GregorianCalendar fechaDTSalida;
	private int hora;
	private String lugarDTSalida;
	private String linkImagen;
	
	public DTSalida() {}
	
	public DTSalida(String nombre, int maxTuristas, GregorianCalendar alta, GregorianCalendar fechaDTSalida, int hora, String lugarDTSalida, String linkImagen) {
		this.setNombre(nombre);
		this.setMaxTuristas(maxTuristas);
		this.setAlta(alta);
		this.setFechaDTSalida(fechaDTSalida);
		this.setHora(hora);
		this.setLugarDTSalida(lugarDTSalida);
		this.setLinkImagen(linkImagen);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMaxTuristas() {
		return maxTuristas;
	}

	public void setMaxTuristas(int maxTuristas) {
		this.maxTuristas = maxTuristas;
	}

	public GregorianCalendar getAlta() {
		return alta;
	}

	public void setAlta(GregorianCalendar alta) {
		this.alta = alta;
	}

	public GregorianCalendar getFechaDTSalida() {
		return fechaDTSalida;
	}

	public void setFechaDTSalida(GregorianCalendar fechaDTSalida) {
		this.fechaDTSalida = fechaDTSalida;
	}

	public String getLugarDTSalida() {
		return lugarDTSalida;
	}

	public void setLugarDTSalida(String lugarDTSalida) {
		this.lugarDTSalida = lugarDTSalida;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public String getLinkImagen() {
		return linkImagen;
	}

	public void setLinkImagen(String linkImagen) {
		this.linkImagen = linkImagen;
	}
}