package com.tco.requests;
import java.util.Arrays;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.misc.SQLQueryStatementConstructor;
import com.tco.database.Database;

public class CreateUserRequest extends Request {
    private static final transient Logger log = LoggerFactory.getLogger(CreateUserRequest.class);
    private String username;
    private String email;
    private String password;
    private String responseStatus = "";

    public CreateUserRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public void buildResponse() {
        Database data = new Database();
        ArrayList<String> values = new ArrayList<String>(Arrays.asList(username, email, password));
        int count = data.getColumnCount("Email", email);
        int count2 = data.getColumnCount("Username", username);
        String nope = "This email is already linked to an account.";

        if(count == 0 && count2 == 0){
            data.updateTable(SQLQueryStatementConstructor.generateSQLInsertCommand(values, "User"));
            responseStatus = "Account Created!";
            log.trace("buildResponse -> {}", this);
        }
        else if(count != 0 && count2 != 0){
            responseStatus = "Username and Email is already associated with an account";
            log.info(nope);
        }
        else if (count != 0 && count2 == 0){
            responseStatus = "Email is already associated with an account";
            log.info(nope);
        }
        else if (count == 0 && count2 != 0){
            responseStatus = "Username is already associated with an account";
            log.info(nope);
        }   
        else{
            responseStatus = "There has been an error, please try again";
            log.error("There has been an error checking the database.");
        }

    }
    //the following methods contain getters for testing
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword(){
        return password;
    }

}
