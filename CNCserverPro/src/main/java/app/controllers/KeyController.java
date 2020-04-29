package app.controllers;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.bl.DecryptionLogic;
import app.bl.EncryptionLogic;
import app.entities.Bitcoin;
import app.entities.CryptoKey;
import app.exceptions.DecryptionLogicException;
import app.exceptions.EncryptionLogicException;
import app.exceptions.PaymentNotFoundException;
import app.repositories.KeyRepository;

@RestController
public class KeyController {
	@Inject
	private EncryptionLogic encLogic;
	@Inject
	private DecryptionLogic decLogic;
	@Inject
	private KeyRepository repository;

	@GetMapping("/requestKey")
	public CryptoKey saveKey(HttpServletRequest request) throws EncryptionLogicException {
		return repository.save(encLogic.startProcess(request.getRemoteAddr()));
	}

	@GetMapping("/buyKey")
	public CryptoKey buyKey(@RequestParam Bitcoin btc, HttpServletRequest request)
			throws DecryptionLogicException, PaymentNotFoundException {
		return decLogic.getKey(request.getRemoteAddr(), btc);
	}
}
