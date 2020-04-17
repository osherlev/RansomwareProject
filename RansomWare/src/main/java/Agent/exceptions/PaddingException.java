package Agent.exceptions;

public class PaddingException extends RansomwareException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -730486901110786410L;

	public PaddingException() {
		super();
	}

	public PaddingException(String msg) {
		super(msg);
	}

	public PaddingException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
