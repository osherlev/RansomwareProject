package server.controllers;

import java.util.Optional;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Payment.Bitcoin;
import server.bl.*;

import server.entities.CryptoKey;
import server.exceptions.PaymentNotFoundException;
import server.repositories.KeyRepository;

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
	public CryptoKey<?> getKey(@RequestParam String ip) throws NullPointerException {

		Optional<CryptoKey> key = repository.findById(ip);
		return key.get();

	}

}
