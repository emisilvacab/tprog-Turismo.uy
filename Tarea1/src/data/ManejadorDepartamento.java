package data;

import java.util.Map;

import logica.Departamento;

public class ManejadorDepartamento{
	private Map<String, Departamento> departamentos;
	private static ManejadorDepartamento instancia = null;
	
	private ManejadorDepartamento() {}
	
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
	
	public Map<String, Departamento> getDepartamentos(){
		if (departamentos.isEmpty())
			return null;
		else
			return departamentos;
	}
}