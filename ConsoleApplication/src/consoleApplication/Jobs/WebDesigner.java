package consoleApplication.Jobs;

import java.io.Serializable;

public class WebDesigner extends Job implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Override
	public String[] skills() {
		return new String[] {"WordPress","HTML","Web Development",
							"CSS","Graphic Design","Web Design",
							"Search Engine Optimisation","Online Marketing",
							"JavaScript","Content Management System","Web Server Management",
							"Copywriting","Responsive Design","UI / UX","Colour Theory"};
	}

	@Override
	public String[] services() {
		return new String[] {"Pay($)","Revisions","Delivery days","Number of pages",
							"Responsive design","Source file","Content upload","Prototype",
							"Convert to HTML/CSS"};
	}

	@Override
	public String getName() {
		return "WebDesigner";
	}

		
}
