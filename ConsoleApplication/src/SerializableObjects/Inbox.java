package SerializableObjects;

import java.util.ArrayList;
import java.io.Serializable;

public class Inbox implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public ArrayList<Message> messages = new ArrayList<Message>();
	public ArrayList<AppointmentRequest> appointmentRequests = new ArrayList<AppointmentRequest>();
	public ArrayList<AcceptedAppointment> acceptedAppointments = new ArrayList<AcceptedAppointment>();
}
