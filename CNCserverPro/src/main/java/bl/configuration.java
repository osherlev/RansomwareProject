package bl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class configuration {
	public File configFileMaker() {

		File configFile = new File("./application.properties");
		Properties prop = new Properties();
		// prop.setProperty("algorithm.0", "AES");
		// prop.setProperty("algorithm.1", "Blowfish");
		// prop.setProperty("algorithm.2", "twofish");
		// prop.setProperty("algorithm.3", "DESes");
		prop.setProperty("algorithms.arr", "AES,Blowfish,twofish,DESes");
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
}
