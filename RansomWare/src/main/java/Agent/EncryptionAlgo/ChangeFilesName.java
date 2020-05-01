package Agent.EncryptionAlgo;

import java.io.File;

import Agent.configuration.ConfigureProps;
import Agent.exceptions.CryptOperationException;
import Agent.exceptions.InOutException;

public class ChangeFilesName {

	private String decryptedFileExtension;
	private String encryptedFileExtension;

	public File decryptedOutputFile(File fileToDecrypt) throws CryptOperationException {
		try {
			decryptedFileExtension = ConfigureProps.getPropsValue("File.decrypted");
			return new File(fileToDecrypt.getAbsolutePath().replaceAll(encryptedFileExtension, decryptedFileExtension));
		} catch (InOutException e) {
			throw new CryptOperationException("Could not get encrypt file extention",e);
		}
	}

	public File encryptedOutputFile(File fileToencrypt) throws CryptOperationException, InOutException {
		try {
			encryptedFileExtension = ConfigureProps.getPropsValue("File.encrypted");
			return new File(fileToencrypt.getAbsolutePath().concat(encryptedFileExtension));
		} catch (InOutException e) {
			throw new CryptOperationException("Could not get decrypt file extention",e);
		}
	}

}
