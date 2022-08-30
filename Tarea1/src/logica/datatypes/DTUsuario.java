package logica.datatypes;

import java.util.GregorianCalendar;

public class DTUsuario{
	
	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;
	private GregorianCalendar nacimiento;
	
	public DTUsuario() {
		this.setNickname(new String());
		this.setNombre(new String());
		this.setApellido(new String());
		this.setCorreo(new String());
		this.setNacimiento(new GregorianCalendar());
	}
	
	public DTUsuario(String nickname, String nombre, String apellido, String correo, GregorianCalendar nacimiento) {
		this.setNickname(nickname);
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setCorreo(correo);
		this.setNacimiento(nacimiento);
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
	
    /* Sirve para mostrar textualmente la información del usuario, por ejemplo en un ComboBox
     
    public String toString() {
        return getCedulaIdentidad() + " (" + getNombre() + " " + getApellido() + ")";
    }
	*/
	
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