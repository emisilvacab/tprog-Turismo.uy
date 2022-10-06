package excepciones;

@SuppressWarnings("serial")
public class categoriaYaExisteException extends Exception {
	public categoriaYaExisteException(String string) {
        super(string);
    }
}
