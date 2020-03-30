package Agent.EncryptionAlgo;

import server.exceptions.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CruptoUtil {
	public void encrypt(SecretKey key, File inputFile, String outputFile, String algorithm) throws CryptoException {
		doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile, algorithm);
	}

	public void decrypt(SecretKey key, File inputFile, String outputFile, String algorithm) throws CryptoException {
		doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile, algorithm);
	}

	private void doCrypto(int cipherMode, SecretKey key, File inputFile, String outputFile, String algorithm) throws CryptoException {
		try {
			Key secretKey = new SecretKeySpec(key.getEncoded(), algorithm);
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(cipherMode, secretKey);

			FileInputStream inputStream = new FileInputStream(inputFile);
			byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);

			byte[] outputBytes = cipher.doFinal(inputBytes);

			File outputF = new File(outputFile);
			FileOutputStream outputStream = new FileOutputStream(outputF);
			outputStream.write(outputBytes);

			inputStream.close();
			outputStream.close();

		} catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException
				| IllegalBlockSizeException | IOException ex) {
			throw new CryptoException();
		}
	}
}
