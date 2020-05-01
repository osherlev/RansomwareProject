package Agent.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.hibernate.Hibernate;

import Agent.exceptions.InOutException;

public class ConfigureProps {
	private static Properties properties;

	public static String getPropsValue(String key) throws InOutException {

		try (InputStream is = ConfigureProps.class.getResourceAsStream("/application.properties");) {
			properties = new Properties();
			if (!(Hibernate.isInitialized(properties))) {
				properties.load(is);
			}
			return properties.getProperty(key);
		} catch (IOException e) {
			throw new InOutException("Could not find properties file", e);
		}
	}
}
