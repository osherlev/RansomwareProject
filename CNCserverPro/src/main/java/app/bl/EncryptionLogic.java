package app.bl;

import java.security.SecureRandom;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import app.entities.CryptoKey;
import app.exceptions.AlgorithmNotFoundException;
import app.exceptions.InvalidCryptoKeyException;
import app.exceptions.ProcessFailedException;
import app.keys.KeyManagement;

@Service
public class EncryptionLogic {

	@Value("${algorithms.arr}")
	private String[] algorithmsArr;
	@Inject
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
