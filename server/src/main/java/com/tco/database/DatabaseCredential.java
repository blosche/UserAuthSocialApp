package com.tco.database;


public class DatabaseCredential {
final static String USER = System.getenv("DB_USERNAME");
final static String PASSWORD = System.getenv("DB_PASSWORD");

    static String url(){
        String useTunnel = System.getenv("CS414_USE_DATABASE_TUNNEL");
        String onDocker = System.getenv("CS414_DOCKER");
        String dburl;
        // Note that if the variable isn't defined, System.getenv will return null
        if(useTunnel != null && useTunnel.equals("true")) {
            dburl = "jdbc:mariadb://127.0.0.1:56247/cs414_team63";
        }
        else if(onDocker != null && onDocker.equals("true")) {
            dburl = "jdbc:mariadb://127.0.0.1:3306/cs414_team63";
        }
        else {
            dburl = "jdbc:mariadb://faure.cs.colostate.edu/cs414_team63";
        }
    
        return dburl;
    }
}