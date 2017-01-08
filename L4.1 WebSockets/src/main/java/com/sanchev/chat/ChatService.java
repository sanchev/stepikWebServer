package com.sanchev.chat;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class ChatService {
    private Set<ChatWebSocket> chatWebSocketSet;

    ChatService() {
        this.chatWebSocketSet = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    void add(ChatWebSocket chatWebSocket) {
        chatWebSocketSet.add(chatWebSocket);
    }

    void remove(ChatWebSocket chatWebSocket) {
        chatWebSocketSet.remove(chatWebSocket);
    }

    void sendMessage(String message) {
        for (ChatWebSocket user : chatWebSocketSet) {
            try {
                user.sendString(message);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}