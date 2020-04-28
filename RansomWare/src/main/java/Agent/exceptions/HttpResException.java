package Agent.exceptions;

public class HttpResException extends CryptoException {

	private static final long serialVersionUID = 5717072743017452209L;

	public HttpResException() {
		super();
	}

	public HttpResException(String msg) {
		super(msg);
	}

	public HttpResException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
