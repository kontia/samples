package com.kontia;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.kontia.client.ClientServlet;

/**
 * @author vikashkumar
 */
public class StartApp 
{
	public static void main( String[] args )
	{
		Server server = new Server(8081);
		ServletContextHandler handler = new ServletContextHandler(server, "/client/*");
		handler.addServlet(ClientServlet.class, "/");

		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

/*

Test URL:
http://localhost:8081/client?client=urlconnection&multithreaded=true

http://localhost:8081/client?client=apache43&multithreaded=true

http://localhost:8081/client?client=resttemplate&multithreaded=true

http://localhost:8081/client?client=resttemplate&multithreaded=true

*/