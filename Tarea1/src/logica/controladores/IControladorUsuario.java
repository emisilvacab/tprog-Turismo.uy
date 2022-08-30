package logica.controladores;

import java.util.GregorianCalendar;
import java.util.Set;

import logica.datatypes.DTUsuario;
import logica.datatypes.DTActividad;
import logica.datatypes.DTSalida;
import excepciones.usuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import excepciones.actividadNoExisteException;

public interface IControladorUsuario {
	public abstract String[] obtenerUsuarios();

	public abstract DTUsuario obtenerUsuario(String nickname) throws usuarioNoExisteException;
	
	public abstract String[] obtenerSalidasInscripto(String nickname) throws usuarioNoExisteException;

	public abstract String[] obtenerActividadesOfrecidas(String nickname) throws usuarioNoExisteException;

	public abstract void altaUsuario(DTUsuario user) throws UsuarioRepetidoException;
	
	public abstract String ingresarDatosInscripcion(String nickname,String nombre,int capacidad, GregorianCalendar fechaAlta) throws excepciones.salidaNoExisteException, usuarioNoExisteException;
	
	public abstract String[] obtenerProveedores();
	
	public abstract String[] obtenerSalidasDeActividad(String nickname, String nombreAct) throws usuarioNoExisteException, actividadNoExisteException;
	
	public abstract DTActividad obtenerDatoActividadProveedor(String nickname, String nombreAct) throws usuarioNoExisteException;
	
	public abstract DTSalida obtenerDatoSalidaProveedor(String nickname, String nombreAct, String nombreSal) throws usuarioNoExisteException, actividadNoExisteException;
    
}