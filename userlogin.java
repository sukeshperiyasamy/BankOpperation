package bank.opp;

import java.util.Scanner;

public class userlogin {
 static String username;
 static String password;
 static String emailid;
	
	
	
	
	
	public static void main  ( String[] args) {
		
		

		Scanner sc = new Scanner(System.in);
		System.out.println("1.New User");
		System.out.println("2.Existing User");
		int userlogin = sc.nextInt();

		 DbConnection db = new DbConnection();
		
//		Scanner sc = new Scanner(System.in);
		if (userlogin==1)
		{
			System.out.println("Enter the username ");
			sc.nextLine();
			 username = sc.nextLine();
			System.out.println("Enter the password ");
			 password = sc.nextLine();
			System.out.println("Enter the EmailId ");
			 emailid = sc.nextLine();
			System.out.println(username+password+emailid);
			
			 System.out.println("DB Inserted Sented!!");
		        db.insert( username, password, emailid);
		        System.out.println("Done !!");
		} else if(userlogin==2) {
			
			
			System.out.println("Enter the username ");
			sc.nextLine();
			 username = sc.nextLine();
			System.out.println("Enter the password ");
			 password = sc.nextLine();
			 System.out.println("details sent to db");
			  db.checkUserLogin( username, password);
			  
			  System.out.println("login check succesful");
			
		}
		else {
			System.out.println("enter the correct option");
		}

		

		
		
	      
	     
	}

}
