package excepciones;

@SuppressWarnings("serial")
public class salidaNoExisteException extends Exception {
	
	public salidaNoExisteException(String string) {
		super(string);
	}
	
}
