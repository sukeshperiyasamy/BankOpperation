package bank.opp;

import java.util.Scanner;

public class AccOpp extends AccCreation {

	 static DbConnection db = new DbConnection();

	static void BankOperation(int BankOperation, int acc) {
		Scanner sc = new Scanner(System.in);
//		viewbalance
		if (BankOperation == 1) {
			System.out.println("viewbal");
			
db.viewbal(acc);

		} // Withdraw
		else if (BankOperation == 2) {

			System.out.println("Withdraw");

			System.out.println("Enter the WithDraw Amount");
			int WithdrawAmount = sc.nextInt();


		} // deposit
		else if (BankOperation == 3) {

			System.out.println("Deposit");

			System.out.println("Enter the Deposit Amount");
			int DepositAmount = sc.nextInt();

//			AccountBalance = AccountBalance + DepositAmount;

		} else {
			System.out.println("Enter the Correct Option");
		}
	}

	public static void main(String[] args, int acc) {

		
		 DbConnection db = new DbConnection();
//		 int dbaccnumber=db.existinguserlogin
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the Option ");
		System.out.println("1.ViewBalance");
		System.out.println("2.Withdraw");
		System.out.println("3.Deposit");
		int BankOperation = sc.nextInt();
		BankOperation(BankOperation,acc);
		
	}

}