package Agent.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import Agent.exceptions.HttpResException;
import Agent.exceptions.InOutException;
import Agent.exceptions.JsonException;

public class HttpUtil {
	private static int httpCode;

	private static boolean isSucceed(int statusCode) {

		return (statusCode >= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES);

	}

	private static boolean isServerFault(int statusCode) {
		return (statusCode >= HttpStatus.SC_INTERNAL_SERVER_ERROR);
	}

	public static Object get(String urlToRead) throws InOutException, HttpResException, JsonException {
		StringBuilder sb = new StringBuilder();
		HttpURLConnection conn = null;
		URL url;

		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			httpCode = conn.getResponseCode();
		} catch (IOException e) {
			throw new InOutException("You do not have enough permissions to connect this page", e);
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
				throw new InOutException("You do not have enough permissions to view this page", e);
			} catch (JSONException e) {
				throw new JsonException("Object did not recieve during the process ", e);
			}

		} else if (isServerFault(httpCode)) {
			throw new HttpResException("We are dealing with some server issues now");
		} else {
			throw new HttpResException("Page does not exist");
		}
	}
}
