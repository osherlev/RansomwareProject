package com.server.app.keys;

import com.server.app.entities.CryptoKey;
import com.server.app.exceptions.InvalidCryptoKeyException;

public interface Management {
	public CryptoKey createAndSaveKey(String encalgo, String ip) throws InvalidCryptoKeyException;
}
