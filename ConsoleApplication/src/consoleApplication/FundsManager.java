package consoleApplication;

import java.util.Scanner;

public class FundsManager {
	
	public static void fundsManagerfn() {
		Scanner scString = new Scanner(System.in);
		String userInput = "";
		
		do {
			System.out.println("1. Show Balance");
			System.out.println("2. Add Funds");
			System.out.println("0. Exit");
			
			userInput = scString.nextLine();
			
			switch(userInput) {
			case"1":
				System.out.println(LogInPage.currentProfile.wallet.getBalance());
				break;
			}
			
		}while(!userInput.equalsIgnoreCase("0"));
		
	}
}
