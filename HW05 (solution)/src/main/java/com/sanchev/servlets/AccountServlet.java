package com.sanchev.servlets;

import com.sanchev.accountServer.AccountServer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AccountServlet extends HttpServlet {
	public static final String PAGE_URL = "/admin";
	private AccountServer accountServer;
	
	public AccountServlet(AccountServer accountServer) {
		this.accountServer = accountServer;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(accountServer.getUsersLimit());
		response.setStatus(HttpServletResponse.SC_OK);
	}
}