package excepciones;

@SuppressWarnings("serial")
public class proveedorNoExisteException extends Exception {
	
	public proveedorNoExisteException(String string) {
        super(string);
    }
}