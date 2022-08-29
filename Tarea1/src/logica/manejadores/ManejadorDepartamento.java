package logica.manejadores;

import java.util.HashMap;

import logica.Departamento;

public class ManejadorDepartamento{
	private HashMap<String, Departamento> departamentos;
	private static ManejadorDepartamento instancia = null;
	
	private ManejadorDepartamento() {
		departamentos = new HashMap<String, Departamento>();
	}
	
	public static ManejadorDepartamento getInstance() {
		if (instancia == null)
			instancia = new ManejadorDepartamento();
		return instancia;
	}
	
	public void addDepartamento(Departamento departamento) {
		departamentos.put(departamento.getNombre(), departamento);
	}
	
	public Departamento getDepartamento(String nombre) {
		return departamentos.get(nombre);
	}
	
	public HashMap<String, Departamento> getDepartamentos(){
			return departamentos;
	}
}