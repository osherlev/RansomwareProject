package Agent.EncryptionAlgo;

import java.io.File;
import javax.crypto.SecretKey;

import Agent.Utils.JavaCryptoUtil;
import Agent.exceptions.CryptoException;
import Agent.exceptions.InvalidCryptoKeyException;

public class DESede implements CryptoAlgorithm {

	@Override
	public void encrypt(SecretKey skey, File fileToEncrypt, File outputFile) throws CryptoException {
		try {
			/// CBC/PKCS5Padding
			JavaCryptoUtil.encrypt(skey, fileToEncrypt, outputFile, "DESede");
		} catch (InvalidCryptoKeyException e) {
			throw new CryptoException("Could not encrypt", e.getCause());
		}

	}

	@Override
	public void decrypt(SecretKey skey, File fileToDecrypt, File outputFile) throws CryptoException {
		try {
			JavaCryptoUtil.decrypt(skey, fileToDecrypt, outputFile, "DESede");
		} catch (InvalidCryptoKeyException e) {
			throw new CryptoException("Could not decrypt", e.getCause());
		}
	}

}
