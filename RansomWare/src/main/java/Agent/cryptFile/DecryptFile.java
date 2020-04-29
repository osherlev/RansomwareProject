package Agent.cryptFile;

import java.io.File;

import Agent.EncryptionAlgo.ChangeFilesName;
import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.entites.CryptoKey;
import Agent.exceptions.CryptOperationException;
import Agent.exceptions.CryptoException;

public class DecryptFile implements CryptoOperation {
	private ChangeFilesName change;

	public DecryptFile() {
		change = new ChangeFilesName();
	}

	@Override
	public void operate(CryptoKey key, File file, CryptoAlgorithm crypto) throws CryptOperationException {
		try {

			crypto.decrypt(key.get_key(), file, change.decryptedOutputFile(file));
		} catch (CryptoException e) {
			throw new CryptOperationException("Failed encrypting the file", e);
		}

	}
}
