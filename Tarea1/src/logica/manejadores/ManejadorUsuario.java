package logica.manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.Proveedor;
import logica.Turista;

public class ManejadorUsuario{
	private Map<String, Proveedor> proveedores;
	private Map<String, Turista> turistas;
	private static ManejadorUsuario instancia = null;
	
	private ManejadorUsuario() {
		proveedores = new HashMap<String, Proveedor>();
		turistas = new HashMap<String, Turista>();
	}
	
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
			return proveedores;
	}
	
	public Map<String, Turista> getTuristas(){
			return turistas;
	}

	public Turista getTuristaPorEmail(String email) {
		for (Turista tur: turistas.values()) {
			if (tur.getCorreo().equals(email)) {
				return tur;
			}
		}
		return null;
	}

	public Proveedor getProveedorPorEmail(String email) {
		for (Proveedor pro: proveedores.values()) {
			if (pro.getCorreo().equals(email)) {
				return pro;
			}
		}
		return null;
	}
	
	
}