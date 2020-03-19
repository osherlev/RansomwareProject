package keys;

import java.security.NoSuchAlgorithmException;
import java.util.Random;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CreateKeys {
	public SecretKey keyMaker(String chosenAlgorithm) {

		KeyGenerator keyGen = null;
		try {
			keyGen = KeyGenerator.getInstance(chosenAlgorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		SecretKey secretKey = keyGen.generateKey();
		return secretKey;

	}

	public String randomAlgorithm() {
		final String[] algorithms = { "AES", "Blowfish", "DESede", "twofish" };
		Random r = new Random();
		final int randomNumber = r.nextInt(algorithms.length);
		return algorithms[randomNumber];
	}

}
