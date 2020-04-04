package Agent.EncryptionAlgo;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class TripleDES extends CryptoUtil {


	@Override
	public SecretKey getSecretKey(SecretKey skey) {

		return (new SecretKeySpec(skey.getEncoded(), "deses"));

	}

}