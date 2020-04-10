package Agent.traversal;

import java.io.File;
import java.util.Stack;

public class DFS extends Traverse {
	private Stack<File> _stack;

	@Override
	public void addToStruct(File file) {
		_stack.push(file);
	}

	@Override
	public boolean checkEmptyStruct() {

		return _stack.isEmpty();
	}

	@Override
	public File removeFromStruct() {

		return _stack.pop();
	}
}
