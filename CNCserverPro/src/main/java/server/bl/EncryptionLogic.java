package server.bl;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Value;

import server.entities.CryptoKey;
import server.exceptions.AlgorithmNotFoundException;
import server.keys.KeyManagement;

public class EncryptionLogic {
	@Value("${algorithms.arr}")
	private String[] algorithmsArr;

	public String randomAlgorithm() throws AlgorithmNotFoundException {

		SecureRandom rand = new SecureRandom();
		try {
			int random = rand.nextInt(algorithmsArr.length);
			return algorithmsArr[random];
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		throw new AlgorithmNotFoundException();
	}

	public <T> CryptoKey<T> startProcess(String ip) throws AlgorithmNotFoundException, NoSuchAlgorithmException {
		String chosenAlgo = randomAlgorithm();
		KeyManagement km = new KeyManagement();
		return km.createAndSaveKey(chosenAlgo, ip); // Generates key and saves new entity in DB
	}
}
