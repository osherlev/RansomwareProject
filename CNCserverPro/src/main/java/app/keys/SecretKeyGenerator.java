package app.keys;

import javax.crypto.SecretKey;

import app.exceptions.AlgorithmNotFoundException;

public interface SecretKeyGenerator {
	public SecretKey generateKey(String chosenAlgorithm) throws AlgorithmNotFoundException;
}
