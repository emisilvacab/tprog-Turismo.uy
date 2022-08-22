package logica.controladores;

import java.util.Set;

import excepciones.usuarioNoExisteException;
import logica.DataType.*;

public interface IControladorUsuario {
	public abstract Set<String> obtenerUsuarios();

	public abstract DataUsuario obtenerUsuario(String nickname) throws usuarioNoExisteException;
	
	public abstract Set<String> obtenerSalidasInscripto(String nickname) throws usuarioNoExisteException;

	public abstract Set<String> mostrarActividadesOfrecidas(String nickname) throws usuarioNoExisteException;

	public abstract Set<String> mostrarSalidasAsociadas(Set<String> actividadesOfrecidas);

}