package Agent.exceptions;


public class AlgorithmNotFoundException extends CryptoException {

	private static final long serialVersionUID = 3818306675321865941L;

	public AlgorithmNotFoundException() {
		super("algorithm did not found ");
	}

	public AlgorithmNotFoundException(String string, Throwable cause) {
		super(string, cause);
	}
}
