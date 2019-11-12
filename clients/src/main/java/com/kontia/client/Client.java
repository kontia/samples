package com.kontia.client;

public interface Client {
	
	String makeSyncHttpCall() throws Exception;
	
	String makeAsyncHttpCall() throws Exception;

}
