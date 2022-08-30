package logica;

import logica.controladores.ControladorDepartamento;
import logica.controladores.ControladorUsuario;
import logica.controladores.IControladorDepartamento;
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

}
