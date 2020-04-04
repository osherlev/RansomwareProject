package Agent.EncryptionAlgo;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Blowfish extends CryptoUtil {

	@Override
	public SecretKey getSecretKey(SecretKey skey) {

		return (new SecretKeySpec(skey.getEncoded(), "Blowfish"));

	}
}
