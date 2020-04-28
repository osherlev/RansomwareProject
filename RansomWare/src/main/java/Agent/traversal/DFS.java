package Agent.traversal;

import java.util.Stack;

public class DFS<T> implements Traverse<T> {
	private Stack<T> _stack;

	@Override
	public void add(T obj) {
		_stack.add(obj);
	}

	@Override
	public T remove() {
		return (T) _stack.pop();
	}

	@Override
	public boolean isEmpty() {
		return _stack.isEmpty();
	}

	@Override
	public void init() {
		_stack = new Stack<T>();

	}

}
