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

import org.springframework.beans.factory.annotation.Value;

import Agent.exceptions.CryptoException;

public class JavaCryptoUtil {
	@Value("${File.decrypted}")
	private String decfile;
	@Value("${File.encrypted}")
	private String encfile;

	public void doCrypto(SecretKey skey, File inputFile, int cipherMode, File outputFile, String algorithm)
			throws CryptoException {

		SecretKey secretKey = new SecretKeySpec(skey.getEncoded(), algorithm);
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(algorithm);
			cipher.init(cipherMode, secretKey);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			throw new CryptoException("Cipher problem");
		}

		try (FileInputStream inputStream = new FileInputStream(inputFile);) {
			byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);
			byte[] outputBytes = null;
			try {
				outputBytes = cipher.doFinal(inputBytes);
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				throw new CryptoException("output stream problem");
			}
			try (FileOutputStream outputStream = new FileOutputStream(outputFile);) {
				outputStream.write(outputBytes);
				inputStream.close();
				outputStream.close();
			}
		} catch (IOException e) {
			throw new CryptoException("input stream problem");
		}

	}

	public void decrypt(SecretKey skey, File fileToDecrypt, String algoritm) throws CryptoException {

		doCrypto(skey, fileToDecrypt, Cipher.DECRYPT_MODE, new File(fileToDecrypt.getAbsolutePath() + decfile), algoritm);
	}

	public void encrypt(SecretKey skey, File fileToEncrypt, String algoritm) throws CryptoException {

		doCrypto(skey, fileToEncrypt, Cipher.ENCRYPT_MODE, new File(fileToEncrypt.getAbsolutePath() + encfile), algoritm);
	}


}
