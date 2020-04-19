package Agent.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AlgorithmNotFoundException extends RansomwareException {

	private static final long serialVersionUID = 3818306675321865941L;

	public AlgorithmNotFoundException() {
		super("algorithm did not found ");
	}

	public AlgorithmNotFoundException(String string, Throwable cause) {
		super(string, cause);
	}
}
