package Agent.EncryptionAlgo;

import java.io.File;
import javax.crypto.SecretKey;

import server.exceptions.CryptoException;

public interface EncryptionCodec {
	public abstract void decrypt(SecretKey skey, File fileToDecrypt) throws CryptoException;

	public abstract void encrypt(SecretKey skey, File fileToEncrypt) throws CryptoException;

}
