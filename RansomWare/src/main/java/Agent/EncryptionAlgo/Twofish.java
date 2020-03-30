package Agent.EncryptionAlgo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import server.exceptions.CryptoException;

public class Twofish implements EncryptionCodec {

	@Override
	public void decrypt(SecretKey skey, File fileToDecrypt) throws CryptoException {
		try {
			Key secretKey = new SecretKeySpec(skey.getEncoded(), "twofish");

			Cipher cipher = Cipher.getInstance("twofish");
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
			throw new CryptoException();
		}
	}

	@Override
	public void encrypt(SecretKey skey, File fileToEncrypt) throws CryptoException {

		try {
			SecretKey secretKey = new SecretKeySpec(skey.getEncoded(), "twofish");
			Cipher cipher = Cipher.getInstance("DESede");
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
			throw new CryptoException();
		}

	}

}