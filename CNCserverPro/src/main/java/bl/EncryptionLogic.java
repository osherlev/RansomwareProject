package bl;

import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Value;

import keys.KeyManagement;

public class EncryptionLogic {
	@Value("${algorithms.arr}")
	private String[] algorithmsArr;

	public String[] getArr() {
		return algorithmsArr;
	}

	public void makeConfigFiles() {
		configuration configfile = new configuration();
		configfile.configFileMaker();

	}

	public String randomAlgorithm() {

		SecureRandom rand = new SecureRandom();
		try {
			int a = rand.nextInt(algorithmsArr.length);
			return algorithmsArr[a];
		} catch (NullPointerException e) {

		}
		return "fucking shit";
	}

	public <T> void startProcess(String ip) {
		String chosenAlgo = randomAlgorithm();
		KeyManagement km = new KeyManagement();
		T key = (T) km.createKey(chosenAlgo, ip); // Generates key and saves new entity in DB
	}
}
