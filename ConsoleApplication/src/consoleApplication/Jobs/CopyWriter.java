package consoleApplication.Jobs;

import java.io.Serializable;

public class CopyWriter extends Job implements Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "CopyWriter";
	}

	@Override
	public String[] skills() {
		return new String[] {"Copy Editing","Copy Writing","On-Page SEO","Creative Writing","Sales",
				"Writing","Content Writing","Blog Writing","Proofreading","Social Media Copywriting",
				"Website Copywriting","SEO copywriting","Business Copywriting"};
	}

	@Override
	public String[] services() {
		return new String[]{"Pay($)","Revisions","Delivery days","Number of pages",
				"Words included","Competitor research"};
	}
	
}
