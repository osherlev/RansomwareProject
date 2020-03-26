package server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PAYMENT_REQUIRED)
public class PaymentNotFoundException extends RansomwareException {

	public PaymentNotFoundException() {
		super("you didn't pay bitch");
	}

}