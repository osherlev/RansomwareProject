package Agent;

import Agent.exceptions.RansomwareException;
import Agent.ransomGUI.RansomGUI;

public class RansomApplication {

	public static void main(String[] args) throws RansomwareException {
		RansomGUI ransomProcess = new RansomGUI();
		try {
			ransomProcess.start();
		} catch (RansomwareException e) {
			throw new RansomwareException("Ransomware process has failed");
		}

	}

}
