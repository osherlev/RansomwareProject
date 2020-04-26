package Agent.cryptFile;

import java.io.File;

import javax.inject.Inject;

import Agent.EncryptionAlgo.ChangeFilesName;
import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.entites.CryptoKey;
import Agent.exceptions.AlgorithmNotFoundException;
import Agent.exceptions.CryptoException;
import Agent.exceptions.InOutException;
import Agent.exceptions.KeyNotFoundException;
import Agent.exceptions.PaddingException;

public class EncryptFile implements CryptoOperation {

	@Inject
	private ChangeFilesName change;
	@Override
	public void operate(CryptoKey key, File file, CryptoAlgorithm crypto) throws CryptoException {
		try {
			crypto.encrypt(key.get_key(), file, change.encryptedOutputFile(file));
		} catch (AlgorithmNotFoundException | PaddingException | KeyNotFoundException | InOutException e) {
			throw new CryptoException("Problem encrypting file", e.getCause());
		}
	}
}