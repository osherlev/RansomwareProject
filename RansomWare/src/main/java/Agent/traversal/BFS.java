package Agent.traversal;

import java.util.Queue;

public class BFS<T> implements Traverse<T> {
	private Queue _queue;

	@Override
	public void add(T obj) {
		_queue.add(obj);

	}

	@Override
	public T remove() {
		return (T) _queue.poll();
	}

	@Override
	public boolean isEmpty() {
		return _queue.isEmpty();
	}

}
