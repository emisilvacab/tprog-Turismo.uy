package logica.controladores;

import excepciones.actividadNoExisteException;
import excepciones.departamentoNoExisteException;
import excepciones.paqueteNoExisteException;

public interface IControladorPaquete {
	
	public abstract void agregarActividadPaquete(String nombreDpto, String nombrePaq, String nombreAct) throws paqueteNoExisteException, departamentoNoExisteException, actividadNoExisteException;

}
