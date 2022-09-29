package excepciones;

@SuppressWarnings("serial")
public class categoriaNoExisteException extends Exception {
	public categoriaNoExisteException(String string) {
        super(string);
    }
}
