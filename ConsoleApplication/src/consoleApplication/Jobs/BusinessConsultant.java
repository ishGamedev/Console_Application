package consoleApplication.Jobs;

import java.io.Serializable;

public class BusinessConsultant extends Job implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "BusinessConsultant";
	}

	@Override
	public String[] skills() {
		return new String[] {"Business Strategy", "Business consulting", "Executive coaching", "Customer service",
				"Product design", "Sourcing", "Product Development", "Product research",
				"Business Coaching", "Life Coaching"};
	}

	@Override
	public String[] services() {
		return new String[] {"Pay($)","Delivery days", "Number of sessions", "Consulting hours","Personalised action plan",
				"SWOT analysis", "Strategic recommendations", "Initial assessment", "Strategy implementation"};
	}

}
