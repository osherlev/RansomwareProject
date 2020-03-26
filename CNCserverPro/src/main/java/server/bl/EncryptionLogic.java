package server.bl;

import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Value;

import server.keys.KeyManagement;

public class EncryptionLogic {
	@Value("${algorithms.arr}")
	private String[] algorithmsArr;

	public String randomAlgorithm() {

		SecureRandom rand = new SecureRandom();
		try {
			int random = rand.nextInt(algorithmsArr.length);
			return algorithmsArr[random];
		} catch (NullPointerException e) {

		}
		return "AES";
	}

	public <T> void startProcess(String ip) {
		String chosenAlgo = randomAlgorithm();
		KeyManagement km = new KeyManagement();
		T key = (T) km.createKey(chosenAlgo, ip); // Generates key and saves new entity in DB
	}
}
