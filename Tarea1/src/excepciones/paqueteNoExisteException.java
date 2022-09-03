package excepciones;

@SuppressWarnings("serial")
public class paqueteNoExisteException extends Exception {
	
	public paqueteNoExisteException(String string) {
        super(string);
    }

}
