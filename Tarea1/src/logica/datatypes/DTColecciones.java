package logica.datatypes;

import java.util.HashSet;
import java.util.Set;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTColecciones {
	private Set<String> setString = new HashSet<String>();
	private Set<DTActividad> setDtActividad = new HashSet<DTActividad>();
	private Set<DTInscripcion> setDtInscripcion = new HashSet<DTInscripcion>();
	private Set<DTCompra> setDtCompra = new HashSet<DTCompra>();
	
	public DTColecciones(){}

	public Set<String> getSetString() {
		return setString;
	}

	public void setSetString(Set<String> setString) {
		this.setString = setString;
	}
	
	public Set<DTActividad> getSetDtActividad() {
		return setDtActividad;
	}

	public void setSetDtActividad(Set<DTActividad> setDtActividad) {
		this.setDtActividad = setDtActividad;
	}

	public Set<DTInscripcion> getSetDtInscripcion() {
		return setDtInscripcion;
	}

	public void setSetDtInscripcion(Set<DTInscripcion> setDtInscripcion) {
		this.setDtInscripcion = setDtInscripcion;
	}
	
	public Set<DTCompra> getSetDtCompra() {
		return setDtCompra;
	}

	public void setSetDtCompra(Set<DTCompra> setDtCompra) {
		this.setDtCompra = setDtCompra;
	}
	
}