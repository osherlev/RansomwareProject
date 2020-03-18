package EncryptionAlgo;

import java.io.File;
import javax.crypto.SecretKey;

public interface EncryptionCodec<T> {
	public abstract void decrypt(T key, File fileToDecrypt);

	@SuppressWarnings("hiding")
	public abstract <T> T encrypt(File fileToEncrypt);

	@SuppressWarnings("hiding")
	public abstract <T> SecretKey createKey();

}
