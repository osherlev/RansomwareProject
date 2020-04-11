package server.networking;

import java.io.IOException;
import java.net.Socket;

public class Monitor {

	public synchronized void waitForMyTurn(Socket s) {
		while (s.isConnected()) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
	}

	public synchronized void imDone(Socket s) {
		try {
			s.close();
		} catch (IOException e) {

		}
		notifyAll();
	}
}