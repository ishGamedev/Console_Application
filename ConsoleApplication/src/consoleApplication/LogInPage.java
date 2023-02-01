package consoleApplication;

import java.io.*;
import java.util.*;


public class LogInPage {
	
		//static and outside main so can accessed by all the methods
		//this is the arrayList that is being stored and read by output and input streams
		static ArrayList<User> userList = new ArrayList<User>();
		static String fileLocation = "/Users/nithish-pt6880/Documents/Work/Console Application/Profiles";
		//Creating a file which points to the directory of our stored objects
		static File file = new File(fileLocation);
		
		//object output stream is not initialized and pointing to null during creation
		//because whenever you instantiate a stream it will override the available contents
		//so before writing we should read which is done in line 34(at the start of main code)
		static ObjectOutputStream oos = null;
		static ObjectInputStream ois = null;
	
		//creating a Profile variable to store which profile we are currently in
		//And To check the password match and mobile number already exists check
		static User currentProfile = null;
		
	public static void showLoginPageFn() throws Exception {
				
				//two scanners for scanning integers and one scanning strings		
				Scanner scInt= new Scanner(System.in);
				Scanner scString= new Scanner(System.in);
				
				//list iterator is to read the data in a organized manner
				ListIterator<User> li = null;
				
				//this choice refers to the choices made in the main menu
				int choice = -1;
				
				//reading the contents in the saved file and copying them to a ArrayList known userList
				if(file.isFile()) {
					ois = new ObjectInputStream(new FileInputStream(file));
					userList = (ArrayList<User>)ois.readObject();
					ois.close();
				}
				
				do {
					//instantiating the iterator
					li = userList.listIterator();
					
					//Options in main menu
					System.out.println("1.Log In");
					System.out.println("2.Sign Up");
					System.out.println("3.Exit");
					
					//users choice is  saved			
					choice = scInt.nextInt();
					
					switch(choice){
					//Log In
						case 1:
							System.out.println("Enter your Name: ");
							String name = scString.nextLine();
							
							
							
							//iterating through the list to find our target (Name)
							while(li.hasNext()) {
								User tempProfile = (User) li.next();
								if(name.equals(tempProfile.name)) {
									currentProfile = tempProfile;//when found match that profile will be stored in crntProfile
									break;
								}
							}
							if(currentProfile != null) {//current profile will be null if we don't find any saved profiles with the given name
								boolean correctPasswordEntered = false;
								
								//Until Correct password is entered we stay on this loop and ask for correct password
								//NOTE : NEED TO ADD A WAY CANCEL FROM THIS LOOP AND BACK TO MAIN MENU PS: Found and Added one
								while(!correctPasswordEntered) {
									System.out.println("Enter password:");
									String localPassword = scString.nextLine();
									if(localPassword.equals(currentProfile.password)) {
										System.out.println("Welcome "+ currentProfile.name);
										correctPasswordEntered = true;//Boolean resets and escapes from the loop
										MainMenu.showMainMenuFn();//passing the current profile to a different
										//function where the screen displays the inside of a function
										choice = 0;//choice sets to 0 to break from the main while loop
									}
									else {
										System.out.println("Wrong Password");
									}							
								}
								
							}
							else {//if we gone through the entire list and didn't found any match then there is no such profile
								//with that name
								System.out.println("No Profile Found");
							}
							break;
						
						//Sign Up
						case 2:
							//boolean so that no multiple accounts can be created with the same number
							boolean profileAlreadyExists = false;
							System.out.println("Enter Mobile Number");
							String newMobileNumber = scString.nextLine();
							
							//checking for the same mobile number in the entire list if we didn't found a match we proceed
							//else we break out the entire loop
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
						
					}
				}while(choice != 0);
				scString.close();
				scInt.close();
	}
	
	public static void writeObject() throws Exception{
		//saving the updated arrayList via output stream
		oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(userList);
		oos.close();
	}
	
	private static void getNewProfileInformation(String newMobileNumber) throws Exception {

		//creating a new profile with the newly given credentials and updating the profile in the ArrayList
		userList.add(SetupProfile.setupProfileFn(newMobileNumber));
		writeObject();
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
