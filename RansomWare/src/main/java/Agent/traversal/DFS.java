package Agent.traversal;


import java.util.Stack;

public class DFS<T> implements Traverse<T> {
	private Stack _stack = new Stack();

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

}
