package keys;

import java.security.NoSuchAlgorithmException;
import java.util.Random;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CreateKeys {
	public <T> SecretKey createKey() {
		String chosenAlgoritm = randomAlgorithm();
		KeyGenerator keyGen = null;
		try {
			keyGen = KeyGenerator.getInstance(chosenAlgoritm);
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

	public String getClientIp() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String remoteAddr = "";
		remoteAddr = request.getHeader("X-FORWARDED-FOR");
		if (remoteAddr == null || "".equals(remoteAddr)) {
			remoteAddr = request.getRemoteAddr();
		}
		return remoteAddr;
	}

}
