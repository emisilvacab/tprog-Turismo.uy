package logica.controladores;

import java.util.Set;

import datatypes.DTUsuario;
import excepciones.usuarioNoExisteException;

public interface IControladorUsuario {
	public abstract Set<String> obtenerUsuarios();

	public abstract DTUsuario obtenerUsuario(String nickname) throws usuarioNoExisteException;
	
	public abstract Set<String> obtenerSalidasInscripto(String nickname) throws usuarioNoExisteException;

	public abstract Set<String> mostrarActividadesOfrecidas(String nickname) throws usuarioNoExisteException;

	public abstract Set<String> mostrarSalidasAsociadas(Set<String> actividadesOfrecidas);

}