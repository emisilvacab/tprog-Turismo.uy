package datatypes;

import java.util.GregorianCalendar;

public class DTInscripcion{
	
	private GregorianCalendar fecha;
	private int cantDTTuristas;
	private float costo;
	
	public DTInscripcion(GregorianCalendar fecha, int cantDTTuristas) {
		this.fecha = fecha;
		this.cantDTTuristas = cantDTTuristas;
		this.costo = 0; //CALCULAR ESTO
	}


	public GregorianCalendar getFecha() {
		return fecha;
	}


	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}


	public int getCantDTTuristas() {
		return cantDTTuristas;
	}


	public float getCosto() {
		return costo;
	}


	public void setCantDTTuristas(int cantDTTuristas) {
		this.cantDTTuristas = cantDTTuristas;
	}
		
}