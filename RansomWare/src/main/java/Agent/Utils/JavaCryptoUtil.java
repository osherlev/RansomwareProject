package Agent.Utils;

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

import Agent.exceptions.CipherException;
import Agent.exceptions.InvalidCryptoKeyException;

public class JavaCryptoUtil {
	public static void encrypt(SecretKey skey, File fileToEncrypt, File outPutFile, String algorithm)
			throws InvalidCryptoKeyException {

		doCrypto(skey, fileToEncrypt, Cipher.ENCRYPT_MODE, outPutFile, algorithm);
	}

	public static void decrypt(SecretKey skey, File fileToDecrypt, File outPutFile, String algorithm)
			throws InvalidCryptoKeyException {
		doCrypto(skey, fileToDecrypt, Cipher.DECRYPT_MODE, outPutFile, algorithm);
	}

	private static void doCrypto(SecretKey skey, File inputFile, int cipherMode, File outputFile, String algorithm)
			throws InvalidCryptoKeyException {

		Cipher cipher = null;
		try {
			cipher = initCipher(skey, algorithm, cipherMode);
		} catch (CipherException e) {
			throw new InvalidCryptoKeyException("Cipher did not initalizied", e.getCause());
		}
		try (FileInputStream inputStream = new FileInputStream(inputFile);
				FileOutputStream outputStream = new FileOutputStream(outputFile);) {
			byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);
			byte[] outputBytes = cipher.doFinal(inputBytes);
			outputStream.write(outputBytes);
			inputStream.close();
			inputFile.delete();
		} catch (IOException e) {
			throw new InvalidCryptoKeyException("You do not have enough premissions to do this", e.getCause());
		} catch (IllegalBlockSizeException e) {
			throw new InvalidCryptoKeyException(
					"Given key does not match the algorithm requirements -Illegal block size", e.getCause());
		} catch (BadPaddingException e) {
			throw new InvalidCryptoKeyException(
					"Given key does not match the algorithm requirements - Bad Cipher padding", e.getCause());
		}

	}

	private static Cipher initCipher(SecretKey skey, String algorithm, int cipherMode) throws CipherException {
		try {
			Key key = new SecretKeySpec(skey.toString().getBytes(), algorithm);
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(cipherMode, key);
			return cipher;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			throw new CipherException("Cipher initalization went wrong", e.getCause());
		}
	}
}
