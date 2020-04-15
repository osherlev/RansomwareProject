package Agent.traversal;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BFS<T> implements Traverse<T> {
	private Queue _queue=new LinkedList<T>();

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
