package com.example.andersonprojeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:h2:~/test;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load H2 driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initialize() {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            // Create doctors table
            statement.execute("CREATE TABLE IF NOT EXISTS doctors (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "crm VARCHAR(6) NOT NULL UNIQUE," +
                    "password VARCHAR(255) NOT NULL" +
                    ")");

            // Create pharmacies table
            statement.execute("CREATE TABLE IF NOT EXISTS pharmacies (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "code VARCHAR(10) NOT NULL UNIQUE," +
                    "password VARCHAR(255) NOT NULL" +
                    ")");

            // Create prescriptions table
            statement.execute("CREATE TABLE IF NOT EXISTS prescriptions (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "doctor_name VARCHAR(255) NOT NULL," +
                    "crm VARCHAR(10) NOT NULL," +
                    "patient_name VARCHAR(255) NOT NULL," +
                    "cpf VARCHAR(11) NOT NULL," +
                    "born_date VARCHAR(11) NOT NULL," +
                    "health_insurance VARCHAR(255) NOT NULL," +
                    "description TEXT NOT NULL," +
                    "medicine_name VARCHAR(255) NOT NULL," +
                    "dosage VARCHAR(255) NOT NULL," +
                    "amount INT NOT NULL," +
                    "admin VARCHAR(255) NOT NULL," +
                    "obs TEXT," +
                    "pharmacy_id INT NOT NULL," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
