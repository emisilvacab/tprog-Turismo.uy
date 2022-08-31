package logica.controladores;

import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.proveedorNoExisteException;
import excepciones.salidaNoExisteException;
import logica.Actividad;
import logica.Departamento;
import logica.Proveedor;
import logica.Salida;
import logica.datatypes.DTActividad;
import logica.datatypes.DTSalida;
import logica.manejadores.ManejadorDepartamento;
import logica.manejadores.ManejadorSalida;
import logica.manejadores.ManejadorUsuario;

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
	
	public boolean ingresarDatosActividad(String nombreAct, String descripcion, int duracion, float costo, String ciudad, GregorianCalendar fecha, String nicknameProv, String nombreDep) throws excepciones.proveedorNoExisteException, departamentoNoExisteException {
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
    		if (depAsignado == null) {
    			throw new departamentoNoExisteException("No existe departamento");
    		}
    		Proveedor proveedor = manUsuario.getProveedores().get(nicknameProv);
    		if (proveedor == null) {
    			throw new excepciones.proveedorNoExisteException("No existe proveedor");
    		}
    		Actividad nuevaActividad = new Actividad(nombreAct, descripcion, duracion, costo, ciudad, fecha, depAsignado, proveedor);
    		depAsignado.getActividades().put(nombreAct, nuevaActividad);
    		proveedor.getActividades().put(nombreAct, nuevaActividad);
    	}
		return encontro;
	}
	
	public boolean ingresarDatosSalida(String nombre, int maxTuristas, GregorianCalendar fechaAlta, GregorianCalendar fechaSalida, int horaSalida, String lugarSalida, String nombreDpto, String nombreAct) throws excepciones.proveedorNoExisteException, actividadNoExisteException {
		ManejadorDepartamento mDptos = ManejadorDepartamento.getInstance();
		ManejadorSalida mSals = ManejadorSalida.getInstance();
		Departamento dpto = mDptos.getDepartamento(nombreDpto);
		if (dpto == null)
			throw new proveedorNoExisteException("No se encontró un proveedor con el nombre ingresado.");
		Actividad act = dpto.obtenerActividad(nombreAct);
		if (act == null)
			throw new actividadNoExisteException("No se encontró una actividad con el nombre ingresado.");
		//boolean existeSalida = act.existeSalida(nombre);
		Salida sal = mSals.getSalida(nombre);
		if (sal == null) {
			Salida nueva = new Salida(nombre, maxTuristas, fechaAlta, fechaSalida, horaSalida, lugarSalida, act);
			act.addSalida(nueva);
			ManejadorSalida msal = ManejadorSalida.getInstance();
			msal.addSalida(nueva);
		}
		
		return (sal != null);
	}
	
	public void ingresarDepartamento(String nombre, String descripcion, String url) {
		ManejadorDepartamento mDptos = ManejadorDepartamento.getInstance();
		Departamento nuevo = new Departamento(nombre, descripcion, url);
		mDptos.addDepartamento(nuevo);
	}

	@Override
	public DTActividad obtenerDatosActividad(String actividadSeleccionada) throws actividadNoExisteException{
		Actividad act = null;
		ManejadorDepartamento mDptos = ManejadorDepartamento.getInstance();
		HashMap<String, Departamento> departamentos = mDptos.getDepartamentos();
		for (Departamento dep: departamentos.values()) {
			act = dep.obtenerActividad(actividadSeleccionada);
			if (act != null) {
				break;
			}
			
		}
		if (act == null) {
			throw new actividadNoExisteException("actividad no encontrada");
		}else {
			DTActividad res = new DTActividad(act.getNombre(), act.getDescripcion(), act.getDuracion(), act.getCosto(), act.getCiudad(), act.getAlta());
			return res;
		}
	}

	@Override
	public String obtenerDeptoActividad(String actividad) {
		String resu = null;
		ManejadorDepartamento mDptos = ManejadorDepartamento.getInstance();
		HashMap<String, Departamento> departamentos = mDptos.getDepartamentos();
		for (Departamento depto: departamentos.values()) {
			if (depto.getActividades().containsKey(actividad)) {
				resu = depto.getNombre();
				break;
			}
		}
		return resu;
	}

}










