package logica.controladores;

import java.util.Set;

import datatypes.DTUsuario;
import excepciones.usuarioNoExisteException;
import excepciones.UsuarioRepetidoException;

public interface IControladorUsuario {
	public abstract Set<String> obtenerUsuarios();

	public abstract DTUsuario obtenerUsuario(String nickname) throws usuarioNoExisteException;
	
	public abstract Set<String> obtenerSalidasInscripto(String nickname) throws usuarioNoExisteException;

	public abstract Set<String> mostrarActividadesOfrecidas(String nickname) throws usuarioNoExisteException;

	public abstract void altaUsuario(DTUsuario user) throws UsuarioRepetidoException;
	
	public abstract String ingresarDatosInscripcion(String nickname,String nombre,int capacidad) throws excepciones.salidaNoExisteException, usuarioNoExisteException;
	

}