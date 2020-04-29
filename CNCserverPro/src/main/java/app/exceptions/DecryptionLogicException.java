package app.exceptions;

public class DecryptionLogicException extends RansomwareException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4275773158954966568L;

	public DecryptionLogicException() {
		super();
	}

	public DecryptionLogicException(String message) {
		super(message);
	}

	public DecryptionLogicException(String message, Throwable cause) {
		super(message, cause);
	}
}
