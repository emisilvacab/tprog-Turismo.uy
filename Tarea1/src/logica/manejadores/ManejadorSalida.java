package logica.manejadores;

import java.util.Map;
import java.util.HashMap;

import logica.Salida;

public class ManejadorSalida{
	private Map<String, Salida> salidas;
	private static ManejadorSalida instancia = null;
	
	private ManejadorSalida() {
		this.salidas = new HashMap<String, Salida>();
	}
	
	public static ManejadorSalida getInstance() {
		if (instancia == null)
			instancia = new ManejadorSalida();
		return instancia;
	}
	
	public void addSalida(Salida salida) {
		salidas.put(salida.getNombre(), salida);
	}
	
	public Salida getSalida(String nombre) {
		return salidas.get(nombre);
	}
	//esto se puede devolver en un array asi es mas facil pa despues recorrer no se q pasa si devuelvo el map se prueba
	public Map<String, Salida> getSalidas(){
		if (salidas.isEmpty())
			return null;
		else
			return salidas;
	}
}