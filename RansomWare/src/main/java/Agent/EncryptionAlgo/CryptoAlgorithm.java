package Agent.EncryptionAlgo;

import java.io.File;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Repository;

import Agent.exceptions.AlgorithmNotFoundException;
import Agent.exceptions.CryptoException;
import Agent.exceptions.InOutException;
import Agent.exceptions.KeyNotFoundException;
import Agent.exceptions.PaddingException;

@Repository
public interface CryptoAlgorithm {

	public void encrypt(SecretKey skey, File fileToEncrypt,File outputFile)
			throws AlgorithmNotFoundException, PaddingException, KeyNotFoundException, CryptoException, InOutException;

	public void decrypt(SecretKey skey, File fileToDecrypt,File outputFile)
			throws AlgorithmNotFoundException, PaddingException, KeyNotFoundException, CryptoException, InOutException;
}
