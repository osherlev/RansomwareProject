package app.keys;

import javax.inject.Inject;

import app.entities.CryptoKey;
import app.exceptions.AlgorithmNotFoundException;
import app.exceptions.InvalidCryptoKeyException;

public class KeyManagement implements Management {
	@Inject
	private RansomwareKeyGenerator generator;

	@Override
	public CryptoKey createAndSaveKey(String encalgo, String ip) throws InvalidCryptoKeyException {
		try {
			return new CryptoKey(ip, generator.generateKey(encalgo), encalgo);
		} catch (AlgorithmNotFoundException e) {
			throw new InvalidCryptoKeyException("Key requierments- algoritm not found", e);
		}

	}
}
