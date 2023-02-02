package consoleApplication;

import java.util.Scanner;

import SerializableObjects.*;
import consoleApplication.Jobs.*;

public class SetupProfile {
	
	public static User setupProfileFn(String newMobileNumber) {
		Scanner scString = new Scanner(System.in);
		Scanner scInt = new Scanner(System.in);
		
		//A temporary Profile Object is created and all the values will be assigned to this object.
		//At the end this temporary profile object is used to construct a final profile object which will be written in Output Stream
		User tempProfileHolder = new User(newMobileNumber);
		
		System.out.println("Enter User Name: ");
		tempProfileHolder.name = scString.nextLine();
		
		System.out.println("Create Password: ");
		tempProfileHolder.password = scString.nextLine();	
		
		System.out.println("Enter your Country: ");
		tempProfileHolder.country = scString.nextLine();
		String tempStringHolder = null;
		String wantToContinue = "";
		
		//LANGUAGE
				do {
					System.out.println("Enter the Language you know: ");
					tempStringHolder = scString.nextLine();
					String languageKeyString = tempStringHolder;
					System.out.println("Your proficiency in "+tempStringHolder+"(Novice Intermediate Expert Native) : ");
					tempStringHolder = scString.nextLine();
					tempProfileHolder.languagesKnownObjects.add(new LanguagesKnown(languageKeyString,tempStringHolder));
					System.out.println("Do you want to enter more languages: Yes/No");
					wantToContinue = scString.nextLine();
				}while(tempStringHolder != null && wantToContinue.equalsIgnoreCase("Yes"));
	
		tempProfileHolder.inFreelancerMode = false;
		tempProfileHolder.registeredAsAFreelancer = false;
		tempProfileHolder.userId = LogInPage.userList.size();
				
		return tempProfileHolder;
	}
	
	public static User setupProfileFreelancerfn(User user,int _userId){
		
		//creating objects for all the available jobs
		Accountant accountant = new Accountant(); BusinessConsultant businessConsultant = new BusinessConsultant();
	    CopyWriter copyWriter = new CopyWriter(); DataAnalyst dataAnalyst = new DataAnalyst();
		DigitalMarketingConsultant digitalMarketingConsultant = new DigitalMarketingConsultant();
		Editor editor = new Editor(); Photographer photographer = new Photographer(); Programmer programmer = new Programmer();
		SocialMediaManager socialMediaManager = new SocialMediaManager(); WebDesigner webDesigner = new WebDesigner();
		
		//Upcasting the objects and storing them in an array
		Job[] allJobs = {accountant,businessConsultant,copyWriter,dataAnalyst,digitalMarketingConsultant,
						editor,photographer,programmer,socialMediaManager,webDesigner};
		
		
		Scanner scString = new Scanner(System.in);
		Scanner scInt = new Scanner(System.in);

		User tempProfileHolder = user;
		
		System.out.println("Choose Your Profession");
		//all available jobs will be listed
		for(int i = 0;i<allJobs.length;i++) {
			System.out.println(i+1+". "+allJobs[i].getName());
		}
		
		int userChoosingProfession = 0;
		userChoosingProfession = scInt.nextInt();	
		
		//Assigning the chosen job object to the temporary profile object
		tempProfileHolder.jobObject = allJobs[userChoosingProfession-1];
		tempProfileHolder.profession = tempProfileHolder.jobObject.getName();	
		
		String wantToContinue = "";
		String tempStringHolder = null;
		
		
		//Calling the StoreSkillLevels function to gather information about the user's Skills
		StoreSkillLevels.storeSkillLevelsFn(tempProfileHolder,tempProfileHolder.jobObject.skills());
		wantToContinue = "";
		tempStringHolder = null;
		
		//EDUCATION
		do {
			System.out.println("Enter your Education one by one(X XII Degree): ");
			tempStringHolder = scString.nextLine();
			String educationKeyString = tempStringHolder;
			System.out.println("Where did you studied "+tempStringHolder+"(School Name) :");
			tempStringHolder = scString.nextLine();
			tempProfileHolder.educationObjects.add(new Education(educationKeyString,tempStringHolder));
			System.out.println("Do you want to enter more about your Education: Yes/No");
			wantToContinue = scString.nextLine();
			
		}while(tempStringHolder != null && wantToContinue.equalsIgnoreCase("Yes"));
		
		tempProfileHolder.inFreelancerMode = true;
		tempProfileHolder.registeredAsAFreelancer = true;
		tempProfileHolder.userId = _userId;
		//Creating a new Profile object and returning the object which will be stored in userList in LOGIN PAGE.
		return new User(tempProfileHolder);
	}
}
