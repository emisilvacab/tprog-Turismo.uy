package logica.datatypes;

import java.util.GregorianCalendar;

public class DTInscripcion{
	
	private GregorianCalendar fecha;
	private int cantTuristas;
	private float costo;
	
	private String salida;
	private String nickname;
	
	public DTInscripcion(GregorianCalendar fecha, int cantTuristas, String salida, String nickname, float costo) {
		this.setFecha(fecha);
		this.setCantTuristas(cantTuristas);
		this.setSalida(salida);
		this.setNickname(nickname);
		this.setCosto(costo);

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

	public float setCosto(float costo) {
		return this.costo = costo;
	}
	
	public float getCosto() {
		return costo;
	}

	public void setCantTuristas(int cantTuristas) {
		this.cantTuristas = cantTuristas;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}
	

	public void setNickname(String turista) {
		this.nickname = turista;
	}

	public String getNickname() {
		return this.nickname;
	}
	
}