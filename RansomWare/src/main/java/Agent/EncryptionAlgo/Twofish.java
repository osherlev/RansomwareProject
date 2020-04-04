package Agent.EncryptionAlgo;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Twofish extends CryptoUtil {


	@Override
	public SecretKey getSecretKey(SecretKey skey) {

		return (new SecretKeySpec(skey.getEncoded(), "twofish"));

	}
}