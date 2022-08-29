package logica.controladores;

import java.util.GregorianCalendar;
import java.util.Set;

import datatypes.DTUsuario;
import excepciones.usuarioNoExisteException;
import excepciones.UsuarioRepetidoException;

public interface IControladorUsuario {
	public abstract String[] obtenerUsuarios();

	public abstract DTUsuario obtenerUsuario(String nickname) throws usuarioNoExisteException;
	
	public abstract String[] obtenerSalidasInscripto(String nickname) throws usuarioNoExisteException;

	public abstract String[] mostrarActividadesOfrecidas(String nickname) throws usuarioNoExisteException;

	public abstract void altaUsuario(DTUsuario user) throws UsuarioRepetidoException;
	
	public abstract String ingresarDatosInscripcion(String nickname,String nombre,int capacidad) throws excepciones.salidaNoExisteException, usuarioNoExisteException;
	
	public abstract String[] obtenerProveedores();
	
}