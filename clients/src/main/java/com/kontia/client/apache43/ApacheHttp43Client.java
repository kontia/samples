package com.kontia.client.apache43;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.kontia.client.Client;


/**
 * Hello world!
 *
 */
public class ApacheHttp43Client implements Client{
	
	public String makeSyncHttpCall() throws ClientProtocolException, IOException, URISyntaxException {
		
		String uri = "http://localhost:8080/hello";
		System.out.println("Sending:> "+uri);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);
		/*// 1st Implementation 
		ResponseHandler myrspHandler = new MyResponseHandler();
		Object response1 = httpclient.execute(httpGet, myrspHandler);
		System.out.println(response1.getClass().getName());*/
		// 2st Implementation 
		CloseableHttpResponse response1 = httpclient.execute(httpGet);
		String response = null;
		try {
			response = response1.getStatusLine().toString();
			System.out.println("Response Received:Status: "+response);
		} finally {
			response1.close();
		}
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return response;
	}

	public String makeAsyncHttpCall() throws Exception {
		throw new UnsupportedOperationException("Apache 4.3 async http client support not yet added");
	}

	
	
}
