package Agent.exceptions;

public class CryptoException extends RansomwareException {

	private static final long serialVersionUID = 7814732335026618575L;

	public CryptoException()

	{
		super("Error encrypting/decrypting file");
	}

	public CryptoException(String msg)

	{
		super(msg);
	}

	public CryptoException(String string, Throwable cause) {
		super(string, cause);
	}

	public CryptoException(Throwable cause) {
		super(cause);
	}
}
