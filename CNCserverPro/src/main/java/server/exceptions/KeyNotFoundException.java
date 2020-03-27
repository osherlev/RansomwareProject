package server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class KeyNotFoundException extends RansomwareException {
	public KeyNotFoundException() {
		super("key not found");
	}
}
