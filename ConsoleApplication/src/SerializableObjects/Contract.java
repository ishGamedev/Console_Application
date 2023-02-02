package SerializableObjects;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import consoleApplication.User;

public class Contract implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int amount;
	private String typeOfContract;
	private User freelancer;
	private User client;
	private int days;
	private String description;
	private String contractRequestInitiatedDate;
	private String contractAcceptedDate;
	private String contractEndDate;
	private int progress;
	
	public Contract(ContractRequest contractRequest){
		this.amount = contractRequest.amount;
		this.typeOfContract = contractRequest.typeOfContract;
		this.freelancer = contractRequest.freelancer;
		this.client = contractRequest.client;
		this.days = contractRequest.days;
		this.description = contractRequest.description;
		this.contractRequestInitiatedDate = contractRequest.contractRequestInitiatedDate;
		
		//Storing Date
		LocalDate startDate = LocalDate.now();
		contractAcceptedDate = startDate.format(DateTimeFormatter.ofPattern("dd MM uuuu"));
		contractEndDate = startDate.plusDays(this.days).format(DateTimeFormatter.ofPattern("dd MM uuuu"));
		this.progress = 0;
	}
	
	public void increaseProgress(int incrementLevel){
		if(incrementLevel>progress) {
			progress = incrementLevel;
		}
		else {
			System.out.println("Invalid Request Progress can only increase");
		}
	}
	
	public void	printOneLineContractClient() {
		System.out.println(freelancer.getName()+"  "+typeOfContract+"  "+amount+"$");
	}
	
	public void	printOneLineContractFreelancer() {
		System.out.println(client.getName()+"  "+typeOfContract+"  "+amount+"$");
	}
	
	public void printFullContract() {
		System.out.println("Client: "+client.getName());
		System.out.println("FreeLancer: "+freelancer.getName());
		System.out.println("Total Pay: "+amount);
		System.out.println("Type Of Contract: "+typeOfContract);
		System.out.println("Contract Request Initiated at: "+contractRequestInitiatedDate);
		System.out.println("Contract Accpeted at: "+contractAcceptedDate);
		System.out.println("Contract End at: "+contractEndDate);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
		try {
			Date date1 = sdf.parse(contractAcceptedDate);
			Date date2 = sdf.parse(contractEndDate);
			long time_difference = date2.getTime() - date1.getTime();  
			long days_difference = (time_difference / (1000*60*60*24)) % 365; 
			
			System.out.println("Days Left: "+days_difference);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("Description:\n\t"+description);
		
	}
}
