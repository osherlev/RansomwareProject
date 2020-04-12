package Agent.traversal;

import java.util.Stack;

public class DFS implements Traverse {
	private Stack _stack;

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
