package datatypes;

import java.util.GregorianCalendar;

public class DTCompra{
	
	private GregorianCalendar fecha;
	private int cantDTTuristas;
	private GregorianCalendar vencimiento;
	private int costoTotal;

	private DTPaquete paquete;
	private DTTurista turista;
	
	public DTCompra(GregorianCalendar fecha, int cantDTTuristas, GregorianCalendar vencimiento, int costoTotal,DTPaquete paquete, DTTurista turista) {
		this.fecha = fecha;
		this.cantDTTuristas = cantDTTuristas;
		//this.vencimiento = vencimiento; Calculado
		//this.costoTotal = costoTotal; Calculado
		this.paquete = paquete;
		this.turista = turista;
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

	public DTPaquete getDTPaquete() {
		return paquete;
	}

	public void setDTPaquete(DTPaquete paquete) {
		this.paquete = paquete;
	}

	public DTTurista getDTTurista() {
		return turista;
	}

	public void setDTTurista(DTTurista turista) {
		this.turista = turista;
	}

	public GregorianCalendar getVencimiento() {
		return vencimiento;
	}

	public int getCostoTotal() {
		return costoTotal;
	}
	
	
	
}
