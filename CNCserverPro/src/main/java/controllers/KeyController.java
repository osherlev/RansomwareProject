package controllers;

import java.util.Optional;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Payment.CreditCard;
import Payment.PaymentProcess;
import entities.KeyEntity;
import keys.CreateKeys;
import keys.KeyManagement;
import repositories.KeyRepository;

@RestController
public class KeyController {
	@Inject
	private KeyRepository repository;


	/*public void requestForKey(@RequestParam String ip) {
		CreateKeys ck = new CreateKeys();
		KeyManagement<SecretKeySpec> km = new KeyManagement<SecretKeySpec>();
		String algorithm = ck.randomAlgorithm(); // Server decides the algorithm
		SecretKeySpec clientKey = km.createKey(algorithm); // The key
		km.save(new KeyEntity<SecretKeySpec>(ip, clientKey, algorithm)); // Save to DB
	}
*/
	// GET
	@GetMapping("/startEncryptProcess")
	public KeyEntity<?> findKeyById(@RequestParam String ip) {
		@SuppressWarnings("rawtypes")
		Optional<KeyEntity> key = repository.findById(ip);
		return key.get();

	}

	@GetMapping("/paymentProcess")
	public KeyEntity<?> getKey(@RequestParam CreditCard cr, @RequestParam String ip) {
		PaymentProcess clientPayment = new PaymentProcess();
		if (clientPayment.isPaid())
			findKeyById(ip);
		return null;
	}

}
