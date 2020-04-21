package Agent.exceptions;

public class JsonException extends CryptoException {

	private static final long serialVersionUID = 7312296222409363931L;

	public JsonException() {
		super();
	}

	public JsonException(String msg) {
		super(msg);
	}

	public JsonException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
