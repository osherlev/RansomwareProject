package Agent.Attack;

import java.io.File;

import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.entites.CryptoKey;
import Agent.exceptions.RansomwareException;

public interface Cryptable {


public void doingCrypto(CryptoKey key,File file,CryptoAlgorithm crypto)throws RansomwareException;



}
