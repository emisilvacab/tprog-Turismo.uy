package logica;

import java.util.GregorianCalendar;
import java.util.Vector;

public class Compra {
	private GregorianCalendar fecha;
	private int cantTuristas;
	private GregorianCalendar vencimiento;
	private float costo;
	
	private Paquete paquete;
	private Vector<Cuponera> cuponeras;
	

	public Compra(GregorianCalendar fecha, int cantTuristas, GregorianCalendar vencimiento, float costo, Paquete paquete) {
		this.fecha = fecha;
		this.cantTuristas = cantTuristas;
		this.vencimiento = vencimiento;
		this.costo = costo;
		this.paquete = paquete;
		for (Actividad act : paquete.getActividades().values()) {
	        Cuponera cup = new Cuponera(cantTuristas,act);
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

	public Vector<Cuponera> getCuponeras() {
		return cuponeras;
	}

	public void setCuponeras(Vector<Cuponera> cuponeras) {
		this.cuponeras = cuponeras;
	}
	
	public void addCuponera(Cuponera cuponera) {
		this.cuponeras.add(cuponera);
	}

	public void descontarCupos(Salida salida, int cantidad) {
		String nombreAct = salida.getActividad().getNombre();
		for(Cuponera cuponera : cuponeras) {
			if(cuponera.getActividad().getNombre() == nombreAct) {
				cuponera.setCuposRestantes(cuponera.getCuposRestantes() - cantidad);
			}
		}
	}
	

}
