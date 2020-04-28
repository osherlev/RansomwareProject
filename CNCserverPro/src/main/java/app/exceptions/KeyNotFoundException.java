package app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class KeyNotFoundException extends RansomwareException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2096265002516976528L;

	public KeyNotFoundException() {
		super("key not found");
	}
}
