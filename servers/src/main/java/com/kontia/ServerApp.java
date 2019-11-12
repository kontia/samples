package com.kontia;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * @author vikashkumar
 *
 */
public class ServerApp 
{
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		ServletContextHandler handler = new ServletContextHandler(server, "/hello/*");
		handler.addServlet(ServerServlet.class, "/");
		System.out.println("Server Started!");
		server.start();
	}
}
