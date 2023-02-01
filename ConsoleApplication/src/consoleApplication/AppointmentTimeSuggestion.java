package consoleApplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import SerializableObjects.*;



public class AppointmentTimeSuggestion {
	
		public static ArrayList<ArrayList<Float[]>> finalResults = new ArrayList<ArrayList<Float[]>>();
		public static String[] availableDays = new String[3];
		
		//user1 = profile
		//user = LogInPage.currentProfile
		public static ArrayList<ArrayList<Float[]>> appointmentTimeSuggestionFn(User user1,User user2,float durationForMeeting){
			finalResults.clear();
			LocalTime crntTime = LocalTime.now();
			LocalDate today = LocalDate.now();
			String tempTime = crntTime.toString().substring(0,2);
			int index = 0;
			int days = 0;
			while(index<3) {
				String tempDate = today.plusDays(days).format(DateTimeFormatter.ISO_DATE);
				freeTimesForOneDay(user1,user2,tempDate,durationForMeeting);
				index = finalResults.size();
				days++;
			}
			return finalResults;
		}
	
		public static void freeTimesForOneDay(User user1,User user2,String date,float durationForMeeting){
			
			ArrayList<Float> arrayList1 = new ArrayList<Float>();
			ArrayList<Float> arrayList2 = new ArrayList<Float>();
	
			
			for(int i = 0;i<user1.inbox.acceptedAppointments.size();i++) {
				String tempDate = user1.inbox.acceptedAppointments.get(i).year+"-"+user1.inbox.acceptedAppointments.get(i).month+"-"+
									user1.inbox.acceptedAppointments.get(i).date;
				if(tempDate.equals(date)) {
					arrayList1.add(Float.parseFloat(user1.inbox.acceptedAppointments.get(i).startTime));
					arrayList1.add(Float.parseFloat(user1.inbox.acceptedAppointments.get(i).endTime));
				}
			}
			
			
			int arrayIterator = 0;
			float[][] acceptedAppointments1 = new float[arrayList1.size()/2][2];
			for(int i = 0;i<arrayList1.size();i+=2) {
				acceptedAppointments1[arrayIterator][0] = hourToDecimal(arrayList1.get(i));
				acceptedAppointments1[arrayIterator][1] = hourToDecimal(arrayList1.get(i+1));
				arrayIterator++;
			}
			
			Arrays.sort(acceptedAppointments1, (a, b) -> Double.compare(a[0], b[0]));
			
			
			
			for(int i = 0;i<user2.inbox.acceptedAppointments.size();i++) {
				String tempDate = user2.inbox.acceptedAppointments.get(i).year+"-"+user2.inbox.acceptedAppointments.get(i).month+"-"+
						user2.inbox.acceptedAppointments.get(i).date;
				if(tempDate.equals(date)) {
					arrayList2.add(Float.parseFloat(user2.inbox.acceptedAppointments.get(i).startTime));
					arrayList2.add(Float.parseFloat(user2.inbox.acceptedAppointments.get(i).endTime));
				}
			}
			
			float[][] acceptedAppointments2 = new float[arrayList2.size()/2][2];
			
			arrayIterator =0;
			for(int i = 0;i<arrayList2.size();i+=2) {
				acceptedAppointments2[arrayIterator][0] = (float) Math.ceil(hourToDecimal(arrayList2.get(i)));
				acceptedAppointments2[arrayIterator][1] = (float) Math.ceil(hourToDecimal(arrayList2.get(i+1)));
				arrayIterator = 0;
			}
			
			Arrays.sort(acceptedAppointments2, (a, b) -> Double.compare(a[0], b[0]));
			
			float[] person1Boundries = { user1.checkInTime,user1.checkOutTime};
			float[] person2Boundries = { user2.checkInTime,user2.checkOutTime};
			
			ArrayList<Float[]> person1FreeTimes = availableTimesForEachPersonfn(acceptedAppointments1,durationForMeeting,person1Boundries);
			ArrayList<Float[]> person2FreeTimes = availableTimesForEachPersonfn(acceptedAppointments2,durationForMeeting,person2Boundries);

			ArrayList<Float[]> finalResult = finalAvailableTimesfn(person1FreeTimes,person2FreeTimes);
			
			
			if(finalResult.size()!=0) {
				availableDays[finalResults.size()] = date;
				finalResults.add(finalResult);
			}
			else return;

		}
	
		//function to calculate available free times of each individual persons
		//calendar refers to the already allocated bookings
		public static ArrayList<Float[]> availableTimesForEachPersonfn(float[][] calendar,float meetingDuration,float[] boundaries){
			ArrayList<Float[]> result = new ArrayList<Float[]>(); 
			
			
			if(calendar.length<=0) {
				float low = boundaries[0],high = boundaries[1];
				
				while(high - low >= meetingDuration) {
					result.add(new Float[] {low,low + meetingDuration});
					if(meetingDuration<1) {
						low = low + meetingDuration;
					}
					else {
						low = low+1;
					}
				}
				return result;
			}
			
			//logic to check if there is time before the starting of first meeting and morning boundary
			if(boundaries[0] + meetingDuration <= calendar[0][0]) {
				//low and high pointers are used to separate each chunk of time intervals into
				//time intervals with exact meeting durations which can be further used to find identical-
				//- time intervals from another person
				float low = boundaries[0],high = calendar[0][0];
				while(high - low >= meetingDuration) {
					result.add(new Float[]{low,low + meetingDuration});
					//after adding one valid time interval the low pointer will get updated
					if(meetingDuration<1) {
						low = low + meetingDuration;
					}
					else {
						low = low+1;
					}
				}
			}
			
			//logic to check if there is time in between each time frame
			for(int i = 0;i<calendar.length-1;i++) {
				if(calendar[i][1] + meetingDuration <= calendar[i+1][0]) {
					float low = calendar[i][1],high = calendar[i+1][0];
					while(high - low >= meetingDuration) {
						result.add(new Float[] {low,low + meetingDuration});
						if(meetingDuration<1) {
							low = low + meetingDuration;
						}
						else {
							low = low+1;
						}
					}
				}
			}

			
			//logic to check if there is time after the last meeting and evening boundary
			if(calendar[calendar.length-1][calendar[0].length-1] + meetingDuration <= boundaries[1]) {
				float low = calendar[calendar.length-1][calendar[0].length-1],
					//float low = boundaries[0],
						  high = boundaries[1];
				while(high - low >= meetingDuration) {
					result.add(new Float[] {low,low + meetingDuration});
					if(meetingDuration<1) {
						low = low + meetingDuration;
					}
					else {
						low = low+1;
					}
				}
			}
			
			return result;
			
		}
		
		public static ArrayList<Float[]> finalAvailableTimesfn(ArrayList <Float[]>person1FreeTimes,
													ArrayList<Float[]> person2FreeTimes) {
			
			ArrayList<Float[]> result = new ArrayList<Float[]>(); 
			
			for(int i = 0;i<person1FreeTimes.size();i++){
				for(int j = 0;j<person2FreeTimes.size();j++){
					if(Math.abs(person1FreeTimes.get(i)[0] - person2FreeTimes.get(j)[0]) < 0.1) {
						result.add(new Float[] {person1FreeTimes.get(i)[0],person1FreeTimes.get(i)[1]});
					}
				}
			}		
			return result;
		}
		
		private static Float hourToDecimal(Float value) {
			String numberD = String.valueOf(value);
			numberD = numberD.substring(numberD.indexOf("."));
			float f = Float.parseFloat(numberD);
			f /= 0.6f;
			value = (float) Math.floor(value);
			value += f;
			
			return value;
		}
		
		private static void printArrayList(ArrayList<Float[]> s) {
			System.out.println(Arrays.deepToString(s.toArray()));
		}
		
		
}
