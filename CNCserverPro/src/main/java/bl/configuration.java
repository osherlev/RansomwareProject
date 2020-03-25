package bl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath=application.properties")
public class configuration {

	
	public File configFileMaker() {

		FileWriter writer = null;
		File configFile = new File(".\\src\\main\\resources\\application.properties");
		Properties prop = new Properties();
		prop.setProperty("db.url", "localhost");
		prop.setProperty("db.user", "root");
		prop.setProperty("db.password", "root");
		prop.setProperty("algorithms.arr", "AES,Blowfish,twofish,DESes");
		
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
