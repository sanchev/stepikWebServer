package com.sanchev.chat;

import org.eclipse.jetty.websocket.servlet.*;

public class WebSocketChatServlet extends WebSocketServlet {
    private final static int IDLE_TIMEOUT = 10 * 60 * 1000;
    private ChatService chatService;

    public WebSocketChatServlet() {
        this.chatService = new ChatService();
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(IDLE_TIMEOUT);
        factory.setCreator((req, resp) -> new ChatWebSocket(chatService));
    }
}