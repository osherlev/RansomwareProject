package server.networking;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Server {
	@Value("${server.port}")
	private int port;
	@Value("${server.host}")
	private String host;

	public synchronized void connectClients() throws IOException {
	

		ServerSocket server = new ServerSocket(port);
		
		// running infinite loop for getting
		// client request
		while (true) {
			Socket client = null;

			try {
				client = server.accept();

				System.out.println("A new client is connected : " + client);
				ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
				System.out.println("Assigning new thread for this client");

				// create a new thread object
				Thread t = new ClientHandler(client, os);

				// Invoking the start() method
				t.start();
			} catch (IOException e) {

				server.close();
				throw new IOException();
			} catch (Exception e) {

			}
		}
	}
}