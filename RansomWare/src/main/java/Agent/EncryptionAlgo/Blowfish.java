package Agent.EncryptionAlgo;

import java.io.File;
import javax.crypto.SecretKey;

import Agent.Utils.JavaCryptoUtil;
import Agent.exceptions.AlgorithmNotFoundException;
import Agent.exceptions.CryptoException;
import Agent.exceptions.InOutException;
import Agent.exceptions.KeyNotFoundException;
import Agent.exceptions.PaddingException;

public class Blowfish implements CryptoAlgorithm {

	@Override
	public void encrypt(SecretKey skey, File fileToEncrypt, File outputFile) throws CryptoException {
		try {
			JavaCryptoUtil.encrypt(skey, fileToEncrypt, outputFile, "Blowfish/ECB/PKCS5Padding");
		} catch (AlgorithmNotFoundException e) {
			throw new AlgorithmNotFoundException("algorithm class not found", e.getCause());
		} catch (PaddingException e) {
			throw new PaddingException("padding went wrong", e.getCause());
		} catch (KeyNotFoundException e) {
			throw new KeyNotFoundException("key not found", e.getCause());
		} catch (InOutException e) {
			throw new InOutException(e.getCause());
		} catch (CryptoException e) {
			throw new CryptoException("Problem encrypting file", e.getCause());
		}

	}

	@Override
	public void decrypt(SecretKey skey, File fileToDecrypt, File outputFile) throws CryptoException {
		try {
			JavaCryptoUtil.decrypt(skey, fileToDecrypt, outputFile, "Blowfish/ECB/PKCS5Padding");
		} catch (AlgorithmNotFoundException e) {
			throw new AlgorithmNotFoundException("algorithm class not found", e.getCause());
		} catch (PaddingException e) {
			throw new PaddingException("padding went wrong", e.getCause());
		} catch (KeyNotFoundException e) {
			throw new KeyNotFoundException("key not found", e.getCause());
		} catch (InOutException e) {
			throw new InOutException("You dont have enough premission to decrypt", e.getCause());
		} catch (CryptoException e) {
			throw new CryptoException("Problem decrypting file", e.getCause());
		}
	}

}
