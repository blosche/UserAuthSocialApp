package com.tco.server;
import java.util.List;
import java.util.ArrayList;
import java.lang.Thread;
import spark.Session;
import com.tco.server.UserSession;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.Deque;
public class SessionManager {
    private Deque<UserSession> authenticatedSessions;
    Thread currentRemover;
    public SessionManager() {
        authenticatedSessions = new ConcurrentLinkedDeque<>();
        startMonitor();
    }
    public void startMonitor() {
        Thread remover = new SessionRemover(authenticatedSessions);
        currentRemover = remover;
        remover.start();
    }
    public void addSession(UserSession session){
        authenticatedSessions.offerFirst(session);
    }
    public boolean threadValid() {
        if (currentRemover.isAlive()) {
            return true;
        }
        return false;
    }
    
}
