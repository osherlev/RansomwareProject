package Agent.exceptions;

public class RansomwareException extends Exception {

	private static final long serialVersionUID = 326273912718318229L;

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