package logica.controladores;

import java.util.GregorianCalendar;
import java.util.Set;

import excepciones.usuarioNoExisteException;
import logica.datatypes.DTUsuario;
import excepciones.UsuarioRepetidoException;

public interface IControladorUsuario {
	public abstract String[] obtenerUsuarios();

	public abstract DTUsuario obtenerUsuario(String nickname) throws usuarioNoExisteException;
	
	public abstract String[] obtenerSalidasInscripto(String nickname) throws usuarioNoExisteException;

	public abstract String[] obtenerActividadesOfrecidas(String nickname) throws usuarioNoExisteException;

	public abstract void altaUsuario(DTUsuario user) throws UsuarioRepetidoException;
	
	public abstract String ingresarDatosInscripcion(String nickname,String nombre,int capacidad, GregorianCalendar fechaAlta) throws excepciones.salidaNoExisteException, usuarioNoExisteException;
	
	public abstract String[] obtenerProveedores();
	
}