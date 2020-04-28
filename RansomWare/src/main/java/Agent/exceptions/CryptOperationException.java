package Agent.exceptions;

public class CryptOperationException extends CryptoException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7639760619196328463L;

	public CryptOperationException() {
		super();
	}

	public CryptOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CryptOperationException(String message) {
		super(message);
	}

	public CryptOperationException(Throwable cause) {
		super(cause);
	}
}
