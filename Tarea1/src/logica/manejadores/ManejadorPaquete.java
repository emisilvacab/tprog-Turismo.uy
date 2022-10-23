package logica.manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.Paquete;


public class ManejadorPaquete {
	private Map<String, Paquete> paquetes;
	
	private static ManejadorPaquete instancia = null;
	
	private ManejadorPaquete() {
		paquetes = new HashMap<String, Paquete>();
	}
	
	public static ManejadorPaquete getInstance() {
		if (instancia == null)
			instancia = new ManejadorPaquete();
		return instancia;
	}
	
	public void addPaquete(Paquete paquete) {
		paquetes.put(paquete.getNombre(), paquete);
	}
	
	public Paquete getPaquete(String nombre) {
		return paquetes.get(nombre);
	}
	
	public Map<String, Paquete> getPaquetes(){
		return paquetes;
	}
}