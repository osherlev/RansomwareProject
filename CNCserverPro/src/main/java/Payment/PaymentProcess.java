package Payment;

import entities.CreditCard;

public class PaymentProcess {
	public boolean checkCreditCard(CreditCard cr) {

		return CreditCard.isValid(cr.getCreditCardNum());
	}

	public void charge() {

	}

	public boolean isPaid() {
		return true;

	}
}
