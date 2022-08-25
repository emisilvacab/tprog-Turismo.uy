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
import logica.Actividad;
import logica.Departamento;
import logica.Proveedor;
import logica.Salida;


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

	@Override
	public void cargarDatos() { //importe proveedor que por ahora no lo ve,salida, 
		Departamento dpto1 = new Departamento ("Montevideo","capital", "mvdeo.com");
		Departamento dpto2 = new Departamento ("Florida","lindo", "florida.com");
		ManejadorDepartamento md = ManejadorDepartamento.getInstance();
		md.addDepartamento(dpto1);
		md.addDepartamento(dpto2);
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Proveedor p1 = mu.getProveedor("joacomanya");
		Actividad act1 = new Actividad ("Pasear en Parque Rodo", "rara", 3, (float) 100,"cordon", new GregorianCalendar(2022,8,24),dpto1,p1);
		Actividad act2 = new Actividad ("Paseo en Florida", "lindo", 3, (float) 1000,"cabo", new GregorianCalendar(2022,8,24),dpto2,p1);
		p1.addActividad(act1);
		p1.addActividad(act2);
		dpto1.addActividad(act1);
		dpto2.addActividad(act2);
		Salida s1 = new Salida("Paseo por los juegos", 3, new GregorianCalendar(2022,8,24), new GregorianCalendar(2022,8,25), "Obelisco",act1);
		Salida s2 = new Salida("Paseo por casco viejo", 30, new GregorianCalendar(2022,8,24), new GregorianCalendar(2022,8,25), "Rio negro",act2);
		act1.addSalida(s1);
		act2.addSalida(s2);
		ManejadorSalida ms = ManejadorSalida.getInstance();
		ms.addSalida(s1);
		ms.addSalida(s2);
	}
}
