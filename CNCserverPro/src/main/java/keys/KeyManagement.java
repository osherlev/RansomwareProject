package keys;

import javax.crypto.SecretKey;
import repositories.KeyRepository;
import entities.KeyEntity;

public class KeyManagement<T> {

	private KeyRepository repository;

	public void save() {

		CreateKeys clientKey = new CreateKeys();
		SecretKey k = clientKey.createKey();
		@SuppressWarnings("unchecked")
		KeyEntity<T> entity = new KeyEntity<T>(clientKey.getClientIp(), (T) k, k.getAlgorithm());
		repository.save(entity);

	}

}
