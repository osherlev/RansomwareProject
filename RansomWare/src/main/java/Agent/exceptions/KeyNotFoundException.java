package Agent.exceptions;


public class KeyNotFoundException extends CryptoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9059221305463967977L;

	public KeyNotFoundException() {
		super("key not found");
	}

	public KeyNotFoundException(String string, Throwable cause) {
		super(string, cause);
	}
}
