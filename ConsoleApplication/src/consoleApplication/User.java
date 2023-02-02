package consoleApplication;

import java.io.Serializable;
import java.util.ArrayList;

import consoleApplication.Jobs.*;
import SerializableObjects.*;

public class User implements Serializable{
		
	
	private static final long serialVersionUID = 1L;
	
	//Group them in a different object called Basic Information
	String mobileNumber,name,profession,description,country,password;
	float checkInTime,checkOutTime;
	boolean willWorkOnWeekends;
	Job jobObject;
	ArrayList<LanguagesKnown> languagesKnownObjects = new ArrayList<LanguagesKnown>();
	ArrayList<Skills> skillsObjects = new ArrayList<Skills>();
	ArrayList<Education> educationObjects = new ArrayList<Education>();
	ArrayList<String> appointments = new ArrayList<String>();
	//////////////////////////////////
	
	boolean registeredAsAFreelancer;
	boolean inFreelancerMode;
	int userId;
	
	ServiceOffers[] serviceOffersArray = new ServiceOffers[4];
	Inbox inbox = new Inbox();
	Wallet wallet = new Wallet();
	ContractContainer contractContainer = new ContractContainer();
	
	public User(String _mobileNumber) {
		mobileNumber = _mobileNumber;
		
		checkInTime = 9;checkOutTime = 18;
	}
	
	public User(User profile) {
		jobObject = profile.jobObject;
		ServiceOffers serviceOffers = new ServiceOffers();
		serviceOffers.setTypesOfGigsString(jobObject.services(),jobObject.services().length);
		BasicServiceOffers basicServiceOffers = new BasicServiceOffers(jobObject.services().length);
		StandardServiceOffers standardServiceOffers= new StandardServiceOffers(jobObject.services().length);
		PremiumServiceOffers premiumServiceOffers= new PremiumServiceOffers(jobObject.services().length);
		
		serviceOffersArray[0] = serviceOffers;
		serviceOffersArray[1] = basicServiceOffers;
		serviceOffersArray[2] = standardServiceOffers;
		serviceOffersArray[3] = premiumServiceOffers;
		assignNotUpdatedValuesToGigs();
		
		name = profile.name;
		password = profile.password;
		mobileNumber  = profile.mobileNumber;
		profession = profile.profession;
		country = profile.country;
		languagesKnownObjects = profile.languagesKnownObjects;
		educationObjects = profile.educationObjects;
		skillsObjects = profile.skillsObjects;
		
		inFreelancerMode = profile.inFreelancerMode;
		registeredAsAFreelancer = profile.registeredAsAFreelancer;
		userId = profile.userId;
		
		checkInTime = 9;checkOutTime = 18;
	}
	
	//Called From the Constructor. Will show not updated in the gigs menu if the gigs are not assigned by the user
	public void assignNotUpdatedValuesToGigs() {
		String[] notUpdatedString = new String[jobObject.services().length];
		for(int i = 0;i<jobObject.services().length;i++) {
			notUpdatedString[i] = "-";
		}
		
		for(int i = 0;i<serviceOffersArray.length;i++) {
			serviceOffersArray[i].setServices(notUpdatedString);
		}
	}
	

	public void addAppointment(String newAppointment){
		appointments.add(newAppointment);
	}
	
	public String getName() {
		return name;
	}
	
	//METHODS RESPONSIBLE FOR PRINTING PROFILE INFORMATIONS WHILE DISPLAYING PROFILES
	//*********************************************************************************************
	public  void printContentsForSearch() {
		System.out.printf("%-20s  %-20s %n",name,profession);
	}
	
	public void printAppointment() {
		System.out.println(appointments.size());
		for(int i = 0;i<appointments.size();i++) System.out.println(appointments.get(i));
	}
	public void printLanguagesKnown() {
		System.out.println("\nLanguages Known: ");
		for(int i = 0;i<languagesKnownObjects.size();i++) {
			System.out.printf("  %-20s  %-20s%n",languagesKnownObjects.get(i).language,languagesKnownObjects.get(i).proficiency);
		}
	}
	public void printSkills() {
		System.out.println("\nSkills: ");
		for(int i = 0;i<skillsObjects.size();i++) {
			System.out.printf("  %-20s  %-20s%n",skillsObjects.get(i).skill,skillsObjects.get(i).skillLevel);
		}
	}
	
	public void printEducation() {
		System.out.println("\nEducation: ");
		for(int i = 0;i<educationObjects.size();i++) {
			System.out.printf("  %-20s  %-20s%n",educationObjects.get(i).degree,educationObjects.get(i).school);
		}
	}
	
	public void printGigs() {
		//Calling draw gigs cause gigs will be printed in a tabular column
		DrawGigs.drawGigsFn(serviceOffersArray[0],serviceOffersArray[1], serviceOffersArray[2], serviceOffersArray[3]);
	}
	//*********************************************************************************************
	
	
	
	
	
	
}
