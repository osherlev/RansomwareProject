package Agent.Utils;

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

import com.github.raychatter.ExceptionHandler;

import Agent.exceptions.AlgorithmNotFoundException;
import Agent.exceptions.CryptoException;
import Agent.exceptions.InvalidCryptoKeyException;
import Agent.exceptions.KeyNotFoundException;
import Agent.exceptions.PaddingException;

@ExceptionHandler
public class JavaCryptoUtil {

	public static void encrypt(SecretKey skey, File fileToEncrypt, File outPutFile, String algorithm)
			throws CryptoException {

		doCrypto(skey, fileToEncrypt, Cipher.ENCRYPT_MODE, outPutFile, algorithm);
	}

	public static void decrypt(SecretKey skey, File fileToDecrypt, File outPutFile, String algorithm)
			throws CryptoException {

		doCrypto(skey, fileToDecrypt, Cipher.DECRYPT_MODE, outPutFile, algorithm);
	}

	private static void doCrypto(SecretKey skey, File inputFile, int cipherMode, File outputFile, String algorithm)
			throws CryptoException {

		Cipher cipher = initCipher(skey, algorithm, cipherMode);
		try (FileInputStream inputStream = new FileInputStream(inputFile);
				FileOutputStream outputStream = new FileOutputStream(outputFile);) {
			byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);
			byte[] outputBytes = null;
			outputBytes = cipher.doFinal(inputBytes);
			outputStream.write(outputBytes);
		} catch (IOException e) {
			throw new InvalidCryptoKeyException("Given key does not match the algorithm requirements", e.getCause());
		} catch (IllegalBlockSizeException e) {
			throw new InvalidCryptoKeyException(
					"Given key does not match the algorithm requirements -Illegal block size", e.getCause());
		} catch (BadPaddingException e) {
			throw new InvalidCryptoKeyException(
					"Given key does not match the algorithm requirements - Bad Cipher padding", e.getCause());
		}

	}

	private static Cipher initCipher(SecretKey skey, String algorithm, int cipherMode)
			throws AlgorithmNotFoundException, PaddingException, KeyNotFoundException {
		SecretKey secretKey = new SecretKeySpec(skey.getEncoded(), skey.getAlgorithm());
		try {
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(cipherMode, secretKey);
			return cipher;
		} catch (NoSuchAlgorithmException e) {
			throw new AlgorithmNotFoundException();
		} catch (NoSuchPaddingException e) {
			throw new PaddingException("There is no Cipher padding ", e.getCause());
		} catch (InvalidKeyException e) {
			throw new KeyNotFoundException();
		}

	}
}
