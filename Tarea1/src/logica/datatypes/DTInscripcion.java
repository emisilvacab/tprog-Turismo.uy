package logica.datatypes;

import java.util.GregorianCalendar;

public class DTInscripcion{
	
	private GregorianCalendar fecha;
	private int cantTuristas;
	private float costo;
	
	public DTInscripcion(GregorianCalendar fecha, int cantTuristas, float costo) {
		this.setFecha(fecha);
		this.setCantTuristas(cantTuristas);
		this.setCosto(costo);
	}


	public GregorianCalendar getFecha() {
		return fecha;
	}


	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}


	public int getCantTuristas() {
		return cantTuristas;
	}


	public float getCosto() {
		return costo;
	}
	
	public void setCosto(float costo) {
		this.costo = costo;
	}


	public void setCantTuristas(int cantDTTuristas) {
		this.cantTuristas = cantDTTuristas;
	}
		
}