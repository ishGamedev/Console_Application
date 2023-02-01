package consoleApplication.Jobs;

import java.io.Serializable;

public class Programmer extends Job implements Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Programmer";
	}

	@Override
	public String[] skills() {
		return new String[] {"MySQL", "PHP", "JavaScript", "React", "Python", "Java", "SpringBoot", "Angular",
				"jQuery", "HTML", "CSS", "CPP", "Node.js", "Image Processing", "Website Consultation",
				"Email Scraping", "Artificial Intelligence", "Bot Development", "Data Extraction", "Data Mining"
			   };
	}

	@Override
	public String[] services() {
		return new String[] {"Pay($)","Revisions","Delivery days", "Include source code", "Database integration", "Setup file",
				"Detailed code comments"};
	}

}
