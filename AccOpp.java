package bank.opp;

import java.util.Scanner;

public class AccOpp extends AccCreation {

	static void UserLogin(int UserLogin) {

		Scanner sc = new Scanner(System.in);
		// new user
		if (UserLogin == 1) {
//			NewUser(0);
		} else {

			System.out.println("Enter the Account Number: ");
			int AccountNo = sc.nextInt();

			System.out.println("Enter the Option ");
			System.out.println("1.ViewBalance");
			System.out.println("2.Withdraw");
			System.out.println("3.Deposit");
			int BankOperation = sc.nextInt();
			BankOperation(BankOperation);
		}

	}

	static void BankOperation(int BankOperation) {
		Scanner sc = new Scanner(System.in);
//		viewbalance
		if (BankOperation == 1) {
			System.out.println("viewbal");

//			AccountNo = 

		} // Withdraw
		else if (BankOperation == 2) {

			System.out.println("Withdraw");

			System.out.println("Enter the WithDraw Amount");
			int WithdrawAmount = sc.nextInt();

//			if (AccountBalance>=WithdrawAmount) {
//			
//				AccountBalance = AccountBalance-WithdrawAmount;
//				return FinalBalance;
//			}
//			else {
//				String InsufficinatBalance="InsufficinatBalance";
//			}

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

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("1.New User");
		System.out.println("2.Existing User");
		int UserLogin = sc.nextInt();
		UserLogin(UserLogin);

	}

}