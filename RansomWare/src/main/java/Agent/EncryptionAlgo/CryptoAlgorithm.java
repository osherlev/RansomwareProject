package Agent.EncryptionAlgo;

import java.io.File;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Repository;

import Agent.exceptions.RansomwareException;

@Repository
public interface CryptoAlgorithm {
	public static final ChangeFilesName CHANGENAME = new ChangeFilesName();
	public void encrypt(SecretKey skey, File fileToEncrypt) throws RansomwareException;

	public void decrypt(SecretKey skey, File fileToDecrypt) throws RansomwareException;
}
