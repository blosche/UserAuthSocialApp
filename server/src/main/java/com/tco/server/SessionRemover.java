package com.tco.server;
import java.util.List;
import java.util.LinkedList;
import java.lang.Thread;
import spark.Session;
import java.util.Deque;
public class SessionRemover extends Thread {
    Deque<UserSession> authenticatedSessions;
    public SessionRemover(Deque<UserSession> authenticatedSessions) {
        this.authenticatedSessions = authenticatedSessions;
    }
    public void run() {
        while(authenticatedSessions.size() != 0) {
            try {
                Thread.sleep(authenticatedSessions.peekLast().timeToDeletion());
             }
                catch(Exception e) {
            }
            if (!authenticatedSessions.peekLast().isValid()) {
                synchronized(authenticatedSessions) {
                    authenticatedSessions.pollLast().invalidateSession();
            }
        }
        }
    }

}
