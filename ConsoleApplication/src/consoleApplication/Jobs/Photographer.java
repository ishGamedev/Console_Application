package consoleApplication.Jobs;

import java.io.Serializable;

public class Photographer extends Job implements Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Photographer";
	}

	@Override
	public String[] skills() {
		return new String[] {"Product Photography", "Video Production", "Brand Marketing", "Photography", "Adobe Photoshop",
				"Adobe After Effects", "Fashion photography", "Graphic design", "Photo editing", "Social Media Marketing"};
	}

	@Override
	public String[] services() {
		return new String[] {"Pay($)","Revisions", "Delivery days", "Photos per product", "Number of products", "Live model", "Lifestyle staging",
				"Photo retouching"};
	}

}
