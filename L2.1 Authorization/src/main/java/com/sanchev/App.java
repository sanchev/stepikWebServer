package com.sanchev;

import com.sanchev.accounts.AccountService;
import com.sanchev.accounts.UserProfile;
import com.sanchev.servlets.SessionServlet;
import com.sanchev.servlets.UserServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class App {
	
	public static void main(String[] args) throws Exception {

		AccountService accountService = new AccountService();

		accountService.addNewUser(new UserProfile("admin"));
		accountService.addNewUser(new UserProfile("test"));

		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContextHandler.addServlet(new ServletHolder(new UserServlet(accountService)), "/api/v1/users");
		servletContextHandler.addServlet(new ServletHolder(new SessionServlet(accountService)), "/api/v1/sessions");

		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setResourceBase("public_html");

		HandlerList handlerList = new HandlerList();
		handlerList.setHandlers(new Handler[]{resourceHandler, servletContextHandler});

		Server server = new Server(8080);
		server.setHandler(handlerList);

		server.start();
		server.join();
	}
}