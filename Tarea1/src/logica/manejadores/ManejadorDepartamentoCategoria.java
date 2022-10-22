package logica.manejadores;

import java.util.HashMap;

import logica.Categoria;
import logica.Departamento;

public class ManejadorDepartamentoCategoria{
	private HashMap<String, Departamento> departamentos;
	private HashMap<String, Categoria> categorias;
	
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
	
	public HashMap<String, Departamento> getDepartamentos(){
			return departamentos;
	}
	
	public void addCategoria(Categoria categoria) {
		categorias.put(categoria.getNombre(), categoria);
	}
	
	public Categoria getCategoria(String nombre) {
		return categorias.get(nombre);
	}
	
	public HashMap<String, Categoria> getCategorias(){
		return categorias;
	}
}