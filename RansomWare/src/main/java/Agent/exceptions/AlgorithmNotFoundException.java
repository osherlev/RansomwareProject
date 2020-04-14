package Agent.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AlgorithmNotFoundException extends RansomwareException {



	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;

	public AlgorithmNotFoundException() {
		super("algorithm did not found ");
	}
}
