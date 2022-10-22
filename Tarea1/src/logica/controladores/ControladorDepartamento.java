package logica.controladores;

import java.awt.Image;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import excepciones.actividadNoExisteException;
import excepciones.categoriaNoExisteException;
import excepciones.categoriaYaExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.salidaNoExisteException;
import logica.Actividad;
import logica.Categoria;
import logica.Departamento;
import logica.Estado;
import logica.Proveedor;
import logica.Salida;
import logica.datatypes.DTActividad;
import logica.datatypes.DTSalida;
import logica.manejadores.ManejadorDepartamentoCategoria;
import logica.manejadores.ManejadorSalida;
import logica.manejadores.ManejadorUsuario;

public class ControladorDepartamento implements IControladorDepartamento {
	
	public ControladorDepartamento() {}
	
	public HashSet<String> obtenerDepartamentos(){
		ManejadorDepartamentoCategoria mDptos = ManejadorDepartamentoCategoria.getInstance();
		HashMap<String, Departamento> dptos = (HashMap<String, Departamento>) mDptos.getDepartamentos();
		HashSet<String> res = new HashSet<String>();
		dptos.forEach((key, value)-> {
			res.add(key);
		});
		return res;
	}
	
	public HashSet<DTActividad> obtenerDatosActividadesAsociadas(String nombreDpto) throws departamentoNoExisteException {
		ManejadorDepartamentoCategoria mDptos = ManejadorDepartamentoCategoria.getInstance();
		Departamento dpto = mDptos.getDepartamento(nombreDpto);
		if (dpto == null) 
			throw new departamentoNoExisteException("No se encontró un departamento con el nombre ingresado");
		return dpto.obtenerDatosActividades();
	}
	
	public HashSet<DTSalida> obtenerDatosSalidasParaActividad(String nombreAct) throws actividadNoExisteException {
		ManejadorDepartamentoCategoria mDptos = ManejadorDepartamentoCategoria.getInstance();
		HashMap<String, Departamento> dptos = mDptos.getDepartamentos();
		HashMap<String, Salida> sals = new HashMap<String, Salida>(); 
		boolean encontro = false;
		for (Departamento dpto : dptos.values()) {
			for (Actividad a : dpto.getActividades().values()) {
				if (a.getNombre().equals(nombreAct)) {
					sals = (HashMap<String, Salida>) a.getSalidas();
					encontro = true;
				} 		
			}
		}
		if (!encontro)
			throw new actividadNoExisteException("No se encontró una actividad con el nombre ingresado");
		HashSet<DTSalida> res = new HashSet<DTSalida>();
		for (Salida s : sals.values()) {
			res.add(s.getDatos());
		}
		return res;
	}
	
	public HashSet<DTSalida> obtenerDatosSalidasVigentes(String nombreAct) throws actividadNoExisteException {
		ManejadorDepartamentoCategoria mDptos = ManejadorDepartamentoCategoria.getInstance();
		HashMap<String, Departamento> dptos = mDptos.getDepartamentos();
		for (Departamento dpto : dptos.values()) {
			if (dpto.getActividades().get(nombreAct) != null)
				return dpto.getActividades().get(nombreAct).obtenerSalidasVigentes();
		}
		throw new actividadNoExisteException("No se encontró una actividad con el nombre ingresado");
	}
	
	public boolean ingresarDatosActividad(String nombreAct, String descripcion, int duracion, float costo, String ciudad, GregorianCalendar fecha, String nicknameProv, String nombreDep, Set<String> categorias) throws excepciones.proveedorNoExisteException, departamentoNoExisteException {
		ManejadorDepartamentoCategoria manDepartamento = ManejadorDepartamentoCategoria.getInstance();
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
    		HashMap<String, Categoria> cats = new HashMap<String, Categoria>();
    		for (String nomCat : categorias) {
    			Categoria cat = manDepartamento.getCategoria(nomCat);
    			cats.put(nomCat, cat);
    			cat.addActividad(nuevaActividad);
    		}
    		nuevaActividad.setCategorias(cats);
    		depAsignado.getActividades().put(nombreAct, nuevaActividad);
    		proveedor.getActividades().put(nombreAct, nuevaActividad);
    	}
		return encontro;
	}
	
	public boolean ingresarDatosSalida(String nombre, int maxTuristas, GregorianCalendar fechaAlta, GregorianCalendar fechaSalida, int horaSalida, String lugarSalida, String nombreDpto, String nombreAct, Image figura) throws excepciones.departamentoNoExisteException, actividadNoExisteException{
		ManejadorDepartamentoCategoria mDptos = ManejadorDepartamentoCategoria.getInstance();
		ManejadorSalida mSals = ManejadorSalida.getInstance();
		Departamento dpto = mDptos.getDepartamento(nombreDpto);
		if (dpto == null)
			throw new departamentoNoExisteException("No se encontró un departamento con el nombre ingresado.");
		Actividad act = dpto.obtenerActividad(nombreAct);
		if (act == null)
			throw new actividadNoExisteException("No se encontró una actividad con el nombre ingresado.");
		Salida sal = mSals.getSalida(nombre);
		if (sal == null) {
			Salida nueva = new Salida(nombre, maxTuristas, fechaAlta, fechaSalida, horaSalida, lugarSalida, act);
			nueva.setFigura(figura);
			act.addSalida(nueva);
			ManejadorSalida msal = ManejadorSalida.getInstance();
			msal.addSalida(nueva);
		}
		return (sal != null);
	}
	
	public void ingresarDepartamento(String nombre, String descripcion, String url) {
		ManejadorDepartamentoCategoria mDptos = ManejadorDepartamentoCategoria.getInstance();
		Departamento nuevo = new Departamento(nombre, descripcion, url);
		mDptos.addDepartamento(nuevo);
	}

	@Override
	public DTActividad obtenerDatosActividad(String actividadSeleccionada) throws actividadNoExisteException{
		Actividad act = null;
		ManejadorDepartamentoCategoria mDptos = ManejadorDepartamentoCategoria.getInstance();
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
			DTActividad res = new DTActividad(act.getNombre(), act.getDescripcion(), act.getDuracion(), act.getCosto(), act.getCiudad(), act.getAlta(), act.getEstado());
			return res;
		}
	}

	@Override
	public String obtenerDeptoActividad(String actividad) {
		String resu = null;
		ManejadorDepartamentoCategoria mDptos = ManejadorDepartamentoCategoria.getInstance();
		HashMap<String, Departamento> departamentos = mDptos.getDepartamentos();
		for (Departamento depto: departamentos.values()) {
			if (depto.getActividades().containsKey(actividad)) {
				resu = depto.getNombre();
				break;
			}
		}
		return resu;
	}
	
	@Override
	public HashSet<String> obtenerCategoriasActividad(String actividad) throws actividadNoExisteException {
		HashSet<String> ans = new HashSet<String>();
		ManejadorDepartamentoCategoria mDptosCat = ManejadorDepartamentoCategoria.getInstance();
		HashMap<String, Categoria> categorias = mDptosCat.getCategorias();
		for (Categoria cat: categorias.values()) {
			if (cat.getActividades().containsKey(actividad)) {
				ans.add(cat.getNombre());
			}
		}
		if (ans.isEmpty())
			throw new actividadNoExisteException("Actividad no existe");
		return ans;
	}
	
	public int obtenerlugaresDisponibles(String nombreSal) throws salidaNoExisteException {
		ManejadorSalida mSals = ManejadorSalida.getInstance();
		Salida sal = mSals.getSalida(nombreSal);
		if (sal == null)
			throw new salidaNoExisteException("Salida no encontrada");
		return sal.obtenerlugaresDisponibles();
	}

	@Override
	public String[] obtenerActividadesAgregadas() {
		HashSet<String> actividades = new HashSet<String>();
		ManejadorDepartamentoCategoria mDptos = ManejadorDepartamentoCategoria.getInstance();
		HashMap<String, Departamento> deptos = mDptos.getDepartamentos();
		for (String dpto: deptos.keySet()) {
			try {
				HashSet<DTActividad> actividadesEnDepto = obtenerDatosActividadesAsociadas(dpto);
				for (DTActividad act: actividadesEnDepto) {
					if (act.getEstado() == Estado.AGREGADA) {
						actividades.add(act.getNombre());
					}
				}
				
			} catch (departamentoNoExisteException e) {
				e.printStackTrace();
			}
		}
		return actividades.toArray(new String[actividades.size()]);
		
	}

	@Override
	public void modificarEstadoActividad(String actividadSeleccionada, Estado estado) {
		ManejadorDepartamentoCategoria mDptos = ManejadorDepartamentoCategoria.getInstance();
		String nomDepto = obtenerDeptoActividad(actividadSeleccionada);
		Departamento depto = mDptos.getDepartamento(nomDepto);
		depto.modificarEstadoActividad(actividadSeleccionada, estado);
	}

	
	public HashSet<DTActividad> obtenerDatosActividadesConfirmadasDpto(String nombreDpto) throws departamentoNoExisteException{
		ManejadorDepartamentoCategoria mDpto = ManejadorDepartamentoCategoria.getInstance();
		Departamento dpto = mDpto.getDepartamento(nombreDpto);
		if (dpto == null)
			throw new departamentoNoExisteException("Departamento no encontrado");
		Vector<Actividad> actsConfi = dpto.getActividadesConfirmadas();
		HashSet<DTActividad> res = new HashSet<DTActividad>();
		for (Actividad act : actsConfi) {
			res.add(act.getDatos());
		}
		return res;
	}
	
	public HashSet<DTActividad> obtenerDatosActividadesConfirmadasCat(String nombreCat) throws categoriaNoExisteException{
		ManejadorDepartamentoCategoria mCat = ManejadorDepartamentoCategoria.getInstance();
		Categoria cat = mCat.getCategoria(nombreCat);
		if (cat == null)
			throw new categoriaNoExisteException("Categoria no encontrada");
		Vector<Actividad> actsConfi = cat.getActividadesConfirmadas();
		HashSet<DTActividad> res = new HashSet<DTActividad>();
		for (Actividad act : actsConfi) {
			res.add(act.getDatos());
		}
		return res;
	}

	public void ingresarDatosCategoria(String nombre) throws categoriaYaExisteException{
		ManejadorDepartamentoCategoria mCat = ManejadorDepartamentoCategoria.getInstance();
		Categoria existe = mCat.getCategoria(nombre);
		if (existe != null)
			throw new categoriaYaExisteException("Categoria ya existe");
		else {
			Categoria nueva = new Categoria(nombre);
			mCat.addCategoria(nueva);
		}
	}
	
	public Set<String> obtenerCategorias(){
		ManejadorDepartamentoCategoria mCat = ManejadorDepartamentoCategoria.getInstance();
		HashMap<String, Categoria> cats = mCat.getCategorias();
		HashSet<String> res = new HashSet<String>();
		cats.forEach((key, value)-> {
			res.add(key);
		});
		return res;
	}
	
}










