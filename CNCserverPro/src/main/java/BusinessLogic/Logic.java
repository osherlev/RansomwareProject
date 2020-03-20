package BusinessLogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Properties;

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
		return (prop.getProperty("algorithm." + a));

	}

	private File configFileMaker() {
		File configFile = new File("./config.properties");
		FileWriter fr = null;
		try {
			fr = new FileWriter(configFile);
			fr.write("algorithm.0=AES\n" + "algorithm.1=Blowfish\n" + "algorithm.2=twofish\n" + "algorithm.3=DESes");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			// close resources
			try {
				fr.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return configFile;
	}

}
