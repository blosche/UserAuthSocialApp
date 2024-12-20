package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import com.tco.requests.AuthenticationRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAuthenticationRequest {

    public TestAuthenticationRequest() {
    }
    @Test
    @DisplayName("brent135: check if assignment is non-null")
    public void testVariableAssignment() {
        AuthenticationRequest t = new AuthenticationRequest();
        t.setPassword("Dave");
        t.setUsername("420Dave");
        assertEquals(t.password, "Dave");
        assertEquals(t.username, "420Dave");
    }
    @Test
    @DisplayName("brent135: check if authenticatedUser method functions")
    public void testAuthenticateUser() {
        AuthenticationRequest t = new AuthenticationRequest();
        assertEquals(t.callAuthenticate("Dummy", "Dummy@Dummy.com"), true);
    }
}
