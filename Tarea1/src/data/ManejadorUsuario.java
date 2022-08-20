package data;

import java.util.Map;

import logica.Proveedor;
import logica.Turista;

public class ManejadorUsuario{
	private Map<String, Proveedor> proveedores;
	private Map<String, Turista> turistas;
	private static ManejadorUsuario instancia = null;
	
	private ManejadorUsuario() {}
	
	public static ManejadorUsuario getInstance() {
		if (instancia == null)
			instancia = new ManejadorUsuario();
		return instancia;
	}
	
	public void addProveedor(Proveedor proveedor) {
		proveedores.put(proveedor.getNickname(), proveedor);
	}
	
	public void addTurista(Turista turista) {
		turistas.put(turista.getNickname(), turista);
	}
	
	public Proveedor getProveedor(String nickname) {
		return proveedores.get(nickname);
	}
	
	public Turista getTurista(String nickname) {
		return turistas.get(nickname);
	}
	
	public Map<String, Proveedor> getProveedores() {
		if (turistas.isEmpty())
			return null;
		else
			return proveedores;
	}
	
	public Map<String, Turista> getTuristas(){
		if (turistas.isEmpty())
			return null;
		else
			return turistas;
	}
	
	
}