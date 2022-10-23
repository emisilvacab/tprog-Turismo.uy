package logica.controladores;


import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;



import excepciones.UsuarioRepetidoException;
import excepciones.actividadNoExisteException;
import excepciones.inscripcionExisteException;
import excepciones.paqueteNoExisteException;
import excepciones.salidaNoExisteException;
import excepciones.usuarioNoExisteException;
import excepciones.limiteSuperadoException;
import logica.Inscripcion;
import logica.Proveedor;
import logica.Salida;
import logica.Turista;
import logica.Usuario;
import logica.datatypes.DTActividad;
import logica.datatypes.DTCompra;
import logica.datatypes.DTInscripcion;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTSalida;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;
import logica.manejadores.ManejadorSalida;
import logica.manejadores.ManejadorUsuario;
import logica.Actividad;
import logica.Compra;

public class ControladorUsuario implements IControladorUsuario {

    public ControladorUsuario() {
    }
    
    public String[] obtenerUsuarios(){
    	ManejadorUsuario mUsr = ManejadorUsuario.getInstance();
    	Map<String, Proveedor> proveedores = mUsr.getProveedores();
    	Map<String, Turista> turistas = mUsr.getTuristas();
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
		ManejadorUsuario mUsr = ManejadorUsuario.getInstance();
		Usuario user = mUsr.getTurista(nickname);
		if (user == null) {
			user = mUsr.getProveedor(nickname);
		}
		if (user == null) {
			throw new usuarioNoExisteException("no se encontro ningun usuario con el nickname ingresado");
		}else {
			if (user.getClass() == Turista.class) {
				Turista tur = (Turista) user;
				return new DTTurista(user.getNickname(), user.getNombre(), user.getApellido(), user.getCorreo(), user.getNacimiento(), user.getContrasena(), user.getLinkImagen(), tur.getNacionalidad());
			}else {
				Proveedor prov = (Proveedor) user;
				return new DTProveedor(user.getNickname(), user.getNombre(), user.getApellido(), user.getCorreo(), user.getNacimiento(), user.getContrasena(), user.getLinkImagen(), prov.getDescripcion(), prov.getLink());
			}
	
		}
		
	}

	@Override
	public String[] obtenerSalidasInscripto(String nickname) throws usuarioNoExisteException {
		ManejadorUsuario mUsr = ManejadorUsuario.getInstance();
		Turista tur = mUsr.getTurista(nickname);
		if (tur == null) throw new usuarioNoExisteException("El turista no fue encontrado");
		HashSet<Inscripcion> inscripciones = (HashSet<Inscripcion>) tur.getInscripciones();
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
		ManejadorUsuario mUsr = ManejadorUsuario.getInstance();
		Proveedor prov = mUsr.getProveedor(nickname);
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
		ManejadorUsuario mUsr = ManejadorUsuario.getInstance();
		if (mUsr.getProveedor(user.getNickname())!=null || mUsr.getTurista(user.getNickname())!=null || existeUsuarioConCorreo(user.getCorreo())){
			throw new UsuarioRepetidoException("Ya existe un usuario con el nickname o correo ingresado");
		}else {
			if (user.getClass() == DTTurista.class) {
				DTTurista dttur = (DTTurista) user;
				mUsr.addTurista(new Turista(user.getNickname(), user.getNombre(), user.getApellido(), user.getCorreo(), user.getNacimiento(), user.getContrasena(), user.getLinkImagen(), dttur.getNacionalidad()));
			}else {
				DTProveedor dtprov = (DTProveedor) user;
				mUsr.addProveedor(new Proveedor(user.getNickname(), user.getNombre(), user.getApellido(), user.getCorreo(), user.getNacimiento(), user.getContrasena(), user.getLinkImagen(), dtprov.getDescripcion(), dtprov.getLink()));
				
			}
		}
		
	}

	
	private boolean existeUsuarioConCorreo(String correo) {
		ManejadorUsuario mUsr = ManejadorUsuario.getInstance();
		boolean resu = false;
		for (Turista tur: mUsr.getTuristas().values()) {
			if (tur.getCorreo().equals(correo)) {
				resu = true;
			}
		}
		for (Proveedor prov: mUsr.getProveedores().values()) {
			if (prov.getCorreo().equals(correo)) {
				resu = true;
			}
		}
		return resu;
	}

	public String ingresarDatosInscripcion(String nickname, String  nombre, int capacidad, GregorianCalendar fechaAlta) throws excepciones.salidaNoExisteException, usuarioNoExisteException{
		
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
			Inscripcion insc = new Inscripcion(fechaAlta, capacidad, salida, turista, null);
			salida.addInscripcion(insc);
			turista.addInscripcion(insc);
			return "no";
		}
		else {
			if (!hayLugar)
				return "lleno";
			else 
				return "existe";
		}
	}
	
	public String[] obtenerProveedores() {
		ManejadorUsuario mUsr = ManejadorUsuario.getInstance();
    	Map<String, Proveedor> proveedores = mUsr.getProveedores();
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
		
		ManejadorUsuario mUsr = ManejadorUsuario.getInstance();
		Proveedor proveedor = mUsr.getProveedor(nickname);
		if (proveedor == null)
			throw new usuarioNoExisteException("No se encontró un proveedor con el nickname ingresado.");
		Map<String, Actividad> actividades = proveedor.getActividades();
		Actividad act = actividades.get(nombreAct);
		if (act == null) {
			throw new actividadNoExisteException("No se encontró una actividad con el nombre ingresado." );
		}
		Map<String, Salida> salidas = act.getSalidas();
		res.addAll(salidas.keySet());
		
		return res.toArray(new String[res.size()]);
	}
	
	@Override
	public DTActividad obtenerDatoActividadProveedor(String nickname, String nombreAct) throws usuarioNoExisteException, actividadNoExisteException{
		ManejadorUsuario mUsr = ManejadorUsuario.getInstance();
		Proveedor proveedor = mUsr.getProveedor(nickname);
		if (proveedor == null)
			throw new usuarioNoExisteException("Usuario no existe");
		DTActividad actividad = null;
		Map<String, Actividad> actividades = proveedor.getActividades();
		for (Map.Entry<String, Actividad> act : actividades.entrySet()) {
			if (act.getKey() == nombreAct) {
				actividad = act.getValue().getDatos();
					
			}
							
		}
		if (actividad == null)
			throw new actividadNoExisteException("Actividad no existe");
	
		return actividad;
	}
	
	@Override
	public DTSalida obtenerDatoSalidaProveedor(String nickname, String nombreAct, String nombreSal) throws usuarioNoExisteException, actividadNoExisteException{
		ManejadorUsuario mUsr = ManejadorUsuario.getInstance();
		Proveedor proveedor = mUsr.getProveedor(nickname);
		if (proveedor == null)
			throw new usuarioNoExisteException("Usuario no existe");
		Actividad actividad = null;
		Map<String, Actividad> actividades = proveedor.getActividades();
		for (Map.Entry<String, Actividad> act : actividades.entrySet()) {
			if (act.getKey() == nombreAct) {
				actividad = act.getValue();
					
			}
							
		}		
		
		if (actividad == null)
			throw new usuarioNoExisteException("Usuario no existe");
		
		
		DTSalida salida = null;
		Map<String, Salida> salidas = actividad.getSalidas();
		for (Map.Entry<String, Salida> sal : salidas.entrySet()) {
			if (sal.getKey() == nombreSal) {
				salida = sal.getValue().getDatos();
					
			}
							
		}
		return salida;
		
	}

	@Override
	public DTSalida obtenerSalidaInscripto(String nombreSalida, String nickname) {
		ManejadorUsuario mUsr = ManejadorUsuario.getInstance();
		Turista tur = mUsr.getTurista(nickname);
		HashSet<Inscripcion> inscripciones = (HashSet<Inscripcion>) tur.getInscripciones();
		DTSalida resu = null;
		for (Inscripcion inscripcion: inscripciones) {
			Salida sal = inscripcion.getSalida();
			if (sal != null) {
				if (sal.getNombre() == nombreSalida) {
					resu = sal.getDatos();
					break;
				}
			}
		}
		return resu;
	}
	
	public void ingresarDatosInscripcionPaq(String nickname, String nombreSal, int cantidad, GregorianCalendar fecha, String nombrePaq) throws salidaNoExisteException, usuarioNoExisteException, paqueteNoExisteException, inscripcionExisteException, limiteSuperadoException {
		ManejadorSalida mSalida = ManejadorSalida.getInstance();
		ManejadorUsuario mUsuario = ManejadorUsuario.getInstance();
		
		Salida salida = mSalida.getSalida(nombreSal);
		if (salida == null)
			throw new salidaNoExisteException("Salida no encontrada.");
		Turista turista = mUsuario.getTurista(nickname);
		if (turista == null) 
			throw new usuarioNoExisteException("Usuario no encontrado.");
		
		Compra compra = null;
		if (nombrePaq != null) {
			HashSet<Compra> compras = (HashSet<Compra>) turista.getCompras();
			for (Compra c : compras) {
				if (c.getPaquete().getNombre().equals(nombrePaq)) {
					compra = c;
					break;
				}
			}
		}
		
		boolean hayLugar = salida.admiteCapacidad(cantidad);
		boolean existe = salida.existeInscripcion(turista.getNickname());
		if (hayLugar && !existe) {
			Inscripcion insc = new Inscripcion(fecha, cantidad, salida, turista, compra);
			salida.addInscripcion(insc);
			turista.addInscripcion(insc);
			if (compra != null) {
				compra.descontarCupos(salida, cantidad);
			}
		}
		else {
			if (!hayLugar)
				throw new limiteSuperadoException("Capacidad de inscripciones a salida superada");
			else 
				throw new inscripcionExisteException("Inscripcion ya existente");
		}
	}
	
	public DTUsuario iniciarSesion(String ident, String pass) {
		
		String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	    Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
	    Matcher matcher = EMAIL_PATTERN.matcher(ident);
	    matcher.matches();
	    
		ManejadorUsuario mUsr = ManejadorUsuario.getInstance();
			
		if (!matcher.matches()) {
			Turista turista = mUsr.getTurista(ident);
			Proveedor proveedor = mUsr.getProveedor(ident);
			if (turista != null && turista.getContrasena().equals(pass)) 
				return turista.getDatos();
			else {
				if (proveedor != null && proveedor.getContrasena().equals(pass)) 
					return proveedor.getDatos();
				else 
					return null;
			}
		}
		else {
			Turista turista = mUsr.getTuristaPorEmail(ident);
			Proveedor proveedor = mUsr.getProveedorPorEmail(ident);
			if (turista != null && turista.getContrasena().equals(pass)) 
				return turista.getDatos();
			else {
				if (proveedor != null && proveedor.getContrasena().equals(pass)) 
					return proveedor.getDatos();
				else 
					return null;
			}
		}
	}
	
	public Set<DTCompra> obtenerComprasTurista(String nickname) throws usuarioNoExisteException{
		ManejadorUsuario mUsr = ManejadorUsuario.getInstance();
		Turista user = mUsr.getTurista(nickname);
		
		if (user == null) {
			throw new usuarioNoExisteException("no se encontro ningun turista con el nickname ingresado");
		}
		else {
			HashSet<DTCompra> comprasDT = new HashSet<DTCompra>();
			HashSet<Compra> compras = (HashSet<Compra>) user.getCompras();
			
			for (Compra comp : compras) {
				String nomPaquete = comp.getPaquete().getNombre();
				DTCompra nueva = new DTCompra(comp.getFecha(), comp.getCantTuristas(), comp.getVencimiento(), comp.getCosto(), nomPaquete, nickname);
				comprasDT.add(nueva);
			}
				
			return comprasDT;
	
		}

	}
	
	public Set<DTInscripcion> obtenerInscripcionesTurista(String nickname) throws usuarioNoExisteException{
		ManejadorUsuario mUsr = ManejadorUsuario.getInstance();
		Turista user = mUsr.getTurista(nickname);
		
		if (user == null) {
			throw new usuarioNoExisteException("no se encontro ningun turista con el nickname ingresado");			
		}else {
			HashSet<DTInscripcion> inscripcionesDT = new HashSet<DTInscripcion>();
			HashSet<Inscripcion> inscripciones = (HashSet<Inscripcion>) user.getInscripciones();
			
			for (Inscripcion ins : inscripciones) {
				DTInscripcion nueva = new DTInscripcion(ins.getFecha(), ins.getCantTuristas(), ins.getSalida().getNombre(), ins.getTurista().getNickname(), ins.getCosto());
				inscripcionesDT.add(nueva);
			}
			
			return inscripcionesDT;
		}

	}
	
}



















