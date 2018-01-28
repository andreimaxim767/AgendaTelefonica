package com.java.contacts.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private static final String db = "lbji_phonebook";
    private static final String user = "lbji_phonebook";
    private static final String pass = "phonebook-16";
    private static final String url = "jdbc:mysql://lbji.myd.infomaniak.com:3306/" + db;
    private static Connection Conn;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.getMessage();
        }
        return Conn;
    }
}
