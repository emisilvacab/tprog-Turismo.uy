package logica.controladores;

import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import excepciones.actividadNoExisteException;
import excepciones.actividadPerteneceAPaqueteException;
import excepciones.actividadTieneSalidasVigentesException;
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
	
	public Set<String> obtenerDepartamentos(){
		ManejadorDepartamentoCategoria mDptos = ManejadorDepartamentoCategoria.getInstance();
		HashMap<String, Departamento> dptos = (HashMap<String, Departamento>) mDptos.getDepartamentos();
		HashSet<String> res = new HashSet<String>();
		dptos.forEach((key, value)-> {
			res.add(key);
		});
		return res;
	}
	
	public Set<DTActividad> obtenerDatosActividadesAsociadas(String nombreDpto) throws departamentoNoExisteException {
		ManejadorDepartamentoCategoria mDptos = ManejadorDepartamentoCategoria.getInstance();
		Departamento dpto = mDptos.getDepartamento(nombreDpto);
		if (dpto == null) 
			throw new departamentoNoExisteException("No se encontró un departamento con el nombre ingresado");
		return dpto.obtenerDatosActividades();
	}
	
	public Set<DTSalida> obtenerDatosSalidasParaActividad(String nombreAct) throws actividadNoExisteException {
		ManejadorDepartamentoCategoria mDptos = ManejadorDepartamentoCategoria.getInstance();
		HashMap<String, Departamento> dptos = (HashMap<String, Departamento>) mDptos.getDepartamentos();
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
	
	public Set<DTSalida> obtenerDatosSalidasVigentes(String nombreAct) throws actividadNoExisteException {
		ManejadorDepartamentoCategoria mDptos = ManejadorDepartamentoCategoria.getInstance();
		HashMap<String, Departamento> dptos = (HashMap<String, Departamento>) mDptos.getDepartamentos();
		for (Departamento dpto : dptos.values()) {
			if (dpto.getActividades().get(nombreAct) != null)
				return dpto.getActividades().get(nombreAct).obtenerSalidasVigentes();
		}
		throw new actividadNoExisteException("No se encontró una actividad con el nombre ingresado");
	}
	
	public boolean ingresarDatosActividad(String nombreAct, String descripcion, int duracion, float costo, String ciudad, GregorianCalendar fecha, String nicknameProv, String nombreDep, Set<String> categorias, String linkImagen, String linkVideo) throws excepciones.proveedorNoExisteException, departamentoNoExisteException {
		ManejadorDepartamentoCategoria manDepartamento = ManejadorDepartamentoCategoria.getInstance();
		ManejadorUsuario manUsuario = ManejadorUsuario.getInstance();
		
		HashMap<String, Departamento> departamentos = (HashMap<String, Departamento>) manDepartamento.getDepartamentos();
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
    		Actividad nuevaActividad = new Actividad(nombreAct, descripcion, duracion, costo, ciudad, fecha, depAsignado, proveedor, linkImagen, linkVideo);
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
	
	public boolean ingresarDatosSalida(String nombre, int maxTuristas, GregorianCalendar fechaAlta, GregorianCalendar fechaSalida, int horaSalida, String lugarSalida, String nombreDpto, String nombreAct, String linkImagen) throws excepciones.departamentoNoExisteException, actividadNoExisteException{
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
			Salida nueva = new Salida(nombre, maxTuristas, fechaAlta, fechaSalida, horaSalida, lugarSalida, act, linkImagen);
			act.addSalida(nueva);
			ManejadorSalida msal = ManejadorSalida.getInstance();
			msal.addSalida(nueva);
		}
		return sal != null;
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
		HashMap<String, Departamento> departamentos = (HashMap<String, Departamento>) mDptos.getDepartamentos();
		for (Departamento dep: departamentos.values()) {
			act = dep.obtenerActividad(actividadSeleccionada);
			if (act != null) {
				break;
			}
			
		}
		if (act == null) {
			throw new actividadNoExisteException("actividad no encontrada");
		}else {
			DTActividad res = new DTActividad(act.getNombre(), act.getDescripcion(), act.getDuracion(), act.getCosto(), act.getCiudad(), act.getAlta(), act.getEstado(), act.getLinkImagen(), act.getLinkVideo());
			return res;
		}
	}

	@Override
	public String obtenerDeptoActividad(String actividad) {
		String resu = null;
		ManejadorDepartamentoCategoria mDptos = ManejadorDepartamentoCategoria.getInstance();
		HashMap<String, Departamento> departamentos = (HashMap<String, Departamento>) mDptos.getDepartamentos();
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
		HashMap<String, Categoria> categorias = (HashMap<String, Categoria>) mDptosCat.getCategorias();
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
		HashMap<String, Departamento> deptos = (HashMap<String, Departamento>) mDptos.getDepartamentos();
		for (String dpto: deptos.keySet()) {
			try {
				HashSet<DTActividad> actividadesEnDepto = (HashSet<DTActividad>) obtenerDatosActividadesAsociadas(dpto);
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

	
	public Set<DTActividad> obtenerDatosActividadesConfirmadasDpto(String nombreDpto) throws departamentoNoExisteException{
		ManejadorDepartamentoCategoria mDpto = ManejadorDepartamentoCategoria.getInstance();
		Departamento dpto = mDpto.getDepartamento(nombreDpto);
		if (dpto == null)
			throw new departamentoNoExisteException("Departamento no encontrado");
		HashSet<Actividad> actsConfi = (HashSet<Actividad>) dpto.getActividadesConfirmadas();
		HashSet<DTActividad> res = new HashSet<DTActividad>();
		for (Actividad act : actsConfi) {
			res.add(act.getDatos());
		}
		return res;
	}
	
	public Set<DTActividad> obtenerDatosActividadesConfirmadasCat(String nombreCat) throws categoriaNoExisteException{
		ManejadorDepartamentoCategoria mCat = ManejadorDepartamentoCategoria.getInstance();
		Categoria cat = mCat.getCategoria(nombreCat);
		if (cat == null)
			throw new categoriaNoExisteException("Categoria no encontrada");
		HashSet<Actividad> actsConfi = (HashSet<Actividad>) cat.getActividadesConfirmadas();
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
		HashMap<String, Categoria> cats = (HashMap<String, Categoria>) mCat.getCategorias();
		HashSet<String> res = new HashSet<String>();
		cats.forEach((key, value)-> {
			res.add(key);
		});
		return res;
	}
	
	@Override
	public DTSalida obtenerDatosSalida(String nombreSalida) throws salidaNoExisteException {
		ManejadorSalida manSalida = ManejadorSalida.getInstance();
		Salida salida = manSalida.getSalida(nombreSalida);
		if (salida == null)
			 throw new salidaNoExisteException("Salida no encontrada");
		return salida.getDatos();
	}
	
	@Override
	public String obtenerNombreActividadDeSalida(String nombreSalida) throws salidaNoExisteException {
		ManejadorSalida manSalida = ManejadorSalida.getInstance();
		Salida salida = manSalida.getSalida(nombreSalida);
		if (salida == null)
			throw new salidaNoExisteException("Salida no encontrada");
		return salida.getActividad().getNombre();
	}
	
	@Override
	public String obtenerNombreProveedorDeActividad(String nombreAct) throws actividadNoExisteException {
		ManejadorDepartamentoCategoria manDepartamento = ManejadorDepartamentoCategoria.getInstance();
		String nombreDepartamento = obtenerDeptoActividad(nombreAct);
		Departamento departamento = manDepartamento.getDepartamento(nombreDepartamento);
		Actividad actividad = departamento.obtenerActividad(nombreAct);
		String nombreProveedor = actividad.getProveedor().getNombre();
		return nombreProveedor;
	}
	
	@Override
	public void finalizarActividad(String nombreAct) throws actividadTieneSalidasVigentesException, actividadPerteneceAPaqueteException {
		ManejadorDepartamentoCategoria manDepartamento = ManejadorDepartamentoCategoria.getInstance();
		String nombreDepartamento = obtenerDeptoActividad(nombreAct);
		Actividad act = manDepartamento.getDepartamento(nombreDepartamento).obtenerActividad(nombreAct);
		if (act.obtenerSalidasVigentes().size() != 0) 
			throw new actividadTieneSalidasVigentesException("La actividad tiene salidas vigentes");
		if (act.getPaquetes().size() != 0)
			throw new actividadPerteneceAPaqueteException("La actividad pertenece a al menos un paquete");
		act.setEstado(Estado.FINALIZADA);
	}
	
	@Override
	public boolean salidaEstaVigente(String nombreSal) throws salidaNoExisteException {
		ManejadorSalida mSalidas = ManejadorSalida.getInstance();
		Salida sal = mSalidas.getSalida(nombreSal);
		if (sal == null)
			throw new salidaNoExisteException("No existe una salida con ese nombre");
		return sal.getFechaSalida().after(GregorianCalendar.from(ZonedDateTime.now()));
	}
}










