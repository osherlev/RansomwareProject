package Agent.cryptFile;

import java.io.File;

import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.entites.CryptoKey;
import Agent.exceptions.CryptoException;

public interface CryptoOperation {

	public void operate(CryptoKey key, File file, CryptoAlgorithm crypto) throws CryptoException;

}
