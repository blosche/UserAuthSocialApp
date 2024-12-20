package com.tco.server;
import java.lang.Thread;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import com.tco.requests.AuthenticationRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.tco.server.SessionManager;
import com.tco.server.UserSession;
public class TestSessionManager {
    public void TestSessionManager() {

    }
    @Test
    @DisplayName("Test if thread finishes")
    public void testThread() {
        SessionManager manager = new SessionManager();
        try {
        Thread.sleep(1000);
        }
        catch(Exception e) {

        }
        assertEquals(manager.threadValid(), false);
        UserSession sess = new UserSession("dave", null);
        manager.addSession(sess);
    }
}
