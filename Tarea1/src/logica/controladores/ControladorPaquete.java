package logica.controladores;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import excepciones.actividadNoExisteException;
import excepciones.compraExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.paqueteNoExisteException;
import excepciones.paqueteYaExisteException;
import excepciones.usuarioNoExisteException;
import logica.Actividad;
import logica.Compra;
import logica.Departamento;
import logica.Estado;
import logica.Categoria;
import logica.Paquete;
import logica.Turista;
import logica.datatypes.DTActividad;
import logica.datatypes.DTPaquete;
import logica.manejadores.ManejadorDepartamentoCategoria;
import logica.manejadores.ManejadorPaquete;
import logica.manejadores.ManejadorUsuario;

public class ControladorPaquete implements IControladorPaquete {
	
	public void agregarActividadPaquete(String nombreDpto, String nombrePaq, String nombreAct) throws paqueteNoExisteException, departamentoNoExisteException, actividadNoExisteException {
		ManejadorDepartamentoCategoria mDep = ManejadorDepartamentoCategoria.getInstance();
		ManejadorPaquete mPaq = ManejadorPaquete.getInstance();
		
		Paquete paq = mPaq.getPaquete(nombrePaq);
		if (paq == null)
			throw new paqueteNoExisteException("No existe el paquete ingresado");
		
		Departamento dpto = mDep.getDepartamento(nombreDpto);
		if (dpto == null)
			throw new departamentoNoExisteException("No existe el departamento ingresado");
		
		Actividad act = dpto.getActividades().get(nombreAct);
		if (act == null)
			throw new actividadNoExisteException("No existe la actividad ingresada");
		
		paq.addActividad(act);
		act.addPaquete(paq);
	}
	
	public void ingresarDatosPaquete(String nombrePaq, String descripcion, int validez, float descuento, GregorianCalendar fechaAlta, String linkImagen) throws paqueteYaExisteException{
		ManejadorPaquete mPaq = ManejadorPaquete.getInstance();
		Paquete existe = mPaq.getPaquete(nombrePaq);
		if (existe != null)
			throw new paqueteYaExisteException("Ya existe el paquete ingresado");
		Paquete nuevo = new Paquete(nombrePaq, descripcion, validez, descuento, fechaAlta, linkImagen);
		mPaq.addPaquete(nuevo);
	}
	
	public DTPaquete obtenerDatosPaquete(String nombrePaq) throws paqueteNoExisteException{
		ManejadorPaquete mPaq = ManejadorPaquete.getInstance();
		Paquete paq = mPaq.getPaquete(nombrePaq);
		if (paq == null)
			throw new paqueteNoExisteException("No existe el paquete ingresado");
		DTPaquete datos = paq.getDatos();
		return datos;
	}
	
	public Set<DTPaquete> obtenerPaquetesDisponibles(String nickname, String nombreSalida, int cantTuristas) throws usuarioNoExisteException{ 
		ManejadorUsuario mUsr = ManejadorUsuario.getInstance();
		Turista turista = mUsr.getTurista(nickname);
		if (turista == null) throw new usuarioNoExisteException("Usuario no encontrado.");
		return turista.obtenerPaquetesDisponibles(nombreSalida, cantTuristas);	
	}
	
	public Set<DTPaquete> obtenerPaquetesConActividades(){
		HashSet<DTPaquete> res = new HashSet<DTPaquete>();
		ManejadorPaquete mPaq = ManejadorPaquete.getInstance();
		HashMap<String, Paquete> paquetes = (HashMap<String, Paquete>) mPaq.getPaquetes();
		
		for (Paquete paquete : paquetes.values()) {
			if (paquete.getActividades().size() > 0)
				res.add(paquete.getDatos());
	    }
		return res;
	}
	
	public void comprarPaquete(String nickname, String nombrePaq, GregorianCalendar fechaCompra, int cantidadTuristas) throws usuarioNoExisteException, paqueteNoExisteException, compraExisteException {
		ManejadorUsuario mUsr = ManejadorUsuario.getInstance();
		ManejadorPaquete mPaq= ManejadorPaquete.getInstance();
		
		Paquete paquete = mPaq.getPaquete(nombrePaq);
		Turista turista = mUsr.getTurista(nickname);
		
		if (paquete == null) throw new paqueteNoExisteException("Paquete no encontrado");
		if (turista == null) throw new usuarioNoExisteException("Usuario no encontrado");
		if (turista.existeCompra(paquete.getNombre())) throw new compraExisteException("Compra de paquete ya realizada anteriormente");
		
		GregorianCalendar vencimiento = fechaCompra;
		vencimiento.add(GregorianCalendar.DAY_OF_MONTH, paquete.getValidez());
		
		float costo = 0;
		for (Actividad act : paquete.getActividades().values()) {
			costo = costo + act.getCosto();
		}
		costo = costo * cantidadTuristas;
		costo = costo - ((costo * paquete.getDescuento()) / 100);
		
		Compra compra = new Compra(fechaCompra, cantidadTuristas, vencimiento, costo, turista, paquete);
		turista.addCompra(compra);
		paquete.addCompra(compra);		
	}
	
	public Set<DTPaquete> obtenerPaquetesNoComprados(){
		ManejadorPaquete mPaq = ManejadorPaquete.getInstance();
		HashMap<String, Paquete> paquetes = (HashMap<String, Paquete>) mPaq.getPaquetes();
		HashSet<DTPaquete> res = new HashSet<DTPaquete>();
		
		for (Paquete p : paquetes.values()) {
			if (p.getCompras().size() == 0) {
				res.add(p.getDatos());
			}	
		}
		return res;
	}
	
	//Función que devuelve las actividades confirmadas del dpto que no pertenecen al paquete pasado por parámetro
	public Set<DTActividad> obtenerDatosActividadesConfirmadasNoPaquete(String nombreDpto, String nombrePaq) throws departamentoNoExisteException, paqueteNoExisteException{
		ManejadorDepartamentoCategoria mDpto = ManejadorDepartamentoCategoria.getInstance();
		Departamento dpto = mDpto.getDepartamento(nombreDpto);
		ManejadorPaquete mPaq = ManejadorPaquete.getInstance();
		Paquete paquete = mPaq.getPaquete(nombrePaq);
		
		if (dpto == null)
			throw new departamentoNoExisteException("Departamento no encontrado");
		if (paquete == null)
			throw new paqueteNoExisteException("Paquete no encontrado");
		
		HashSet<DTActividad> res = new HashSet<DTActividad>();
		for (Actividad act : dpto.getActividades().values()) {
			if (act.getEstado() == Estado.CONFIRMADA && !act.getPaquetes().containsKey(nombrePaq))
				res.add(act.getDatos());
		}
		return res;
	}
	
	@Override
	public Set<DTPaquete> obtenerDatosPaquetesParaActividad(String nombreAct) {
		HashSet<DTPaquete> ans = new HashSet<DTPaquete>();
		ManejadorPaquete manPaquete = ManejadorPaquete.getInstance();
		HashMap<String, Paquete> setPaquetes = (HashMap<String, Paquete>) manPaquete.getPaquetes();
		for (Paquete paquete: setPaquetes.values()) {
			if (paquete.getActividades().containsKey(nombreAct)) {
				ans.add(paquete.getDatos());
			}
		}
		return ans;
	}
	
	public Set<DTPaquete> obtenerPaquetesAll(){
		HashSet<DTPaquete> paquetesDT = new HashSet<DTPaquete>();
		ManejadorPaquete manPaquete = ManejadorPaquete.getInstance();
		HashMap<String, Paquete> paquetes = (HashMap<String, Paquete>) manPaquete.getPaquetes();
		
		paquetes.forEach((nombre, paq) -> {
			DTPaquete dato = paq.getDatos();
			paquetesDT.add(dato);		
		});
		
		return paquetesDT;
	}

	public Set<DTActividad> obtenerActividadesPaquete(String nombrePaq) throws paqueteNoExisteException{
		HashSet<DTActividad> actividadesConfirmadas = new HashSet<DTActividad>();
		ManejadorPaquete manPaquete = ManejadorPaquete.getInstance();
		
		Paquete paquete = manPaquete.getPaquete(nombrePaq);
		if (paquete == null) {
			throw new paqueteNoExisteException("Paquete no encontrado"); 
		}else {
			HashMap<String, Actividad> actividadesPaq = (HashMap<String, Actividad>) paquete.getActividades();
		
			actividadesPaq.forEach((key, actividad)-> {
				//if(actividad.getEstado().equals(Estado.CONFIRMADA)) {
					DTActividad nueva = actividad.getDatos();
					actividadesConfirmadas.add(nueva);
				//}
			
			});
		
		
			return actividadesConfirmadas;
		}
	}
	
	public Set<String> obtenerCategoriasPaquete(String nombrePaq) throws paqueteNoExisteException{
		ManejadorPaquete manPaquete = ManejadorPaquete.getInstance();
		Paquete paquete = manPaquete.getPaquete(nombrePaq);
		if (paquete == null) {
			throw new paqueteNoExisteException("Paquete no encontrado"); 
		}else {
			HashSet<String> categoriasStr = new HashSet<String>();
			HashMap<String, Actividad> actividadesPaq = (HashMap<String, Actividad>) paquete.getActividades();
			
			actividadesPaq.forEach((key, actividad)-> {
				Map<String, Categoria> catMap = actividad.getCategorias();
				
				catMap.forEach((keyMap, categoria)-> {
					if (!categoriasStr.contains(categoria.getNombre())){
						categoriasStr.add(categoria.getNombre());
					}
					
				});
			});
		
			return categoriasStr;
		}
	}


}