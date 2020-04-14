package Agent.traversal;

public interface  Traverse<T> {
	
	public abstract void add(T obj);

	public abstract T remove();

	public abstract boolean isEmpty();



}
