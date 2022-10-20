package logica;

import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Vector;

import logica.datatypes.DTPaquete;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;
import logica.manejadores.ManejadorSalida;

public class Turista extends Usuario{
	
	private String nacionalidad;
	
	private Vector<Inscripcion> inscripciones;
	private Vector<Compra> compras;
	
	
	public Turista(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String contrasena,String nacionalidad) {
		super(nickname, nombre, apellido, correo, nacimiento, contrasena);
		this.setNacionalidad(nacionalidad);
		this.setInscripciones(new Vector<Inscripcion>());
		this.setCompras(new Vector<Compra>());
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Vector<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(Vector<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}
	
	public void addInscripcion(Inscripcion inscripcion) {
		this.inscripciones.add(inscripcion);
	}

	public Vector<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Vector<Compra> compras) {
		this.compras = compras;
	}

	public HashSet<DTPaquete> obtenerPaquetesDisponibles(String nombreSalida, int cantTuristas) {
		ManejadorSalida mSal = ManejadorSalida.getInstance();
		Salida salida = mSal.getSalida(nombreSalida);
		HashSet<DTPaquete> res = new HashSet<DTPaquete>();
		for (Compra compra : compras) {
			if (compra.getVencimiento().after(GregorianCalendar.from(ZonedDateTime.now()))) {
				Vector<Cuponera> cuponeras = compra.getCuponeras();
				for(Cuponera cuponera : cuponeras) {
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
		for(Compra c : compras) {
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
		return new DTTurista(this.getNickname(),this.getNombre(),this.getApellido(),this.getCorreo(),this.getNacimiento(),this.getContrasena(),nacionalidad);
	}
}



















