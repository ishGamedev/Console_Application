package SerializableObjects;

import java.io.Serializable;

public class ServiceOffers implements Serializable{
	private static final long serialVersionUID = 1L;
	public  String[] typesOfGigs;
	
	//Called From the BasicServiceOffers constructor
	public void setTypesOfGigsString(String[] services,int numberOfServices){
		typesOfGigs = new String[numberOfServices];
		for(int i = 0;i<numberOfServices;i++) {
			typesOfGigs[i] = services[i];
		}
	}
	
	public  String[] getTypesOfGigs() {
		return typesOfGigs;
	}
	
	public void setServices(String[] temp) {
		
	}

	public String[] getServices() {
		return typesOfGigs;
	}
	
	public int getPay() {
		return 0; 
	}
}
