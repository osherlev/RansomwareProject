package app.controllers;

import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.bl.DecryptionLogic;
import app.bl.EncryptionLogic;
import app.entities.Bitcoin;
import app.entities.CryptoKey;
import app.exceptions.AlgorithmNotFoundException;
import app.exceptions.InvalidCryptoKeyException;
import app.exceptions.KeyNotFoundException;
import app.exceptions.PaymentNotFoundException;
import app.exceptions.ProcessFailedException;
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
	public CryptoKey saveKey(HttpServletRequest request) throws AlgorithmNotFoundException, NoSuchAlgorithmException, ProcessFailedException, InvalidCryptoKeyException {
		return repository.save(encLogic.startProcess(request.getRemoteAddr()));

	}

	@GetMapping("/buyKey")
	public CryptoKey buyKey(@RequestParam Bitcoin btc, HttpServletRequest request)
			throws KeyNotFoundException, PaymentNotFoundException {
		return decLogic.getKey(request.getRemoteAddr(), btc);

	}
}
