package Agent.traversal;

import java.io.File;
import java.util.Collection;
import java.util.Stack;
public class DFS extends TraverseUtil {

	@Override
	public void traverseAndEncrypt(String inputDir, Collection<File> dirs) {
		/* make a stack to store files and directories */
		Stack<File> stack = new Stack<File>();

		while (!stack.isEmpty()) {
			if (!(isVisitedFolder(new File(inputDir), dirs))) {
				stack.push(new File(inputDir));
			}
			sharedCode(inputDir, dirs, stack);

		}

	}
}
