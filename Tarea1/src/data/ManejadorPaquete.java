package data;

import java.util.Map;

import logica.Paquete;

public class ManejadorPaquete{
	private Map<String, Paquete> paquetes;
	private static ManejadorPaquete instancia = null;
	
	private ManejadorPaquete(){}
	
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
		if (paquetes.isEmpty())
			return null;
		else
			return paquetes;
	}
	
}