package datatypes;

import java.util.GregorianCalendar;

public class DTInscripcion{
	
	private GregorianCalendar fecha;
	private int cantDTTuristas;
	private float costo;
	
	private DTSalida salida;
	private DTTurista turista;
	private DTCompra pagoConPaquete;//Ver otro nombre o para DTCompra o para este atributo
	
	public DTInscripcion(GregorianCalendar fecha, int cantDTTuristas, DTSalida salida, DTTurista turista) {
		this.fecha = fecha;
		this.cantDTTuristas = cantDTTuristas;
		this.salida = salida;
		this.turista = turista;
		this.costo = 0; //CALCULAR ESTO
		this.pagoConPaquete = null;
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


	public DTSalida getDTSalida() {
		return salida;
	}


	public void setDTSalida(DTSalida salida) {
		this.salida = salida;
	}
	
	public DTTurista getDTTurista() {
		return turista;
	}


	public void setDTTurista(DTTurista turista) {
		this.turista = turista;
	}


	public DTCompra getPagoPaquete() {
		return pagoConPaquete;
	}


	public void setPagoPaquete(DTCompra pagoConPaquete) {
		this.pagoConPaquete = pagoConPaquete;
	}

	
	
}