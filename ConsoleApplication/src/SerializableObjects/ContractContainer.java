package SerializableObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class ContractContainer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ArrayList<ContractRequest> contractRequests = new ArrayList<ContractRequest>();
	public ArrayList<Contract> clientContracts = new ArrayList<Contract>();
	public ArrayList<Contract> freelancerContracts = new ArrayList<Contract>();
	
}
