package com.sanchev.servlets;


import com.sanchev.accountServer.AccountServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomePageServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(HomePageServlet.class.getName());
    public static final String PAGE_URL = "/home";
    private final AccountServer accountServer;

    public HomePageServlet(AccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        String remove = request.getParameter("remove");

        if (remove != null) {
            accountServer.removeUser();
            response.getWriter().println("Goodbye!");
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        int limit = accountServer.getUsersLimit();
        int count = accountServer.getUsersCount();

        LOGGER.info("Limit: {}. Count: {}", limit, count);
        if (limit > count) {
            LOGGER.info("User pass");
            accountServer.addNewUser();
            response.getWriter().println("Hello!");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            LOGGER.info("User were rejected");
            response.getWriter().println("Server is closed for maintenance!");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}