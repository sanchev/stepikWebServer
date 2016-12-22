package com.sanchev;

import com.sanchev.accounts.AccountService;
import com.sanchev.servlets.SignInServlet;
import com.sanchev.servlets.SignUpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class App {
    public static void main(String[] args) {
        try {
            ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
            AccountService accountService = new AccountService();
            servletContextHandler.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
            servletContextHandler.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

            Server server = new Server(8080);
            server.setHandler(servletContextHandler);

            server.start();
            System.out.println("Server started");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}