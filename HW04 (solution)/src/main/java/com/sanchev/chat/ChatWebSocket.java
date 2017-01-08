package com.sanchev.chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@SuppressWarnings("UnusedDeclaration")
@WebSocket
public class ChatWebSocket {
    private ChatService chatService;
    private Session session;

    ChatWebSocket(ChatService chatService) {
        this.chatService = chatService;
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        chatService.add(this);
        this.session = session;
    }

    @OnWebSocketClose
    public void onClose(int StatusCode, String reason) {
        chatService.remove(this);
    }

    @OnWebSocketMessage
    public void OnMessage(String message) {
        chatService.sendMessage(message);
    }

    void sendString(String message) {
        try {
            session.getRemote().sendString(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}