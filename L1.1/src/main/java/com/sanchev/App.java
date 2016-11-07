package com.sanchev;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class App {
	
	public static void main(String[] args) throws Exception {
		Frontend frontend = new Frontend();

		Server server = new Server(8080);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		server.setHandler(context);
		context.addServlet(new ServletHolder(frontend), "/*");

		server.start();
		server.join();
	}
	
}