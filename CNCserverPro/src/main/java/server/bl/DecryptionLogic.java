package server.bl;

import org.springframework.web.bind.annotation.RequestParam;

import Payment.Bitcoin;
import Payment.PaymentProcess;
import server.controllers.KeyController;
import server.entities.CryptoKey;
import server.exceptions.PaymentNotFoundException;
import server.keys.KeyManagement;

public class DecryptionLogic {

	public <T> void endProcess(CryptoKey<?> key) {

	}

	public CryptoKey<?> checkPay(String ip, @RequestParam Bitcoin btc) throws PaymentNotFoundException {
		PaymentProcess clientPayment = new PaymentProcess();
		if (clientPayment.isPaid()) {
			KeyController controller = new KeyController();
			return controller.getKey(ip);
		}
		throw new PaymentNotFoundException();
	}
}
