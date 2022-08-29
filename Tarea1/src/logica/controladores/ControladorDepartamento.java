package logica.controladores;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;

import data.ManejadorDepartamento;
import data.ManejadorSalida;
import data.ManejadorUsuario;
import datatypes.DTActividad;
import datatypes.DTSalida;
import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.salidaNoExisteException;
import excepciones.salidaYaExisteException;
import logica.Actividad;
import logica.Departamento;
import logica.Salida;

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
	
	public boolean ingresarDatosSalida(String nombre, int maxTuristas, GregorianCalendar fechaSalida, String lugarSalida, String nombreDpto, String nombreAct) throws excepciones.salidaYaExisteException, actividadNoExisteException {
		ManejadorDepartamento mDptos = ManejadorDepartamento.getInstance();
		Departamento dpto = mDptos.getDepartamento(nombreDpto);
		Actividad act = dpto.obtenerActividad(nombreAct);
		if (act == null)
			throw new actividadNoExisteException("No se encontró una actividad con el nombre ingresado.");
		boolean existeSalida = act.existeSalida(nombre);
		if (existeSalida)
			throw new salidaYaExisteException("Ya existe Salida");
		else {
			GregorianCalendar fechaActual = new GregorianCalendar();//fecha actual
			Salida nueva = new Salida(nombre, maxTuristas, fechaActual, fechaSalida, lugarSalida, act);
			act.addSalida(nueva);
			ManejadorSalida msal = ManejadorSalida.getInstance();
			msal.addSalida(nueva);
		}
		return existeSalida;
	}
}
