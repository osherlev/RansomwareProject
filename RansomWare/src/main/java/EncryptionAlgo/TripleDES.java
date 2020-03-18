package EncryptionAlgo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class TripleDES<T> implements EncryptionCodec<T> {
	@Override
	public void decrypt(T key, File fileToDecrypt) {

		SecretKey sKey = (SecretKey) key;
		try {
			Key secretKey = new SecretKeySpec(sKey.getEncoded(), "DESede");

			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);

			FileInputStream inputStream = new FileInputStream(fileToDecrypt);
			byte[] inputBytes = new byte[(int) fileToDecrypt.length()];
			inputStream.read(inputBytes);

			byte[] outputBytes = cipher.doFinal(inputBytes);
			File outputFile = new File(fileToDecrypt.getAbsolutePath().replaceAll(".encrypted", ".decrypted"));
			FileOutputStream outputStream = new FileOutputStream(outputFile);
			outputStream.write(outputBytes);

			inputStream.close();
			outputStream.close();

		} catch (Exception e) {
			System.out.println(" SecretKeySpec not working bc " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T encrypt(File fileToEncrypt) {

		SecretKey sKey = createKey();
		try {

			Key secretKey = new SecretKeySpec(sKey.getEncoded(), "DESede");
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
			System.out.println(" didn't work bc " + e.getMessage());
		}
		return (T) sKey;
	}

	@Override
	public <T> SecretKey createKey() {

		KeyGenerator keyGen = null;
		try {
			keyGen = KeyGenerator.getInstance("DESede");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		keyGen.init(256); // 256 bit key
		SecretKey secretKey = keyGen.generateKey();

		return secretKey;

	}
}