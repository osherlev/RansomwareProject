package Agent.EncryptionAlgo;

import java.io.File;
import javax.crypto.SecretKey;

import server.exceptions.RansomwareException;

public interface EncryptionCodec {
	public abstract void decrypt(SecretKey skey, File fileToDecrypt) throws RansomwareException;

	public abstract void encrypt(SecretKey skey, File fileToEncrypt) throws RansomwareException;

}
