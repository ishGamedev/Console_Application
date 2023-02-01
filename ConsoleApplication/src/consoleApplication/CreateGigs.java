package consoleApplication;

import java.util.*;
import java.util.stream.Collectors;

import SerializableObjects.*;
import consoleApplication.Jobs.*;

public class CreateGigs {
	
	//get all the services provided by the 
	Scanner scString = new Scanner(System.in);
	
	public static void createGigsFn() throws Exception {
		
		Set<String> inputIntServices = new HashSet<String>();
		inputIntServices.add("Delivery days");inputIntServices.add("Number of sessions");
		inputIntServices.add("Consulting hours");inputIntServices.add("Revisions");
		inputIntServices.add("Number of pages"); inputIntServices.add("Words included");
		inputIntServices.add("Photos per product");inputIntServices.add("Number of products");
		inputIntServices.add("Management duration");inputIntServices.add("Number of platforms");
		inputIntServices.add("Number of pages or screens");inputIntServices.add("Pay($)");
		
		String[] allAvailableGigs = LogInPage.currentProfile.serviceOffersArray[0].typesOfGigs;
		
		
		getInformationEachLevelGig("BASIC",LogInPage.currentProfile.serviceOffersArray[1],inputIntServices,allAvailableGigs);
		getInformationEachLevelGig("STANDARD",LogInPage.currentProfile.serviceOffersArray[2],inputIntServices,allAvailableGigs);
		getInformationEachLevelGig("PREMIUM",LogInPage.currentProfile.serviceOffersArray[3],inputIntServices,allAvailableGigs);
		LogInPage.writeObject();
	}
	
	private static void getInformationEachLevelGig(String typeOfGig,ServiceOffers service,Set<String>inputIntServices,String[] allAvailableGigs){
		Scanner scString = new Scanner(System.in);
		String[] tempStringArray = new String[service.getServices().length];
		
		System.out.println();
		
		for(int i = 0;i<service.getServices().length;i++) {
			System.out.println(allAvailableGigs[i]+" for "+typeOfGig);
			if(inputIntServices.contains(allAvailableGigs[i])) {
				System.out.println("ENTER A NUMBER: ");
				String userInput = scString.nextLine();
				//Assigning values directly into the hashMap in the profile class
				tempStringArray[i] = userInput;
			}
			else{
				System.out.println("ENTER YES/NO: ");
				String userInput = scString.nextLine();
				userInput.toUpperCase();
				//Assigning values directly into the hashMap in the profile class
				tempStringArray[i] = userInput;
			}
		}
		service.setServices(tempStringArray);
		System.out.println();
	}
	

}
