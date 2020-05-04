package Agent.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import Agent.exceptions.InOutException;

public class ConfigureProps {
	private static Optional<Properties> properties;

	public static String getPropsValue(String key) throws InOutException {
		if (!(Optional.ofNullable(properties).isPresent())) {
			try (InputStream is = ConfigureProps.class.getResourceAsStream("/application.properties");) {
				{
					properties = Optional.ofNullable(new Properties());
					properties.get().load(is);

				}
			} catch (IOException e) {
				throw new InOutException("Could not find properties file", e);
			}
		}
		return properties.get().getProperty(key);
	}
}
