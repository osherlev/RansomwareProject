package com.server.app.bl;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.server.app.entities.CryptoKey;
import com.server.app.exceptions.AlgorithmNotFoundException;
import com.server.app.exceptions.InvalidCryptoKeyException;
import com.server.app.exceptions.ProcessFailedException;
import com.server.app.keys.KeyManagement;

@Service
public class EncryptionLogic {

	@Value("${algorithms.arr}")
	private String[] algorithmsArr;
	private KeyManagement km;

	public String randomAlgorithm() throws AlgorithmNotFoundException {

		SecureRandom rand = new SecureRandom();
		try {
			int random = rand.nextInt(algorithmsArr.length);
			return algorithmsArr[random];
		} catch (RuntimeException e) {
			throw new AlgorithmNotFoundException();
		}

	}

	public CryptoKey startProcess(String ip) throws ProcessFailedException, InvalidCryptoKeyException {
		km = new KeyManagement();
		try {
			String chosenAlgo = randomAlgorithm();
			return (km.createAndSaveKey(chosenAlgo, ip)); // Create and saves Key
		} catch (AlgorithmNotFoundException e1) {
			throw new InvalidCryptoKeyException("Algorithm not found", e1.getCause());
		} catch (InvalidCryptoKeyException e) {
			throw new ProcessFailedException("Creating the key went Wrong", e.getCause());
		}
	}

}
