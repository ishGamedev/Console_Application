package consoleApplication.Jobs;

import java.io.Serializable;

public class DigitalMarketingConsultant extends Job implements Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "DigitalMarketingConsultant";
	}

	@Override
	public String[] skills() {
		return new String[] {"Marketing Strategy" ,"Social Media Marketing", "Facebook Marketing", "Search Engine Optimization",
				"Email Marketing", "Facebook Ads", "Instagram Marketing", "Google Ads",
				"Internet Marketing", "Online Business", "Content Marketing", "Advertising Strategy",
				"E-Commerce", "Social Media Management"
			};
	}

	@Override
	public String[] services() {
		return new String[] {"Pay($)","Delivery days", "Consultation", "Review existing material",
							"Optimisation suggestions", "Follow up"};
	}
	
}
