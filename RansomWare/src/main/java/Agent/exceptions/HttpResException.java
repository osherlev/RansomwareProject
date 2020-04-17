package Agent.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
public class HttpResException extends RansomwareException {

	private static final long serialVersionUID = 5717072743017452209L;

	public HttpResException() {
		super();
	}

	public HttpResException(String msg) {
		super(msg);
	}

	public HttpResException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
