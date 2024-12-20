package com.tco.server;
import java.lang.System;
import spark.Session;
public class UserSession {
    public String User;
    public Session session;
    public final long validSessionTimeSeconds = 3600;
    public UserSession(String User, Session session) {
        this.User = User;
        this.session = session;
    }
    public boolean isValid() {
        if ((System.currentTimeMillis() - session.creationTime()) / 1000 < validSessionTimeSeconds) {
            return false;
        } 
        return true;
    }
    public long timeToDeletion() {
       return (validSessionTimeSeconds - ((System.currentTimeMillis() - session.creationTime()) / 1000));
    }
    public void invalidateSession() {
        session.invalidate();
    }
}
