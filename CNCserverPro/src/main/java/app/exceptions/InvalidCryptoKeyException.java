package app.exceptions;

public class InvalidCryptoKeyException extends RansomwareException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7639760619196328463L;

	public InvalidCryptoKeyException() {
		super();
	}

	public InvalidCryptoKeyException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidCryptoKeyException(String message) {
		super(message);
	}



}
