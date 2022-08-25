package logica.controladores;

import java.util.Set;

import excepciones.salidaNoExisteException;
import excepciones.usuarioNoExisteException;
import logica.DataType.*;

public interface IControladorUsuario {
	public abstract Set<String> obtenerUsuarios();

	public abstract DataUsuario obtenerUsuario(String nickname) throws usuarioNoExisteException;
	
	public abstract Set<String> obtenerSalidasInscripto(String nickname) throws usuarioNoExisteException;

	public abstract Set<String> mostrarActividadesOfrecidas(String nickname) throws usuarioNoExisteException;

	public abstract Set<String> mostrarSalidasAsociadas(Set<String> actividadesOfrecidas);
	
	public abstract String ingresarDatosInscripcion(String nickname,String nombre,int capacidad) throws usuarioNoExisteException,salidaNoExisteException;

	public abstract void cargarDatos();

}