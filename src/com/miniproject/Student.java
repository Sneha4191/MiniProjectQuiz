package com.miniproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Student {

	Connection conn = null;
	PreparedStatement pstmt = null;
	DatabaseConnection databaseConnection = new DatabaseConnection();


	public void insertStudentDetails(String fname, String lname, String username, String password, String city,
			String email, String mobileNo) throws SQLException {
		try {
			conn = databaseConnection.getConnectionDetails();
			String sql = "insert into student_register(firstname,lastname,username,password,city,email,mobileno)values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fname);
			pstmt.setString(2, lname);
			pstmt.setString(3, username);
			pstmt.setString(4, password);
			pstmt.setString(5, city);
			pstmt.setString(6, email);
			pstmt.setString(7, mobileNo);
			System.out.println("Student registration done successfully....");
			int i = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			pstmt.close();
		}
	}

	public void checkStudentLoginCredential(String uname, String password) throws SQLException {

		try {
			conn = databaseConnection.getConnectionDetails();
			String selectsql = "SELECT studentid, firstname, lastname FROM student_register WHERE username = ? AND password = ?";
			pstmt = conn.prepareStatement(selectsql);
			pstmt.setString(1, uname);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int studentId = rs.getInt("studentid");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				System.out.println("Student Login Successfully, " + firstName + " " + lastName + "!");
				displayQuizQuestions(studentId);
			} else {
				System.out.println("Incorrect Credentials............");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void displayQuizQuestions(int studentId) throws SQLException {
		int marks = 0;
		try {
			DatabaseConnection databaseConnection = new DatabaseConnection();
			conn = databaseConnection.getConnectionDetails();

			pstmt = conn.prepareStatement("select * from quizquestions");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// for question and options
				System.out.println("Question " + rs.getInt(1) + " : " + rs.getString(2));
				System.out.println("a : " + rs.getString(3));
				System.out.println("b : " + rs.getString(4));
				System.out.println("c : " + rs.getString(5));
				System.out.println("d : " + rs.getString(6));

				// for answer checking
				String answer = rs.getString(7);
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter Your Answer : ");
				String userAnswer = sc.next();
				if (userAnswer.equals(answer)) {
					marks++;
				}
				System.out.println(".......................................");
			}
			// Store the quiz result in the student's record
			String updateSql = "UPDATE student_register SET result = ? WHERE studentid = ?";
			pstmt = conn.prepareStatement(updateSql);
			pstmt.setInt(1, marks);
			pstmt.setInt(2, studentId);
			pstmt.executeUpdate();

			System.out.println("Your score is: " + marks);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			pstmt.close();
		}
	}

	public void getStudentResult(String username, String password) throws SQLException {
		try {
			conn = databaseConnection.getConnectionDetails();
			String selectSql = "SELECT firstname, lastname,result FROM student_register WHERE username = ? AND password = ?";
			pstmt = conn.prepareStatement(selectSql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int result = rs.getInt("result");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				System.out.println(firstName + " " + lastName + " your Score is : " + result);
			} else {
				System.out.println("Incorrect Credentials............");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
			pstmt.close();
		}
	}

}
