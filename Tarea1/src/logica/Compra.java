package logica;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class Compra {
	
	private GregorianCalendar fecha;
	private int cantTuristas;
	private GregorianCalendar vencimiento;
	private float costo;
	
	private Paquete paquete;
	private Set<Cuponera> cuponeras;
	private Turista turista;
	

	public Compra(GregorianCalendar fecha, int cantTuristas, GregorianCalendar vencimiento, float costo, Turista turista, Paquete paquete) {
		this.setFecha(fecha); 
		this.setCantTuristas(cantTuristas);
		this.setVencimiento(vencimiento);
		this.setCosto(costo);
		this.setTurista(turista);
		this.setPaquete(paquete);
		this.setCuponeras(new HashSet<Cuponera>());
		for (Actividad act : paquete.getActividades().values()) {
	        Cuponera cup = new Cuponera(cantTuristas, act);
			cuponeras.add(cup);
	        act.addCuponera(cup);
	    }
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

	public Set<Cuponera> getCuponeras() {
		return cuponeras;
	}

	public void setCuponeras(Set<Cuponera> cuponeras) {
		this.cuponeras = cuponeras;
	}
	
	public void addCuponera(Cuponera cuponera) {
		this.cuponeras.add(cuponera);
	}

	public Turista getTurista() {
		return turista;
	}

	public void setTurista(Turista turista) {
		this.turista = turista;
	}

	public void descontarCupos(Salida salida, int cantidad) {
		String nombreAct = salida.getActividad().getNombre();
		for (Cuponera cuponera : cuponeras) {
			if (cuponera.getActividad().getNombre().equals(nombreAct)) {
				cuponera.setCuposRestantes(cuponera.getCuposRestantes() - cantidad);
			}
		}
	}
	

}
