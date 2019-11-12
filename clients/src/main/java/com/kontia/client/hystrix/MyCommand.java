package com.kontia.client.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyCommand extends HystrixCommand<String> {
	
	public MyCommand(String key) {
		super(HystrixCommandGroupKey.Factory.asKey(key));
	}

	@Override
	protected String run() throws Exception {
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
			return response.toString();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.disconnect();
		}
		return null;
	}

}
