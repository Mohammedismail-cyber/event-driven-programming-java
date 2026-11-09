package com.university;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The "bridge" between the Java application and the MySQL database.
 *
 * The password is NOT hard-coded (so you never push it to GitHub).
 * Set it once in PowerShell before running:
 *     $env:UNI_DB_PASSWORD = "your_real_mysql_password"
 * Optional overrides: UNI_DB_URL, UNI_DB_USER
 */
public class DatabaseUtil {

    private static final String URL = env("UNI_DB_URL", "jdbc:mysql://localhost:3306/university_db");
    private static final String USER = env("UNI_DB_USER", "root");
    private static final String PASSWORD = env("UNI_DB_PASSWORD", "your_password");

    private static String env(String name, String fallback) {
        String value = System.getenv(name);
        return (value == null || value.isEmpty()) ? fallback : value;
    }

    // Returns a Connection object. Throws SQLException if it fails.
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
