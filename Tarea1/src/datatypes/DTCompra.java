package datatypes;

import java.util.GregorianCalendar;

public class DTCompra{
	
	private GregorianCalendar fecha;
	private int cantDTTuristas;
	private GregorianCalendar vencimiento;
	private int costoTotal;

	public DTCompra(GregorianCalendar fecha, int cantDTTuristas, GregorianCalendar vencimiento, int costoTotal) {
		this.fecha = fecha;
		this.cantDTTuristas = cantDTTuristas;
		//this.vencimiento = vencimiento; Calculado
		//this.costoTotal = costoTotal; Calculado
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

	public void setCantDTTuristas(int cantDTTuristas) {
		this.cantDTTuristas = cantDTTuristas;
	}

	public GregorianCalendar getVencimiento() {
		return vencimiento;
	}

	public int getCostoTotal() {
		return costoTotal;
	}
	
	
	
}
