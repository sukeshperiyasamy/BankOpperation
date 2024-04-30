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

public class DbConnection extends AccCreation {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";

	AccOpp accountoperation = new AccOpp();
	AccCreation accopening = new AccCreation();

	public static void main(String[] args) {
//	        try {
//	            // Establishing a connection to the database
//	            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
//	            System.out.println("Connected to the database!");
//
//	            // Creating a statement
//	             Statement statement = connection.createStatement();
//
//	             // Executing a simple query
//	             String query = "SELECT * FROM bank.userdetails";
//	             ResultSet resultSet = statement.executeQuery(query);
//
//	             // Processing the result set
//	             while (resultSet.next()) {
//	                 // Retrieve by column name
//	                 int acc_no = resultSet.getInt("accountnumber");
//	                 String username = resultSet.getString("username");
//	                 String dob = resultSet.getString("dob");
//	                 String proof = resultSet.getString("proof");
//	                 
//	           
//	                 
//	                 // Display values
//	                 System.out.println("accountnumber : " + acc_no + ", username : " + username +", dob : " + dob +" proof : "+ proof);
//	                 
//	                 
//	                 
//	             }
////	             Closing resources
//	             resultSet.close();
//	             statement.close();
//	            connection.close();
//	            System.out.println("Disconnected from the database!");
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
	}

	void insertaccdetails(String username, String dob, String proof) {
		// Executing a simple query
		LocalDate today = LocalDate.now();
		Date sqlDate = Date.valueOf(today);
		int initialDep = 500;
		String query = "insert into bank.userdetails(username, dob,proof,date)values(?,?,?,?)";
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
			stmt.setDate(4, sqlDate);
			stmt.executeUpdate();
			System.out.println("Data Inserted into UserAcc Table Successfully");
//				             Closing resources
				
		int accountnumber=	existinguserlogin(username,false);
			
			
			
			
				stmt1.setInt(1, accountnumber);
				stmt1.setInt(2, initialDep);
				stmt1.executeUpdate();
				System.out.println("Data Inserted into Acc Table and Deposited 500 Successfully");

			existinguserlogin(username,true);
			
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void insert(String username, String password, String emailid) {
		// Executing a simple query
		LocalDate today = LocalDate.now();
		Date sqlDate = Date.valueOf(today);

		String query = "insert into bank.signup(username, password,emailid,date)values(?,?,?,?)";

//					ResultSet resultSet;
		try {
			// Establishing a connection to the database
			Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			PreparedStatement stmt = connection.prepareStatement(query);

			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, emailid);
			stmt.setDate(4, sqlDate);
			stmt.executeUpdate();
			System.out.println("Data Inserted into UserAcc Table Successfully");
//			existinguserlogin(username);
			accopening.main(null);
//			             Closing resources
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void checkUserLogin(String username, String password) {
		String usernameQuery = "SELECT username FROM bank.signup WHERE username = ?";
		String passwordQuery = "SELECT password FROM bank.signup WHERE username = ?";

		try {
			// Establish a connection to the database
			Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			// Check if the provided username exists in the database
			PreparedStatement usernameStmt = connection.prepareStatement(usernameQuery);
			usernameStmt.setString(1, username);
			ResultSet usernameResultSet = usernameStmt.executeQuery();

			if (usernameResultSet.next()) { // Username exists
				// Check if the corresponding password matches the provided password
				PreparedStatement passwordStmt = connection.prepareStatement(passwordQuery);
				passwordStmt.setString(1, username);
				ResultSet passwordResultSet = passwordStmt.executeQuery();

				if (passwordResultSet.next()) { // Password exists
					String storedPassword = passwordResultSet.getString("password");
					if (storedPassword.equals(password)) {
						// Username and password match.
						System.out.println("Login successful!");
existinguserlogin(username,true);
//						accountoperation.main(null);
						
					} else {
						// Password doesn't match
						System.out.println("Incorrect password!\n enter the correct password ");

					}
				} else {
					// Password doesn't exist for the provided username
					System.out.println("Username exists but password doesn't match!");
				}

				passwordResultSet.close();
				passwordStmt.close();
			} else {
				// Username doesn't exist
				System.out.println("Username doesn't exist!");
			}

			usernameResultSet.close();
			usernameStmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void viewbal(int accountnumber) {
		String viewbalquery = "select balance from bank.acc_details where accountnumber= ?";

		try {
			// Establish a connection to the database
			Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			PreparedStatement viewbalStmt = connection.prepareStatement(viewbalquery);
			viewbalStmt.setInt(1, accountnumber);
			ResultSet viewbalResultSet = viewbalStmt.executeQuery();

			if (viewbalResultSet.next()) {
				int accbalance = viewbalResultSet.getInt("balance");
				System.out.println("the balance for account number " + accountnumber + " is " + accbalance);
			}

			else {
				System.out.println(" else part is working in db");
			}

			System.out.println(" done !!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

int lastaccnumber() {
String lastaccountnumberquery="select max(accountnumber) as accountnumber from bank.userdetails ";
int lastaccountnumber = 0;
try {
	// Establish a connection to the database
	Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	PreparedStatement lastaccountStmt = connection.prepareStatement(lastaccountnumberquery);
//	lastaccountStmt.setInt(1, accountnumber);
	ResultSet lastaccountnumberResultSet = lastaccountStmt.executeQuery();

	if (lastaccountnumberResultSet.next()) {
		 lastaccountnumber = lastaccountnumberResultSet.getInt("accountnumber");
		System.out.println("the account number is:  " + lastaccountnumber);
		
	}

	else {
		System.out.println(" else part is working in db");
	}

	System.out.println(" done !!!");
	
} catch (SQLException e) {
	e.printStackTrace();
}
return lastaccountnumber;
}

int existinguserlogin(String username ,boolean bool) {
	int existinguserlogin = 0;
String existinguserloginquery="select distinct usr.accountnumber from bank.userdetails usr inner join bank.signup sin on usr.username = ? ";

try {
	Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	PreparedStatement existinguserloginStmt = connection.prepareStatement(existinguserloginquery);
	existinguserloginStmt.setString(1, username);
	ResultSet existinguserloginResultSet =existinguserloginStmt.executeQuery();
	
	if (existinguserloginResultSet.next()) {
		existinguserlogin = existinguserloginResultSet.getInt("accountnumber");
		System.out.println("the account number is:  " +existinguserlogin);
		if(bool)
		accountoperation.main(null,existinguserlogin);
	}
	

	else {
		System.out.println(" else part is working in db");
	}
	
} catch (SQLException e) {
	e.printStackTrace();
}return existinguserlogin;
}
}
