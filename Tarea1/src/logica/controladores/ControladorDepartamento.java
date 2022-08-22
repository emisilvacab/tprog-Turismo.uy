package logica.controladores;

import java.util.HashMap;
import java.util.HashSet;

import data.ManejadorDepartamento;
import logica.Departamento;

public class ControladorDepartamento implements IControladorDepartamento {
	
	public ControladorDepartamento() {}
	public HashSet<String> obtenerDepartamentos(){
		ManejadorDepartamento mDptos = ManejadorDepartamento.getInstance();
		HashMap<String, Departamento> dptos = mDptos.getDepartamentos();
		HashSet<String> res = new HashSet<String>();
		dptos.forEach((key,value)->{
			res.add(key);
		});
		return res;
	};
}
