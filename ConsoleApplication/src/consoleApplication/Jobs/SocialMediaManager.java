package consoleApplication.Jobs;

import java.io.Serializable;

public class SocialMediaManager extends Job implements Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "SocialMediaManager";
	}

	@Override
	public String[] skills() {
		return new String[] {"Writing", "Research", "SEO Knowledge", "Social Media Expertise", "Adobe Photoshop",
				"Adobe Illustrator", "Visual Intelligence"};
	}

	@Override
	public String[] services() {
		return new String[] {"Pay($)","Management duration", "Content creation", "Number of platforms", 
				"Schedule posts", "Engagement with followers", "Reporting"};
	}

}
