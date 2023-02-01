package consoleApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import SerializableObjects.*;

public class InboxManager {
	
	//Called from "showMainMenufn" function from Main Menu class
	public static void inboxManagerFn(Inbox inbox) throws Exception{
		Scanner scInt = new Scanner(System.in);
		String userInput = "";
		
		do {
			System.out.println("1.Show Messages");
			System.out.println("2.Show Appointments");
			System.out.println("0.Exit");
			userInput = scInt.nextLine();
			switch(userInput) {
				case"1":
					displayMessages(inbox);
					break;
				
				case "2":
					displayAppointments(inbox);
					break;
			}
			
			
		}while(!userInput.equals("0"));
		MainMenu.showMainMenuFn();
	}
	
	//Called from "DisplayProfile" function in "DisplayProfile" class
	public static void storeMessage(User sender,User reciever) throws Exception{
		Message message = new Message();
		
		Scanner scString = new Scanner(System.in);
		String usersInput = "";
		
		System.out.println("Enter the title for this message: ");
		usersInput = scString.nextLine();
		message.title = usersInput;
		
		
		message.sender = sender.name;
	    
	    System.out.println("Enter the message: ");
		usersInput = scString.nextLine();
		
		message.content = usersInput;
		message.senderProfile = sender;
		
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss dd-MMM-yyyy");
		String formattedDate = dateTime.format(myFormatObj);
		
	    message.timeDate = formattedDate;

		reciever.inbox.messages.add(message);
		LogInPage.writeObject();
	}
	
	
	private static void displayMessages(Inbox inbox) throws Exception{
		
		if(inbox.messages.size()<=0) {
			System.out.println("\nInbox is Empty \n");
			MainMenu.showMainMenuFn();
			return;
		}
		
		Scanner scInt = new Scanner(System.in);
		
		for(int i = inbox.messages.size()-1;i>=0;i--) {
			System.out.println(i+1 + ". "+inbox.messages.get(i).title);
		}
		System.out.println("Enter the message number to view: ");
		System.out.println("0 Exit");
		int userChoice = scInt.nextInt();
		if(userChoice == 0) inboxManagerFn(inbox);
		
		showSpecificMessage(inbox,inbox.messages.get(userChoice-1),userChoice-1);
	}
	
	private static void displayAppointments(Inbox inbox) throws Exception{
		if(inbox.appointmentRequests.size()<=0) {
			System.out.println("\nYou don't have any pending appiontments\n");
			MainMenu.showMainMenuFn();
			return;
		}
		
		Scanner scInt = new Scanner(System.in);
		
		for(int i = inbox.appointmentRequests.size()-1;i>=0;i--) {
			System.out.println(i+1 + ". "+inbox.appointmentRequests.get(i).title);
		}
		
		System.out.println("Enter the appointment number to view: ");
		System.out.println("0 Exit");
		int userChoice = scInt.nextInt();
		if(userChoice == 0) MainMenu.showMainMenuFn();
		
		showSpecificAppointment(inbox,inbox.appointmentRequests.get(userChoice-1),userChoice-1);
	}
	
	
	private static void showSpecificAppointment(Inbox inbox,AppointmentRequest appointmentRequest,int index) throws Exception{
		appointmentRequest.printAppointment();
		
		Scanner scString = new Scanner(System.in);
		System.out.println("1. Accept Appointment");
		System.out.println("2. Reject Appointment");
		System.out.println("0. Return");
		String userInput = scString.nextLine();
		
		switch(userInput) {
			
			//Accept
			case"1":
				String newAppointment = "";
				for(int i = 0;i<appointmentRequest.bookedHours.size();i++) {
					newAppointment = appointmentRequest.bookedHours.get(i)+" "+appointmentRequest.year+
							"-"+appointmentRequest.month+"-"+appointmentRequest.date;
					BookAppointment.addAppointment(appointmentRequest.senderProfile, newAppointment);
					BookAppointment.addAppointment(LogInPage.currentProfile, newAppointment);
				}
				AcceptedAppointment acceptedAppointment = new AcceptedAppointment(appointmentRequest);
				inbox.acceptedAppointments.add(acceptedAppointment);
				acceptedAppointment.senderProfile.inbox.acceptedAppointments.add(acceptedAppointment);
				appointmentAcceptedMessage(inbox,index);
				deleteAppointment(inbox,index);
				displayAppointments(inbox);
				break;
			
			//Reject
			case "2":
				appointmentRejectedMessage(inbox,index);
				deleteAppointment(inbox,index);
				displayAppointments(inbox);
				break;
			
			//Exit
			case "0":
				displayAppointments(inbox);
				break;
		}
	}
	
	private static void deleteAppointment(Inbox inbox,int index) throws Exception {
		inbox.appointmentRequests.remove(index);
		LogInPage.writeObject();
	}
	private static void appointmentAcceptedMessage(Inbox inbox,int index) {
		Message message = new Message();
		String content = "Your appointment request to "+LogInPage.currentProfile.name+" has been accepted. Appointmeent is fixed on "+
				inbox.appointmentRequests.get(index).date+"-"+inbox.appointmentRequests.get(index).month+"-"+inbox.appointmentRequests.get(index).year+
							" From: "+inbox.appointmentRequests.get(index).startTime + " To: "+inbox.appointmentRequests.get(index).endTime+".";
		
		message.title = "Appointment Request Accepted";
		message.sender = "AutoGenerated";
		message.content = content;
		message.senderProfile = null;
		
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss dd-MMM-yyyy");
		String formattedDate = dateTime.format(myFormatObj);
		
	    message.timeDate = formattedDate;

		
	    inbox.appointmentRequests.get(index).senderProfile.inbox.messages.add(message);
	}
	
	private static void appointmentRejectedMessage(Inbox inbox,int index) {
		Message message = new Message();
		String content = "Appointmeent request on "+
						inbox.appointmentRequests.get(index).date+"-"+inbox.appointmentRequests.get(index).month+"-"+inbox.appointmentRequests.get(index).year+
							" From: "+inbox.appointmentRequests.get(index).startTime + " To: "+inbox.appointmentRequests.get(index).endTime+" sent to "+
							LogInPage.currentProfile.name+" has been rejected.";
		
		message.title = "Appointment Request Rejected";
		message.sender = "AutoGenerated";
		message.content = content;
		message.senderProfile = null;
		
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss dd-MMM-yyyy");
		String formattedDate = dateTime.format(myFormatObj);
		
	    message.timeDate = formattedDate;

		
	    inbox.appointmentRequests.get(index).senderProfile.inbox.messages.add(message);
	}
	
	private static void showSpecificMessage(Inbox inbox,Message message,int index) throws Exception{
		message.printMessage();
		
		Scanner scString = new Scanner(System.in);
		System.out.println("1.To reply");
		System.out.println("2.To delete");
		System.out.println("3.To Exit");
		String userInput = scString.nextLine();
		switch(userInput) {
			case"1":
				if(message.senderProfile != null) {
					storeMessage(LogInPage.currentProfile,message.senderProfile);
				}
				else System.out.println("This is an Auto Generated Message you can't reply to this");
				break;
			case "2":
				deleteMessage(inbox,index);
				displayMessages(inbox);
			case"3":
				displayMessages(inbox);
		}
		
	}
	
	private static void deleteMessage(Inbox inbox,int index) throws Exception {
		inbox.messages.remove(index);
		System.out.println("Message Deleted\n");
		LogInPage.writeObject();
	}
}











