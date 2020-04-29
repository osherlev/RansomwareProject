package Agent.entites;

import java.util.Map;

import Agent.EncryptionAlgo.AES;
import Agent.EncryptionAlgo.Blowfish;
import Agent.EncryptionAlgo.CryptoAlgorithm;
import Agent.EncryptionAlgo.DESede;
import Agent.EncryptionAlgo.Twofish;

public class AlgorithmsMap {
	private Map<String, CryptoAlgorithm> algorithmMap;

	public Map<String, CryptoAlgorithm> getMap() {
		algorithmMap.put("AES", new AES());
		algorithmMap.put("Blowfish", new Blowfish());
		algorithmMap.put("Twofish", new Twofish());
		algorithmMap.put("DESede", new DESede());

		return algorithmMap;
	}

}
