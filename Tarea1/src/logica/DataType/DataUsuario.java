package logica.DataType;

import java.util.GregorianCalendar;

public class DataUsuario {
	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;
	private GregorianCalendar nacimiento;
	
	public DataUsuario() {
        this.setNombre(new String());
        this.setApellido(new String());
        this.setNickname(new String());
        this.setCorreo(new String());
        this.setNacimiento(new GregorianCalendar());
    }
	
	public DataUsuario(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento) {
		this.setNickname(nickname);
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setCorreo(correo);
		this.setNacimiento(nacimiento);
	}

	public GregorianCalendar getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(GregorianCalendar nacimiento) {
		this.nacimiento = nacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	

}
