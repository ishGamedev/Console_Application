package consoleApplication;

import java.util.Scanner;

public class MainMenu {
	public static void showMainMenuFn() throws Exception{
		
		if(LogInPage.currentProfile.serviceOffersArray[1].getServices()[1].equals("-")) {
			System.out.println("\nCan't receive contracts until gigs are created 4 to create Gigs\n(But can sent contracts to other FreeLancers)\n");
		}
		
		String choice = "-1"; //each function has their own Integer choice variable
		Scanner scInt = new Scanner(System.in);
		do {
			System.out.println("1. Inbox");
			System.out.println("2. Search For People ");
			System.out.println("3. Show Calendar ");
			System.out.println("4. Create Gigs");
			System.out.println("5. Contracts");
			System.out.println("6. Manage Funds");
			System.out.println("0 Return to Login Page");
			
			choice = scInt.next();
			
			switch(choice) {
				
				case "1":
					InboxManager.inboxManagerFn(LogInPage.currentProfile.inbox);
					break;
				case "2":		
					SearchForPeople.searchForPeopleFn();
					break;
				
				case "3":
					Calendar.calendarFn();
					break;
				
				case "4":
					CreateGigs.createGigsFn();
					break;
					
				case "5":
					ContractManager.contractManagerfn();
					break;
				
				case "6":
					FundsManager.fundsManagerfn();
					break;
					
				case "0":
					break;
			}
			
		}while(!choice.equals("0"));
		LogInPage.showLoginPageFn();
	}
	
	
}
