package com.kontia.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kontia.client.apache43.ApacheHttp43Client;
import com.kontia.client.homegrown.HomeGrownHttpClient;
import com.kontia.client.hystrix.HystrixClient;
import com.kontia.client.jdk.JDKDefaultHttpClient;
import com.kontia.client.springrest.SpringRestTemplateClient;


/**
 * @author vikashkumar
 *
 */
public class ClientServlet extends HttpServlet{

	Map<String, Object> tm = new HashMap<String, Object>();
	private static final long serialVersionUID = 1L;
	
	private static final String APACHE_43 = "apache43";
	private static final String URLCONNECTION = "urlconnection";
	private static final String HYSTRIX = "hystrix";
	private static final String RESTTEMPLATE = "resttemplate";
	private static final String HOMEGROWN = "homegrown";

	String statusCode = null ;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Client client = null;
		String responseCode = null;
		
		try {
			String exitClientType = req.getParameter("client");
			String multiThreaded  = req.getParameter("multithreaded");
			System.out.println("******HTTP Exit Client: "+ exitClientType+" MultiThreaded = "+multiThreaded+" *******");
			
			if(exitClientType.equals(APACHE_43)) {
				client = new ApacheHttp43Client();
			}
			if(exitClientType.equals(URLCONNECTION)) {
				client = new JDKDefaultHttpClient();
			}
			if(exitClientType.equals(HYSTRIX)) {
				client = new HystrixClient();
			}
			if(exitClientType.equals(RESTTEMPLATE)) {
				client = new SpringRestTemplateClient();
			}
			if(exitClientType.equals(HOMEGROWN)) {
				client = new HomeGrownHttpClient();
			}
			if(client != null) {
				
				if(multiThreaded != null && Boolean.getBoolean(multiThreaded)) {
					responseCode = makeCallInThread(client);
				}else {
					responseCode = makeSyncCall(client);
				}
			}

			if(responseCode != null) {
				resp.getWriter().println("Downsteam Response: "+responseCode);
			}
			resp.setStatus(200);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String makeSyncCall(Client client) {
		try {
			return  client.makeSyncHttpCall();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 

	}

	private String makeCallInThread(final Client client) {
		
		Runnable r = new Runnable() {
			
			public void run() {
				System.out.println(this.getClass().getName());
				try {
					statusCode = client.makeSyncHttpCall();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		Thread t = new Thread(r);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return statusCode;
	}

}
