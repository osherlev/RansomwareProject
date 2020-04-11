package Agent.traversal;

import java.io.File;
import java.util.Queue;

public class BFS<T> extends Traverse {
	private Queue<T> _queue;

	@Override
	public <T> void add(T file) {
	_queue.add((T) file);
		
	}

	@Override
	public <T> T remove() {
		return (T) _queue.poll();
	}

	@Override
	public boolean isEmpty() {
		return _queue.isEmpty();
	}



}
