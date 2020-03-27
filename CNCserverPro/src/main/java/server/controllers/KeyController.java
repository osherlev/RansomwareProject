package server.controllers;

import java.security.NoSuchAlgorithmException;
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
	public <T> void saveKey(@RequestParam String ip) throws AlgorithmNotFoundException, NoSuchAlgorithmException {
		encLogic.startProcess(ip);
	}

	@GetMapping("/buyKey")
	public CryptoKey<?> getKey(@RequestParam String ip, @RequestParam Bitcoin btc)
			throws KeyNotFoundException, PaymentNotFoundException {
		return decLogic.checkPay(ip, btc);

	}

}
