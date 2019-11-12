package com.kontia.client.apache43;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("rawtypes")
public class MyResponseHandler implements ResponseHandler {

	public Object handleResponse(HttpResponse response)
			throws ClientProtocolException, IOException {
		System.out.println(response.getStatusLine());
		HttpEntity entity1 = response.getEntity();
		System.out.println("Response Start");
		System.out.println(EntityUtils.toString(entity1));
		System.out.println("Response End");
		return entity1;

	}

}
