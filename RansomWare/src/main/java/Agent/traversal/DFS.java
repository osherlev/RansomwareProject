package Agent.traversal;

import java.io.File;
import java.util.Stack;

public class DFS<T> extends Traverse {
	private Stack<T> _stack;

	@Override
	public void add(File file) {
		_stack.push((T) file);
	}

	@Override
	public boolean isEmpty() {

		return _stack.isEmpty();
	}

	@Override
	public File remove() {
		return (File) _stack.pop();
	}
}
