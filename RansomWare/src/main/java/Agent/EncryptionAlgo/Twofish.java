package Agent.EncryptionAlgo;

import java.io.File;

import javax.crypto.SecretKey;

import Agent.Utils.JavaCryptoUtil;
import Agent.exceptions.AlgorithmNotFoundException;
import Agent.exceptions.CryptoException;
import Agent.exceptions.InOutException;
import Agent.exceptions.KeyNotFoundException;
import Agent.exceptions.PaddingException;

public class Twofish implements CryptoAlgorithm {

	@Override
	public void encrypt(SecretKey skey, File fileToEncrypt, File outputFile)
			throws AlgorithmNotFoundException, PaddingException, KeyNotFoundException, CryptoException, InOutException {
		try {
			JavaCryptoUtil.encrypt(skey, fileToEncrypt, outputFile, "twofish");
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
	public void decrypt(SecretKey skey, File fileToDecrypt, File outputFile)
			throws AlgorithmNotFoundException, PaddingException, KeyNotFoundException, CryptoException, InOutException {
		try {
			JavaCryptoUtil.decrypt(skey, fileToDecrypt, outputFile, "twofish");
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
