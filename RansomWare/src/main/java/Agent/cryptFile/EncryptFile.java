package Agent.cryptFile;

import java.io.File;

import Agent.EncryptionAlgo.ChangeFilesName;
import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.entites.CryptoKey;
import Agent.exceptions.CryptOperationException;
import Agent.exceptions.CryptoException;

public class EncryptFile implements CryptoOperation {

	@Override
	public void operate(CryptoKey key, File file, CryptoAlgorithm crypto) throws CryptOperationException {

		try {
			crypto.encrypt(key.get_key(), file, ChangeFilesName.encryptedOutputFile(file));
		} catch (CryptoException e) {
			throw new CryptOperationException("Failed encrypting the file", e.getCause());
		}

	}
}