package bank.opp;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

public class DbConnection extends AccCreation{
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";
	
	 public static void main(String[] args) {
	        try {
	            // Establishing a connection to the database
	            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	            System.out.println("Connected to the database!");

	            // Creating a statement
	             Statement statement = connection.createStatement();

	             // Executing a simple query
	             String query = "SELECT * FROM bank.userdetails";
	             ResultSet resultSet = statement.executeQuery(query);

	             // Processing the result set
	             while (resultSet.next()) {
	                 // Retrieve by column name
	                 int acc_no = resultSet.getInt("accountnumber");
	                 String username = resultSet.getString("username");
	                 String dob = resultSet.getString("dob");
	                 String proof = resultSet.getString("proof");
	                 
	           
	                 
	                 // Display values
	                 System.out.println("accountnumber : " + acc_no + ", username : " + username +", dob : " + dob +" proof : "+ proof);
	                 
	                 
	                 
	             }
//	             Closing resources
	             resultSet.close();
	             statement.close();
	            connection.close();
	            System.out.println("Disconnected from the database!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	 
////crete account
//		static void insert(Connection connection, Scanner sc) {
//			// Executing a simple query
//			String query = "insert into bank.userdetails ( usernme, name,gpa,department)values(?,?,?,?)";
////						ResultSet resultSet;
//			try {
//				PreparedStatement stmt = connection.prepareStatement(query);
//				System.out.println("Enter the username");
//				int username = sc.nextInt();
//				sc.nextLine();
//				System.out.println("Enter the dob");
//				String dob = sc.nextLine();
//				System.out.println("Enter the GPA :");
//				String proof = sc.nextFloat();
//				sc.nextLine();
//				System.out.println("Enter the Department :");
//				String dep = sc.nextLine();
//				stmt.setInt(1, id);
//				stmt.setString(2, name);
//				stmt.setFloat(3, gpa);
//				stmt.setString(4, dep);
//				stmt.executeUpdate();
//				System.out.println("Data Inserted Successfully");
////				             Closing resources
//				stmt.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//	 
	 
	 void insert(String username , String dob, String proof , int accountnumber) {
			// Executing a simple query
		 	LocalDate today = LocalDate.now();
		 	Date sqlDate = Date.valueOf(today);
		 	int initialDep =500;
			String query = "insert into bank.userdetails(username, dob,proof,accountnumber,date)values(?,?,?,?,?)";
			String accDetails = "insert into bank.acc_details(accountnumber,balance)values(?,?)";
//						ResultSet resultSet;
			try {
				 // Establishing a connection to the database
	            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement stmt = connection.prepareStatement(query);
				PreparedStatement stmt1 = connection.prepareStatement(accDetails);
				stmt.setString(1, username);
				stmt.setString(2, dob);
				stmt.setString(3, proof);
				stmt.setInt(4, accountnumber);
				stmt.setDate(5, sqlDate);
				stmt.executeUpdate();
				System.out.println("Data Inserted into UserAcc Table Successfully");
//				             Closing resources
				stmt1.setInt(1, accountnumber);
				stmt1.setInt(2, initialDep);
				stmt1.executeUpdate();
				System.out.println("Data Inserted into Acc Table and Deposited 500 Successfully");
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	 
}