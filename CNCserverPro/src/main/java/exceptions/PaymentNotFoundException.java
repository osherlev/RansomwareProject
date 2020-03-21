package exceptions;

import entities.CreditCard;

public class PaymentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PaymentNotFoundException() {
		super("you didn't pay bitch : ");
	}


}