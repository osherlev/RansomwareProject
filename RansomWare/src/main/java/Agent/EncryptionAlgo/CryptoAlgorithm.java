package Agent.EncryptionAlgo;

import java.io.File;
import javax.crypto.SecretKey;

import Agent.exceptions.CryptoException;

public interface CryptoAlgorithm {

	public void encrypt(SecretKey skey, File fileToEncrypt, File outputFile) throws CryptoException;

	public void decrypt(SecretKey skey, File fileToDecrypt, File outputFile) throws CryptoException;
}
