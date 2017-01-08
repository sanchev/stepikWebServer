package com.sanchev;

import com.sanchev.chat.WebSocketChatServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class App {
	public static void main(String[] args) throws Exception {
		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setDirectoriesListed(true);
		resourceHandler.setResourceBase("public_html");

		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContextHandler.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");

		HandlerList handlerList = new HandlerList();
		handlerList.setHandlers(new Handler[] {resourceHandler, servletContextHandler});

		Server server = new Server(8080);
		server.setHandler(handlerList);

		server.start();
		server.join();
	}
}