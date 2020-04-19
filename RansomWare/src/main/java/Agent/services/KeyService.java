package Agent.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.raychatter.ExceptionHandler;

import Agent.Utils.HttpUtil;
import Agent.entites.CryptoKey;
import Agent.exceptions.HttpResException;
import Agent.exceptions.InOutException;
import Agent.exceptions.JsonException;

@Service
@ExceptionHandler
public class KeyService {
	@Value("${server.url.get}")
	private String urlgetKey;
	@Value("${server.url.buy}")
	private String urlbuyKey;

	public CryptoKey getKey() throws JsonException, HttpResException, InOutException {
		return sendRequest(urlgetKey);
	}

	public CryptoKey buyKey() throws JsonException, HttpResException, InOutException {
		return sendRequest(urlbuyKey);
	}

	private CryptoKey sendRequest(String url) throws JsonException, HttpResException, InOutException {
		try {
			return (CryptoKey) ((JSONObject) HttpUtil.get(url)).get("CryptoKey");
		} catch (JSONException e) {
			throw new JsonException("We got a different object than we expected", e.getCause());
		} catch (InOutException e) {
			throw new InOutException("You do not have the premission to get this information", e.getCause());
		} catch (HttpResException e) {
			throw new HttpResException("Connection to server went wrong", e.getCause());
		}

	}
}