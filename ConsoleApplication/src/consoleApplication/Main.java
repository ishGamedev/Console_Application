package consoleApplication;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	//GitHub token => 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
	public static void main(String[] args) throws Exception {
		LogInPage.showLoginPageFn();
		
		LocalDate date = LocalDate.now();
		String s = date.format(DateTimeFormatter.ofPattern("dd-MM-uuuu"));
		
		System.out.println(s);
		
		//Calendar.calendarFn();
		//LogInPage.onlyReadForDeveloping();
		//LogInPage.userList.get(0).inbox.messages.add("Hello");
		//LogInPage.userList.get(0).inbox.messages.add("Hi");
		//for(int i = 0;i<LogInPage.userList.get(0).inbox.messages.size();i++) {
		//	System.out.println(LogInPage.userList.get(0).inbox.messages.get(i));
		//}
		//LogInPage.writeObject();
		//Filter.filterFn();
		//BookAppointment.showAppointmentSuggestions();
	}
	
	
}


//LogInPage.onlyReadForDeveloping();
//CustomSearch.customSearchFn();
