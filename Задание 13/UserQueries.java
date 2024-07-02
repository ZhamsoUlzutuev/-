package com.example.database;

import java.sql.*;

public class UserQueries {
    private final Connection connection;
    public UserQueries() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }
    public String getUserWithShortestLetters() throws SQLException {
        String query = "SELECT u.full_name FROM users u JOIN letters l ON u.id = l.sender_id GROUP BY u.id ORDER BY AVG(LENGTH(l.body)) ASC LIMIT 1";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getString("full_name");
            }
        }
        return null;
    }
    public void getUsersWithLetterCounts() throws SQLException {
        String query = "SELECT u.full_name, " +
                "(SELECT COUNT(*) FROM letters WHERE sender_id = u.id) AS sent_count, " +
                "(SELECT COUNT(*) FROM letters WHERE receiver_id = u.id) AS received_count " +
                "FROM users u";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("full_name") + " - Отправил: " + resultSet.getInt("sent_count") +
                        " - Полученный: " + resultSet.getInt("received_count"));
            }
        }
    }
    public void getUsersWhoReceivedSpecificSubject(String subject) throws SQLException {
        String query = "SELECT DISTINCT u.full_name FROM users u JOIN letters l ON u.id = l.receiver_id WHERE l.subject = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, subject);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("full_name"));
                }
            }
        }
    }
    public void getUsersWhoDidNotReceiveSpecificSubject(String subject) throws SQLException {
        String query = "SELECT u.full_name FROM users u WHERE NOT EXISTS (SELECT 1 FROM letters l WHERE l.receiver_id = u.id AND l.subject = ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, subject);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("full_name"));
                }
            }
        }
    }
}