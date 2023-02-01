package consoleApplication;

import java.util.*;

import SerializableObjects.ContractRequest;

public class CreateContract {
	static int index = 0;
	
	public static void createContractfn() throws Exception{
		index = 0;
		User user = LogInPage.currentProfile;
		Set<User> collectionOfFreeLancersSet = new HashSet<User>();
		Scanner scInt = new Scanner(System.in);
		Scanner scString = new Scanner(System.in);
		
		for(int i = user.inbox.acceptedAppointments.size()-1;i>=0;i--){
			if(user!=user.inbox.acceptedAppointments.get(i).senderProfile) {
				collectionOfFreeLancersSet.add(user.inbox.acceptedAppointments.get(i).senderProfile);
			}
			else {
				collectionOfFreeLancersSet.add(user.inbox.acceptedAppointments.get(i).receiverProfile);
			}
		}
		
		if(collectionOfFreeLancersSet.size()<=0) {
			System.out.println("\nCan't create contract without attending a appointment with the freelancer\n");
			ContractManager.contractManagerfn();
			return;
		}

		User[] collectionOfFreeLancersArray = new User[collectionOfFreeLancersSet.size()] ;
		
		System.out.println("Choose the Freelancer to create contract: ");
		collectionOfFreeLancersSet.forEach(entry ->{
			collectionOfFreeLancersArray[index] = entry;
			System.out.println(index+1+". "+entry.name);
			index++;
		});
		
		int userChoice = scInt.nextInt();
		
		ContractRequest contractRequest = new ContractRequest();
		
		contractRequest.freelancer = collectionOfFreeLancersArray[userChoice-1];
		
		if(contractRequest.freelancer.serviceOffersArray[1].getServices()[1].equals("-")) {
			System.out.println("Can't create contract with a Freelancer who does't have any predefined gigs");
			ContractManager.contractManagerfn();
			return;
		}
		contractRequest.client = LogInPage.currentProfile;
		
		System.out.println("Total Days of Contract: ");
		userChoice = scInt.nextInt();
		contractRequest.days = userChoice;
		
		String userInput = "";
		System.out.println("Contract Description: ");
		userInput = scString.nextLine();
		contractRequest.description = userInput;
		
		int amountToPay = 0;
		do {
			System.out.println("1. Basic");
			System.out.println("2. Standard");
			System.out.println("3. Premium");
			System.out.println("4. Custom");
			
			userInput = scString.nextLine();
			System.out.println(userInput);
			switch(userInput) {
			case"1":
				contractRequest.typeOfContract = "Basic";
				System.out.println(contractRequest.freelancer.serviceOffersArray[1].getPay());
				amountToPay =contractRequest.freelancer.serviceOffersArray[1].getPay();
				break;
			case"2":
				contractRequest.typeOfContract = "Standard";
				amountToPay = contractRequest.freelancer.serviceOffersArray[2].getPay();
				break;
			case"3":
				contractRequest.typeOfContract = "Premium";
				amountToPay = contractRequest.freelancer.serviceOffersArray[3].getPay();
				break;
			case"4":
				contractRequest.typeOfContract = "Custom";
				System.out.println("Enter the amount for the contract: ");
				amountToPay = scInt.nextInt();
				if(amountToPay == 0) userInput = "no";
				break;
			default:
				userInput = "no";
				break;
			}
		}while(userInput.equalsIgnoreCase("No"));
		
		System.out.println("Pay "+amountToPay+"$ and sent Contract Request");
		System.out.println("1. Yes");
		System.out.println("2. No");
		contractRequest.amount = amountToPay;
		
		userInput = scString.nextLine();
		if(userInput.equalsIgnoreCase("Yes") || userInput.equals("1")) {
			if(paymentProcess(amountToPay,user)){
				contractRequest.freelancer.contractContainer.contractRequests.add(contractRequest);
				LogInPage.writeObject();
			}
			else {
				ContractManager.contractManagerfn();
			}
		}
		else {
			ContractManager.contractManagerfn();
		}
		
	}
	
	private static boolean paymentProcess(int amountToPay,User client) throws Exception {
		
		if(client.wallet.getBalance() - amountToPay>=0) {
			client.wallet.setBalance(-amountToPay);
			LogInPage.writeObject();
			return true;
		}
		
		else {
			System.out.println("\nInsufficient Funds\n");
		}
		return false;
	}
}























