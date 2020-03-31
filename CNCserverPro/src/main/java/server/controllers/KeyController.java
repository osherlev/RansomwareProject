package server.controllers;

import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;

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
	private HttpServletRequest request;

	@GetMapping("/requestKey")
	public CryptoKey saveKey() throws AlgorithmNotFoundException, NoSuchAlgorithmException {
		return encLogic.startProcess(request.getRemoteAddr());

	}

	@GetMapping("/buyKey")
	public CryptoKey buyKey(@RequestParam Bitcoin btc) throws KeyNotFoundException, PaymentNotFoundException {
		return decLogic.getKey(request.getRemoteAddr(), btc);

	}

}
