package app.keys;

import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;

import app.exceptions.AlgorithmNotFoundException;
import app.exceptions.InvalidCryptoKeyException;

public class AsymetricKeyWrapper {
	@Value("${asymetric.algorithm}")
	private String asmAlgo;
	@Value("${asymetric.algorithm.size}")
	private int size;

	public KeyPair createKeyPair(SecretKey key) throws AlgorithmNotFoundException {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(asmAlgo);
			keyPairGenerator.initialize(size);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			return keyPair;
		} catch (NoSuchAlgorithmException | InvalidParameterException e) {
			throw new AlgorithmNotFoundException();
		}

	}

	public SecretKey wrapKey(SecretKey symKey, String algo)
			throws InvalidCryptoKeyException, AlgorithmNotFoundException {
		try {
			PublicKey pubKey = createKeyPair(symKey).getPublic();
			final Cipher cipher = Cipher.getInstance(asmAlgo);
			cipher.init(Cipher.WRAP_MODE, pubKey);
			final byte[] wrapped = cipher.wrap(symKey);
			SecretKey wrappedKey = new SecretKeySpec(wrapped, algo);
			return wrappedKey;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException e) {
			throw new InvalidCryptoKeyException("Java runtime does not support RSA");
		}
	}

}
