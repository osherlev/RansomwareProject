package Agent.cryptFile;

import java.io.File;

import Agent.EncryptionAlgo.ChangeFilesName;
import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.entites.CryptoKey;
import Agent.exceptions.CryptOperationException;
import Agent.exceptions.CryptoException;
import Agent.exceptions.InOutException;

public class EncryptFile implements CryptoOperation {
	private ChangeFilesName change;

	public EncryptFile() throws InOutException {
		change = new ChangeFilesName();
	}

	@Override
	public void operate(CryptoKey key, File file, CryptoAlgorithm crypto) throws CryptOperationException {

		try {
			crypto.encrypt(key.get_key(), file, change.encryptedOutputFile(file));
		} catch (CryptoException e) {
			throw new CryptOperationException("Failed encrypting the file", e);
		}

	}
}