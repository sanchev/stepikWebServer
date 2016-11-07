package com.sanchev;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Frontend extends HttpServlet {

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariablesMap = new HashMap<String, Object>();
        pageVariablesMap.put("method", request.getMethod());
        pageVariablesMap.put("URL", request.getRequestURL().toString());
        pageVariablesMap.put("pathInfo", request.getPathInfo());
        pageVariablesMap.put("sessionId", request.getSession().getId());
        pageVariablesMap.put("parameters", request.getParameterMap().toString());
        return pageVariablesMap;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> pageVariablesMap = createPageVariablesMap(request);
        pageVariablesMap.put("message", "");

        response.getWriter().println(PageGenerator.getInstance().getPage("page.html", pageVariablesMap));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariablesMap = createPageVariablesMap(request);

        String message = request.getParameter("message");

        response.setContentType("text/html;charset=utf-8");

        if (message == null || message.isEmpty())
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        else
            response.setStatus(HttpServletResponse.SC_OK);

        pageVariablesMap.put("message", message == null ? "" : message);

        response.getWriter().println(PageGenerator.getInstance().getPage("page.html", pageVariablesMap));
    }
	
}