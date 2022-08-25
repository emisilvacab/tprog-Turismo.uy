package logica.controladores;

import logica.DataType.DataUsuario;

import logica.DataType.*;

import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import data.ManejadorUsuario;
import excepciones.salidaNoExisteException;
import excepciones.usuarioNoExisteException;
import logica.Inscripcion;
import logica.Paquete;
import logica.Proveedor;
import logica.Salida;
import logica.Turista;
import logica.Usuario;
import logica.Actividad;
import data.ManejadorPaquete;
import data.ManejadorSalida;

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
	public Set<String> mostrarSalidasAsociadas(Set<String> actividadesOfrecidas) {
		Set<String> salidas = new HashSet<String>();
		ManejadorPaquete mp = ManejadorPaquete.getInstance();
		for (Paquete paq: mp.getPaquetes().values()) {
			salidas.addAll(paq.obtenerNombresSalidasAsociadas(actividadesOfrecidas));
		}
		return salidas;
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
//    } QUE ES ESTO?????
	
	public String ingresarDatosInscripcion(String nickname,String nombre,int capacidad) throws usuarioNoExisteException,salidaNoExisteException{
		
		ManejadorSalida mSalida = ManejadorSalida.getInstance();
		ManejadorUsuario mUsuario = ManejadorUsuario.getInstance();
		
		Salida salida = mSalida.getSalida(nombre);
		if (salida == null)
			throw new salidaNoExisteException("No se encontró una salida con el nombre ingresado.");
		Turista turista = mUsuario.getTurista(nickname);
		if (turista == null) 
			throw new usuarioNoExisteException("No se encontró un turista con el nickname ingresado.");
		
		boolean hayLugar = salida.admiteCapacidad(capacidad);
		boolean existe = salida.existeInscripcion(turista.getNickname());
		if (hayLugar && !existe) {
			GregorianCalendar fechaActual = GregorianCalendar.from(ZonedDateTime.now());
			Inscripcion insc = new Inscripcion(fechaActual,capacidad,salida,turista);
			salida.addInscripcion(insc);
			turista.addInscripcion(insc);
			return "no";
		}
		else {
			if(!hayLugar)
				return "lleno";
			else 
				return "existe";
		}
	}
}