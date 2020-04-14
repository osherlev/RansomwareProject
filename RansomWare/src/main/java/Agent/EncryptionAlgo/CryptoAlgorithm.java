package Agent.EncryptionAlgo;

import java.io.File;
import java.security.GeneralSecurityException;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Repository;

import Agent.exceptions.RansomwareException;

@Repository
public interface CryptoAlgorithm {

	public void encrypt(SecretKey skey, File fileToEncrypt) throws RansomwareException;

	public void decrypt(SecretKey skey, File fileToDecrypt) throws RansomwareException;
}
