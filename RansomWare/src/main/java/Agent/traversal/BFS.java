package Agent.traversal;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import server.exceptions.RansomwareException;

public class BFS extends Traverse {
	private Queue<File> _queue;

	@Override
	public void addToStruct(File file) {
		_queue.add(file);

	}

	@Override
	public File removeFromStruct() {

		return _queue.poll();
	}

	@Override
	public boolean checkEmptyStruct() {

		return _queue.isEmpty();
	}

}
