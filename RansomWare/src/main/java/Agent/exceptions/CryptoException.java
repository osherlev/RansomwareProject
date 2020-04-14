package Agent.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
public class CryptoException extends RansomwareException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9L;

	public CryptoException()

	{
		super("Error encrypting/decrypting file");
	}
	public CryptoException(String msg)

	{
		super(msg);
	}
	public CryptoException(String string, Throwable cause) {
		super(string,cause);
	}
}
