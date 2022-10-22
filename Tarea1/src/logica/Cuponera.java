package logica;

public class Cuponera {
	private int cuposRestantes;
	private Actividad actividad;
	
	Cuponera(int cuposRestantes, Actividad actividad){
		setCuposRestantes(cuposRestantes);
		setActividad(actividad);
	}
	
	public int getCuposRestantes() {
		return cuposRestantes;
	}
	
	public void setCuposRestantes(int cuposRestantes) {
		this.cuposRestantes = cuposRestantes;
	}
	
	public Actividad getActividad() {
		return actividad;
	}
	
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	
}
