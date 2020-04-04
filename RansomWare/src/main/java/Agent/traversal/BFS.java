package Agent.traversal;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends TraverseUtil {

	
	@Override
	public void traverseAndEncrypt(String inputDir, Collection<File> dirs) {
		Queue<File> queue=new LinkedList<File>();
		sharedCode(inputDir, dirs,queue);
	
	}

}
