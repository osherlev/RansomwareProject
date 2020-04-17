package Agent.cryptFile;

import java.io.File;

import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.entites.CryptoKey;
import Agent.exceptions.AlgorithmNotFoundException;
import Agent.exceptions.CryptoException;
import Agent.exceptions.InOutException;
import Agent.exceptions.KeyNotFoundException;
import Agent.exceptions.PaddingException;

public interface CryptoOperation {

	public void operate(CryptoKey key, File file, CryptoAlgorithm crypto)
			throws AlgorithmNotFoundException, PaddingException, KeyNotFoundException, CryptoException, InOutException;

}
