package Agent.EncryptionAlgo;

import java.io.File;
import javax.crypto.SecretKey;

import Agent.Utils.JavaCryptoUtil;
import Agent.exceptions.CryptoException;
import Agent.exceptions.InvalidCryptoKeyException;

public class AES implements CryptoAlgorithm {

	@Override
	public void encrypt(SecretKey skey, File fileToEncrypt, File outputFile) throws CryptoException {
		try {
			JavaCryptoUtil.encrypt(skey, fileToEncrypt, outputFile, "AES");
		} catch (InvalidCryptoKeyException e) {
			throw new CryptoException("Could not encrypt", e.getCause());
		}

	}

	@Override
	public void decrypt(SecretKey skey, File fileToDecrypt, File outputFile) throws CryptoException {
		try {
			JavaCryptoUtil.decrypt(skey, fileToDecrypt, outputFile, "AES");
		} catch (InvalidCryptoKeyException e) {
			throw new CryptoException("Could not decrypt", e.getCause());
		}
	}

}
