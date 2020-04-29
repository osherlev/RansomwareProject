package app.exceptions;

public class EncryptionLogicException extends RansomwareException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8201495883509712294L;

	public EncryptionLogicException() {
		super();
	}

	public EncryptionLogicException(String message) {
		super(message);
	}

	public EncryptionLogicException(String message, Throwable cause) {
		super(message, cause);
	}
}
