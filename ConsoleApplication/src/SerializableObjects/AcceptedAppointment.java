package SerializableObjects;

import java.io.Serializable;
import java.util.ArrayList;

import consoleApplication.User;

public class AcceptedAppointment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public String title;
	public String sender;
	public String year;
	public String month;
	public String date;
	public String startTime;
	public String endTime;
	public String description;
	public String sentAtTimeDate;
	public User senderProfile;
	public User receiverProfile;
	
	public ArrayList<String> bookedHours = new ArrayList<String>();
	
	public AcceptedAppointment(AppointmentRequest appointmentRequest){
		this.title = appointmentRequest.title;
		this.sender = appointmentRequest.sender;
		this.year = appointmentRequest.year;
		this.month = appointmentRequest.month;
		this.date = appointmentRequest.date;
		this.startTime = appointmentRequest.startTime;
		this.endTime = appointmentRequest.endTime;
		this.description = appointmentRequest.description;
		this.sentAtTimeDate = appointmentRequest.sentAtTimeDate;
		this.senderProfile = appointmentRequest.senderProfile;
		this.receiverProfile = appointmentRequest.receiverProfile;
		for(int i = 0;i<appointmentRequest.bookedHours.size();i++) {
			this.bookedHours.add(appointmentRequest.bookedHours.get(i));
		}
	}
	
	public void printAppointment() {
		System.out.println("\nTitle:"+title+"\n");
		System.out.println("Description: \n"+"\t"+description+"\n");
		System.out.println("APPOINTMENT AT:"+date+"/"+month+"/"+year+" FROM:"+startTime+" To:"+endTime);
		System.out.println("\nBy");
		System.out.println("  "+sender);
		System.out.println("  send at "+sentAtTimeDate+"\n");
	}
}
