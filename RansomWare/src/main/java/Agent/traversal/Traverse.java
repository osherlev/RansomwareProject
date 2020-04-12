package Agent.traversal;

public interface  Traverse {
	
	public abstract <T> void add(T file);

	public abstract <T> T remove();

	public abstract boolean isEmpty();

}
