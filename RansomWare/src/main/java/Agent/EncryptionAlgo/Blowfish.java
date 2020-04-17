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
	public void encrypt(SecretKey skey, File fileToEncrypt, File outputFile)
			throws AlgorithmNotFoundException, PaddingException, KeyNotFoundException, CryptoException, InOutException {
		try {
			JavaCryptoUtil.encrypt(skey, fileToEncrypt, outputFile, "Blowfish/ECB/PKCS5Padding");
		} catch (AlgorithmNotFoundException e) {
			throw new AlgorithmNotFoundException();
		} catch (PaddingException e) {
			throw new PaddingException();
		} catch (KeyNotFoundException e) {
			throw new KeyNotFoundException();
		} catch (CryptoException e) {
			throw new CryptoException("problem encrypting file");
		} catch (InOutException e) {
			throw new InOutException();
		}

	}

	@Override
	public void decrypt(SecretKey skey, File fileToDecrypt, File outputFile)
			throws AlgorithmNotFoundException, PaddingException, KeyNotFoundException, CryptoException, InOutException {
		try {
			JavaCryptoUtil.decrypt(skey, fileToDecrypt, outputFile, "Blowfish/ECB/PKCS5Padding");
		} catch (AlgorithmNotFoundException e) {
			throw new AlgorithmNotFoundException();
		} catch (PaddingException e) {
			throw new PaddingException();
		} catch (KeyNotFoundException e) {
			throw new KeyNotFoundException();
		} catch (CryptoException e) {
			throw new CryptoException("problem decrypting file");
		} catch (InOutException e) {
			throw new InOutException();
		}
	}

}
