package consoleApplication;

import java.util.*;
import SerializableObjects.*;

public class Filter {
	
	public static String userFilterChoice = "";
	public static String userChoiceInFilter = "";
	
	public static void filterFn() throws Exception {
		Scanner scInt = new Scanner(System.in);
		Scanner scString = new Scanner(System.in);
		
		System.out.println("1. Language");
		System.out.println("2. Profession");
		System.out.println("3. Country");
		System.out.println("4. Particular Skill");
		System.out.println("0. Back to Search");
		int filterType = scInt.nextInt();
		
		//set is created and used to avoid duplicates
		Set<String> availableChoicesSet = new HashSet<String>();
		
		switch(filterType){
			case 1:
				userFilterChoice = "Language";
				for(int i = 0;i<LogInPage.freeLancerList.size();i++) {
					for(int j = 0;j<LogInPage.freeLancerList.get(i).languagesKnownObjects.size();j++) {
						availableChoicesSet.add(LogInPage.freeLancerList.get(i).languagesKnownObjects.get(j).language);
					}
				}
				break;
			case 2:
				userFilterChoice = "Profession";
				for(int i = 0;i<LogInPage.freeLancerList.size();i++) {
					availableChoicesSet.add(LogInPage.freeLancerList.get(i).profession);
				}
				break;
			case 3:
				userFilterChoice = "Country";
				for(int i = 0;i<LogInPage.freeLancerList.size();i++) {
					availableChoicesSet.add(LogInPage.freeLancerList.get(i).country);
				}
				break;
			case 4:
				userFilterChoice = "Skill";
				for(int i = 0;i<LogInPage.freeLancerList.size();i++) {
					for(int j = 0;j<LogInPage.freeLancerList.get(i).skillsObjects.size();j++) {
						availableChoicesSet.add(LogInPage.freeLancerList.get(i).skillsObjects.get(j).skill);
					}
				}
				break;
			case 0:
				SearchForPeople.searchForPeopleFn();
		}
		
		//Copy all the elements of the set to array to allow random access
		
		String[] availableChoicesArray = new String[availableChoicesSet.size()];
		
		Iterator itr = availableChoicesSet.iterator();
		 
        int index = 0;
        while (itr.hasNext()) {
        	availableChoicesArray[index] = (String) itr.next();
        	index++;
        }
        
        for(int i = 0;i<availableChoicesArray.length;i++) {
        	System.out.println(i+1 + ". "+availableChoicesArray[i]);
        }
        
        int userIndexChoice = scInt.nextInt();
        userChoiceInFilter = availableChoicesArray[userIndexChoice-1];
        SearchForPeople.isFilterOn = true;
        SearchForPeople.lastElementInFilterPage = 0;
        SearchForPeople.firstElementInPreviousFilterPage.clear();
        SearchForPeople.searchForPeopleFn();
	}
}
