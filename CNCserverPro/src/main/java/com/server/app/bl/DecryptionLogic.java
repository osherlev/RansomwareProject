package com.server.app.bl;

import java.util.Optional;

import javax.inject.Inject;

import com.server.app.entities.Bitcoin;
import com.server.app.entities.CryptoKey;
import com.server.app.exceptions.KeyNotFoundException;
import com.server.app.exceptions.PaymentNotFoundException;
import com.server.app.repositories.KeyRepository;

public class DecryptionLogic {

	@Inject
	private KeyRepository repository;

	public CryptoKey getKey(String ip, Bitcoin btc) throws PaymentNotFoundException, KeyNotFoundException {
		PaymentProcess clientPayment = new PaymentProcess();
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
