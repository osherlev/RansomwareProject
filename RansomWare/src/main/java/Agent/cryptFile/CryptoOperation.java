package Agent.cryptFile;

import java.io.File;

import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.entites.CryptoKey;
import Agent.exceptions.CryptOperationException;

public interface CryptoOperation {

	public void operate(CryptoKey key, File file, CryptoAlgorithm crypto) throws CryptOperationException;

}
