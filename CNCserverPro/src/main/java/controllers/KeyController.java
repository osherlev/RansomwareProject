package controllers;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import BusinessLogic.Logic;
import Payment.PaymentProcess;
import entities.CreditCard;
import entities.CryptoKey;
import exceptions.PaymentNotFoundException;
import repositories.KeyRepository;

@RestController
public class KeyController {
	@Inject
	private KeyRepository repository;

	public <T> void saveKey(@RequestParam String ip) {
		Logic logic = new Logic();
		logic.startProcess(ip);
	}

	@GetMapping("/paymentProcess")
	public CryptoKey<?> getKey(@RequestParam CreditCard cr, @RequestParam String ip) {
		PaymentProcess clientPayment = new PaymentProcess();
		if (clientPayment.isPaid()) {
			Optional<CryptoKey> key = repository.findById(ip);
			return key.get();

		}
		throw new PaymentNotFoundException();
	}

}
