package logica;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import logica.datatypes.DTSalida;

public class Salida{
	
	private String nombre;
	private int maxTuristas;
	private GregorianCalendar alta;
	private GregorianCalendar fechaSalida;
	private int hora;
	private String lugarSalida;
	private String linkImagen;
	
	private Actividad actividad;
	private Set<Inscripcion> inscripciones;

	public Salida(String nombre, int maxTuristas, GregorianCalendar alta, GregorianCalendar fechaSalida, int hora, String lugarSalida, Actividad actividad, String linkImagen) {
		this.setNombre(nombre);
		this.setMaxTuristas(maxTuristas);
		this.setAlta(alta);
		this.setFechaSalida(fechaSalida);
		this.setHora(hora);
		this.setLugarSalida(lugarSalida);
		this.setActividad(actividad);
		this.setInscripciones(new HashSet<Inscripcion>());
		this.setLinkImagen(linkImagen);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMaxTuristas() {
		return maxTuristas;
	}

	public void setMaxTuristas(int maxTuristas) {
		this.maxTuristas = maxTuristas;
	}

	public GregorianCalendar getAlta() {
		return alta;
	}

	public void setAlta(GregorianCalendar alta) {
		this.alta = alta;
	}

	public GregorianCalendar getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(GregorianCalendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getLugarSalida() {
		return lugarSalida;
	}

	public void setLugarSalida(String lugarSalida) {
		this.lugarSalida = lugarSalida;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Set<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(Set<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}
	
	public void addInscripcion(Inscripcion inscripcion) {
		this.inscripciones.add(inscripcion);
	}

	public boolean admiteCapacidad(int capacidad) {
		int total = 0;
		for (Inscripcion ins: inscripciones) {
			total += ins.getCantTuristas();
		}
		return total + capacidad <= maxTuristas;
	}

	public boolean existeInscripcion(String nickname) {
		for (Inscripcion ins : inscripciones) {
			if (ins.getNicknameInscripto().equals(nickname))
				return true;
		}
		return false;
	}

	public DTSalida getDatos() {
		DTSalida res = new DTSalida(nombre, maxTuristas, alta, fechaSalida, hora, lugarSalida, linkImagen);
		return res;
	}

	public float getCostoActividad() {
		return actividad.getCosto();
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int obtenerlugaresDisponibles() {
		int total = 0;
		for (Inscripcion ins: inscripciones) {
			total += ins.getCantTuristas();
		}
		return maxTuristas - total;
	}

	public String getLinkImagen() {
		return linkImagen;
	}

	public void setLinkImagen(String linkImagen) {
		this.linkImagen = linkImagen;
	}

	
}