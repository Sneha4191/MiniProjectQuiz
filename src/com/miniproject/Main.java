package com.miniproject;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {
		String uname,password=" ";
		Scanner sc = new Scanner(System.in);
		Student student = new Student();
		Admin admin = new Admin();
		
		for(int i = 1; i <= 7; i++) {
			System.out.println("\n......Welcome to console based application......");
			System.out.println(".....For Student.....");
			System.out.println("1. Student Registration\n2.Student Login" + 
					"\n3.Display Quiz result");
			System.out.println("..........For Admin Only...........");
			System.out.println("4.Display all students score as per ascending order\n5.Fetch student score by using id" + 
					"\n6.Add question with 4 options into database");
			System.out.println("Enter your choice");
			int choice = sc.nextInt();


		switch(choice){
		case 1 :
			System.out.println("Enter First Name >>> ");
			String fname = sc.next();
			System.out.println("Enter Last Name >>> ");
			String lname = sc.next();
			System.out.println("Enter Username >>> ");
			uname = sc.next();
			System.out.println("Enter password >>> ");
			password = sc.next();
			System.out.println("Enter City >>> ");
			String city = sc.next();
			System.out.println("Enter Email Id >>> ");
			String email = sc.next();
			System.out.println("Enter Mobile Number >>> ");
			String mobileNo = sc.next();
			
			student.insertStudentDetails(fname, lname, uname, password, city, email, mobileNo);
			
		break;
			
		case 2 :
			System.out.println("Enter Username >>> ");
			uname = sc.next();
			System.out.println("Enter password >>> ");
			password = sc.next();
			student.checkStudentLoginCredential(uname,password);
		break;
		
		case 3 :
			System.out.println("Enter Username: ");
		    uname = sc.next();
		    System.out.println("Enter password: ");
		    password = sc.next();
		    student.getStudentResult(uname, password);
		    break;
		    
		case 4:
		    System.out.println("Enter Admin Username: ");
		    String adminUsername = sc.next();
		    System.out.println("Enter Admin Password: ");
		    String adminPassword = sc.next();
		    admin.checkAdminLoginCredential(adminUsername, adminPassword);
		    break;
		    
		case 5:
		    System.out.println("Enter student ID: ");
		    int studentId = sc.nextInt();
		    
		    int result = admin.getStudentResultById(studentId);
		    
		    if (result != -1) {
		        System.out.println("Score is: " + result);
		    } else {
		        System.out.println("No result found for the provided student ID.");
		    }
		    break;  
		    
		case 6:
		    System.out.println("Enter the question: ");
		    String question = sc.next();
		    System.out.println("Enter option A: ");
		    String optionA = sc.next();
		    System.out.println("Enter option B: ");
		    String optionB = sc.next();
		    System.out.println("Enter option C: ");
		    String optionC = sc.next();
		    System.out.println("Enter option D: ");
		    String optionD = sc.next();
		    System.out.println("Enter the correct answer: ");
		    String correctAnswer = sc.next();
		    
		    admin.addQuizQuestion(question, optionA, optionB, optionC, optionD, correctAnswer);
		    break;
    
		default :
			System.out.println("Invalid Input ....");
		
			}
		}
		
	}

}
