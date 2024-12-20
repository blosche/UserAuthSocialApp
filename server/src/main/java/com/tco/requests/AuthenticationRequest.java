package com.tco.requests;
import com.tco.requests.Request;
import com.tco.server.UserSession;
import com.tco.server.SessionManager;
import spark.Session;
import com.tco.database.Database;
import com.tco.misc.SQLQueryStatementConstructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.*;

public class AuthenticationRequest extends Request {


    private static final transient Logger log = LoggerFactory.getLogger(AuthenticationRequest.class);
    String username;
    String password;
    String token;
    boolean validated;


    private transient SessionManager manager;
    public transient Session authenticatedSession;
    
    public AuthenticationRequest() {
        
    }

    public void buildResponse() {
        //perform lookup, if valid then call SessionManager.add(authenticatedSession)
        //then conditionally check if thread isValid and if not then call SessionManager.startMonitor();
        //this step makes sure there is only one thread working on the synchronized list at a time, and also allows
        //the remover thread to terminate if the list is empty.
        //The result is that we only have an active session list if valid sessions are present on the server.
        //and we only ever spawn a thread when an authenticated request arrives and the list is empty.
        boolean isValid = authenticateUser(this.username, this.password);
        
        // log.info("Validated {} ", isValid);

        if (isValid) {
            this.token = authenticatedSession.id();
            UserSession userSession = new UserSession(this.username, this.authenticatedSession);
            this.manager.addSession(userSession);

            if(!manager.threadValid()){
                manager.startMonitor();
            }
            this.validated = true;
        }
        else {
            this.validated = false;
        }
        log.info("Validated {} ", this.validated);
    }

    private boolean authenticateUser(String username, String password) {
        //Implement authentication logic - ie. perform database lookup for username/password
        //Return true if authentication is successful, false otherwise
        ArrayList<String> values = new ArrayList<String>(Arrays.asList("User.Username", "User.Password"));
        Database database = new Database();
        String command = SQLQueryStatementConstructor.generateSQLQueryCommand(values, "User", "Username", username);
        HashMap<String, ArrayList<String>> map = database.sendQuery(command, values);
        if (map.get("User.Password").contains(password)) {
            return true;
        }
        return false;
    }
    public void setSessionManager(SessionManager manager) {
        this.manager = manager;
    }
    //Tests
    public boolean callAuthenticate(String username, String password) {
        return authenticateUser(username, password);
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
