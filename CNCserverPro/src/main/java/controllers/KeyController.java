package controllers;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import BusinessLogic.Logic;
import Payment.PaymentProcess;
import entities.CreditCard;
import entities.CryptoKey;
import keys.KeyManagement;
import repositories.KeyRepository;

@RestController
public class KeyController {
	@Inject
	private KeyRepository repository;

	
	public <T> void beginCommunicate(@RequestParam String ip) {
		Logic logic=new Logic();
		String chosenAlgo = logic.randomAlgorithm();
		KeyManagement<T> km = new KeyManagement<T>();
		T key = km.createKey(chosenAlgo, ip); // Generates key and saves new entity in DB
	}


	@GetMapping("/paymentProcess")
	public CryptoKey<?> getKey(@RequestParam CreditCard cr, @RequestParam String ip) {
		PaymentProcess clientPayment = new PaymentProcess();
		if (clientPayment.isPaid()) {

			Optional<CryptoKey> key = repository.findById(ip);
			return key.get();

		}
		return null;
		
	}

}
