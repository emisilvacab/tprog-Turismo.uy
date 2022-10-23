package logica.manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.Categoria;
import logica.Departamento;

public class ManejadorDepartamentoCategoria{
	private Map<String, Departamento> departamentos;
	private Map<String, Categoria> categorias;
	
	private static ManejadorDepartamentoCategoria instancia = null;
	
	private ManejadorDepartamentoCategoria() {
		departamentos = new HashMap<String, Departamento>();
		categorias = new HashMap<String, Categoria>();
	}
	
	public static ManejadorDepartamentoCategoria getInstance() {
		if (instancia == null)
			instancia = new ManejadorDepartamentoCategoria();
		return instancia;
	}
	
	public void addDepartamento(Departamento departamento) {
		departamentos.put(departamento.getNombre(), departamento);
	}
	
	public Departamento getDepartamento(String nombre) {
		return departamentos.get(nombre);
	}
	
	public Map<String, Departamento> getDepartamentos(){
			return departamentos;
	}
	
	public void addCategoria(Categoria categoria) {
		categorias.put(categoria.getNombre(), categoria);
	}
	
	public Categoria getCategoria(String nombre) {
		return categorias.get(nombre);
	}
	
	public Map<String, Categoria> getCategorias(){
		return categorias;
	}
}