package Agent.exceptions;

public class CipherException extends RansomwareException {

	private static final long serialVersionUID = 7814732335026618575L;

	public CipherException()

	{
		super();
	}

	public CipherException(String msg)

	{
		super(msg);
	}

	public CipherException(String string, Throwable cause) {
		super(string, cause);
	}

	public CipherException(Throwable cause) {
		super(cause);
	}
}
