package com.sanchev.servlets;

import com.sanchev.accountServer.AccountServer;
import com.sanchev.accountServer.AccountServerImpl;
import junit.framework.TestCase;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

public class HomePageServletTest extends TestCase {
    private AccountServer accountServer = mock(AccountServerImpl.class);

    @Test
    public void testRemove() throws Exception {
        HttpServletRequest request = getMockedRequest(HomePageServlet.PAGE_URL);
        when(request.getParameter("remove")).thenReturn("");

        final StringWriter stringWriter = new StringWriter();
        HttpServletResponse response = getMockedResponse(stringWriter);

        HomePageServlet homePageServlet = new HomePageServlet(accountServer);

        homePageServlet.doGet(request, response);

        assertEquals("Goodbye!", stringWriter.toString().trim());
        verify(accountServer, times(1)).removeUser();
    }

    private HttpServletRequest getMockedRequest(String pageUrl) {
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn(pageUrl);

        return request;
    }

    private HttpServletResponse getMockedResponse(StringWriter stringWriter) throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);

        final PrintWriter writer = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(writer);

        return response;
    }
}