package excepciones;

@SuppressWarnings("serial")
public class departamentoNoExisteException extends Exception {
	
	public departamentoNoExisteException(String string) {
        super(string);
    }
}


