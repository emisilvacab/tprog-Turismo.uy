package logica;

import java.util.GregorianCalendar;

public class Compra{
	
	private GregorianCalendar fecha;
	private int cantTuristas;
	private GregorianCalendar vencimiento;
	private int costoTotal;

	private Paquete paquete;
	private Turista turista;
	
	public Compra(GregorianCalendar fecha, int cantTuristas, GregorianCalendar vencimiento, int costoTotal,Paquete paquete, Turista turista) {
		this.fecha = fecha;
		this.cantTuristas = cantTuristas;
		this.vencimiento = new GregorianCalendar(); //Calculado
		this.costoTotal = 0; //Calculado
		this.paquete = paquete;
		this.turista = turista;
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

	public Paquete getPaquete() {
		return paquete;
	}

	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}

	public Turista getTurista() {
		return turista;
	}

	public void setTurista(Turista turista) {
		this.turista = turista;
	}

	public GregorianCalendar getVencimiento() {
		return vencimiento;
	}

	public int getCostoTotal() {
		return costoTotal;
	}
	
	
	
}
