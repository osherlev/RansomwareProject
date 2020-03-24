package controllers;

import java.util.Optional;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Payment.PaymentProcess;
import bl.*;

import entities.CreditCard;
import entities.CryptoKey;
import exceptions.PaymentNotFoundException;
import repositories.KeyRepository;

@RestController
public class KeyController {
	@Inject
	private KeyRepository repository;
	@Autowired
	EncryptionLogic encLogic;

	@GetMapping("/requestKey")
	public <T> void saveKey(@RequestParam String ip) {
		encLogic.startProcess(ip);
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
