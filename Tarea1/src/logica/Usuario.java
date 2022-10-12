package logica;

import java.awt.Image;
import java.util.GregorianCalendar;

public abstract class Usuario{
	
	private String nickname;
	private String nombre;
	private String apellido;
	private String contrasena;
	private String correo;
	private GregorianCalendar nacimiento;

	private Image figura;

	
	public Usuario(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento, String contrasena) {
		this.setNickname(nickname);
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setCorreo(correo);
		this.setNacimiento(nacimiento);
		this.setContrasena(contrasena);
		this.setFigura(null);
	}
	
	
	public String getNickname() {
		return nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public GregorianCalendar getNacimiento() {
		return nacimiento;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setNacimiento(GregorianCalendar nacimiento) {
		this.nacimiento = nacimiento;
	}

	public Image getFigura() {
		return figura;
	}

	public void setFigura(Image figura) {
		this.figura = figura;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
}