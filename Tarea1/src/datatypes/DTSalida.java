package datatypes;

import java.util.GregorianCalendar;

public class DTSalida{
	
	private String nombre;
	private int maxTuristas;
	private GregorianCalendar alta;
	private GregorianCalendar fechaDTSalida;
	private String lugarDTSalida;
	
	public DTSalida() {
		this.setNombre(new String());
		this.setMaxTuristas(0);
		this.setAlta(new GregorianCalendar());
		this.setFechaDTSalida(new GregorianCalendar());
		this.setLugarDTSalida(new String());
	}
	
	public DTSalida(String nombre, int maxTuristas, GregorianCalendar alta, GregorianCalendar fechaDTSalida, String lugarDTSalida) {
		this.setNombre(nombre);
		this.setMaxTuristas(maxTuristas);
		this.setAlta(alta);
		this.setFechaDTSalida(fechaDTSalida);
		this.setLugarDTSalida(lugarDTSalida);
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
}