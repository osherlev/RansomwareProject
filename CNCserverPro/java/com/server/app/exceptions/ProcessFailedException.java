package com.server.app.exceptions;

public class ProcessFailedException extends RansomwareException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3300919948016520449L;

	public ProcessFailedException() {
		super();
	}

	public ProcessFailedException(String message) {
		super(message);
	}

	public ProcessFailedException(String message, Throwable cause) {
		super(message, cause);
	}
}
