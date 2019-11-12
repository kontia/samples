package com.kontia.client.jdk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.kontia.client.Client;

public class JDKDefaultHttpClient implements Client{

	public String makeSyncHttpCall() throws Exception {
		HttpURLConnection connection = null;
		try {
			URL url = new URL("http://localhost:8080/hello");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setDoInput(true);

			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return String.valueOf(connection.getResponseCode());
		}catch (Exception e) {
		}finally {
			connection.disconnect();
		}

		return null;
	}

	public String makeAsyncHttpCall() throws Exception {
		throw new UnsupportedOperationException("Async HTTPURLConnection is not supported");
	}

}
