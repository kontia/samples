package com.kontia.client.homegrown;

import java.util.HashMap;
import java.util.Map;


public class HttpRequest {
	
	private String host;
	
	private int port;
	
	private String path;
	
	private Map<String, String> headers= new HashMap<String, String>();

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setHeader(String name, String value) {
		this.headers.put(name, value);
	}
}
