package BusinessLogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Properties;

import keys.KeyManagement;

public class Logic {
	public String randomAlgorithm() {
		Properties prop = new Properties();
		FileInputStream is = null;
		try {
			is = new FileInputStream(configFileMaker().getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SecureRandom rand = new SecureRandom();
		int a = rand.nextInt(prop.size());
		System.out.println(a);
		return (prop.getProperty("algorithm." + a));

	}

	private File configFileMaker() {

		File configFile = new File("./config.properties");
		Properties prop = new Properties();
		prop.setProperty("algorithm.0", "AES");
		prop.setProperty("algorithm.1", "Blowfish");
		prop.setProperty("algorithm.2", "twofish");
		prop.setProperty("algorithm.3", "DESes");

		FileWriter writer = null;
		try {
			writer = new FileWriter(configFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			prop.store(writer, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return configFile;
	}

	public <T> void startProcess(String ip) {
		String chosenAlgo = randomAlgorithm();
		KeyManagement<T> km = new KeyManagement<T>();
		T key = km.createKey(chosenAlgo, ip); // Generates key and saves new entity in DB
	}
}
