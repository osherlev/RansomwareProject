package Agent.exceptions;

public class AttackVectorException extends RansomwareException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8952733796847920004L;

	public AttackVectorException() {
		super();
	}

	public AttackVectorException(String message, Throwable cause) {
		super(message, cause);
	}

	public AttackVectorException(String message) {
		super(message);
	}

	public AttackVectorException(Throwable cause) {
		super(cause);
	}
}
