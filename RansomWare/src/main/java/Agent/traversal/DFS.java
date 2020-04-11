package Agent.traversal;

import java.io.File;
import java.util.Stack;

public class DFS extends Traverse {
	private Stack<File> _stack;

	@Override
	public void add(File file) {
		_stack.push(file);
	}

	@Override
	public boolean isEmpty() {

		return _stack.isEmpty();
	}

	@Override
	public File remove() {
		return _stack.pop();
	}
}
