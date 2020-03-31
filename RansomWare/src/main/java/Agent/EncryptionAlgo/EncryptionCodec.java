package Agent.EncryptionAlgo;

import java.io.File;
import javax.crypto.SecretKey;

public interface EncryptionCodec {
	public abstract void decrypt(SecretKey skey, File fileToDecrypt);

	public abstract void encrypt(SecretKey skey, File fileToEncrypt);

}
