package com.server.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AlgorithmNotFoundException extends RansomwareException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2664479432632571320L;

	public AlgorithmNotFoundException() {
		super("algorithm did not found ");
	}
}
