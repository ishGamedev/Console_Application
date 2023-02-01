package consoleApplication;

import java.util.*;

public class CustomSearch {
	public static void customSearchFn() throws Exception {
		
		Scanner scString = new Scanner(System.in);
		
		String crValue = null;
		ArrayList<Integer> displayedIds = new ArrayList<Integer>();
		
		int currentLetters = 0;
		do {	
			System.out.println("Enter the name you want to search: ");
			crValue = scString.next();
			currentLetters = crValue.length();
			
			for(int i = 0;i<displayedIds.size();i++) {
				if(crValue.charAt(0)-'0' == displayedIds.get(i)) {
					DisplayProfile.displayProfileFn(LogInPage.userList.get(displayedIds.get(i)-1));
				}
			}

			displayedIds.removeAll(displayedIds);
			
			for(int i = 0;i<LogInPage.userList.size();i++) {
				if(LogInPage.userList.get(i).name.length()>=currentLetters) {
					if(crValue.equalsIgnoreCase(LogInPage.userList.get(i).name.substring(0, currentLetters))||
							crValue.equalsIgnoreCase(LogInPage.userList.get(i).name)) {
						System.out.print(i+1 + ".");
						LogInPage.userList.get(i).printContentsForSearch();
						displayedIds.add(i+1);
					}				
				}
			}
			if(displayedIds.size()<=0)System.out.println("No Profile Starts with "+crValue);
			System.out.println("0 Exit");
			
			if(crValue == "0") SearchForPeople.searchForPeopleFn();
			
		}while(!crValue.equals("0"));
		
		scString.close();
		
		
	}
}
