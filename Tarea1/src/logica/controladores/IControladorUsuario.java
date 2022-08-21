package logica.controladores;

import java.util.Set;

import excepciones.usuarioNoExisteException;
import logica.DataType.*;

public interface IControladorUsuario {
	public abstract Set<String> obtenerUsuarios();

	public abstract DataUsuario obtenerUsuario(String nickname) throws usuarioNoExisteException;

}