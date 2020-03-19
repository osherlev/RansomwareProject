package keys;

import javax.crypto.SecretKey;
import javax.inject.Inject;

import repositories.KeyRepository;
import entities.KeyEntity;

public class KeyManagement<T> {
	@Inject
	private KeyRepository repository;

	@SuppressWarnings("unchecked")
	public T createKey(String Encalgo, String ip) {
		CreateKeys clientKey = new CreateKeys();
		SecretKey key = clientKey.keyMaker(Encalgo);
		save((KeyEntity<T>) new KeyEntity<T>(ip, (T) clientKey, Encalgo)); // Save to DB
		return (T) key;
	}

	public void save(KeyEntity<T> entity) {
		repository.save(entity);

	}

}
