package Agent.traversal;

import java.io.File;
import java.util.Queue;

public class BFS<T> extends Traverse {
	private Queue<T> _queue;

	@Override
	public void add(File file) {
		_queue.add((T) file);
	}

	@Override
	public File remove() {

		return (File) _queue.poll();
	}

	@Override
	public boolean isEmpty() {

		return _queue.isEmpty();
	}

}
