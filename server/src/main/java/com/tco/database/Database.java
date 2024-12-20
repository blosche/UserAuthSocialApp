package com.tco.database;

import com.tco.database.DatabaseCredential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Database{
    HashMap<String, ArrayList<String>> resultSetHashMap;
    public Database() {

    }
    public HashMap<String, ArrayList<String>> sendQuery(String sql, ArrayList<String> columns){
        try(
            // connect to the database and query
            Connection conn = DriverManager.getConnection(DatabaseCredential.url(), DatabaseCredential.USER, DatabaseCredential.PASSWORD);
            Statement query = conn.createStatement();
            ResultSet results = query.executeQuery(sql);
            )
            {

                resultSetHashMap = parse(columns, results);
                return resultSetHashMap;
            }catch (SQLException e){
                e.printStackTrace();
            }
            return resultSetHashMap;
    }
    public Integer getColumnCount(String column, String email) {
        String countEmail = String.format("SELECT COUNT(%s) FROM User WHERE %s = \"%s\";", column, column, email);
        try(
            // connect to the database and query
            Connection conn = DriverManager.getConnection(DatabaseCredential.url(), DatabaseCredential.USER, DatabaseCredential.PASSWORD);
            Statement query = conn.createStatement();
            ResultSet results = query.executeQuery(countEmail);
            )
            {
                results.next();
                Integer count = new Integer(results.getInt(1));
                return count;
            }catch (SQLException e){
                e.printStackTrace();
            }
            return -1;
    }
    public boolean updateTable(String sql){
        try(
            Connection conn = DriverManager.getConnection(DatabaseCredential.url(), DatabaseCredential.USER, DatabaseCredential.PASSWORD);
            Statement query = conn.createStatement();
            )
            {
                int result = query.executeUpdate(sql);
                if(result > 0){
                    return true;
                }else{
                    return false;
                }
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }
    }
    public static HashMap<String, ArrayList<String>> parse(ArrayList<String> columns, ResultSet rs) {
        HashMap<String, ArrayList<String>> hashTable = new HashMap<String,ArrayList<String>>();
        //assume count as first parameter
    try{
        ArrayList<ArrayList<String>> listOList = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < columns.size(); ++i) {
            ArrayList<String> newList = new ArrayList<String>();
            listOList.add(newList);
        }
       while(rs.next()) {
        for (int i = 0; i < columns.size(); ++i) {
            listOList.get(i).add(rs.getString(i+1));
        }
       }
       for (int i = 0; i < columns.size(); ++i) {
        hashTable.put(columns.get(i), listOList.get(i));
    }
    }
    catch(SQLException e) {
        e.printStackTrace();
    }
       return hashTable;
    }
    
    
}