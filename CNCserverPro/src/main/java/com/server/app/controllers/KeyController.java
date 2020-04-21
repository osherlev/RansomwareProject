package com.server.app.controllers;

import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.app.bl.DecryptionLogic;
import com.server.app.bl.EncryptionLogic;
import com.server.app.entities.Bitcoin;
import com.server.app.entities.CryptoKey;
import com.server.app.exceptions.AlgorithmNotFoundException;
import com.server.app.exceptions.KeyNotFoundException;
import com.server.app.exceptions.PaymentNotFoundException;

@RestController

public class KeyController {
	@Inject
	private EncryptionLogic encLogic;
	private DecryptionLogic decLogic;

	@GetMapping("/requestKey")
	public CryptoKey saveKey(HttpServletRequest request) throws AlgorithmNotFoundException, NoSuchAlgorithmException {
		return encLogic.startProcess(request.getRemoteAddr());

	}

	@GetMapping("/buyKey")
	public CryptoKey buyKey(@RequestParam Bitcoin btc, HttpServletRequest request)
			throws KeyNotFoundException, PaymentNotFoundException {
		return decLogic.getKey(request.getRemoteAddr(), btc);

	}
}
