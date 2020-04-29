package app.bl;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import app.entities.CryptoKey;
import app.exceptions.AlgorithmNotFoundException;
import app.exceptions.EncryptionLogicException;
import app.exceptions.InvalidCryptoKeyException;
import app.keys.KeyManagement;

@Component
public class EncryptionLogic {

	@Value("${algorithms.arr}")
	private String[] algorithmsArr;
	private KeyManagement km;

	public EncryptionLogic() {
		km = new KeyManagement();
	}

	public String randomAlgorithm() throws AlgorithmNotFoundException {

		SecureRandom rand = new SecureRandom();
		try {
			int random = rand.nextInt(algorithmsArr.length);
			return algorithmsArr[random];
		} catch (RuntimeException e) {
			throw new AlgorithmNotFoundException();
		}

	}

	public CryptoKey startProcess(String ip) throws EncryptionLogicException {

		try {
			String chosenAlgo = randomAlgorithm();
			return (km.createAndSaveKey(chosenAlgo, ip)); // Create and saves Key
		} catch (AlgorithmNotFoundException | InvalidCryptoKeyException e) {
			throw new EncryptionLogicException("Encryption starting process failed", e);

		}
	}

}
