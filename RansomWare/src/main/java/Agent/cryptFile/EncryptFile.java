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
			throw new AlgorithmNotFoundException();
		} catch (PaddingException e) {
			throw new PaddingException();
		} catch (KeyNotFoundException e) {
			throw new KeyNotFoundException();
		} catch (CryptoException e) {
			throw new CryptoException("problem encrypting file");
		} catch (InOutException e) {
			throw new InOutException();
		}

	}
}