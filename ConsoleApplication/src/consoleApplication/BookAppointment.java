package consoleApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import SerializableObjects.AppointmentRequest;

public class BookAppointment {
	
		static int chosenDate = 0;
	
	public static void addAppointment(User profile,String newAppointment) throws Exception {
		profile.addAppointment(newAppointment);
		LogInPage.writeObject();
	}
		
	//user1 = profile (receiver)
	//user2 = LogInPage.currentProfile (Sender)
	public static void showAppointmentSuggestions(User user1,User user2) throws Exception{
		Scanner scString = new Scanner(System.in);
		Scanner scInt = new Scanner(System.in);
		AppointmentRequest appointmentRequest = new AppointmentRequest();
		String userInput = "";
		
		float startTime = 0,endTime=0;
		
		do {
			float meetingDurationHours = 0,meetingDurationMinutes = 0,totalMeetingDuration;
			System.out.println("Duration of the Meeting Hours: ");
			meetingDurationHours = scInt.nextFloat();
			meetingDurationHours *= 60;
			
			System.out.println("Duration of the Meeting Minutes: ");
			System.out.println("1) 00");
			System.out.println("2) 15");			
			System.out.println("3) 30");
			System.out.println("4) 45");
			userInput = scString.nextLine();
			
			switch(userInput) {
				case "1":
				case "00":
					meetingDurationMinutes = 0;
					break;
				
				case "2":
				case"15":
					meetingDurationMinutes = 15;
					break;
				
				case"3":
				case"30":
					meetingDurationMinutes = 30;
					break;
				
				case"4":
				case"45":
					meetingDurationMinutes = 45;
					break;
				
			}
			
			totalMeetingDuration = (meetingDurationHours+meetingDurationMinutes)/60;
			
			if(totalMeetingDuration<=0) {
				System.out.println("Meeting Duration Can't be zero");
				userInput = "No";
				continue;
			}
			
			appointmentRequest.meetingDuration = totalMeetingDuration;
			
			System.out.println("These are the available common free times: ");
			ArrayList<ArrayList<Float[]>> suggestedTimes = new ArrayList<ArrayList<Float[]>>();
			suggestedTimes = AppointmentTimeSuggestion.appointmentTimeSuggestionFn(user1,user2,appointmentRequest.meetingDuration);
			printResultFn(suggestedTimes);
			
			int chosenInt = scInt.nextInt();
			Float[] chosenTimePeriod = iteratingForTheChosenTimePeriod(suggestedTimes,chosenInt);
			startTime = decimalToHour(chosenTimePeriod[0]);
			endTime = decimalToHour(chosenTimePeriod[1]);
			System.out.println("Fix Appoinment at:"+AppointmentTimeSuggestion.availableDays[chosenDate]
								+" From:"+startTime+"0 To:"+endTime);
			
			System.out.println("1.Yes");
			System.out.println("2.No");
			userInput = scString.nextLine();
		}while(userInput.equals("2")||userInput.equalsIgnoreCase("No"));
		

		System.out.println("Title for this request: ");
		userInput = scString.nextLine();
		appointmentRequest.title = userInput;
		

		System.out.println("Description for this appointment: ");
		userInput = scString.nextLine();
		appointmentRequest.description = userInput;
		
		appointmentRequest.date = AppointmentTimeSuggestion.availableDays[chosenDate].substring(0,2);
		appointmentRequest.month = AppointmentTimeSuggestion.availableDays[chosenDate].substring(3,5);
		appointmentRequest.year = AppointmentTimeSuggestion.availableDays[chosenDate].substring(6,10);
		
		appointmentRequest.startTime = String.valueOf(startTime);
		appointmentRequest.endTime = String.valueOf(endTime);
		
		if(endTime %1 !=0) {
			endTime++;
		}
		
		for(int i = (int) startTime;i<(int)endTime;i++) {
			if(i<10) appointmentRequest.bookedHours.add("0"+String.valueOf(i));
			else appointmentRequest.bookedHours.add(String.valueOf(i));
		}
		
		appointmentRequest.sender = LogInPage.currentProfile.name;
		appointmentRequest.senderProfile = LogInPage.currentProfile;
		appointmentRequest.receiverProfile = user1;
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss dd-MMM-yyyy");
		String formattedDate = dateTime.format(myFormatObj);
		
		appointmentRequest.sentAtTimeDate = formattedDate;
		user1.inbox.appointmentRequests.add(appointmentRequest);
		LogInPage.writeObject();
	}
		
	public static Float[] iteratingForTheChosenTimePeriod(ArrayList<ArrayList<Float[]>> listToPrint,int chosenInt) {
		int index = 0;
		for(int i = 0;i<listToPrint.size();i++) {
			chosenDate = i;
			for(int j = 0;j<listToPrint.get(i).size();j++){
				index++;
				if(index == chosenInt) return listToPrint.get(i).get(j);
			}
		}
		System.out.println("No match for the chosen time period found in BookAppointment class");
		return null;
	}
		
	public static void printResultFn(ArrayList<ArrayList<Float[]>> listToPrint) {
		int index = 1;
		System.out.println("\n");
		System.out.println("Dd-MM-YYYY");
		for(int i = 0;i<listToPrint.size();i++) {
			System.out.println(AppointmentTimeSuggestion.availableDays[i]);
			for(int j = 0;j<listToPrint.get(i).size();j++) {
				Float startDecimalToHourValue = decimalToHour(listToPrint.get(i).get(j)[0]);
				Float endDecimalToHourValue = decimalToHour(listToPrint.get(i).get(j)[1]);
				System.out.print("\t"+index+". ["+startDecimalToHourValue+"-"+endDecimalToHourValue+"]\n");
				index++;
			}
			System.out.println("\n");
		}
		System.out.println("Enter the number of the time period you want to choose: ");
	}


	private static Float decimalToHour(Float value) {
		String numberD = String.valueOf(value);
		numberD = numberD.substring(numberD.indexOf("."));
		float f = Float.parseFloat(numberD);
		f *= 0.6f;
		value = (float) Math.floor(value);
		value += f;
		
		return value;
	}
}