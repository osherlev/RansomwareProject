package Agent.traversal;

import java.io.File;
import java.util.Queue;

public class BFS extends Traverse {
	private Queue<File> _queue;

	@Override
	public void add(File file) {
		_queue.add(file);
	}

	@Override
	public File remove() {

		return _queue.poll();
	}

	@Override
	public boolean isEmpty() {

		return _queue.isEmpty();
	}

}
