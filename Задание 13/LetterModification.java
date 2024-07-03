package com.example.database;

import java.sql.*;

public class LetterModification {
    private final Connection connection;

    public LetterModification() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }


    public boolean deleteOldLetters(Date thresholdDate) throws SQLException {
        String query = "DELETE FROM letters WHERE send_date < ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, thresholdDate);
            return preparedStatement.executeUpdate() > 0;
        }
    }


    public boolean addUser(String fullName, Date birthDate) throws SQLException {
        String checkQuery = "SELECT COUNT(*) FROM users WHERE full_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(checkQuery)) {
            preparedStatement.setString(1, fullName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    return false;
                }
            }
        }

        String insertQuery = "INSERT INTO users (full_name, birth_date) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, fullName);
            preparedStatement.setDate(2, birthDate);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean addLetter(int senderId, int receiverId, String subject, String body, Date sendDate) throws SQLException {
        String query = "INSERT INTO letters (sender_id, receiver_id, subject, body, send_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, senderId);
            preparedStatement.setInt(2, receiverId);
            preparedStatement.setString(3, subject);
            preparedStatement.setString(4, body);
            preparedStatement.setDate(5, sendDate);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public void sendMailToAllRecipients(int senderId, String subject, String body) throws SQLException {
        String query = "INSERT INTO letters (sender_id, receiver_id, subject, body, send_date) " +
                "SELECT ?, id, ?, ?, CURDATE() FROM users WHERE id != ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, senderId);
            preparedStatement.setString(2, subject);
            preparedStatement.setString(3, body);
            preparedStatement.setInt(4, senderId);
            preparedStatement.executeUpdate();
        }
    }
}
