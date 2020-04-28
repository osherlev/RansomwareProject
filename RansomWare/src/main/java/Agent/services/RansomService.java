package Agent.services;

import Agent.entites.CryptoKey;
import Agent.exceptions.CryptoException;

public interface RansomService {
	public CryptoKey getKey() throws CryptoException;

	public CryptoKey buyKey() throws CryptoException;
}
