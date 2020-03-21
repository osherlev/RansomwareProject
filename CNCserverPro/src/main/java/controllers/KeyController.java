package controllers;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Payment.PaymentProcess;
import bl.EncryptionLogic;

import entities.CreditCard;
import entities.CryptoKey;
import exceptions.PaymentNotFoundException;
import repositories.KeyRepository;

@RestController
public class KeyController {
	@Inject
	private KeyRepository repository;
	@Inject
	private EncryptionLogic logic;

	@GetMapping("/requestKey")
	public <T> void saveKey(@RequestParam String ip) {
		logic.startProcess(ip);
	}

	@GetMapping("/buyKey")
	@RequestMapping(method = RequestMethod.GET)
	public CryptoKey<?> getKey(@RequestParam CreditCard cr, @RequestParam String ip) throws PaymentNotFoundException {
		PaymentProcess clientPayment = new PaymentProcess();
		if (clientPayment.isPaid()) {
			Optional<CryptoKey> key = repository.findById(ip);
			return key.get();

		}
		throw new PaymentNotFoundException();
	}

}
