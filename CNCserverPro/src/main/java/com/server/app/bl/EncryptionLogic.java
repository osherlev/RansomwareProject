package com.server.app.bl;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.server.app.entities.CryptoKey;
import com.server.app.exceptions.AlgorithmNotFoundException;
import com.server.app.keys.KeyManagement;

@Service
public class EncryptionLogic {

	@Value("${algorithms.arr}")
	private String[] algorithmsArr;

	public String randomAlgorithm() throws AlgorithmNotFoundException {

		SecureRandom rand = new SecureRandom();
		try {
			int random = rand.nextInt(algorithmsArr.length);
			return algorithmsArr[random];
		} catch (RuntimeException e) {
			throw new AlgorithmNotFoundException();
		}

	}

	public CryptoKey startProcess(String ip) throws AlgorithmNotFoundException, NoSuchAlgorithmException {
		String chosenAlgo = randomAlgorithm();
		KeyManagement km = new KeyManagement();
		return km.createAndSaveKey(chosenAlgo, ip); // Generates key and saves new entity in DB
	}

}
