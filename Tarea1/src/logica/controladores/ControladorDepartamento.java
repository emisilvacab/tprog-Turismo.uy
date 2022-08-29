package logica.controladores;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import data.ManejadorDepartamento;
import data.ManejadorSalida;
import data.ManejadorUsuario;
import datatypes.DTActividad;
import datatypes.DTSalida;
import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.salidaNoExisteException;
import logica.Actividad;
import logica.Departamento;
import logica.Proveedor;

public class ControladorDepartamento implements IControladorDepartamento {
	
	public ControladorDepartamento() {}
	
	public HashSet<String> obtenerDepartamentos(){
		ManejadorDepartamento mDptos = ManejadorDepartamento.getInstance();
		HashMap<String, Departamento> dptos = (HashMap<String, Departamento>) mDptos.getDepartamentos();
		HashSet<String> res = new HashSet<String>();
		dptos.forEach((key,value)->{
			res.add(key);
		});
		return res;
	}
	
	public HashSet<DTActividad> obtenerDatosActividadesAsociadas(String nombreDpto) throws departamentoNoExisteException {
		ManejadorDepartamento mDptos = ManejadorDepartamento.getInstance();
		Departamento dpto = mDptos.getDepartamento(nombreDpto);
		if (dpto == null) 
			throw new departamentoNoExisteException("No se encontró un departamento con el nombre ingresado");
		return dpto.obtenerDatosActividades();
	}
	
	public HashSet<DTSalida> obtenerDatosSalidasVigentes(String nombreAct, String nombreDpto) throws departamentoNoExisteException, actividadNoExisteException {
		ManejadorDepartamento mDptos = ManejadorDepartamento.getInstance();
		Departamento dpto = mDptos.getDepartamento(nombreDpto);
		if (dpto == null) 
			throw new departamentoNoExisteException("No se encontró un departamento con el nombre ingresado");
		HashSet<DTSalida> res;
		try {
			res = dpto.obtenerDatosSalidasVigentes(nombreAct);
		}
		catch(actividadNoExisteException e1){
			throw new actividadNoExisteException("No se encontró una actividad con el nombre ingresado");	
		}
		return res;
	}
	
	public boolean ingresarDatosActividad(String nombreAct, String descripcion, int duracion, float costo, String ciudad, GregorianCalendar fecha, String nicknameProv, String nombreDep) {
		ManejadorDepartamento manDepartamento = ManejadorDepartamento.getInstance();
		ManejadorUsuario manUsuario = ManejadorUsuario.getInstance();
		
		HashMap<String, Departamento> departamentos = manDepartamento.getDepartamentos();
    	Set<String> setNombresDep = departamentos.keySet();
    	boolean encontro = false;
    	Departamento depAsignado;
    	for (String dep: setNombresDep) {
    		Departamento departamento = manDepartamento.getDepartamento(dep);
    		if (departamento.getActividades().get(nombreAct) != null) {
    			encontro = true;
    			break;
    		}
    	}
    	if (!encontro) {
    		depAsignado = departamentos.get(nombreDep);
    		Proveedor proveedor = manUsuario.getProveedores().get(nicknameProv);
    		Actividad nuevaActividad = new Actividad(nombreAct, descripcion, duracion, costo, ciudad, fecha, depAsignado, proveedor);
    		depAsignado.getActividades().put(nombreAct, nuevaActividad);
    		proveedor.getActividades().put(nombreAct, nuevaActividad);
    	}
		return false;
	}
}
