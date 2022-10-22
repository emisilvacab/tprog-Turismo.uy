package logica;

import java.util.GregorianCalendar;

public class Inscripcion{
	
	private GregorianCalendar fecha;
	private int cantTuristas;
	private float costo;
	
	private Salida salida;
	private Turista turista;
	private Compra compra;
	
	public Inscripcion(GregorianCalendar fecha, int cantTuristas, Salida salida, Turista turista, Compra compra) {
		this.setFecha(fecha);
		this.setCantTuristas(cantTuristas);
		this.setSalida(salida);
		this.setTurista(turista);
		if (compra == null)
			this.costo  = salida.getCostoActividad() * cantTuristas; 
		else 
			this.costo  = (salida.getCostoActividad() * cantTuristas) - (compra.getPaquete().getDescuento()*(salida.getCostoActividad() * cantTuristas) / 100);
		this.setCompra(compra);			
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


	public Compra getCompra() {
		return compra;
	}


	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	
	
}