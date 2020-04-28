package Agent.cryptFile;

import java.io.File;

import Agent.EncryptionAlgo.ChangeFilesName;
import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.entites.CryptoKey;
import Agent.exceptions.CryptOperationException;
import Agent.exceptions.CryptoException;

public class DecryptFile implements CryptoOperation {

	@Override
	public void operate(CryptoKey key, File file, CryptoAlgorithm crypto) throws CryptOperationException {
		try {

			crypto.decrypt(key.get_key(), file, ChangeFilesName.decryptedOutputFile(file));
		} catch (CryptoException e) {
			throw new CryptOperationException("Failed encrypting the file", e.getCause());
		}

	}
}
