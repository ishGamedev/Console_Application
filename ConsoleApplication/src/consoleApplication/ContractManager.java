package consoleApplication;

import java.util.Scanner;

import SerializableObjects.Contract;
import SerializableObjects.ContractContainer;
import SerializableObjects.ContractRequest;

public class ContractManager {
	
	public static void contractManagerfn() throws Exception{
		String choice = "0"; //each function has their own Integer choice variable
		Scanner scInt = new Scanner(System.in);
		do {
			//FreeLancer
			if(LogInPage.currentProfile.inFreelancerMode) {
				System.out.println("1. Current Contracts as a FreeLancer");
				System.out.println("2. Show Contract Requests");
				System.out.println("0. Exit");
				
				choice = scInt.next();
				
				switch(choice) {
				
					case "1":		
						showContractAsAFreeLancer();
						break;
					
					case"2":
						displayContractRequests(LogInPage.currentProfile.contractContainer);
						choice = "0";
						break;
						
					case "0":
						break;
				}
			}
			
			//Client
			else {
				System.out.println("1. Current Contracts as a Client");
				System.out.println("2. Create new Contract");
				System.out.println("0. Exit");
				
				choice = scInt.next();
				
				switch(choice) {
					
					case "1":
						showContractAsAClient();
						break;
					
					case "2":
						CreateContract.createContractfn();
						choice = "0";
						break;
		
					case "0":
						break;
				}
			}
			
		}while(!choice.equals("0"));
		
	}
	
	
	private static void displayContractRequests(ContractContainer contractContainer) throws Exception{
		if(contractContainer.contractRequests.size()<=0) {
			System.out.println("\nYou don't have any Contract Requests\n");
			contractManagerfn();
			return;
		}
		
		Scanner scInt = new Scanner(System.in);
		
		for(int i = contractContainer.contractRequests.size()-1;i>=0;i--) {
			System.out.println(i+1 + ". "+contractContainer.contractRequests.get(i).typeOfContract+" offer From "+
					contractContainer.contractRequests.get(i).client.getName()+"  "+contractContainer.contractRequests.get(i).amount+"$");
		}

		System.out.println("0 Exit");
		System.out.println("Enter the Contract Request number to view: ");
		int userChoice = scInt.nextInt();
		if(userChoice == 0) MainMenu.showMainMenuFn();
		
		showSpecificContractRequest(contractContainer,contractContainer.contractRequests.get(userChoice-1),userChoice-1);
	}
	
	
	private static void showSpecificContractRequest(ContractContainer contractContainer,ContractRequest contractRequest,int index) throws Exception {
		contractRequest.printContractRequest();
		
		Scanner scString = new Scanner(System.in);
		System.out.println("1. Accept Request");
		System.out.println("2. Reject Request");
		System.out.println("0. Return");
		String userInput = scString.nextLine();
		
		switch(userInput) {
			
			//Accept
			case"1":
				Contract contract = new Contract(contractRequest);
				contractRequest.freelancer.contractContainer.freelancerContracts.add(contract);
				contractRequest.client.contractContainer.clientContracts.add(contract);
				deleteContractRequest(contractContainer,index);
				break;
				
			//Reject
			case"2":
				deleteContractRequest(contractContainer,index);
				break;
			
			//Exit
			case"0":
				displayContractRequests(contractContainer);
				break;
		}
	}
	
	private static void deleteContractRequest(ContractContainer contractContainer,int index) throws Exception {
		contractContainer.contractRequests.remove(index);
		LogInPage.writeObject();
	}
	
	private static void showContractAsAClient() {
		Scanner scInt = new Scanner(System.in);
		int index = 0;
		if(LogInPage.currentProfile.contractContainer.clientContracts.size()<=0) {
			System.out.println("\nNo Current Contracts as a Client\n");
			return;
		}
			
		for(int i = 0;i<LogInPage.currentProfile.contractContainer.clientContracts.size();i++) {
			System.out.print(i+1+". ");
			LogInPage.currentProfile.contractContainer.clientContracts.get(i).printOneLineContractClient();
		}
		
		System.out.println("Enter the Contract Number to view: ");
		index = scInt.nextInt();
		LogInPage.currentProfile.contractContainer.clientContracts.get(index-1).printFullContract();
	}
	
	private static void showContractAsAFreeLancer() {
		Scanner scInt = new Scanner(System.in);
		int index = 0;
		if(LogInPage.currentProfile.contractContainer.freelancerContracts.size()<=0) {
			System.out.println("\nNo Current Contracts as a FreeLancer\n");
			return;
		}
		
		for(int i = 0;i<LogInPage.currentProfile.contractContainer.freelancerContracts.size();i++) {
			System.out.print(i+1+". ");
			LogInPage.currentProfile.contractContainer.freelancerContracts.get(i).printOneLineContractFreelancer();
		}
		System.out.println("Enter the Contract Number to view: ");
		index = scInt.nextInt();
		LogInPage.currentProfile.contractContainer.freelancerContracts.get(index-1).printFullContract();
	}
}
