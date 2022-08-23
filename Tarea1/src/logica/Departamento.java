package logica;

import java.util.HashMap;
<<<<<<< Upstream, based on origin/master
=======
import java.util.HashSet;
>>>>>>> 80d68e8 Logica de InscripcionASalida (no testeada)
import java.util.Map;

import datatypes.DTActividad;

public class Departamento{
	
	private String nombre;
	private String descripcion;
	private String url;
	
	private HashMap<String, Actividad> actividades;

	
	public Departamento(String nombre, String descripcion, String url) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
<<<<<<< Upstream, based on origin/master
		this.actividades = new HashMap<String, Actividad>();
=======
		this.actividades = new HashMap<String,Actividad>();
>>>>>>> 80d68e8 Logica de InscripcionASalida (no testeada)
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getUrl() {
		return url;
	}
	
	//SE PUEDE MODIFICAR PARA QUE DEVUELVA SOLO NOMBRES/IDENTIFICADORES
	public HashMap<String, Actividad> getActividades() {
		return actividades;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setActividades(HashMap<String, Actividad> actividades) {
		this.actividades = actividades;
	}
	
	public void addActividad(Actividad actividad) {
		this.actividades.put(actividad.getNombre(), actividad);
	}
	
	public HashSet<DTActividad> obtenerDatosActividades(){
		HashSet<DTActividad> res = new HashSet<DTActividad>();
		actividades.forEach((key,value)->{
			res.add(actividades.get(key).getDatos());
		});
		return res;
	};
	
	
	
	
	
	
	
	
}