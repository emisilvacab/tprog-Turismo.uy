package logica.datatypes;

import java.util.GregorianCalendar;

public class DTCompra{
	
	private GregorianCalendar fecha;
	private int cantTuristas;
	private GregorianCalendar vencimiento;
	private int costoTotal;
	
	public DTCompra() {
		this.setFecha(new GregorianCalendar());
		this.setCantTuristas(0);
		this.setVencimiento(new GregorianCalendar());
		this.setCostoTotal(0);
	}

	public DTCompra(GregorianCalendar fecha, int cantDTTuristas, GregorianCalendar vencimiento, int costoTotal) {
		this.setFecha(fecha);
		this.setCantTuristas(cantDTTuristas);
		this.setVencimiento(vencimiento);
		this.setCostoTotal(costoTotal);
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

	public void setCantTuristas(int cantTuristas) {
		this.cantTuristas = cantTuristas;
	}

	public GregorianCalendar getVencimiento() {
		return vencimiento;
	}
	
	public int getCostoTotal() {
		return costoTotal;
	}

	public void setVencimiento(GregorianCalendar vencimiento) {
		this.vencimiento = vencimiento;
	}

	public void setCostoTotal(int costoTotal) {
		this.costoTotal = costoTotal;
	}
	
	
	
}
