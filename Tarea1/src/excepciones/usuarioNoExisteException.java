package excepciones;

@SuppressWarnings("serial")
public class usuarioNoExisteException extends Exception{
	public usuarioNoExisteException(String string) {
        super(string);
    }

}
