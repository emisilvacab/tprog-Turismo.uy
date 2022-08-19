package logica;

import java.util.GregorianCalendar;

public abstract class Usuario{
	
	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;
	private GregorianCalendar nacimiento;
	
	public Usuario(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.nacimiento = nacimiento;
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
}