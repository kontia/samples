package com.kontia.client.hystrix;

import java.util.concurrent.Future;

import com.kontia.client.Client;


public class HystrixClient implements Client{
	

	public String makeAsyncHttpCall() throws Exception {
		Thread.sleep(20);
		Future<String> myCommand = new MyCommand("appdcs").queue();
		String result = myCommand.get();
		return result;
	}

	public String makeSyncHttpCall() throws Exception {
		System.err.println("Hystrix sync is not support switching to async");
		return makeAsyncHttpCall();
	}
}
