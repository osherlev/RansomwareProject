package app.keys;

import app.entities.CryptoKey;
import app.exceptions.InvalidCryptoKeyException;

public interface Management {
	public CryptoKey createAndSaveKey(String encalgo, String ip) throws InvalidCryptoKeyException;
}
