package Agent.EncryptionAlgo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
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

	private static String STATIC_ENCFILE;
	private static String STATIC_DECFILE;

	public static void doCrypto(SecretKey skey, File inputFile, int cipherMode, File outputFile, String algorithm)
			throws CryptoException, GeneralSecurityException {

		SecretKey secretKey = new SecretKeySpec(skey.getEncoded(), algorithm);
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(skey.getAlgorithm());
			cipher.init(cipherMode, secretKey);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			throw new CryptoException("Cipher problem", e.getCause());
		}

		try (FileInputStream inputStream = new FileInputStream(inputFile);) {
			byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);
			byte[] outputBytes = null;

			try {
				outputBytes = cipher.doFinal(inputBytes);
			} catch (IllegalBlockSizeException e) {
				throw new IllegalBlockSizeException("illegal block size");
			} catch (BadPaddingException e) {
				throw new BadPaddingException("bad padding");
			}

			try (FileOutputStream outputStream = new FileOutputStream(outputFile);) {
				outputStream.write(outputBytes);
			}
		} catch (IOException e) {
			throw new CryptoException("input stream problem", e.getCause());
		}

	}

	@Value("${File.decrypted}")
	private static void setDecName(String name) {
		JavaCryptoUtil.STATIC_DECFILE = name;
	}

	public static void decrypt(SecretKey skey, File fileToDecrypt, String algoritm)
			throws CryptoException, GeneralSecurityException {

		setDecName("");
		doCrypto(skey, fileToDecrypt, Cipher.DECRYPT_MODE,
				new File(fileToDecrypt.getAbsolutePath().replaceAll(STATIC_ENCFILE, STATIC_DECFILE)), algoritm);
	}

	@Value("${File.encrypted}")
	private static void setEncName(String name) {
		JavaCryptoUtil.STATIC_ENCFILE = name;
	}

	public static void encrypt(SecretKey skey, File fileToEncrypt, String algoritm)
			throws CryptoException, GeneralSecurityException {
		setEncName("");
		doCrypto(skey, fileToEncrypt, Cipher.ENCRYPT_MODE, new File(fileToEncrypt.getAbsolutePath() + STATIC_ENCFILE),
				algoritm);
	}

}
