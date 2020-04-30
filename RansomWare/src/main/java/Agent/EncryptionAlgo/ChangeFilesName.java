package Agent.EncryptionAlgo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import Agent.exceptions.InOutException;
import Agent.services.ConfigureProps;

public class ChangeFilesName implements ConfigureProps {

	private String decryptedFileExtension;
	private String encryptedFileExtension;
	private Properties prop;
	private InputStream is;

	public ChangeFilesName() throws InOutException {
		getProperties();
	}

	public File decryptedOutputFile(File fileToDecrypt) {

		return new File(fileToDecrypt.getAbsolutePath().replaceAll(encryptedFileExtension, decryptedFileExtension));
	}

	public File encryptedOutputFile(File fileToencrypt) {
		return new File(fileToencrypt.getAbsolutePath().concat(encryptedFileExtension));
	}

	@Override
	public void getProperties() throws InOutException {
		prop = new Properties();
		is = getClass().getResourceAsStream("/application.properties");
		try {
			prop.load(is);
			encryptedFileExtension = prop.getProperty("File.encrypted");
			decryptedFileExtension = prop.getProperty("File.decrypted");
		} catch (IOException e) {
			throw new InOutException("Could not find properties file", e);
		}
	}

}
