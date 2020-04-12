package Agent.traversal;

import java.util.Queue;

public class BFS  implements Traverse  {
	private Queue _queue; 

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
