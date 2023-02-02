package consoleApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import SerializableObjects.AppointmentRequest;

public class DisplayProfile {
	
	public static void displayProfileFn(User profile) throws Exception {
		
		Scanner scInt = new Scanner(System.in);
		System.out.println("Name: " + profile.name);
		System.out.println("Profession: "+profile.profession);
		System.out.println("MobileNumber: "+profile.mobileNumber);
		System.out.println("Location: "+profile.country);
		profile.printEducation();
		profile.printLanguagesKnown();
		profile.printSkills();
		profile.printGigs();
		
		
		char choice = 'A';
		
		do {
			System.out.println("\n");
			System.out.println("1. Book Appointment");
			System.out.println("2. Send Message");
			System.out.println("0 Back to Search");
			
			choice = scInt.next().charAt(0);
			
			switch(choice) {
			case '1':
				BookAppointment.showAppointmentSuggestions(profile,LogInPage.currentProfile);
				break;
			
			case '2':
				InboxManager.storeMessage(LogInPage.currentProfile,profile);
				break;
				
			case '0':
				break;
			}
			
		}while(choice!='0');
		
		SearchForPeople.searchForPeopleFn();
		scInt.close();
		
	}
}










