package logica.controladores;

import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.paqueteNoExisteException;
import logica.Actividad;
import logica.Departamento;
import logica.Paquete;
import logica.manejadores.ManejadorDepartamento;
import logica.manejadores.ManejadorPaquete;

public class ControladorPaquete implements IControladorPaquete {
	
	public void agregarActividadPaquete(String nombreDpto, String nombrePaq, String nombreAct) throws paqueteNoExisteException, departamentoNoExisteException, actividadNoExisteException {
		ManejadorDepartamento md = ManejadorDepartamento.getInstance();
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
}
