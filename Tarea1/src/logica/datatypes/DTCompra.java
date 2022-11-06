package logica.datatypes;

import java.util.GregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTCompra{
	
	private GregorianCalendar fecha;
	private int cantTuristas;
	private GregorianCalendar vencimiento;
	private float costo;
	
	private String paquete;
	private String turista;
	
	public DTCompra() {}
	
	public DTCompra(GregorianCalendar fechaIn, int cantTuristasIn, GregorianCalendar vencimientoIn, float costoIn, String paqueteIn, String turistaIn){
		this.setFecha(fechaIn); 
		this.setCantTuristas(cantTuristasIn);
		this.setVencimiento(vencimientoIn);
		this.setCosto(costoIn);
		this.setTurista(turistaIn);
		this.setPaquete(paqueteIn);
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

	public String getPaquete() {
		return paquete;
	}

	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}

	public String getTurista() {
		return turista;
	}

	public void setTurista(String turista) {
		this.turista = turista;
	}
	
	
}