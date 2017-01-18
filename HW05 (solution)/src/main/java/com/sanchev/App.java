package com.sanchev;

import com.sanchev.accountServer.AccountServer;
import com.sanchev.accountServer.AccountServerImpl;
import com.sanchev.accountServer.AccountServerController;
import com.sanchev.accountServer.AccountServerControllerMBean;

import com.sanchev.servlets.AccountServlet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class App {
	public static void main(String[] args) throws Exception {
		AccountServer accountServer = AccountServerImpl.getInstance();
		
		AccountServerControllerMBean accountServerController = new AccountServerController(accountServer);
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = new ObjectName("Admin:type=AccountServerController");
		mBeanServer.registerMBean(accountServerController, name);
		
		ServletContextHandler servletContextHandler = new ServletContextHandler();
		servletContextHandler.addServlet(new ServletHolder(new AccountServlet(accountServer)), AccountServlet.PAGE_URL);
		
		Server server = new Server(8080);
		server.setHandler(servletContextHandler);
		
		server.start();
		System.out.println("Server started");
		server.join();
	}
}