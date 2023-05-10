package com.humanresourcesmanagement.controller.session;

import com.humanresourcesmanagement.model.entity.User;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.util.*;


public class SessionManager {

    //  ----------CREATE-MAP-OF-HTTPSESSION-AND-SESSIONS---------
    public static Map<HttpSession, Session> sessionMap = new HashMap<>();


    //  METHOD-------ADD-HTTPSESSION-----------------------------
    public static void addHttpSession(HttpSession httpSession) {
        sessionMap.put(httpSession, null);
    }


    //  METHOD-------ADD-WEBSOCKET-SESSION-----------------------
    public static void addWebSocketSession(HttpSession httpSession, Session Session) {
        sessionMap.put(httpSession, Session);
    }

    //  METHOD-------VALIDATE-HTTPSESSION------------------------
    public static boolean validateHttpSession(HttpSession httpSession) {
        return sessionMap.containsKey(httpSession);
    }


    //  METHOD-------VALIDATE-WEBSOCKET-SESSION------------------
    public static boolean validateWebSocketSession(Session session) {
        return sessionMap.containsValue(session);
    }


    //  METHOD-------GET-WEBSOCKET-SESSION-----------------------
    public static Session getWebSocketSession(HttpSession httpSession) {
        return sessionMap.get(httpSession);
    }


    //  METHOD-------GET-HTTPSESSION-----------------------------
    public static Set<HttpSession> getHttpSessions() {
        return sessionMap.keySet();
    }


    //  METHOD-------GET-WEBSOCKET-SESSIONS----------------------
    public static Collection<Session> getWebSocketSessions() {
        return sessionMap.values();
    }


    //  METHOD-------GET-HTTP-AND-WEBSOCKET-SESSIONS-------------
    public static Map<HttpSession, Session> getSessionMap() {
        return sessionMap;
    }


    //  METHOD-------INVALIDATE-HTTPSESSION----------------------
    public static boolean invalidateHttpSession(HttpSession httpSession) {
        if (sessionMap.containsKey(httpSession)) {
            sessionMap.remove(httpSession);
            return true;
        }
        return false;
    }


    //  METHOD-------GET-USERS-BY-HTTPSESSION------------------------
    public static List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        for (HttpSession httpSession : sessionMap.keySet()) {
            userList.add((User) httpSession.getAttribute("user"));
        }
        return userList;
    }


    //  METHOD-------GET-HTTPSESSION-BY-USERNAME----------------------
    public static HttpSession findHttpSessionByUserName(String userName) {
        for (HttpSession httpSession : sessionMap.keySet()) {
            if (((User) httpSession.getAttribute("user")).getUsername().equals(userName)) {
                return httpSession;
            }
        }
        return null;
    }
}
