package com.da;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public DatabaseConnection() {
    }

    public Connection getConnection() {
        String databaseName = "db_da";
        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.databaseLink = DriverManager.getConnection(url, user, password);
        } catch (Exception var6) {
            var6.printStackTrace();
        }
        return this.databaseLink;
    }
}

