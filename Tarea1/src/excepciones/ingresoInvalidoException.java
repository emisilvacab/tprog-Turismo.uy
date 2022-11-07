package excepciones;

@SuppressWarnings("serial")
public class ingresoInvalidoException extends Exception {
	
	public ingresoInvalidoException(String string) {
        super(string);
    }
}
