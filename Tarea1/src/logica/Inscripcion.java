package logica;

import java.util.GregorianCalendar;

public class Inscripcion{
	
	private GregorianCalendar fecha;
	private int cantTuristas;
	private float costo;
	
	private Salida salida;
	private Turista turista;
	private Compra pagoConPaquete;//Ver otro nombre o para Compra o para este atributo
	
	public Inscripcion(GregorianCalendar fecha, int cantTuristas, Salida salida, Turista turista) {
		this.fecha = fecha;
		this.cantTuristas = cantTuristas;
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


	public int getCantTuristas() {
		return cantTuristas;
	}


	public float getCosto() {
		return costo;
	}


	public void setCantTuristas(int cantTuristas) {
		this.cantTuristas = cantTuristas;
	}


	public Salida getSalida() {
		return salida;
	}


	public void setSalida(Salida salida) {
		this.salida = salida;
	}
	
	public Turista getTurista() {
		return turista;
	}


	public void setTurista(Turista turista) {
		this.turista = turista;
	}


	public Compra getPagoPaquete() {
		return pagoConPaquete;
	}


	public void setPagoPaquete(Compra pagoConPaquete) {
		this.pagoConPaquete = pagoConPaquete;
	}


	public String getNicknameInscripto() {
		return turista.getNickname();
	}

	
	
}