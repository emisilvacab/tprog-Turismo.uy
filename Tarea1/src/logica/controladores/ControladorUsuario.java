package logica.controladores;

import logica.DataType.DataUsuario;
import logica.DataType.*;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import data.ManejadorUsuario;
import excepciones.usuarioNoExisteException;
import logica.Proveedor;
import logica.Turista;
import logica.Usuario;

public class ControladorUsuario implements IControladorUsuario {

    public ControladorUsuario() {
    }
    
    public Set<String> obtenerUsuarios(){
    	ManejadorUsuario mu = ManejadorUsuario.getInstance();
    	Map<String, Proveedor> proveedores = mu.getProveedores();
    	Map<String, Turista> turistas = mu.getTuristas();
    	Set<String> usersName = proveedores.keySet();
    	usersName.addAll(turistas.keySet());
    	return usersName;
    	
    }
    

	@Override
	public DataUsuario obtenerUsuario(String nickname) throws usuarioNoExisteException {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Usuario user = mu.getTurista(nickname);
		if (user == null) {
			user = mu.getProveedor(nickname);
		}
		if (user == null) {
			throw new usuarioNoExisteException("no se encontro ningun usuario con el nickname ingresado");
		}else {
			if (user.getClass() == Turista.class) {
				Turista tur = (Turista)user;
				return new DataTurista(user.getNickname(), user.getNombre(), user.getApellido(), user.getCorreo(), user.getNacimiento(), tur.getNacionalidad());
			}else {
				Proveedor prov = (Proveedor)user;
				return new DataProveedor(user.getNickname(), user.getNombre(), user.getApellido(), user.getCorreo(), user.getNacimiento(), prov.getDescripcion(), prov.getLink());
			}
	
		}
		
	}
    
    
    
//    public DataUsuario[] getUsuarios() throws UsuarioNoExisteException {
//        ManejadorUsuario mu = ManejadorUsuario.getInstance();
//        Usuario[] usrs = mu.getUsuarios();
//
//        if (usrs != null) {
//            DataUsuario[] du = new DataUsuario[usrs.length];
//            Usuario usuario;
//
//            // Para separar lógica de presentación, no se deben devolver los Usuario,
//            // sino los DataUsuario
//            for (int i = 0; i < usrs.length; i++) {
//                usuario = usrs[i];
//                du[i] = new DataUsuario(usuario.getNombre(), usuario.getApellido(), usuario.getCedulaIdentidad());
//            }
//
//            return du;
//        } else
//            throw new UsuarioNoExisteException("No existen usuarios registrados");
//
//    }
}