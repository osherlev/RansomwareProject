package Agent.EncryptionAlgo;

import java.io.File;

public class RansomFiles {

	public File decryptInputFile(File Inputfile) {
		return new File(Inputfile.getAbsolutePath().replaceAll(".encrypted", ""));
	}

	public File encryptInputFile(File inputFile) {
		return new File(inputFile.getAbsolutePath() + ".encrypted");
	}
}
