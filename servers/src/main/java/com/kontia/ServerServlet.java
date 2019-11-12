package com.kontia;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

@SuppressWarnings("serial")
public class ServerServlet extends HttpServlet{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			int sleep = new Random().nextInt(30)+1200;
			System.out.println("Sleeping for "+sleep);
			processRequest(req, sleep);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setStatus(HttpStatus.OK_200);
		resp.getWriter().println("Success Request/Response");
	}

	private void processRequest(HttpServletRequest req, int s) {
		Enumeration<String> headers = req.getHeaderNames();
		while(headers.hasMoreElements()) {
			String header = (String) headers.nextElement();
		    System.out.println(header+" : "+req.getHeader(header));
		}
	}
}
