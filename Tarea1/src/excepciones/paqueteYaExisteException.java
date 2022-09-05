package excepciones;


@SuppressWarnings("serial")
public class paqueteYaExisteException extends Exception {
	
	public paqueteYaExisteException(String string) {
        super(string);
    }

}