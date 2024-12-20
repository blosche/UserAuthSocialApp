package com.tco.requests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.misc.SQLQueryStatementConstructor;
import com.tco.database.Database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnregisterUserRequest extends Request{

    private static final transient Logger log = LoggerFactory.getLogger(ConfigRequest.class);
    
    String username;
    boolean responseStatus = false;
    public UnregisterUserRequest() {
    }
    public void buildResponse() {
        String sql = String.format("DELETE FROM User WHERE Username = '%s';", username);
        Database data = new Database();
        responseStatus = data.updateTable(sql);
        log.trace("buildResponse -> {}", this);
    }
    //testing
    public void setUsername(String username) {
        this.username = username;
    }
}
