package consoleApplication.Jobs;

import java.io.Serializable;

public class Editor extends Job implements Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Editor";
	}

	@Override
	public String[] skills() {
		return new String[] {"Proofreading", "Creative Writing", "Content Writing", "Copy Editing", "Microsoft Word",
				"Report Writing", "Technical Writing", "Grammar", "English Translation"};
	}

	@Override
	public String[] services() {
		return new String[] {"Pay($)","Revisions", "Delivery days", "Words included", "Structural edit", "Line edit",
				"Rewriting", "Proofreading"};
	}

}
