package Agent.EncryptionAlgo;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;

public class ChangeFilesName {

	@Value("${File.decrypted}")
	private String decryptedFile;
	@Value("${File.encrypted}")
	private String encryptedFile;

	public File decryptedOutputFile(File fileToDecrypt) {
		return new File(fileToDecrypt.getAbsolutePath().replaceAll(encryptedFile, decryptedFile));
	}

	public File encryptedOutputFile(File fileToencrypt) {
		return new File(fileToencrypt.getAbsolutePath() + encryptedFile);
	}
}
