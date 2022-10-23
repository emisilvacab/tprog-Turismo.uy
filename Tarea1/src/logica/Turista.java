package logica;

import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import logica.datatypes.DTPaquete;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;
import logica.manejadores.ManejadorSalida;

public class Turista extends Usuario{
	
	private String nacionalidad;
	
	private Set<Inscripcion> inscripciones;
	private Set<Compra> compras;
	
	
	public Turista(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String contrasena, String linkImagen, String nacionalidad) {
		super(nickname, nombre, apellido, correo, nacimiento, contrasena, linkImagen);
		this.setNacionalidad(nacionalidad);
		this.setInscripciones(new HashSet<Inscripcion>());
		this.setCompras(new HashSet<Compra>());
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
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

	public Set<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	public Set<DTPaquete> obtenerPaquetesDisponibles(String nombreSalida, int cantTuristas) {
		ManejadorSalida mSal = ManejadorSalida.getInstance();
		Salida salida = mSal.getSalida(nombreSalida);
		Set<DTPaquete> res = new HashSet<DTPaquete>();
		for (Compra compra : compras) {
			if (compra.getVencimiento().after(GregorianCalendar.from(ZonedDateTime.now()))) {
				Set<Cuponera> cuponeras = compra.getCuponeras();
				for (Cuponera cuponera : cuponeras) {
					if (cuponera.getActividad().getNombre().equals(salida.getActividad().getNombre())  && cuponera.getCuposRestantes() >= cantTuristas) {
						res.add(compra.getPaquete().getDatos());
						break;
					}
				}
			}	
		}
		return res;
	}

	public boolean existeCompra(String nombrePaq) {
		boolean res = false;
		for (Compra c : compras) {
			if (c.getPaquete().getNombre().equals(nombrePaq)) {
				res = true;
				break;
			}
		}
		return res;
	}

	public void addCompra(Compra compra) {
		compras.add(compra);
	}

	public DTUsuario getDatos() {
		return new DTTurista(this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(), this.getNacimiento(), this.getContrasena(), this.getLinkImagen(), nacionalidad);
	}
}



















