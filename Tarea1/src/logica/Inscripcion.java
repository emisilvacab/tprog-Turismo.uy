package logica;

import java.util.GregorianCalendar;

public class Inscripcion{
	
	private GregorianCalendar fecha;
	private int cantTuristas;
	private float costo;
	
	private Salida salida;
	private Turista turista;
	
	public Inscripcion(GregorianCalendar fecha, int cantTuristas, Salida salida, Turista turista) {
		this.setFecha(fecha);
		this.setCantTuristas(cantTuristas);
		this.setSalida(salida);
		this.setTurista(turista);
		this.costo  = (salida.getCostoActividad() * cantTuristas); 	
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

	public String getNicknameInscripto() {
		return turista.getNickname();
	}

	
	
}