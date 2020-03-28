package server.controllers;

import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import server.bl.DecryptionLogic;
import server.bl.EncryptionLogic;
import server.entities.Bitcoin;
import server.entities.CryptoKey;
import server.exceptions.AlgorithmNotFoundException;
import server.exceptions.KeyNotFoundException;
import server.exceptions.PaymentNotFoundException;

@RestController
public class KeyController {

	@Inject
	EncryptionLogic encLogic;
	@Inject
	DecryptionLogic decLogic;

	@GetMapping("/requestKey")
	public <T> CryptoKey<T> saveKey(@RequestParam String ip) throws AlgorithmNotFoundException, NoSuchAlgorithmException {
		CryptoKey<T> key=encLogic.startProcess(ip);
		return key;
	}

	@GetMapping("/buyKey")
	public CryptoKey<?> buyKey(@RequestParam String ip, @RequestParam Bitcoin btc)
			throws KeyNotFoundException, PaymentNotFoundException {
		return decLogic.returnKey(ip, btc);

	}

}
