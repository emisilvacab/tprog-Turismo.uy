package logica.controladores;

import java.awt.Image;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;

import excepciones.actividadNoExisteException;
import excepciones.compraExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.paqueteNoExisteException;
import excepciones.paqueteYaExisteException;
import excepciones.salidaNoExisteException;
import excepciones.usuarioNoExisteException;
import logica.Actividad;
import logica.Compra;
import logica.Departamento;
import logica.Estado;
import logica.Paquete;
import logica.Salida;
import logica.Turista;
import logica.datatypes.DTActividad;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTSalida;
import logica.manejadores.ManejadorDepartamentoCategoria;
import logica.manejadores.ManejadorPaquete;
import logica.manejadores.ManejadorSalida;
import logica.manejadores.ManejadorUsuario;

public class ControladorPaquete implements IControladorPaquete {
	
	public void agregarActividadPaquete(String nombreDpto, String nombrePaq, String nombreAct) throws paqueteNoExisteException, departamentoNoExisteException, actividadNoExisteException {
		ManejadorDepartamentoCategoria md = ManejadorDepartamentoCategoria.getInstance();
		ManejadorPaquete mp = ManejadorPaquete.getInstance();
		
		Paquete paq = mp.getPaquete(nombrePaq);
		if (paq == null)
			throw new paqueteNoExisteException("No existe el paquete ingresado");
		
		Departamento dpto = md.getDepartamento(nombreDpto);
		if (dpto == null)
			throw new departamentoNoExisteException("No existe el departamento ingresado");
		
		Actividad act = dpto.getActividades().get(nombreAct);
		if (act == null)
			throw new actividadNoExisteException("No existe la actividad ingresada");
		
		paq.addActividad(act);
		act.addPaquete(paq);
	}
	
	public void ingresarDatosPaquete(String nombrePaq, String descripcion, int validez, float descuento, GregorianCalendar fechaAlta, Image figura) throws paqueteYaExisteException{
		ManejadorPaquete mp = ManejadorPaquete.getInstance();
		Paquete existe = mp.getPaquete(nombrePaq);
		if (existe != null)
			throw new paqueteYaExisteException("Ya existe el paquete ingresado");
		Paquete nuevo = new Paquete(nombrePaq, descripcion, validez, descuento, fechaAlta);
		nuevo.setFigura(figura);
		mp.addPaquete(nuevo);
	}
	
	public DTPaquete obtenerDatosPaquete(String nombrePaq) throws paqueteNoExisteException{
		ManejadorPaquete mp = ManejadorPaquete.getInstance();
		Paquete paq = mp.getPaquete(nombrePaq);
		if (paq == null)
			throw new paqueteNoExisteException("No existe el paquete ingresado");
		DTPaquete datos = new DTPaquete(paq.getNombre(), paq.getDescripcion(), paq.getValidez(), paq.getDescuento(), paq.getFechaAlta());
		return datos;
	}
	
	public HashSet<DTPaquete> obtenerPaquetesDisponibles(String nickname, String nombreSalida, int cantTuristas) throws usuarioNoExisteException{ 
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Turista turista = mu.getTurista(nickname);
		if (turista == null) throw new usuarioNoExisteException("Usuario no encontrado.");
		return turista.obtenerPaquetesDisponibles(nombreSalida, cantTuristas);	
	}
	
	public HashSet<DTPaquete> obtenerPaquetesConActividades(){
		HashSet<DTPaquete> res = new HashSet<DTPaquete>();
		ManejadorPaquete mp = ManejadorPaquete.getInstance();
		HashMap<String, Paquete> paquetes = mp.getPaquetes();
		
		for (Paquete paquete : paquetes.values()) {
			if (paquete.getActividades().size() > 0)
				res.add(paquete.getDatos());
	    }
		return res;
	}
	
	public void comprarPaquete(String nickname, String nombrePaq, GregorianCalendar fechaCompra, int cantidadTuristas) throws usuarioNoExisteException, paqueteNoExisteException, compraExisteException {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		ManejadorPaquete mp= ManejadorPaquete.getInstance();
		
		Paquete paquete = mp.getPaquete(nombrePaq);
		Turista turista = mu.getTurista(nickname);
		
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
	
	public HashSet<DTPaquete> obtenerPaquetesNoComprados(){
		ManejadorPaquete mp = ManejadorPaquete.getInstance();
		HashMap<String, Paquete> paquetes = mp.getPaquetes();
		HashSet<DTPaquete> res = new HashSet<DTPaquete>();
		
		for (Paquete p : paquetes.values()) {
			if (p.getCompras().size() == 0) {
				res.add(p.getDatos());
			}	
		}
		return res;
	}
	
	//Función que devuelve las actividades confirmadas del dpto que no pertenecen al paquete pasado por parámetro
	public HashSet<DTActividad> obtenerDatosActividadesConfirmadasNoPaquete(String nombreDpto, String nombrePaq) throws departamentoNoExisteException, paqueteNoExisteException{
		ManejadorDepartamentoCategoria mDpto = ManejadorDepartamentoCategoria.getInstance();
		Departamento dpto = mDpto.getDepartamento(nombreDpto);
		ManejadorPaquete mp = ManejadorPaquete.getInstance();
		Paquete paquete = mp.getPaquete(nombrePaq);
		
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
	public HashSet<DTPaquete> obtenerDatosPaquetesParaActividad(String nombreAct) {
		HashSet<DTPaquete> ans = new HashSet<DTPaquete>();
		ManejadorPaquete manPaquete = ManejadorPaquete.getInstance();
		HashMap<String, Paquete> setPaquetes = manPaquete.getPaquetes();
		for (Paquete paquete: setPaquetes.values()) {
			if (paquete.getActividades().containsKey(nombreAct)) {
				ans.add(paquete.getDatos());
			}
		}
		return ans;
	}
	
	@Override
	public DTSalida obtenerDatosSalida(String nombreSalida) throws salidaNoExisteException {
		ManejadorSalida manSalida = ManejadorSalida.getInstance();
		DTSalida salida = manSalida.getSalida(nombreSalida).getDatos();
		if (salida == null)
			 new salidaNoExisteException("Salida no encontrada");
		return salida;
	}
	
	@Override
	public String obtenerNombreActivdadDeSalida(String nombreSalida) throws salidaNoExisteException {
		ManejadorSalida manSalida = ManejadorSalida.getInstance();
		Salida salida = manSalida.getSalida(nombreSalida);
		if (salida == null) {
			new salidaNoExisteException("Salida no encontrada");
		}
		return salida.getActividad().getNombre();
	}
}
























