package Agent.EncryptionAlgo;

import java.io.File;

public class ChangeFilesName {

	public static File decryptedOutputFile(File fileToDecrypt) {

		return new File(fileToDecrypt.getAbsolutePath().replaceAll(".encrypted", "."));
	}

	public static File encryptedOutputFile(File fileToencrypt) {
		return new File(fileToencrypt.getAbsolutePath().concat(".encrypted"));
	}
}
