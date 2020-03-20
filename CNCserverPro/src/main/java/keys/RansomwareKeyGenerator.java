package keys;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;

public class RansomwareKeyGenerator<T> {
	public T keyMaker(String chosenAlgorithm) {

		// Creating a KeyGenerator object
		KeyGenerator keyGen = null;
		try {
			keyGen = KeyGenerator.getInstance(chosenAlgorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// Creating a SecureRandom object
		SecureRandom secRandom = new SecureRandom();

		// Initializing the KeyGenerator
		keyGen.init(secRandom);

		// Creating/Generating a key
		Key key = keyGen.generateKey();
		return (T) key.toString();

	}

}
