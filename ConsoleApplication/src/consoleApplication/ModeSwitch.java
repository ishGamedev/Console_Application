package consoleApplication;

import java.util.Scanner;

public class ModeSwitch {
	
	public static void modeSwitchfn() throws Exception {
		Scanner scString = new Scanner(System.in);
		String userInput = "";
		if(!LogInPage.currentProfile.registeredAsAFreelancer) {
			System.out.println("Register as a Free Lancer to enable mode switching");
			do {
				
				System.out.println("1. To Create a FreeLancing Account");
				System.out.println("0. Exit");
				
				userInput = scString.nextLine();
				switch(userInput) {
					case"1":
						User user = SetupProfile.setupProfileFreelancerfn(LogInPage.currentProfile,LogInPage.currentProfile.userId);  
						LogInPage.currentProfile = user;
						LogInPage.userList.remove(user.userId);
						LogInPage.userList.add(user.userId,user);
						LogInPage.writeObject();
						MainMenu.showMainMenuFn();
						break;
				}
			}while(!userInput.equals("0"));
			
		}
		
		else {
			do {
				if(LogInPage.currentProfile.inFreelancerMode){
					System.out.println("Current Mode: "+ "FreeLancer");
				}
				else {
					System.out.println("Current Mode: "+ "Client");
				}
				
				System.out.println("1. To Swtich Modes");
				System.out.println("0. Exit");
				
				userInput = scString.nextLine();
				
				switch(userInput) {
					case"1":
						System.out.println("Mode Switched");
						LogInPage.currentProfile.inFreelancerMode = !LogInPage.currentProfile.inFreelancerMode;
						LogInPage.writeObject();
						break;
				}
			}while(!userInput.equals("0"));
			
		}
	}
}
