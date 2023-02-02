package SerializableObjects;

import java.io.Serializable;

import consoleApplication.User;

public class ContractRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public int amount;
	public String typeOfContract;
	public User freelancer;
	public User client;
	public int days;
	public String contractRequestInitiatedDate;
	public String description;
	
	
	public void printContractRequest(){
		System.out.println("Description: ");
		System.out.println("\t"+description);
		System.out.println("From: ");
		System.out.println("\t"+client.getName());
		System.out.println("Type Of Contract: "+typeOfContract);
		System.out.println("Pay($): "+amount);
		System.out.println("Days to complete: "+days);
		System.out.println("Request Initiated at: "+contractRequestInitiatedDate);
	}
}