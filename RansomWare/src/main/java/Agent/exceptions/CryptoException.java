package Agent.exceptions;


public class CryptoException extends RansomwareException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CryptoException()

	{
		super("Error encrypting/decrypting file");
	}
	public CryptoException(String msg)

	{
		super(msg);
	}
}
