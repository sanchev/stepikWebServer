package com.sanchev;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class App {
	
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);

		server.setHandler(handler);

		HttpServlet servlet =  new HttpServlet() {
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				resp.getWriter().println(req.getParameter("key"));
			}
		};

		handler.addServlet(new ServletHolder(servlet), "/mirror");

		server.start();

		System.out.println("Server started");

		server.join();
	}
	
}