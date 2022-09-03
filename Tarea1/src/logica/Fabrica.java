package logica;

import logica.controladores.ControladorDepartamento;
import logica.controladores.ControladorPaquete;
import logica.controladores.ControladorUsuario;
import logica.controladores.IControladorDepartamento;
import logica.controladores.IControladorPaquete;
import logica.controladores.IControladorUsuario;

public class Fabrica {
	private static Fabrica instancia;

    private Fabrica() {
    };

    public static Fabrica getInstance() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    public IControladorUsuario getIControladorUsuario() {
        return new ControladorUsuario();
    }

    public IControladorDepartamento getIControladorDepartamento() {
    	return new ControladorDepartamento();
    }
    
    public IControladorPaquete getIControladorPaquete() {
        return new ControladorPaquete();
    }

}
