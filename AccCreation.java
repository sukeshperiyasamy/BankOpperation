package bank.opp;

import java.util.Scanner;

class UserData {
    private String name;
    private String dob;
    private String proof;
    private static int lastaccnum=10008;
    
    // Constructor
    public UserData(String name, String dob, String proof) {
        this.name = name;
        this.dob = dob;
        this.proof = proof;
        this.lastaccnum++;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDOB() {
        return dob;
    }

    public String getProof() {
        return proof;
    }

    public int getAccountNo() {
        return lastaccnum;
    }
}

public class AccCreation {
    static Scanner sc = new Scanner(System.in);
    static int startingAccountNo = 10000;
    static UserData newUser() {
        System.out.println("Enter the Name:");
        String name = sc.nextLine();

        System.out.println("Enter the Date of Birth (e.g., 01/01/2000):");
        String dob = sc.nextLine();

        System.out.println("Select any one of the Proof:");
        System.out.println("1. Aadhar");
        System.out.println("2. Pan");
        int option = sc.nextInt();
        sc.nextLine(); // Consume newline
        String proof = userOption(option);

//        int accountNo = accountNoGeneration();

        return new UserData(name, dob, proof);
    }

    static String userOption(int option) {
        String proof = "";
        if (option == 1) {
            System.out.println("Enter the 10 digit Aadhar Number:");
            proof = sc.nextLine();
            if (proof.length() != 10) {
                System.out.println("Enter the correct 10 Digit Aadhar number.");
                return userOption(1);
            }
        } else if (option == 2) {
            System.out.println("Enter the 7 Digit Pan Number:");
            proof = sc.nextLine();
            if (proof.length() != 7) {
                System.out.println("Enter the correct 7 Digit Pan number.");
                return userOption(2);
            }
        } else {
            System.out.println("Enter the correct option.");
            return userOption(sc.nextInt());
        }
        return proof;
    }

//    static int accountNoGeneration() {
//    	startingAccountNo++;
//        return startingAccountNo + 1;
//    }  

    public static void main(String[] args) {
//        UserData user = newUser();
//        int Dbaccno = user.getAccountNo();
    	 
     // Create a new user
        UserData DbUsr = newUser();

     // Retrieve user data
        String userName = DbUsr.getName();
        String dob = DbUsr.getDOB();
        String proof = DbUsr.getProof();

        System.out.println("Username: " + userName);
        System.out.println("Date of Birth: " + dob);
        System.out.println("Proof: " + proof);
    
        
        DbConnection db = new DbConnection();
     
        System.out.println("DB Inserted Sented!!");
        db.insertaccdetails( userName, dob, proof);
        System.out.println("Done !!");
        
        int lastaccountnumber=db.lastaccnumber();
        System.out.println("Account Number Created: " + lastaccountnumber);
        
        
    }
}
