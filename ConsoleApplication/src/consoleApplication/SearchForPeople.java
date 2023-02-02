package consoleApplication;

import java.util.*;

public class SearchForPeople {

	static int pageNo = 0;
	static int numberOfRowsPerPage = 3;
	//Last Element + 1 will give the first element of the next page
	static int lastElementInFilterPage = 0;
	//Used to return back to previous page if necessary
	static Stack<Integer>firstElementInPreviousFilterPage = new Stack<Integer>();
	//Boolean which is responsible for switching filter modes
	static boolean isFilterOn;
	
	public static void searchForPeopleFn() throws Exception {
		Scanner scInt = new Scanner(System.in);
		showListOfPeopleFn();
		
		char choice = 0;
		System.out.println("> Next Page");//Used symbols because numbers are used as random access modifiers
		System.out.println("< Previous Page");
		System.out.println("/ Add Filter");
		System.out.println(". Remove Filter");
		System.out.println("* Custom Search");
		System.out.println("0 Exit");		
		System.out.println("Enter the Number of the profile you want to Display: ");
		
		choice = scInt.next().charAt(0);
		if(choice == '<' || choice =='>') {
			updatePage(choice);
		}
		
		else if(choice == '0') {
			MainMenu.showMainMenuFn();
			scInt.close();
			return;
		}
		
		else if(choice == '*') {
			CustomSearch.customSearchFn();
		}
		
		else if(choice == '/') {
			Filter.filterFn();
		}
		else if(choice == '.') {
			isFilterOn = false;
			searchForPeopleFn();
		}
		
		int convertedInt = choice - '0';
		if(convertedInt-1 >=0&& convertedInt-1 <= 9) {
			//When "search for people" function is called the logic thinks we updated our current page.
			//When visiting profiles we will be staying in the same page and will be calling the "Search for People" function
			//so the last updated element will be popped out and assigned to the lastElement integer. To make sure the stack and lastElement Integer is not updated for the same page
			if(firstElementInPreviousFilterPage.size()>0)lastElementInFilterPage = firstElementInPreviousFilterPage.pop();
			DisplayProfile.displayProfileFn(LogInPage.freeLancerList.get(convertedInt-1)); 
		}
		scInt.close();
	}
	
	public static void showListOfPeopleFn() {
		if(isFilterOn) {
			System.out.println("Filter Applied '.' To Remove Filter");
		}
		System.out.println("--------------------------------------------------------");
		if(!isFilterOn) {
			//Formula to find the first element given page No and now of rows per page.(This will only work for normal search mode)
			int start = (numberOfRowsPerPage * pageNo),end = start + numberOfRowsPerPage;
			
			//If the start and number of rows in this page exits our total List Size we will clamp it to the last element in the list
			if(end > LogInPage.freeLancerList.size()) end = LogInPage.freeLancerList.size();
			
			//Printing
			for(int i = start;i<end;i++) {
				System.out.print(i+1 + ".");
				LogInPage.freeLancerList.get(i).printContentsForSearch();
			}
		}
		else {
			filterOn();
		}
		
		System.out.println("--------------------------------------------------------");
		
	}
	
	
	public static void filterOn() {
		
		//Getting the type of filter and what choice the user made in that filter from Filter Class
		String userFilterChoice = Filter.userFilterChoice;
		String userChoiceInFilter = Filter.userChoiceInFilter;
		
		//lastElementInFilterPage will start as 0 and when printing the page the last i value of the 'for loop' will be stored.
		int start = lastElementInFilterPage,
				
			//Start + number of rows per page will always give the last element
			//the 'i' in "For Loop" is not used to check whether we reached the end since we will skip elements
			end = start + numberOfRowsPerPage,
			//A separate variable index is created to check with end
			index = start;
		firstElementInPreviousFilterPage.add(start);
		if(end > LogInPage.freeLancerList.size()) end = LogInPage.freeLancerList.size();
		
		switch(userFilterChoice) {
		 //Case 1
		 	case"Language":
				for(int i = start;i<LogInPage.freeLancerList.size();i++) {//Iterating profiles
					for(int j = 0; j<LogInPage.freeLancerList.get(i).languagesKnownObjects.size();j++) {//Iterating all languages in that profile
						if(LogInPage.freeLancerList.get(i).languagesKnownObjects.get(j).language.equalsIgnoreCase(userChoiceInFilter)) {
							System.out.print(i+1 + ".");
							LogInPage.freeLancerList.get(i).printContentsForSearch();
							index++;//index will be only appended if we find a match
							break;//this break is used to break from the current User class.
						}
					}
					
					if(index>=end) {//We reached the page limit
						lastElementInFilterPage = i;//Storing the last element we iterated
						break;
					}
				}
				break;
			
			//Case 2
			case"Profession":
				for(int i = start;i<LogInPage.freeLancerList.size();i++) {
					if(LogInPage.freeLancerList.get(i).profession.equalsIgnoreCase(userChoiceInFilter)) {
						System.out.print(i+1 + ".");
						LogInPage.freeLancerList.get(i).printContentsForSearch();
						index++;
					}
					if(index>=end) {
						lastElementInFilterPage = i;
						break;
					}
				}
				break;
				
			//Case 3
			case"Country":
				for(int i = start;i<LogInPage.freeLancerList.size();i++) {
					if(LogInPage.freeLancerList.get(i).country.equalsIgnoreCase(userChoiceInFilter)) {
						System.out.print(i+1 + ".");
						LogInPage.freeLancerList.get(i).printContentsForSearch();
						index++;
					}
					if(index>=end) {
						lastElementInFilterPage = i;
						break;
					}
				}
				break;
			//Case 4
			case"Skill":
				for(int i = start;i<LogInPage.freeLancerList.size();i++) {//Iterating profiles
					for(int j = 0; j<LogInPage.freeLancerList.get(i).skillsObjects.size();j++) {//Iterating all Skills in that profile
						if(LogInPage.freeLancerList.get(i).skillsObjects.get(j).skill.equalsIgnoreCase(userChoiceInFilter)) {
							System.out.print(i+1 + ".");
							LogInPage.freeLancerList.get(i).printContentsForSearch();
							index++;//index will be only appended if we find a match
							break;//this break is used to break from the current User class.
						}
					}
					
					if(index>=end) {
						lastElementInFilterPage = i;
						break;
					}
				}
				break;
		}
		
	}
	
	//Responsible for Switching pages
	public static void updatePage(char choice) throws Exception {
		//Calculating the Max no of pages cause in normal search the user can visit last page from the first page like a circle
		double maxNoOfPages =  Math.ceil(LogInPage.freeLancerList.size()/(double)numberOfRowsPerPage);
		
		
		if(choice == '>') {
			//Normal Mode
			if(!isFilterOn) {
				pageNo++;
				if(pageNo>=(int)maxNoOfPages) pageNo = 0;//when we try to exceed the last page we will clamp it to first page
			}
			
			//Filter Mode
			else {
				lastElementInFilterPage+=1;//adding 1 to the last element of the previous page will give us the first element if the next page
				if(lastElementInFilterPage >= LogInPage.freeLancerList.size()) lastElementInFilterPage = 0;
			}
		}
		else {
			//Normal Mode
			if(!isFilterOn) {
				pageNo--;
				if(pageNo<0) pageNo = (int)maxNoOfPages-1;//when we try to go lesser than 0 we will visit the last page in normal mode
			}
			
			//Filter Mode
			else {
				firstElementInPreviousFilterPage.pop();//This will pop out the starting index of our current page
				//This stack is the road map for visiting previous pages in filter mode
				//If the stack is empty we can't go the previous page we will just stay in the first page
				if(firstElementInPreviousFilterPage.size()<=0) {
					firstElementInPreviousFilterPage.add(0);
					System.out.println("There is no previous page");
				}
				lastElementInFilterPage = firstElementInPreviousFilterPage.pop();//This will pop out the starting index of our previous page
			}
		}		
		searchForPeopleFn();
	}
	
	
}
