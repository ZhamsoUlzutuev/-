package com.example;

import com.example.database.LetterModification;
import com.example.database.UserQueries;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            UserQueries userQueries = new UserQueries();
            LetterModification letterModification = new LetterModification();

            Date thresholdDate = Date.valueOf("2023-01-01");
            if (letterModification.deleteOldLetters(thresholdDate)) {
                System.out.println("Old letters deleted successfully.");
            } else {
                System.out.println("No old letters were found to delete.");
            }

            letterModification.addUser("Иван Иванов", Date.valueOf("1980-05-12"));
            letterModification.addUser("Мария Петрова", Date.valueOf("1992-08-21"));
            letterModification.addUser("Алексей Сидоров", Date.valueOf("1975-12-03"));

            letterModification.addLetter(1, 2, "Приветствие", "Привет, Мария! Как дела?", Date.valueOf("2023-10-01"));
            letterModification.addLetter(2, 1, "Ответ", "Привет, Иван! У меня все хорошо, а у тебя?", Date.valueOf("2023-10-02"));
            letterModification.addLetter(3, 1, "Проект", "Иван, напомни, пожалуйста, про детали проекта.", Date.valueOf("2023-10-03"));
            letterModification.addLetter(1, 3, "Ответ на проект", "Алексей, детали проекта такие...", Date.valueOf("2023-10-04"));

            String userWithShortestLetters = userQueries.getUserWithShortestLetters();
            System.out.println("User with shortest letters: " + userWithShortestLetters);

            userQueries.getUsersWithLetterCounts();

            String subject = "Приветствие";

            System.out.println("Users who received messages with subject '" + subject + "':");
            userQueries.getUsersWhoReceivedSpecificSubject(subject);

            System.out.println("Users who did not receive messages with subject '" + subject + "':");
            userQueries.getUsersWhoDidNotReceiveSpecificSubject(subject);
            
            String newSubject = "Обновление проекта";
            String newBody = "Уважаемые коллеги, уведомляем вас об обновлении проекта.";
            letterModification.sendMailToAllRecipients(1, newSubject, newBody);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
