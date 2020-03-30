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

public class Blowfish implements EncryptionCodec{
	
	@Override
	public void decrypt(SecretKey skey, File fileToDecrypt) {
		try {
			Key secretKey = new SecretKeySpec(skey.getEncoded(), "Blowfish");

			Cipher cipher = Cipher.getInstance("Blowfish");
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
			System.out.println(" SecretKeySpec not working bc " + e.getMessage());
		}
	}

	@Override
	public void encrypt(SecretKey skey, File fileToEncrypt) {

		try {
			SecretKey secretKey = new SecretKeySpec(skey.getEncoded(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
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

	}

}