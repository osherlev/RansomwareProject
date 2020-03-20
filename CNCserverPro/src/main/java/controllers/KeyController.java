package controllers;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Payment.PaymentProcess;
import entities.CreditCard;
import entities.CryptoKey;
import keys.KeyManagement;
import repositories.KeyRepository;

@RestController
public class KeyController {
	@Inject
	private KeyRepository repository;

	private String randomAlgorithm() {
		
		String[] algorithms = { "AES", "desede", "Blowfish", "twofish" };
		SecureRandom rand = new SecureRandom();
		int a = rand.nextInt(algorithms.length);
		return algorithms[a];
	}

	public <T> void beginCommunicate(@RequestParam String ip) {
		String chosenAlgo = randomAlgorithm();
		KeyManagement<T> km = new KeyManagement<T>();
		T key = km.createKey(chosenAlgo, ip); // Generates key and saves new entity in DB

	}

	// GET
	@GetMapping("/startEncryptProcess")
	public CryptoKey<?> findKeyById(@RequestParam String ip) {

		Optional<CryptoKey> key = repository.findById(ip);
		return key.get();

	}

	@GetMapping("/paymentProcess")
	public CryptoKey<?> getKey(@RequestParam CreditCard cr, @RequestParam String ip) {
		PaymentProcess clientPayment = new PaymentProcess();
		if (clientPayment.isPaid()) {
			findKeyById(ip);
		}
		return null;
	}

}
