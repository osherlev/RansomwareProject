package com.server.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PAYMENT_REQUIRED)
public class PaymentNotFoundException extends RansomwareException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2181032624410319740L;

	public PaymentNotFoundException() {
		super("you didn't pay bitch");
	}

}