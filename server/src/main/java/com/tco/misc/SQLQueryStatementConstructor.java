package com.tco.misc;
import java.util.ArrayList;
import java.lang.String;
public class SQLQueryStatementConstructor {

    private SQLQueryStatementConstructor() {};

    public static String generateSQLQueryCommand(ArrayList<String> columns, String table, String conditional, String match){
        String sqlStatement = "SELECT ";
        for (int i = 0; i < columns.size() - 1; ++i) {
            sqlStatement += columns.get(i) + ", ";
        }
        sqlStatement += columns.get(columns.size() - 1) + " " + "FROM " + table;
        if (conditional.length() > 1) {
            sqlStatement += " WHERE " + conditional + " = \"" + match + "\"";
        }
        sqlStatement += ";";
        return sqlStatement;
    }

    public static String generateSQLInsertCommand(ArrayList<String> values, String table){
        String sqlStatement = "INSERT INTO " + table + " VALUES (";
        for (int i = 0; i < values.size() - 1; ++i) {
            sqlStatement += "'" + values.get(i) + "'" + ", ";
        }
        sqlStatement += "'" + values.get(values.size() - 1) + "'";
        sqlStatement += ");";
        return sqlStatement;
    }
}