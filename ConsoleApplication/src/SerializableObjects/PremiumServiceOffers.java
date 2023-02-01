package SerializableObjects;

public class PremiumServiceOffers extends ServiceOffers implements Gigs{
	private static final long serialVersionUID = 1L;
	public String[] serviceOffers;
	public PremiumServiceOffers(int numberOfGigs) {
		serviceOffers = new String[numberOfGigs];
	}
	@Override
	public void setServices(String[] _serviceOffers) {
		for(int i = 0;i<_serviceOffers.length;i++) {
			serviceOffers[i] = _serviceOffers[i];
		}
	}
	
	@Override
	public String[] getServices() {
		return serviceOffers;
	}
	
	@Override
	public int getPay() {
		return Integer.parseInt(serviceOffers[0]); 
	}

}
