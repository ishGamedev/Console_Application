package consoleApplication;

import java.util.Scanner;

public class FundsManager {
	
	public static void fundsManagerfn() {
		Scanner scString = new Scanner(System.in);
		String userInput = "";
		
		do {
			System.out.println("1. Show Balance");
			System.out.println("2. Add Funds");
			
			userInput = scString.nextLine();
			
			switch(userInput) {
			case"1":
				System.out.println(LogInPage.currentProfile.wallet.getBalance());
			}
			
		}while(userInput.equalsIgnoreCase("0"));
		
	}
}
