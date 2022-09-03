package logica;

import java.util.GregorianCalendar;

public class Compra {
	public GregorianCalendar fecha;
	public int cantTuristas;
	public GregorianCalendar vencimiento;
	public float costo;
	
	public Paquete paquete;
	

	public Compra(GregorianCalendar fecha, int cantTuristas, GregorianCalendar vencimiento, float costo, Paquete paquete) {
		this.fecha = fecha;
		this.cantTuristas = cantTuristas;
		this.vencimiento = vencimiento;
		this.costo = costo;
		this.paquete = paquete;
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

	public void setVencimiento(GregorianCalendar vencimiento) {
		this.vencimiento = vencimiento;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public Paquete getPaquete() {
		return paquete;
	}

	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}
	

}
