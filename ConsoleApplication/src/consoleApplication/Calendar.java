package consoleApplication;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Calendar {
	
	/*
	 * Flow
	 * 
	 * calendarFn => initiateCalendar=> displayCalendar
	 * 									  ^         ^
	 * 									 ||        ||
	 * 						drawOneAppointBox	  drawHorizontalRule
	 */
	
	//Common Variable used across multiple methods
	private static int currentSundayBehind = 0;
	private static String[] collectionOfDays = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"
				,"Saturday"};
	
	//main function which will be called from mainMenu function
	public static void calendarFn() throws Exception {
		String[] collectionOfAppointments = new String[LogInPage.currentProfile.appointments.size()];
		char choice = 'A';
		Scanner scString = new Scanner(System.in);
		for(int i = 0;i<collectionOfAppointments.length;i++) {
			collectionOfAppointments[i] = LogInPage.currentProfile.appointments.get(i);
		}
		initiateCalendar(collectionOfAppointments);
		do {		
			
			System.out.println("Shaded Boxes are non prioritize hours");
			System.out.println("NOTE: Can still book appointments in Non Working Hours");
			System.out.println();
			System.out.println("1. Next Week");
			System.out.println("2. Previous Week");
			System.out.println("3. Modify Non-Working Hours");
			System.out.println("4. Get iformation about particular Appointment");
			System.out.println("0 Exit");
			
			choice = scString.nextLine().charAt(0);			
			if(choice=='1') {
				currentSundayBehind -= 7;//minus 7 cause today.minus(-7) [- * - = +]
				displayCalendar(currentSundayBehind,collectionOfDays,collectionOfAppointments);
			}
			else if(choice=='2') {
				currentSundayBehind += 7;//[+ * - = -]
				displayCalendar(currentSundayBehind,collectionOfDays,collectionOfAppointments);
			}
			else if(choice=='3') {
				modifyNonWorkingHours();
			}
			else if(choice == '4') {
				printAppointmentInformation();
			}
		}while(choice != '0');
		
		MainMenu.showMainMenuFn();
	}
	
	private static void modifyNonWorkingHours() throws Exception {
		Scanner scString = new Scanner(System.in);
		Scanner scInt = new Scanner(System.in);
		String userChoice = "0";
		do {
			System.out.println("Enter Start working hours: ");
			LogInPage.currentProfile.checkInTime = scInt.nextInt();
			System.out.println("Enter end working hours: ");
			LogInPage.currentProfile.checkOutTime = scInt.nextInt();
			System.out.println("Will you work on Weekends: ");
			System.out.println("1.Yes");
			System.out.println("2.No");
			userChoice = scString.nextLine();
			if(userChoice.equalsIgnoreCase("1") || userChoice.equalsIgnoreCase("Yes")) LogInPage.currentProfile.willWorkOnWeekends = true;
			if(userChoice.equalsIgnoreCase("2") || userChoice.equalsIgnoreCase("No")) LogInPage.currentProfile.willWorkOnWeekends = false;
			LogInPage.writeObject();
		}while(userChoice.equals("0"));
		calendarFn();
	}
	
	private static void initiateCalendar(String[] collectionOfAppointments) {
		Format f= new SimpleDateFormat("EEEE");		
		String day = f.format(new Date());//today's day		
		for(int i = 0;i<collectionOfDays.length;i++) {
			if(day.equalsIgnoreCase(collectionOfDays[i])) currentSundayBehind = i;
			//if we found today at i 4. then Sunday is 4 days behind
		}		
		displayCalendar(currentSundayBehind,collectionOfDays,collectionOfAppointments);
	}
	
	
	private static void displayCalendar(int start,String[] days,String[] collectionOfAppointments){	
		System.out.print("        ");
		LocalDate today = LocalDate.now();
		String[] thisWeek = new String[7];//this will store all the available dates in this week
		
		for(int i =0;i<7;i++) {
			//the first element will be this week Sunday's date
			//when start decreases date increases 
			String tempDate = today.minusDays(start).format(DateTimeFormatter.ISO_DATE);
			thisWeek[i] = tempDate;
			System.out.print(tempDate.subSequence(tempDate.length()-2, tempDate.length())
					+ days[i].substring(0,3)+" | ");
			start--;
		}
		
		//The above half is responsible for the dates and days printing at the top
		drawHorizontalLine();
		//the below half takes care of the time periods in each day
		float printHours = 0.00f;
		while(printHours<24) {
			System.out.printf("%6.2f",printHours);//this printf only prints the time not any other logic
			drawOneAppointmentBox(printHours,thisWeek,collectionOfAppointments);//this line takes care of all the logic
			printHours++;
			drawHorizontalLine();
		}
		
		System.out.println();
	}
	
	/*
	 * Logic 
	 * We will take one time(Say 0.00,1.00,2.00) and check what are days we have appointment at this particular time
	 * Example:
	 * 	we will take time 0.00 and check what days in this week have an appointment at 0.00
	 */
	public static void drawOneAppointmentBox(float crntTime,String[] thisWeek,String[] collectionOfAppointments){
		//THIS RESETS FOR EVERY DIFFERENT HOUR (ROW)
		// 7 BOOLEAN FOR 7 COLUMNS.IF TRUE, CHECK MARK WILL BE PRINTED
		boolean[] appointmentIsThere = new boolean[7];
		
		
		
		for(int i = 0;i<collectionOfAppointments.length;i++) {
			//First 'IF' is to find whether we have an appointment in the current given time(row)
			if(crntTime ==  Integer.parseInt(collectionOfAppointments[i].substring(0,2))) {
				//if we found a match we will iterate the list of DATES in this week and search for a match
				for(int j= 0;j<thisWeek.length;j++) {
					if(thisWeek[j].equals(collectionOfAppointments[i].substring(3, 13))) {
						appointmentIsThere[j] = true;
					}
				}
			}
		}
		
		for(int i = 0;i<7;i++) {
			if(appointmentIsThere[i]==true) System.out.print("   \u2713   |");//check mark
			else if(crntTime<LogInPage.currentProfile.checkInTime || crntTime>=LogInPage.currentProfile.checkOutTime ||((i==0 || i==6) && !LogInPage.currentProfile.willWorkOnWeekends))
			{
					System.out.print("  /////|");//empty space
			}
			else System.out.print("       |");//empty space
		}
	}
	
	
	//horizontal rule
	private static void drawHorizontalLine() {
		System.out.println();
		System.out.print("        ");
		int i = 0;
		while(i<55) {
			System.out.print("-");
			i++;
		}
		System.out.println();
	}
	
	private static void printAppointmentInformation(){
		Scanner scString = new Scanner(System.in);
		
		System.out.println("Enter the date of the appointment: ");
		System.out.println("DD MM YYYY");
		String date = scString.nextLine();
		System.out.println("Enter the time of the apointment: ");
		String time = scString.nextLine();
		if(time.length()<2) time = "0"+time;
		
		for(int i = 0;i<LogInPage.currentProfile.inbox.acceptedAppointments.size();i++) {
			String currentAppointmentDate = LogInPage.currentProfile.inbox.acceptedAppointments.get(i).date+" "+
											LogInPage.currentProfile.inbox.acceptedAppointments.get(i).month+" "+
											LogInPage.currentProfile.inbox.acceptedAppointments.get(i).year;
			if(currentAppointmentDate.equals(date)) {
				for(int j = 0;j<LogInPage.currentProfile.inbox.acceptedAppointments.get(i).bookedHours.size();j++) {
					if(LogInPage.currentProfile.inbox.acceptedAppointments.get(i).bookedHours.get(j).equals(time)) {
						LogInPage.currentProfile.inbox.acceptedAppointments.get(i).printAppointment();
						return;
					}
				}
			}
			System.out.println("\nNo Appointment found in the given time period\n");
		}
	}
}
















