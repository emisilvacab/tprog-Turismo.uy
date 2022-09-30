package excepciones;

@SuppressWarnings("serial")
public class compraExisteException extends Exception {
	
	public compraExisteException(String string) {
        super(string);
    }
}
