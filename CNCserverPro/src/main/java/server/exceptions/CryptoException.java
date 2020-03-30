package server.exceptions;

public class CryptoException extends RansomwareException {
	public CryptoException()

	{
		super("Error encrypting/decrypting file");
	}
}
