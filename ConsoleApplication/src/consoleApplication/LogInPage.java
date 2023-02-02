package consoleApplication;

import java.io.*;
import java.util.*;


public class LogInPage {
	
		//Main List
		static ArrayList<User> userList = new ArrayList<User>();
		
		
		static ArrayList<User> freeLancerList = new ArrayList<User>();
		static String fileLocation = "/Users/nithish-pt6880/Documents/Work/Console Application/Profiles";
		static File file = new File(fileLocation);
		
		//object output stream is not initialized and pointing to null during creation
		//because whenever you instantiate a stream it will override the available contents
		//so before writing we should read which is done in line 34(at the start of main code)
		static ObjectOutputStream oos = null;
		static ObjectInputStream ois = null;
	
		static User currentProfile = null;
		
	public static void showLoginPageFn() throws Exception {
					
				Scanner scInt= new Scanner(System.in);
				Scanner scString= new Scanner(System.in);
				
				ListIterator<User> li = null;
				
				int choice = 0;
			
				//Reading
				if(file.isFile()) {
					ois = new ObjectInputStream(new FileInputStream(file));
					userList = (ArrayList<User>)ois.readObject();
					ois.close();
				}
				
				currentProfile = null;//Resetting
				
				freeLancerList.clear();
				do {
					li = userList.listIterator();
				
					System.out.println("1.Log In");
					System.out.println("2.Sign Up");
					System.out.println("3.Exit");
					
					choice = scInt.nextInt();
					
					switch(choice){
					//Log In
						case 1:
							System.out.println("Enter your Name: ");
							String name = scString.nextLine();
							
							//Iterate to find profile match(Name)
							while(li.hasNext()) {
								User tempProfile = (User) li.next();
								if(name.equals(tempProfile.name)) {
									currentProfile = tempProfile;
									break;
								}
							}
							if(currentProfile != null) {
								boolean correctPasswordEntered = false;
								while(!correctPasswordEntered) {
									System.out.println("Enter password:");
									String localPassword = scString.nextLine();
									if(localPassword.equals(currentProfile.password)) {
										System.out.println("Welcome "+ currentProfile.name);
										correctPasswordEntered = true;
										getAllTheFreeLancers();
										MainMenu.showMainMenuFn();
										choice = 0;
									}
									else {
										System.out.println("Wrong Password");
									}							
								}
								
							}
							
							else {
								System.out.println("No Profile Found");
							}
							break;
						
						//Sign Up
						case 2:
							boolean profileAlreadyExists = false;
							System.out.println("Enter Mobile Number");
							String newMobileNumber = scString.nextLine();
							
							//checking for the same mobile number in the entire list if we didn't found a match we proceed
							while(li.hasNext()) {
								User localProfile = (User) li.next();
								if(newMobileNumber.equals(localProfile.mobileNumber)) {
									System.out.println("An Account is already Registered with this Mobile Number");
									profileAlreadyExists = true;
									break;
								}
							}								
							
							if(!profileAlreadyExists) {
								getNewProfileInformation(newMobileNumber);
														
							}					
							break;
							
						case 3:
							choice = 0;//to Exit
							break;
						
					}
				}while(choice != 0);
	}
	
	public static void writeObject() throws Exception{
		//saving the updated arrayList via output stream
		oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(userList);
		oos.close();
	}
	
	//Setting Up Profile
	private static void getNewProfileInformation(String newMobileNumber) throws Exception {
		userList.add(SetupProfile.setupProfileFn(newMobileNumber));
		writeObject();
	}
	
	public static void getAllTheFreeLancers(){
		for(int i = 0;i<userList.size();i++) {
			if(userList.get(i).registeredAsAFreelancer && userList.get(i).userId != currentProfile.userId) {
				freeLancerList.add(userList.get(i));
			}
		}
	}
	
	//Function to read object file for faster prototyping
	public static void onlyReadForDeveloping() throws Exception {
		//Creating a file which points to the directory of our stored objects
		File file = new File(fileLocation);

		ObjectInputStream ois = null;
		//reading the contents in the saved file and copying them to a ArrayList known userList
		if(file.isFile()) {
			ois = new ObjectInputStream(new FileInputStream(file));
			userList = (ArrayList<User>)ois.readObject();
			ois.close();
		}
		
	}
	
	

}
