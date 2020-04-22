package Agent.EncryptionAlgo;

import java.io.File;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Repository;

import Agent.exceptions.CryptoException;

@Repository
public interface CryptoAlgorithm {

	public void encrypt(SecretKey skey, File fileToEncrypt, File outputFile) throws CryptoException;

	public void decrypt(SecretKey skey, File fileToDecrypt, File outputFile) throws CryptoException;
}
