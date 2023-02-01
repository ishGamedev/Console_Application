package SerializableObjects;

import java.io.Serializable;

import consoleApplication.User;

public class Contract implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int amount;
	private String typeOfContract;
	private User freelancer;
	private User client;
	private int days;
	private String description;
	
	public Contract(ContractRequest contractRequest){
		this.amount = contractRequest.amount;
		this.typeOfContract = contractRequest.typeOfContract;
		this.freelancer = contractRequest.freelancer;
		this.client = contractRequest.client;
		this.days = contractRequest.days;
		this.description = contractRequest.description;
	}
	
	@Override
	public String toString() {
		return amount+" "+typeOfContract;
	}
}
