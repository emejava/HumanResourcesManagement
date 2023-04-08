package com.humanresourcesmanagement.controller.websocket;

import com.humanresourcesmanagement.controller.session.SessionManager;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;

@ServerEndpoint(value = "/websocketendpoint", configurator = GetHttpSessionConfigurator.class)
public class WsServer {
    @OnOpen
    public void open(Session session, EndpointConfig config) {
        HttpSession httpSession = (HttpSession) session.getUserProperties()
                .get(HttpSession.class.getName());
        SessionManager.addWebSocketSession(httpSession, session);
    }

    @OnMessage
    public String onMessage(String message) {
//        wsSession.getBasicRemote().sendText(msg);
        System.out.println("Message from the client: " + message);
        String echoMsg = "Echo from the server : " + message;
        return echoMsg;
    }

    public static void send(HttpSession httpSession, String msg) throws IOException {
        SessionManager.getWebSocketSession(httpSession).getBasicRemote().sendText(msg);
    }


    public static void broadcast(String message) throws IOException {
        for (Session webSocketSession : SessionManager.getWebSocketSessions()) {
            webSocketSession.getBasicRemote().sendText(message);
        }
    }

    @OnClose
    public void close(Session session) throws IOException {
        SessionManager.invalidateHttpSession((HttpSession) session.getUserProperties()
                .get(HttpSession.class.getName()));
    }
}
