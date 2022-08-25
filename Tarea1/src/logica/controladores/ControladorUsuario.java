package logica.controladores;


import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import datatypes.*;
import data.ManejadorUsuario;
import excepciones.UsuarioRepetidoException;
import excepciones.usuarioNoExisteException;
import logica.Inscripcion;
import logica.Paquete;
import logica.Proveedor;
import logica.Turista;
import logica.Usuario;
import logica.Actividad;
import data.ManejadorPaquete;

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
	public DTUsuario obtenerUsuario(String nickname) throws usuarioNoExisteException {
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
				return new DTTurista(user.getNickname(), user.getNombre(), user.getApellido(), user.getCorreo(), user.getNacimiento(), tur.getNacionalidad());
			}else {
				Proveedor prov = (Proveedor)user;
				return new DTProveedor(user.getNickname(), user.getNombre(), user.getApellido(), user.getCorreo(), user.getNacimiento(), prov.getDescripcion(), prov.getLink());
			}
	
		}
		
	}

	@Override
	public Set<String> obtenerSalidasInscripto(String nickname) throws usuarioNoExisteException {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Turista tur = mu.getTurista(nickname);
		if (tur == null) throw new usuarioNoExisteException("El turista no fue encontrado");
		Vector<Inscripcion> inscripciones = tur.getInscripciones();
		Set<String> nombresSalidas = new HashSet<String>();
		for (Inscripcion inscripcion: inscripciones) {
			if (inscripcion.getSalida() != null) {
				nombresSalidas.add(inscripcion.getSalida().getNombre());
			}
		}
		return nombresSalidas;
		
	
	}

	@Override
	public Set<String> mostrarActividadesOfrecidas(String nickname) throws usuarioNoExisteException {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Proveedor prov = mu.getProveedor(nickname);
		Set<String> actividades = new HashSet<String>();
		if (prov==null) throw new usuarioNoExisteException("no se encontro ningun usuario con el nickname ingresado");
		else {
			for (Actividad act :prov.getActividades().values()){
				actividades.add(act.getNombre());
			}
		}
		return actividades;
		
	}

	@Override
	public void altaUsuario(DTUsuario user) throws UsuarioRepetidoException {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		if (mu.getProveedor(user.getNickname())!=null || mu.getTurista(user.getNickname())!=null) {
			throw new UsuarioRepetidoException("Ya existe un usuario con el nickname ingresado");
		}else {
			if (user.getClass() == DTTurista.class) {
				DTTurista dttur = (DTTurista)user;
				mu.addTurista(new Turista(user.getNickname(), user.getNombre(), user.getApellido(), user.getCorreo(), user.getNacimiento(), dttur.getNacionalidad()));
			}else {
				DTProveedor dtprov = (DTProveedor)user;
				mu.addProveedor(new Proveedor(user.getNickname(), user.getNombre(), user.getApellido(), user.getCorreo(), user.getNacimiento(), dtprov.getDescripcion(), dtprov.getLink()));
				
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