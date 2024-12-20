package com.tco.misc;

import java.util.ArrayList;
import java.lang.String;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import com.tco.misc.SQLQueryStatementConstructor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSQLQueryStatementConstructor {
    public TestSQLQueryStatementConstructor() {

    }
    @Test
    @DisplayName("Brent: test query Statement Constructor")
    public void testSQLConstructor() {
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("Email");
        columns.add("Username");
        String table = "User";
        String conditional = "Email";
        String match = "Dave@Davemail.com";
        String statement = SQLQueryStatementConstructor.generateSQLQueryCommand(columns, table, conditional, match);
        assertEquals("SELECT Email, Username FROM User WHERE Email = \"Dave@Davemail.com\";", statement);
        columns.add("Password");
        assertEquals("SELECT Email, Username, Password FROM User WHERE Email = \"Dave@Davemail.com\";", SQLQueryStatementConstructor.generateSQLQueryCommand(columns, table, conditional, match));
    }
    @Test
    @DisplayName("Brent: test insertion statement constructor")
    public void testInsertConstructor() {
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("brentMan");
        columns.add("Brentman@dave.com");
        columns.add("Brent's Bees");
        String table = "User";
        String statement = SQLQueryStatementConstructor.generateSQLInsertCommand(columns, table);
        assertEquals("INSERT INTO User VALUES ('brentMan', 'Brentman@dave.com', 'Brent's Bees');", statement);
    }
}