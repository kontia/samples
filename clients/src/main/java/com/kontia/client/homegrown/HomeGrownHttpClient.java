package com.kontia.client.homegrown;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import com.kontia.client.Client;

public class HomeGrownHttpClient implements Client{

	public HomeGrownHttpClient() {
	}

	public String execute(HttpRequest httpRequest) {
		String responseCode = null;
		try {
			Socket clientSocket = null;
			clientSocket = new Socket(httpRequest.getHost(), httpRequest.getPort());

			PrintWriter request = new PrintWriter(clientSocket.getOutputStream(),
					true);

			BufferedReader response = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));

			request.print("GET /" + httpRequest.getPath() + "/ HTTP/1.1\r\n"); // "+path+"
			request.print("Host: " + httpRequest.getHost() + "\r\n");
			request.print("Connection: close\r\n");
			addAdditionalHeaders(request, httpRequest);
			request.print("Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\r\n");
			request.print("\r\n");
			request.flush();
			System.out.println("Request Sent!");
			System.out.println("======================================");

			String responseLine;

			int i = 0;
			while ((responseLine = response.readLine()) != null) {
				if(i==0) {
					responseCode = responseLine;
					i++;
				}
				System.out.println(responseLine);
			}
			System.out.println("======================================");
			System.out.println("Response Recieved!!");
			System.out.println("======================================");

			response.close();
			request.close();
			clientSocket.close();
			return responseCode;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return responseCode;
	}

	private void addAdditionalHeaders(PrintWriter request, HttpRequest httpRequest) {

		Map<String, String> headers = httpRequest.getHeaders();

		for (Map.Entry<String,String> header : headers.entrySet())  {
			request.print(header.getKey()+": "+header.getValue()+"\r\n");
		}

	}

	public String makeSyncHttpCall() throws Exception {
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setHost("localhost");
		httpRequest.setPort(8080);
		httpRequest.setPath("hello");
		HomeGrownHttpClient homeGrownHttpClient = new HomeGrownHttpClient();
		return homeGrownHttpClient.execute(httpRequest);
	}

	public String makeAsyncHttpCall() throws Exception {
		throw new UnsupportedOperationException("Not yet supported");
	}

}
