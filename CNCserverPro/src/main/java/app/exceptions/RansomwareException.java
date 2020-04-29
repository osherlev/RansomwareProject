package app.exceptions;

public class RansomwareException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8197720679321391984L;

	public RansomwareException() {
		super();
	}

	public RansomwareException(String message) {
		super(message);
	}

	public RansomwareException(String message, Throwable cause) {
	super(message,cause);
	}


}
