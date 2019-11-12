package com.kontia.client.springrest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.AsyncClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRequestCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.ResponseExtractor;

import com.kontia.client.Client;

public class SpringRestTemplateClient implements Client{

	public String makeSyncHttpCall() throws Exception {

		System.err.println("Spring Rest Template Sync client not yet implemented switching to async");
		return makeAsyncHttpCall();
	}

	public String makeAsyncHttpCall() throws Exception {
		AsyncRestTemplate asycTemp = new AsyncRestTemplate();
		String url = "http://localhost:8080/hello";
		HttpMethod method = HttpMethod.GET;

		AsyncRequestCallback requestCallback = new AsyncRequestCallback() {
			public void doWithRequest(AsyncClientHttpRequest arg0) throws IOException {
				System.out.println(arg0.getURI());
			}
		};

		ResponseExtractor<String> responseExtractor = new ResponseExtractor<String>() {
			public String extractData(ClientHttpResponse arg0) throws IOException {
				return String.valueOf(arg0.getStatusCode().value());
			}
		};
		Map<String, String> urlVariable = new HashMap<String, String>();
		urlVariable.put("q", "Concretepage");
		ListenableFuture<String> future = asycTemp.execute(url, method, requestCallback, responseExtractor,
				urlVariable);
		try {
			// waits for the result
			String result = future.get();
			return result;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}
}
