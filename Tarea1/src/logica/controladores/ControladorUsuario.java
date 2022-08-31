package logica.controladores;


import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import excepciones.UsuarioRepetidoException;
import excepciones.actividadNoExisteException;
import excepciones.salidaNoExisteException;
import excepciones.usuarioNoExisteException;
import logica.Inscripcion;
import logica.Proveedor;
import logica.Salida;
import logica.Turista;
import logica.Usuario;
import logica.datatypes.DTActividad;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTSalida;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;
import logica.manejadores.ManejadorSalida;
import logica.manejadores.ManejadorUsuario;
import logica.Actividad;

public class ControladorUsuario implements IControladorUsuario {

    public ControladorUsuario() {
    }
    
    public String[] obtenerUsuarios(){
    	ManejadorUsuario mu = ManejadorUsuario.getInstance();
    	Map<String, Proveedor> proveedores = mu.getProveedores();
    	Map<String, Turista> turistas = mu.getTuristas();
    	Set<String> provsName = proveedores.keySet();
    	Set<String> tursName = turistas.keySet();
    	Set<String> usersName = new HashSet<String>();
    	for (String prov: provsName) {
    		usersName.add(prov);
    	}
    	for (String tur: tursName) {
    		usersName.add(tur);
    	}
    	return usersName.toArray(new String[usersName.size()]);
    	
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
	public String[] obtenerSalidasInscripto(String nickname) throws usuarioNoExisteException {
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
		return nombresSalidas.toArray(new String[nombresSalidas.size()]);
		
	}
	
	

	@Override
	public String[] obtenerActividadesOfrecidas(String nickname) throws usuarioNoExisteException {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Proveedor prov = mu.getProveedor(nickname);
		Set<String> actividades = new HashSet<String>();
		if (prov==null) throw new usuarioNoExisteException("no se encontro ningun usuario con el nickname ingresado");
		else {
			for (Actividad act :prov.getActividades().values()){
				actividades.add(act.getNombre());
			}
		}
		return actividades.toArray(new String[actividades.size()]);
		
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

	
	public String ingresarDatosInscripcion(String nickname,String nombre,int capacidad, GregorianCalendar fechaAlta) throws excepciones.salidaNoExisteException, usuarioNoExisteException{
		
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
			Inscripcion insc = new Inscripcion(fechaAlta,capacidad,salida,turista);
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
	
	public String[] obtenerProveedores() {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
    	Map<String, Proveedor> proveedores = mu.getProveedores();
    	Set<String> provsName = proveedores.keySet();
    	Set<String> usersName = new HashSet<String>();
    	for (String prov: provsName) {
    		usersName.add(prov);
    	}
    	return usersName.toArray(new String[usersName.size()]);
	}
	
	
	@Override
	public String[] obtenerSalidasDeActividad(String nickname, String nombreAct) throws usuarioNoExisteException, actividadNoExisteException{
		Set<String> res = new HashSet<String>();
		
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Proveedor proveedor = mu.getProveedor(nickname);
		if(proveedor == null)
			throw new usuarioNoExisteException("No se encontró un proveedor con el nickname ingresado.");
		Map<String,Actividad> actividades = proveedor.getActividades();
		//boolean encontro = false;
		Actividad act = actividades.get(nombreAct);
		if(act == null) {
			throw new actividadNoExisteException("No se encontró una actividad con el nombre ingresado." );
		}
		Map<String,Salida> salidas = act.getSalidas();
		res.addAll(salidas.keySet());
		
		return res.toArray(new String[res.size()]);
	}
	
	@Override
	public DTActividad obtenerDatoActividadProveedor(String nickname, String nombreAct) throws usuarioNoExisteException, actividadNoExisteException{
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Proveedor proveedor = mu.getProveedor(nickname);
		if(proveedor == null)
			throw new usuarioNoExisteException("Usuario no existe");
		DTActividad actividad = null;
		Map<String,Actividad> actividades = proveedor.getActividades();
		for(Map.Entry<String, Actividad> act : actividades.entrySet()) {
			if(act.getKey() == nombreAct) {
				actividad = act.getValue().getDatos();
					
			}
							
		}
		if(actividad == null)
			throw new actividadNoExisteException("Actividad no existe");
	
		return actividad;
	}
	
	@Override
	public DTSalida obtenerDatoSalidaProveedor(String nickname, String nombreAct, String nombreSal) throws usuarioNoExisteException, actividadNoExisteException{
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Proveedor proveedor = mu.getProveedor(nickname);
		if(proveedor == null)
			throw new usuarioNoExisteException("Usuario no existe");
		Actividad actividad = null;
		Map<String,Actividad> actividades = proveedor.getActividades();
		for(Map.Entry<String, Actividad> act : actividades.entrySet()) {
			if(act.getKey() == nombreAct) {
				actividad = act.getValue();
					
			}
							
		}		
		
		if(actividad == null)
			throw new usuarioNoExisteException("Usuario no existe");
		
		
		DTSalida salida = null;
		Map<String,Salida> salidas = actividad.getSalidas();
		for(Map.Entry<String, Salida> sal : salidas.entrySet()) {
			if(sal.getKey() == nombreSal) {
				salida = sal.getValue().getDatos();
					
			}
							
		}
		return salida;
		
	}

	@Override
	public DTSalida obtenerSalidaInscripto(String nombreSalida, String nickname) {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Turista tur = mu.getTurista(nickname);
		Vector<Inscripcion> inscripciones = tur.getInscripciones();
		DTSalida resu = null;
		for (Inscripcion inscripcion: inscripciones) {
			Salida sal = inscripcion.getSalida();
			if (sal != null) {
				if (sal.getNombre() == nombreSalida) {
					resu = new DTSalida(nombreSalida, sal.getMaxTuristas(), sal.getAlta(), sal.getFechaSalida(), sal.getHora(), sal.getLugarSalida());
					break;
				}
			}
		}
		return resu;

	}
	
	
	
}

