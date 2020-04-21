package Agent.cryptFile;

import java.io.File;

import Agent.EncryptionAlgo.ChangeFilesName;
import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.entites.CryptoKey;
import Agent.exceptions.AlgorithmNotFoundException;
import Agent.exceptions.CryptoException;
import Agent.exceptions.InOutException;
import Agent.exceptions.KeyNotFoundException;
import Agent.exceptions.PaddingException;

public class EncryptFile implements CryptoOperation {
	private ChangeFilesName change;

	@Override
	public void operate(CryptoKey key, File file, CryptoAlgorithm crypto)
			throws InOutException, AlgorithmNotFoundException, KeyNotFoundException, CryptoException, PaddingException {

		try {
			crypto.encrypt(key.getKey(), file, change.encryptedOutputFile(file));
		} catch (AlgorithmNotFoundException e) {
			throw new AlgorithmNotFoundException("algorithm class not found", e.getCause());
		} catch (PaddingException e) {
			throw new PaddingException("padding went wrong", e.getCause());
		} catch (KeyNotFoundException e) {
			throw new KeyNotFoundException("key not found", e.getCause());
		} catch (InOutException e) {
			throw new InOutException(e.getCause());
		} catch (CryptoException e) {
			throw new CryptoException("Problem encrypting file", e.getCause());

		}
	}
}