package com.sanchev;

import com.sanchev.accountServer.AccountServer;
import com.sanchev.accountServer.AccountServerController;
import com.sanchev.accountServer.AccountServerControllerMBean;
import com.sanchev.accountServer.AccountServerImpl;
import com.sanchev.servlets.HomePageServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class App {
	private static final Logger LOGGER = LogManager.getLogger(App.class.getName());

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			LOGGER.error("Use port as the first argument");
			System.exit(1);
		}

		int port = Integer.parseInt(args[0]);

		LOGGER.info(String.format("Starting at http://127.0.0.1:%d", port));

		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setDirectoriesListed(true);
		resourceHandler.setResourceBase("public_html");

		AccountServer accountServer = new AccountServerImpl(1);

		AccountServerControllerMBean accountServerController = new AccountServerController(accountServer);
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = new ObjectName("ServerManager:type=AccountServerController");
		mBeanServer.registerMBean(accountServerController, name);

		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContextHandler.addServlet(new ServletHolder(new HomePageServlet(accountServer)), HomePageServlet.PAGE_URL);

		HandlerList handlerList = new HandlerList();
		handlerList.setHandlers(new Handler[] {resourceHandler, servletContextHandler});

		Server server = new Server(8080);
		server.setHandler(handlerList);

		server.start();
		LOGGER.info("Server started");
		server.join();
	}
}