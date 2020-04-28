package Agent.entites;

import javax.crypto.SecretKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class CryptoKey {

	String ip;
	SecretKey _key;
	String algorithm;

}