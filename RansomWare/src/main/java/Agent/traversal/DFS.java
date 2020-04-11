package Agent.traversal;

import java.io.File;
import java.util.Stack;

public class DFS<T> extends Traverse {
	private Stack<T> _stack;

	@Override
	public <T> void add(T file) {
		_stack.push(file);
		
	}

	@Override
	public <T> T remove() {
		return (T) _stack.pop();
	}

	@Override
	public boolean isEmpty() {
		return _stack.isEmpty();
	}


}
