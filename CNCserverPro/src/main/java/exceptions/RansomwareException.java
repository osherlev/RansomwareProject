package exceptions;

public class RansomwareException extends Exception {

	public RansomwareException() {
		super();
	}

	public RansomwareException(String message, Throwable cause) {
		super(message, cause);
	}

	public RansomwareException(String message) {
		super(message);
	}

	public RansomwareException(Throwable cause) {
		super(cause);
	}

}
