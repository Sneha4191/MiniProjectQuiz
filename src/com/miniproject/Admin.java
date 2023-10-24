package com.miniproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    Connection conn = null;
    PreparedStatement pstmt = null;
    DatabaseConnection databaseConnection = new DatabaseConnection();

    public void viewStudentResults() throws SQLException {
        try {
            conn = databaseConnection.getConnectionDetails();
            String selectSql = "SELECT firstname, lastname, result FROM student_register ORDER BY result ASC";
            pstmt = conn.prepareStatement(selectSql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                int score = rs.getInt("result");

                System.out.println("First name: " + firstName);
                System.out.println("Last name: " + lastName);
                System.out.println("Score: " + score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            pstmt.close();
        }
    }
    
    public void checkAdminLoginCredential(String uname, String password) throws SQLException {

		try {
			conn = databaseConnection.getConnectionDetails();
			String selectsql = "SELECT * FROM admin WHERE username = ? AND password = ?";
			pstmt = conn.prepareStatement(selectsql);
			pstmt.setString(1, uname);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("Admin Login Successfully");
				Admin admin = new Admin();
				admin.viewStudentResults();
			} else {
				System.out.println("Incorrect Credentials............");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public int getStudentResultById(int studentId) throws SQLException {
        int result = -1; 
        try {
            conn = databaseConnection.getConnectionDetails();
            String selectSql = "SELECT result FROM student_register WHERE studentid = ?";
            pstmt = conn.prepareStatement(selectSql);
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt("result");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            pstmt.close();
        }    
        return result;
    }

    public void addQuizQuestion(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) throws SQLException {
        
        try {
            conn = databaseConnection.getConnectionDetails();
            String insertSql = "INSERT INTO quizquestions (question, option1, option2, option3, option4, answer) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, question);
            pstmt.setString(2, optionA);
            pstmt.setString(3, optionB);
            pstmt.setString(4, optionC);
            pstmt.setString(5, optionD);
            pstmt.setString(6, correctAnswer);
            int i = pstmt.executeUpdate();

            if (i > 0) {
                System.out.println("Question added successfully.");
            } else {
                System.out.println("Failed to add the question.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            pstmt.close();
        }
    }

}
