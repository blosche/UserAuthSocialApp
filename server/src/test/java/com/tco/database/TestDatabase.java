package com.tco.database;
import com.tco.requests.CreateUserRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import com.tco.misc.SQLQueryStatementConstructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import com.tco.database.Database;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class TestDatabase {

    private String sql = "SHOW tables;";
    public Database database = new Database();

    @Test
    @DisplayName("jibarra1: Successfully sends query")
    public void testSendQuery() {
        ArrayList<String> dummy = new ArrayList<String>();
        database.sendQuery(sql, dummy);
    }
    @Test
    @DisplayName("Brent: test connection, parser, and statement constructor")
    public void master() {
        ArrayList<String> columns = new ArrayList<String>();
        HashMap<String,ArrayList<String>> returnedMap;
        columns.add("User.Username");
        columns.add("User.Email");
        columns.add("User.Password");
        //columns.add("*");
        String sqlStatement = SQLQueryStatementConstructor.generateSQLQueryCommand(columns, "User", "", "");
        //assertEquals(sqlStatement, " ");
        returnedMap = database.sendQuery(sqlStatement, columns);
        assertTrue(returnedMap.get("User.Email").contains("Dummy"));
    }
    @Test
    @DisplayName("Brent: test email count method")
    public void testEmailCount() {
        String exists = "Dummy";
        String unique = "Dummies";
        assertEquals(0, database.getColumnCount("Email", unique));
        assertEquals(1, database.getColumnCount("Email", exists));
    }
    @Test
    @DisplayName("Brent: Test insertion")
    public void testDatabaseInsertion() {
        ArrayList<String> values = new ArrayList<String>(Arrays.asList("Testing", "Testing@testing.com", "YourMom"));
        String table = "User";
        String sql = SQLQueryStatementConstructor.generateSQLInsertCommand(values, table);
        database.updateTable(sql);
        assertTrue(database.getColumnCount("Email","Testing@testing.com") >= 1);
    }
    @Test
    @DisplayName("Corwin: Test email uniqueness")
    public void createUserRequestTest(){
        
        CreateUserRequest init = new CreateUserRequest("Init", "Initial@Yes.com", "hashedPassword");
        String table = "User";
        init.buildResponse();
        CreateUserRequest notInit = new CreateUserRequest("NotInit", "Initial@Yes.com", "Ihaveanose");
        notInit.buildResponse();

        assertEquals(1, database.getColumnCount("Email","Initial@Yes.com"));
    }

}