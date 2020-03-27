package server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AlgorithmNotFoundException extends RansomwareException {

	public AlgorithmNotFoundException() {
		super("algorithm did not found ");
	}
}
