package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCreateUserRequest {
    private CreateUserRequest testRequest;
    public TestCreateUserRequest() {
        testRequest = new CreateUserRequest("dave", "dave@dave.org", "@#$%!139()");
    }
    @Test
    @DisplayName("brent135: check if assignment is null")
    public void testVariableAssignment() {
        assertEquals(testRequest.getUsername(), "dave");
        assertEquals(testRequest.getEmail(), "dave@dave.org");
        assertEquals(testRequest.getPassword(), "@#$%!139()");
    }
}
