package com.tco.requests;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.String;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import com.tco.misc.SQLQueryStatementConstructor;
import com.tco.database.Database;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUnregisterUserRequest {
    Database database;
    public TestUnregisterUserRequest() {
        database = new Database();
    }

    @Test
    @DisplayName("Brent: Test Deletion of User from Database")
    public void testDeletion() {
        ArrayList<String> values = new ArrayList<String>(Arrays.asList("Testing", "Testing@testing.com", "YourMom"));
        String table = "User";
        String sql = SQLQueryStatementConstructor.generateSQLInsertCommand(values, table);
        database.updateTable(sql);
        assertEquals(database.getColumnCount("Email", "Testing@testing.com"), 1);
        UnregisterUserRequest request = new UnregisterUserRequest();
        request.setUsername("Testing");
        request.buildResponse();
        assertEquals(database.getColumnCount("Email", "Testing@testing.com"), 0);
    }
}
