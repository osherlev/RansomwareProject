package Agent.EncryptionAlgo;

import java.io.File;
import javax.crypto.SecretKey;

import Agent.Utils.JavaCryptoUtil;
import Agent.exceptions.CryptoException;
import Agent.exceptions.InvalidCryptoKeyException;

public class Blowfish implements CryptoAlgorithm {

	@Override
	public void encrypt(SecretKey skey, File fileToEncrypt, File outputFile) throws CryptoException {
		try {
			/// ECB/PKCS5Padding
			JavaCryptoUtil.encrypt(skey, fileToEncrypt, outputFile, "blowfish");
		} catch (InvalidCryptoKeyException e) {
			throw new CryptoException("Could not encrypt", e);
		}

	}

	@Override
	public void decrypt(SecretKey skey, File fileToDecrypt, File outputFile) throws CryptoException {
		try {
			JavaCryptoUtil.decrypt(skey, fileToDecrypt, outputFile, "blowfish");
		} catch (InvalidCryptoKeyException e) {
			throw new CryptoException("Could not decrypt", e);
		}
	}

}
